package it.unimi.di.fachini.imp.compiler.ast.conditional;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Condition;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class EQCondition extends Condition {
	EQCondition(Expr left, Expr right) {
		super(left, right);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitEQ(this);
	}
}
