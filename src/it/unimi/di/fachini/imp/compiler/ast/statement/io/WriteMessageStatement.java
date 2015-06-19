package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

import org.objectweb.asm.MethodVisitor;

public class WriteMessageStatement extends Statement {
	private final String message;

	WriteMessageStatement(String message) {
		this.message = message;
	}

	@Override
	public void compile(MethodVisitor mw) {
        // push the 'out' field (of type PrintStream) of the System class
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        // push the string to be printed
        mw.visitLdcInsn(message);

        // invoke the 'print' method (defined in the PrintStream class)
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
	}
}
