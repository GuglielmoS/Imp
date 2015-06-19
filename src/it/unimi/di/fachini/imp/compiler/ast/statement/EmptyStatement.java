package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.Statement;

import static org.objectweb.asm.Opcodes.NOP;

import org.objectweb.asm.MethodVisitor;

public class EmptyStatement extends Statement {
	EmptyStatement() {
		// DO NOTHING
	}

	@Override
	public void compile(MethodVisitor methodWriter) {
		methodWriter.visitInsn(NOP);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
