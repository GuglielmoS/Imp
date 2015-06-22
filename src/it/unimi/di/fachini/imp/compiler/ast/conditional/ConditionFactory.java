package it.unimi.di.fachini.imp.compiler.ast.conditional;

import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class ConditionFactory {
	public static Condition eq(Expr left, Expr right) {
		return new Condition(ConditionType.EQ, left, right);
	}

	public static Condition ne(Expr left, Expr right) {
		return new Condition(ConditionType.NE, left, right);
	}

	public static Condition aeq(Expr left, Expr right) {
		return new Condition(ConditionType.AEQ, left, right);
	}

	public static Condition ane(Expr left, Expr right) {
		return new Condition(ConditionType.ANE, left, right);
	}

	public static Condition lt(Expr left, Expr right) {
		return new Condition(ConditionType.LT, left, right);
	}

	public static Condition le(Expr left, Expr right) {
		return new Condition(ConditionType.LE, left, right);
	}
	
	public static Condition gt(Expr left, Expr right) {
		return new Condition(ConditionType.GT, left, right);
	}

	public static Condition ge(Expr left, Expr right) {
		return new Condition(ConditionType.GE, left, right);
	}
}
