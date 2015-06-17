package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.ast.arith.AddExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.DivExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.ModExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.MulExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.SubExpr;

public class BinaryOpFactory {
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
}
