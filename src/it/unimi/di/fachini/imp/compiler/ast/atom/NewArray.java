package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class NewArray extends Expr {
	private final Expr size;

	NewArray(Expr size) {
		this.size = size;
	}
	
	public Expr getSize() {
		return size;
	}

	@Override
	public boolean isRef() {
		return true;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitNewArray(this);
	}
}
