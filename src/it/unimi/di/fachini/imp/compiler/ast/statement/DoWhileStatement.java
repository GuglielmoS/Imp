package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;

public class DoWhileStatement extends Statement {
	private final Condition condition;
	private final Statement body;

	DoWhileStatement(Statement body, Condition condition) {
		this.body = body;
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}

	public Statement getBody() {
		return body;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitDoWhile(this);
	}
}
