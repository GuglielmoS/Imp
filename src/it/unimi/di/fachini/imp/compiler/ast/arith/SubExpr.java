package it.unimi.di.fachini.imp.compiler.ast.arith;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class SubExpr extends Expr {
	private final Expr left;
	private final Expr right;

	SubExpr(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}

	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitSub(this);
	}
}
