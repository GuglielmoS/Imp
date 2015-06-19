package it.unimi.di.fachini.imp.compiler.ast.statement;

import org.objectweb.asm.MethodVisitor;

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

	public IfStatement(Expr condition, Statement consequent, Statement alternative) {
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
	public void compile(MethodVisitor methodWriter) {
		// TODO Auto-generated method stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alternative == null) ? 0 : alternative.hashCode());
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result
				+ ((consequent == null) ? 0 : consequent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IfStatement other = (IfStatement) obj;
		if (alternative == null) {
			if (other.alternative != null)
				return false;
		} else if (!alternative.equals(other.alternative))
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (consequent == null) {
			if (other.consequent != null)
				return false;
		} else if (!consequent.equals(other.consequent))
			return false;
		return true;
	}
}
