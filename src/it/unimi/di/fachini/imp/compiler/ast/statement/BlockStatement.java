package it.unimi.di.fachini.imp.compiler.ast.statement;

import java.util.List;

import org.objectweb.asm.MethodVisitor;

import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class BlockStatement extends Statement {
	private final List<Statement> statements;

	BlockStatement(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public void compile(MethodVisitor mw) {
		// compile the statements sequentially
		for (Statement stmt : statements) {
			stmt.compile(mw);
		}
	}
}
