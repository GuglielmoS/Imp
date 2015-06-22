package it.unimi.di.fachini.imp.compiler.bytecode;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static org.objectweb.asm.Opcodes.*;
import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.Program;
import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.arith.AddExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.DivExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.ModExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.MulExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.SubExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.UnaryMinusExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.UnaryPlusExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayElem;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayLength;
import it.unimi.di.fachini.imp.compiler.ast.atom.NewArray;
import it.unimi.di.fachini.imp.compiler.ast.atom.NullRef;
import it.unimi.di.fachini.imp.compiler.ast.atom.Num;
import it.unimi.di.fachini.imp.compiler.ast.atom.Var;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.ConditionType;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignArrayStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignVarStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.BlockStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.DoWhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.EmptyStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.ForStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.IfStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.WhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.ReadArrayElemStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.ReadVarStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteMessageStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteStatement;
import it.unimi.di.fachini.imp.compiler.declaration.Declaration;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class CodeGenerator implements AstVisitor {
	private MethodVisitor mv;
	private int nextLocalVar;
	private int scannerIndex;
	private int inputIndex, outputIndex;
	private final String programName;

	public CodeGenerator(String programName) {
		this.programName = programName;
	}
	
	private void resetLocalVariables() {
		nextLocalVar = 0;
		scannerIndex = 0;
		inputIndex = 0;
		outputIndex = 0;
	}

	private int reserveLocal() {
		return nextLocalVar++;
	}

	private int destroyLocal() {
		return --nextLocalVar;
	}

	public byte[] compile(Program program) {
		resetLocalVariables();

		// creates a ClassWriter to compile the parsed program
		ClassWriter cw = new ClassWriter(COMPUTE_FRAMES | COMPUTE_MAXS);
		cw.visit(V1_8, ACC_PUBLIC, programName, null, "java/lang/Object", null);

		/*
		 * Generate the bytecode for the default constructor, the program code
		 * and the main function.
		 */
		genDefaultConstructor(program, cw);
		genExecute(program, cw);
		genMain(program, cw);

		// return the generated bytecode
		return cw.toByteArray();
	}

	private void genMain(Program program, ClassWriter cw) {
		mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mv.visitCode();

		// execute the program by calling execute(System.in, System.out)
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitMethodInsn(INVOKESTATIC, programName, "execute", "(Ljava/io/InputStream;Ljava/io/PrintStream;)V", false);

		// exit from the main method
		mv.visitInsn(RETURN);
		mv.visitMaxs(-1,-1);
		mv.visitEnd();
	}

	private void genExecute(Program program, ClassWriter cw) {
		// public static void execute(InputStream in, PrintStream out)
		mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
							"execute", "(Ljava/io/InputStream;Ljava/io/PrintStream;)V",
							null, null);
		mv.visitCode();

		// reserve a local variable for each argument
		inputIndex = reserveLocal();  // InputStream in
		outputIndex = reserveLocal(); // PrintStream out

		// create a new java.util.Scanner object
		mv.visitTypeInsn(NEW, "java/util/Scanner");
		mv.visitInsn(DUP);
		// retrieve the input stream from the local variables
		mv.visitVarInsn(ALOAD, inputIndex);
		mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
		// store the created object as a local variable
		scannerIndex = reserveLocal();
		mv.visitVarInsn(ASTORE, scannerIndex);

		// declare local variables
		for (Declaration decl : program.getDeclarations()) {
			for (Descriptor descriptor : decl.getDeclaredIdentifiers()) {
				descriptor.setIndex(reserveLocal());
				// initialize the variable to 0 if it's an integer or null if it's a ref
				if (descriptor.isRef()) {
					mv.visitInsn(ACONST_NULL);
					mv.visitVarInsn(ASTORE, descriptor.getIndex());
				} else {
					mv.visitInsn(ICONST_0);
					mv.visitVarInsn(ISTORE, descriptor.getIndex());
				}
			}
		}

		// compile the program statement by statement
		for (Statement stmt : program.getStatements()) {
			stmt.accept(this);
		}

		// return from the method
		mv.visitInsn(RETURN);
		mv.visitMaxs(-1, -1);
		mv.visitEnd();
	}

	private void genDefaultConstructor(Program program, ClassWriter cw) {
		mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		// pushes the 'this' variable
		mv.visitVarInsn(ALOAD, 0);
		// invokes the super class constructor
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv.visitInsn(RETURN);
		// this code uses a maximum of one stack element and one local variable
		mv.visitMaxs(-1, -1);
		mv.visitEnd();
	}

	@Override
	public void visitNum(Num expr) {
		mv.visitLdcInsn(expr.getValue());
	}

	@Override
	public void visitVar(Var expr) {
		mv.visitVarInsn(ILOAD, expr.getDescriptor().getIndex());
	}

	@Override
	public void visitNull(NullRef expr) {
		mv.visitInsn(ACONST_NULL);
	}

	@Override
	public void visitNewArray(NewArray expr) {
		expr.getSize().accept(this);
		mv.visitIntInsn(NEWARRAY, T_INT);
	}

	@Override
	public void visitArrayElem(ArrayElem expr) {
		mv.visitVarInsn(ALOAD, expr.getArrayRef().getIndex());
		expr.getIndex().accept(this);
		mv.visitInsn(IALOAD);
	}

	@Override
	public void visitArrayLength(ArrayLength expr) {
		mv.visitVarInsn(ALOAD, expr.getDescriptor().getIndex());
		mv.visitInsn(ARRAYLENGTH);
	}

	@Override
	public void visitAdd(AddExpr expr) {
		expr.getLeft().accept(this);
		expr.getRight().accept(this);
		mv.visitInsn(IADD);
	}

	@Override
	public void visitSub(SubExpr expr) {
		expr.getLeft().accept(this);
		expr.getRight().accept(this);
		mv.visitInsn(ISUB);
	}

	@Override
	public void visitMul(MulExpr expr) {
		expr.getLeft().accept(this);
		expr.getRight().accept(this);
		mv.visitInsn(IMUL);
	}

	@Override
	public void visitDiv(DivExpr expr) {
		expr.getLeft().accept(this);
		expr.getRight().accept(this);
		mv.visitInsn(IDIV);
	}

	@Override
	public void visitMod(ModExpr expr) {
		// reserve two local variables for keeping left and right
		int a = reserveLocal();
		int b = reserveLocal();

		// compile left and right, thus put the result in 'a' and 'b'
		expr.getLeft().accept(this);
		mv.visitVarInsn(ISTORE, a);
		expr.getRight().accept(this);
		mv.visitVarInsn(ISTORE, b);

		// the MOD operation can be computed as: a % b = a - b*(a/b)
		// in postfix it becomes a b a b / * -
		mv.visitVarInsn(ILOAD, a);
		mv.visitVarInsn(ILOAD, b);
		mv.visitVarInsn(ILOAD, a);
		mv.visitVarInsn(ILOAD, b);
		mv.visitInsn(IDIV);
		mv.visitInsn(IMUL);
		mv.visitInsn(ISUB);

		// destroy the temporary local variables
		destroyLocal();
		destroyLocal();
	}

	@Override
	public void visitUnaryPlus(UnaryPlusExpr expr) {
		expr.getTarget().accept(this);
	}

	@Override
	public void visitUnaryMinus(UnaryMinusExpr expr) {
		expr.getTarget().accept(this);
		mv.visitInsn(INEG);
	}

	@Override
	public void visitWrite(WriteStatement writeStmt) {
		// retrieve the output stream from the local variables
		mv.visitVarInsn(ALOAD, outputIndex);

		// compile the expression to be printed and push it onto the stack
		writeStmt.getExpr().accept(this);

		// invoke Integer.toString(expr)
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString",
				"(I)Ljava/lang/String;", false);

		// invoke System.out.println()
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print",
				"(Ljava/lang/String;)V", false);
	}

	@Override
	public void visitWriteMessage(WriteMessageStatement writeMsgStmt) {
		// retrieve the output stream from the local variables
		mv.visitVarInsn(ALOAD, outputIndex);

		// push the string to be printed
		mv.visitLdcInsn(writeMsgStmt.getMessage());

		// invoke the 'print' method (defined in the PrintStream class)
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
	}

	@Override
	public void visitReadVar(ReadVarStatement readStmt) {
		// push onto the stack the Scanner object stored as local variable
		mv.visitVarInsn(ALOAD, scannerIndex);
		// call nextInt() on the created object
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
		// put the read value in the specified variable
		mv.visitVarInsn(ISTORE, readStmt.getTarget().getDescriptor().getIndex());
	}

	@Override
	public void visitReadArrayElem(ReadArrayElemStatement readStmt) {
		// prepare the stack for the assignment (push the array and the index)
		mv.visitVarInsn(ALOAD, readStmt.getTarget().getDescriptor().getIndex());
		readStmt.getTarget().getIndex().accept(this);
		// push onto the stack the Scanner object stored as local variable
		mv.visitVarInsn(ALOAD, scannerIndex);
		// call nextInt() on the created object
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
		// put the read value in the specified array element
		mv.visitInsn(IASTORE);
	}

	@Override
	public void visitAssignVar(AssignVarStatement assign) {
		assign.getValue().accept(this);
		Descriptor descriptor = assign.getTarget().getDescriptor();
		if (descriptor.isRef())
			mv.visitVarInsn(ASTORE, descriptor.getIndex());
		else
			mv.visitVarInsn(ISTORE, descriptor.getIndex());
	}

	@Override
	public void visitAssignArray(AssignArrayStatement assign) {
		mv.visitVarInsn(ALOAD, assign.getTarget().getDescriptor().getIndex());
		assign.getTarget().getIndex().accept(this);
		assign.getValue().accept(this);
		mv.visitInsn(IASTORE);
	}

	@Override
	public void visitBlock(BlockStatement block) {
		// compile the statements sequentially
		for (Statement stmt : block.getStatements()) {
			stmt.accept(this);
		}
	}

	@Override
	public void visitEmpty(EmptyStatement empty) {
		mv.visitInsn(NOP);
	}

	@Override
	public void visitIf(IfStatement ifStmt) {
		// push (cond.left - cond.right) onto the stack
		Condition cond = ifStmt.getCondition();
		cond.getLeft().accept(this);
		cond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// evaluate the result accordingly to the condition type
		Label alternativeOrEnd = new Label();
		mv.visitJumpInsn(getConditionOpcode(cond.getType()), alternativeOrEnd);

		// consequent branch
		ifStmt.getConsequent().accept(this);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);

		// alternative branch (if it exists)
		mv.visitLabel(alternativeOrEnd);
		if (ifStmt.hasAlternative()) {
			ifStmt.getAlternative().accept(this);
		}

		// if's end
		mv.visitLabel(end);
	}

	@Override
	public void visitWhile(WhileStatement whileStmt) {
		// loop's start
		Label loop = new Label();
		mv.visitLabel(loop);

		// compute (cond.left - cond.right) onto the stack
		Condition cond = whileStmt.getCondition();
		cond.getLeft().accept(this);
		cond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// evaluate the result accordingly to the condition type
		Label end = new Label();
		mv.visitJumpInsn(getConditionOpcode(cond.getType()), end);

		// body
		whileStmt.getBody().accept(this);
		mv.visitJumpInsn(GOTO, loop);

		// loop's end
		mv.visitLabel(end);
	}

	@Override
	public void visitDoWhile(DoWhileStatement doWhileStmt) {
		// loop's start
		Label loop = new Label();
		mv.visitLabel(loop);

		// body
		doWhileStmt.getBody().accept(this);

		// compute (cond.left - cond.right) onto the stack
		Condition cond = doWhileStmt.getCondition();
		cond.getLeft().accept(this);
		cond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// evaluate the result accordingly to the condition type
		Label end = new Label();
		mv.visitJumpInsn(getConditionOpcode(cond.getType()), end);
		mv.visitJumpInsn(GOTO, loop);

		// loop's end
		mv.visitLabel(end);
	}

	public void visitFor(ForStatement forStmt) {
		// create the labels for compiling the loop
		Label loop = new Label();
		Label checkLower = new Label();
		Label body = new Label();
		Label end = new Label();

		// initialize the iteration variable
		int iterIndex = forStmt.getIterVar().getIndex();
		forStmt.getStart().accept(this);
		mv.visitVarInsn(ISTORE, iterIndex);

		// reserve a local variable for the end value
		int endIndex = reserveLocal();
		forStmt.getEnd().accept(this);
		mv.visitVarInsn(ISTORE, endIndex);

		// reserve a local variable for the step value
		int stepIndex = reserveLocal();
		forStmt.getStep().accept(this);
		mv.visitVarInsn(ISTORE, stepIndex);

		// loop's start and end
		mv.visitLabel(loop);

		// check the condition accordingly to the step value (positive/negative)
		mv.visitVarInsn(ILOAD, stepIndex);
		mv.visitJumpInsn(IFLT, checkLower);

		// exit if the iteration variable is greater than end
		mv.visitVarInsn(ILOAD, iterIndex);
		mv.visitVarInsn(ILOAD, endIndex);
		mv.visitInsn(ISUB);
		mv.visitJumpInsn(IFGT, end);
		mv.visitJumpInsn(GOTO, body);

		// exit if the iteration variable is lower than end
		mv.visitLabel(checkLower);
		mv.visitVarInsn(ILOAD, iterIndex);
		mv.visitVarInsn(ILOAD, endIndex);
		mv.visitInsn(ISUB);
		mv.visitJumpInsn(IFLT, end);

		// loop's body
		mv.visitLabel(body);
		forStmt.getBody().accept(this);

		// step increment/decrement
		mv.visitVarInsn(ILOAD, iterIndex);
		mv.visitVarInsn(ILOAD, stepIndex);
		mv.visitInsn(IADD);
		mv.visitVarInsn(ISTORE, iterIndex);

		// jump to the start
		mv.visitJumpInsn(GOTO, loop);

		// loop's end
		mv.visitLabel(end);

		// destroy the local variable used for the step and the end variable
		destroyLocal();
		destroyLocal();
	}

	private int getConditionOpcode(ConditionType type) {
		switch (type) {
			case EQ: return IFNE;
			case NE: return IFEQ;
			case GE: return IFLT;
			case GT: return IFLE;
			case LE: return IFGT;
			case LT: return IFGE;
			default: throw new IllegalStateException("Invalid condition given: " + type);
		}
	}
}
