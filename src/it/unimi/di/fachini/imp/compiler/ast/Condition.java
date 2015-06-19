package it.unimi.di.fachini.imp.compiler.ast;


public abstract class Condition implements AstNode {
	private final Expr left;
	private final Expr right;

	protected Condition(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}

	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}
}
