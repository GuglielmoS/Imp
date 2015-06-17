package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class AtomFactory {
	public static Expr num(Integer value) {
		return new NumExpr(value);
	}

	public static Expr var(Descriptor d) {
		return new VarExpr(d);
	}
}
