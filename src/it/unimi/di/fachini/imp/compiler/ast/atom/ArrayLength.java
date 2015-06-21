package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class ArrayLength extends Expr {
	private final Descriptor descriptor;

	ArrayLength(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitArrayLength(this);
	}
}
