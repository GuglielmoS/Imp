package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class WriteMessageStatement implements Statement {
	private final String message;

	WriteMessageStatement(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitWriteMessage(this);
	}
}
