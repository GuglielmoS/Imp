package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class WhileStatement extends Statement {
	private final Expr condition;
	private final Statement body;

	WhileStatement(Expr condition, Statement body) {
		this.condition = condition;
		this.body = body;
	}

	public Expr getCondition() {
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
