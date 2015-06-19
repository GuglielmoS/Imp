package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class EmptyStatement implements Statement {
	EmptyStatement() {
		// DO NOTHING
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitEmpty(this);
	}
}
