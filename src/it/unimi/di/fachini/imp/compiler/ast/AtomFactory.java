package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.ast.atom.NumExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.VarExpr;

public class AtomFactory {
	public static Expr num(Integer value) {
		return new NumExpr(value);
	}

	public static Expr var(String d) {
		return new VarExpr(d);
	}
}
