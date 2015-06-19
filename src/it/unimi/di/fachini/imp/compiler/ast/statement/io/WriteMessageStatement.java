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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WriteMessageStatement other = (WriteMessageStatement) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
