package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.atom.EmptyExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.NumExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.VarExpr;

public class AtomFactory {
	public static Expr num(Integer value) {
		return new NumExpr(value);
	}

	public static Expr var(Descriptor d) {
		return new VarExpr(d);
	}
	
	public static Expr nop() {
		return new EmptyExpr();
	}
}
