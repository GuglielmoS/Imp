package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Variable;

public class Var extends Variable {
	private Descriptor descriptor;

	Var(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public boolean isRef() {
		return descriptor.isRef();
	}
	
	@Override
	public void accept(AstVisitor v) {
		v.visitVar(this);
	}
}
