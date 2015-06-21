package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class EmptyStatement extends Statement {
	EmptyStatement() {
		// DO NOTHING
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitEmpty(this);
	}
}
