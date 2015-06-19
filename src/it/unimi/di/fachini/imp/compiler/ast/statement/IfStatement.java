package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;

public class IfStatement implements Statement {
	private final Condition condition;
	private final Statement consequent;
	private final Statement alternative;

	IfStatement(Condition condition, Statement consequent) {
		this.condition = condition;
		this.consequent = consequent;
		this.alternative = null;
	}

	public IfStatement(Condition condition, Statement consequent,
			Statement alternative) {
		this.condition = condition;
		this.consequent = consequent;
		this.alternative = alternative;
	}

	public Condition getCondition() {
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
