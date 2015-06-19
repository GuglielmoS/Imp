package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class ReadStatement implements Statement {
	private final Descriptor destination;

	ReadStatement(Descriptor destination) {
		this.destination = destination;
	}

	public Descriptor getDestination() {
		return destination;
	}
	
	@Override
	public void accept(ASTVisitor v) {
		v.visitRead(this);
	}
}
