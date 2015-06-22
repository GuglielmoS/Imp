package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class NullRef extends Expr {
	NullRef() {
		// DO NOTHING
	}
	
	@Override
	public boolean isRef() {
		return true;
	}
	
	@Override
	public void accept(AstVisitor v) {
		v.visitNull(this);
	}
}
