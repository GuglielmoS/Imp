package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.ASTVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

import java.util.List;

public class BlockStatement extends Statement {
	private final List<Statement> statements;

	BlockStatement(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitBlock(this);
	}
}
