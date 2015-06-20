package it.unimi.di.fachini.imp.compiler;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static org.objectweb.asm.Opcodes.*;

import java.io.PrintWriter;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.arith.AddExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.DivExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.ModExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.MulExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.SubExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.NumExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.VarExpr;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.ConditionType;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.BlockStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.EmptyStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.IfStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.WhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.ReadStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteMessageStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteStatement;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;

public class CodeGenerator implements ASTVisitor {
	private MethodVisitor mv;
	private int nextLocalVar;

	private void resetLocalVariables() {
		nextLocalVar = 0;
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
		cw.visit(V1_8, ACC_PUBLIC, program.getName(), null, "java/lang/Object", null);

		/*
		 * Generate the bytecode for the default constructor, the program code
		 * and the main function.
		 */
		genDefaultConstructor(program, cw);
		genExecute(program, cw);
		genMain(program, cw);

		PrintWriter pw = new PrintWriter(System.out);
		CheckClassAdapter.verify(new ClassReader(cw.toByteArray()), false, pw);

		// return the generated bytecode
		return cw.toByteArray();
	}

	private void genMain(Program program, ClassWriter cw) {
		mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
				"([Ljava/lang/String;)V", null, null);
		mv.visitCode();

		// execute the program by calling execute(System.in, System.out)
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitMethodInsn(INVOKESTATIC, program.getName(), "execute", "(Ljava/io/InputStream;Ljava/io/PrintStream;)V", false);

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
		reserveLocal(); // InputStream in
		reserveLocal(); // PrintStream out

		// declare local variables
		for (Declaration decl : program.getDeclarations()) {
			for (Descriptor descriptor : decl.getDeclaredIdentifiers()) {
				descriptor.setIndex(reserveLocal());
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
	public void visitNum(NumExpr expr) {
		mv.visitLdcInsn(expr.getValue());
	}

	@Override
	public void visitVar(VarExpr expr) {
		mv.visitVarInsn(ILOAD, expr.getDescriptor().getIndex());
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
		// push a onto the stack
		mv.visitVarInsn(ILOAD, a);
		mv.visitVarInsn(ILOAD, b);
		mv.visitVarInsn(ILOAD, a);
		mv.visitVarInsn(ILOAD, b);
		mv.visitInsn(IDIV);
		mv.visitInsn(IMUL);
		mv.visitInsn(ISUB);

		// destroy the used local variables
		destroyLocal();
		destroyLocal();
	}

	@Override
	public void visitWrite(WriteStatement writeStmt) {
		// retrieve the output stream from the local variables
		pushOutputStream();

		// compile the expression to be printed and push it onto the stack
		writeStmt.getExpr().accept(this);

		// invoke Integer.toString()
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString",
				"(I)Ljava/lang/String;", false);

		// invoke System.out.println()
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print",
				"(Ljava/lang/String;)V", false);
	}

	@Override
	public void visitWriteMessage(WriteMessageStatement writeMsgStmt) {
		// retrieve the output stream from the local variables
		pushOutputStream();

		// push the string to be printed
		mv.visitLdcInsn(writeMsgStmt.getMessage());

		// invoke the 'print' method (defined in the PrintStream class)
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
	}

	@Override
	public void visitRead(ReadStatement readStmt) {
		// create a new java.util.Scanner object
		mv.visitTypeInsn(NEW, "java/util/Scanner");
		mv.visitInsn(DUP);
		// retrieve the input stream from the local variables
		pushInputStream();
		mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
		// call nextInt() on the created object
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
		// put the read value in the specified variable
		mv.visitVarInsn(ISTORE, readStmt.getDestination().getIndex());
	}

	@Override
	public void visitAssign(AssignStatement assign) {
		assign.getValue().accept(this);
		mv.visitVarInsn(ISTORE, assign.getTarget().getIndex());
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
		// compute (cond.left - cond.right) onto the stack
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

	private void pushOutputStream() {
		mv.visitVarInsn(ALOAD, 1);
	}

	private void pushInputStream() {
		mv.visitVarInsn(ALOAD, 0);
	}
}
