package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.atom.Var;

public class AssignVarStatement extends Statement {
	private final Var target;
	private final Expr value;

	AssignVarStatement(Var target, Expr value) {
		this.target = target;
		this.value = value;
	}

	public Var getTarget() {
		return target;
	}

	public Expr getValue() {
		return value;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitAssignVar(this);
	}
}
