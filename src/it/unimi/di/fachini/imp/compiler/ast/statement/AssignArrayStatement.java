package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayElem;

public class AssignArrayStatement extends Statement {
	private final ArrayElem target;
	private final Expr value;

	AssignArrayStatement(ArrayElem target, Expr value) {
		this.target = target;
		this.value = value;
	}

	public ArrayElem getTarget() {
		return target;
	}

	public Expr getValue() {
		return value;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitAssignArray(this);
	}
}
