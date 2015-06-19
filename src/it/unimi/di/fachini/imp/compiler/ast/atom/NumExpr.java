package it.unimi.di.fachini.imp.compiler.ast.atom;

import org.objectweb.asm.MethodVisitor;

import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class NumExpr extends Expr {
	private final Integer value;

	NumExpr(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public void compile(MethodVisitor mw) {
		mw.visitLdcInsn(value);
	}
}
