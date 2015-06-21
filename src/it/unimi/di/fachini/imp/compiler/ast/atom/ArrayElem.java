package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Variable;

public class ArrayElem extends Variable {
	private final Descriptor arrayRef;
	private final Expr index;

	ArrayElem(Descriptor arrayRef, Expr index) {
		this.arrayRef = arrayRef;
		this.index = index;
	}

	public Descriptor getArrayRef() {
		return arrayRef;
	}

	public Expr getIndex() {
		return index;
	}

	@Override
	public Descriptor getDescriptor() {
		return arrayRef;
	}

	@Override
	public boolean isArrayRef() {
		return true;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitArrayElem(this);
	}
}
