package it.unimi.di.fachini.imp.compiler.ast.conditional;

import it.unimi.di.fachini.imp.compiler.ast.Condition;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class ConditionFactory {
	public static Condition eq(Expr left, Expr right) {
		return new EQCondition(left, right);
	}

	public static Condition ne(Expr left, Expr right) {
		return new NECondition(left, right);
	}

	public static Condition lt(Expr left, Expr right) {
		return new LTCondition(left, right);
	}

	public static Condition le(Expr left, Expr right) {
		return new LECondition(left, right);
	}
	
	public static Condition gt(Expr left, Expr right) {
		return new GTCondition(left, right);
	}

	public static Condition ge(Expr left, Expr right) {
		return new GECondition(left, right);
	}
}
