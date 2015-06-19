package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class NumExpr implements Expr {
	private final Integer value;

	NumExpr(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitNum(this);
	}
}
