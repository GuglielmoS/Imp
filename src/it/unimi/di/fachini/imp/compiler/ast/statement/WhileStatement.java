package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;

public class WhileStatement extends Statement {
	private final Condition condition;
	private final Statement body;

	WhileStatement(Condition condition, Statement body) {
		this.condition = condition;
		this.body = body;
	}

	public Condition getCondition() {
		return condition;
	}

	public Statement getBody() {
		return body;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitWhile(this);
	}
}
