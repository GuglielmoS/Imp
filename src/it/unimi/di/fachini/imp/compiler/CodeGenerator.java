package it.unimi.di.fachini.imp.compiler;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;
import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.arith.AddExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.DivExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.ModExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.MulExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.SubExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.NumExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.VarExpr;
import it.unimi.di.fachini.imp.compiler.ast.conditional.EQCondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.GTCondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.GECondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.LTCondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.LECondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.NECondition;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.BlockStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.EmptyStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.IfStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.WhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteMessageStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteStatement;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

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
		ClassWriter cw = new ClassWriter(COMPUTE_FRAMES);
		cw.visit(V1_8, ACC_PUBLIC, program.getName(), null, "java/lang/Object", null);

		/*********************************************************************
		 * Create the default constructor
		 *********************************************************************/

		mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		// pushes the 'this' variable
		mv.visitVarInsn(ALOAD, 0);
		// invokes the super class constructor
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv.visitInsn(RETURN);
		// this code uses a maximum of one stack element and one local variable
		mv.visitMaxs(1, 1);
		mv.visitEnd();

		/*********************************************************************
		 * Create the main method
		 *********************************************************************/

		mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		mv.visitCode();

		/*********************************************************************
		 * Declare the local variables
		 *********************************************************************/

		// start from 1 because the main function receives an argument (String[] args)
		reserveLocal();
		for (Declaration decl : program.getDeclarations()) {
			for (Descriptor descriptor : decl.getDeclaredIdentifiers()) {
				descriptor.setIndex(reserveLocal());
			}
		}

		/*********************************************************************
		 * Compile the program statements sequentially
		 *********************************************************************/

		for (Statement stmt : program.getStatements()) {
			stmt.accept(this);
		}

		// exit from the main method
		mv.visitInsn(RETURN);

		// define the max stack elements and local variables
		mv.visitMaxs(1000, nextLocalVar);
		mv.visitEnd();

		// return the generated bytecode
		return cw.toByteArray();
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
	public void visitEQ(EQCondition eqCond) {
		// compile the expressions and subtract them
		eqCond.getLeft().accept(this);
		eqCond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// compare the values, thus return
		// 	0 IF the values are not equal
		// 	1 IF the values are equal
		Label fail = new Label();
		mv.visitJumpInsn(IFNE, fail);
		// positive case
		mv.visitLdcInsn(1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		// negative case
		mv.visitLabel(fail);
		mv.visitLdcInsn(0);
		mv.visitLabel(end);
	}

	@Override
	public void visitNE(NECondition neCond) {
		// compile the expressions and subtract them
		neCond.getLeft().accept(this);
		neCond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// compare the values, thus return
		// 	0 IF the values are equal
		// 	1 IF the values are not equal
		Label fail = new Label();
		mv.visitJumpInsn(IFEQ, fail);
		// positive case
		mv.visitLdcInsn(1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		// negative case
		mv.visitLabel(fail);
		mv.visitLdcInsn(0);
		mv.visitLabel(end);
	}

	@Override
	public void visitLT(LTCondition ltCond) {
		// compile the expressions and subtract them
		ltCond.getLeft().accept(this);
		ltCond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// compare the values
		Label fail = new Label();
		mv.visitJumpInsn(IFGE, fail);
		// positive case
		mv.visitLdcInsn(1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		// negative case
		mv.visitLabel(fail);
		mv.visitLdcInsn(0);
		mv.visitLabel(end);
	}

	@Override
	public void visitGT(GTCondition gtCond) {
		// compile the expressions and subtract them
		gtCond.getLeft().accept(this);
		gtCond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// compare the values
		Label fail = new Label();
		mv.visitJumpInsn(IFLE, fail);
		// positive case
		mv.visitLdcInsn(1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		// negative case
		mv.visitLabel(fail);
		mv.visitLdcInsn(0);
		mv.visitLabel(end);
	}

	@Override
	public void visitLE(LECondition leCond) {
		// compile the expressions and subtract them
		leCond.getLeft().accept(this);
		leCond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// compare the values
		Label fail = new Label();
		mv.visitJumpInsn(IFGT, fail);
		// positive case
		mv.visitLdcInsn(1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		// negative case
		mv.visitLabel(fail);
		mv.visitLdcInsn(0);
		mv.visitLabel(end);
	}

	@Override
	public void visitGE(GECondition geCond) {
		// compile the expressions and subtract them
		geCond.getLeft().accept(this);
		geCond.getRight().accept(this);
		mv.visitInsn(ISUB);

		// compare the values
		Label fail = new Label();
		mv.visitJumpInsn(IFLT, fail);
		// positive case
		mv.visitLdcInsn(1);
		Label end = new Label();
		mv.visitJumpInsn(GOTO, end);
		// negative case
		mv.visitLabel(fail);
		mv.visitLdcInsn(0);
		mv.visitLabel(end);
	}

	@Override
	public void visitWrite(WriteStatement writeStmt) {
        // retrieve System.out and push it onto the stack
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		
		// compile the expression to be printed and push it onto the stack
        writeStmt.getExpr().accept(this);

		// invoke Integer.toString()
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;", false);

        // invoke System.out.println()
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);		
	}

	@Override
	public void visitWriteMessage(WriteMessageStatement writeMsgStmt) {
        // push the 'out' field (of type PrintStream) of the System class
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        // push the string to be printed
        mv.visitLdcInsn(writeMsgStmt.getMessage());

        // invoke the 'print' method (defined in the PrintStream class)
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
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
		// condition check & jump
		ifStmt.getCondition().accept(this);
		Label alternativeOrEnd = new Label();
		mv.visitJumpInsn(IFEQ, alternativeOrEnd);

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

		// condition check & jump
		whileStmt.getCondition().accept(this);
		Label end = new Label();
		mv.visitJumpInsn(IFEQ, end);

		// body
		whileStmt.getBody().accept(this);
		mv.visitJumpInsn(GOTO, loop);

		// loop's end
		mv.visitLabel(end);
	}
}
