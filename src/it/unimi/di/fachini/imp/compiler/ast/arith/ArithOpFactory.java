package it.unimi.di.fachini.imp.compiler.ast.arith;

import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class ArithOpFactory {
	public static Expr add(Expr left, Expr right) {
		return new AddExpr(left, right);
	}

	public static Expr sub(Expr left, Expr right) {
		return new SubExpr(left, right);
	}

	public static Expr mul(Expr left, Expr right) {
		return new MulExpr(left, right);
	}

	public static Expr div(Expr left, Expr right) {
		return new DivExpr(left, right);
	}

	public static Expr mod(Expr left, Expr right) {
		return new ModExpr(left, right);
	}

	public static Expr unaryPlus(Expr target) {
		return target;
	}

	public static Expr unaryMinus(Expr target) {
		return new UnaryMinusExpr(target);
	}
}
