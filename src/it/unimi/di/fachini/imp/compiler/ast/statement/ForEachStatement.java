package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class ForEachStatement extends Statement {
	private final Statement body;
	private final Descriptor iterVar;
	private final Descriptor array;

	ForEachStatement(Statement body, Descriptor iterVar, Descriptor array) {
		this.body = body;
		this.iterVar = iterVar;
		this.array = array;
	}

	public Statement getBody() {
		return body;
	}

	public Descriptor getIterVar() {
		return iterVar;
	}

	public Descriptor getArray() {
		return array;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitForEach(this);
	}
}
