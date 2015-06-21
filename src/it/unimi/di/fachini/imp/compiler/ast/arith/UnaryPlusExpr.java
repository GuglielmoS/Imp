package it.unimi.di.fachini.imp.compiler.ast.arith;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class UnaryPlusExpr extends Expr {
	private final Expr target;

	UnaryPlusExpr(Expr target) {
		this.target = target;
	}

	public Expr getTarget() {
		return target;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitUnaryPlus(this);
	}
}
