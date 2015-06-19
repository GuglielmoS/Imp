package it.unimi.di.fachini.imp.compiler.ast.conditional;

import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class Condition {
	private final Expr left;
	private final Expr right;
	private ConditionType type;

	Condition(ConditionType type, Expr left, Expr right) {
		this.type = type;
		this.left = left;
		this.right = right;
	}

	public ConditionType getType() {
		return type;
	}
	
	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}
}
