package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class IfStatement extends Statement {
	private final Expr condition;
	private final Statement consequent;
	private final Statement alternative;

	IfStatement(Expr condition, Statement consequent) {
		this.condition = condition;
		this.consequent = consequent;
		this.alternative = null;
	}

	public IfStatement(Expr condition, Statement consequent,
			Statement alternative) {
		this.condition = condition;
		this.consequent = consequent;
		this.alternative = alternative;
	}

	public Expr getCondition() {
		return condition;
	}

	public Statement getConsequent() {
		return consequent;
	}

	public Statement getAlternative() {
		return alternative;
	}

	public boolean hasAlternative() {
		return alternative != null;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitIf(this);
	}
}
