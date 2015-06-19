package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class VarExpr extends Expr {
	private Descriptor descriptor;

	VarExpr(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitVar(this);
	}
}
