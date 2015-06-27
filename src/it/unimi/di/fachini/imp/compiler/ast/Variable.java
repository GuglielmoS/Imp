package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.Descriptor;

public abstract class Variable extends Expr {
	public abstract Descriptor getDescriptor();

	public boolean isArrayElem() {
		return false;
	}
}
