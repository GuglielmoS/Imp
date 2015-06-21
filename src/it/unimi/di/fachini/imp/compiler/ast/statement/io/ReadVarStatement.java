package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.atom.Var;

public class ReadVarStatement extends Statement {
	private final Var target;

	ReadVarStatement(Var target) {
		this.target = target;
	}

	public Var getTarget() {
		return target;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitReadVar(this);
	}
}
