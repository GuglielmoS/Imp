package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Variable;

public class AtomFactory {
	public static Expr num(Integer value) {
		return new Num(value);
	}

	public static Variable var(Descriptor d) {
		return new Var(d);
	}

	public static Expr nullRef() {
		return new NullRef();
	}

	public static Expr newArray(Expr size) {
		return new NewArray(size);
	}

	public static Variable arrayElem(Descriptor arrayRef, Expr index) {
		return new ArrayElem(arrayRef, index);
	}

	public static Expr arrayLength(Descriptor ident) {
		return new ArrayLength(ident);
	}
}
