package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class ForStatement implements Statement {
	private final Statement body;
	private final Descriptor iterVar;
	private final Expr start;
	private final Expr end;
	private final Expr step;

	ForStatement(Statement body, Descriptor iterVar, Expr start, Expr end, Expr step) {
		this.body = body;
		this.iterVar = iterVar;
		this.start = start;
		this.end = end;
		this.step = step;
	}

	public Statement getBody() {
		return body;
	}
	
	public Descriptor getIterVar() {
		return iterVar;
	}

	public Expr getStart() {
		return start;
	}

	public Expr getEnd() {
		return end;
	}

	public Expr getStep() {
		return step;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitFor(this);
	}
}
