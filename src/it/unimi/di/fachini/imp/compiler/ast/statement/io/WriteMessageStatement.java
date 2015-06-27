package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class WriteMessageStatement extends Statement {
	private final String message;

	WriteMessageStatement(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitWriteMessage(this);
	}
}
