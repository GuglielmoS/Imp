package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;

import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class WriteStatement extends Statement {
	private final Expr expr;

	WriteStatement(Expr expr) {
		this.expr = expr;
	}

	@Override
	public void compile(MethodVisitor mw) {
        // retrieve System.out and push it onto the stack
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		
		// compile the expression to be printed and push it onto the stack
		expr.compile(mw);

		// invoke Integer.toString()
        mw.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;", false);

        // invoke System.out.println()
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
	}
}
