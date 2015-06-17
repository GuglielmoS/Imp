package it.unimi.di.fachini.imp.compiler;

import it.unimi.di.fachini.imp.compiler.ast.Statement;

import java.util.List;

public class Program {
	private final List<Statement> statements;
	private final SymbolTable table;
	
	public Program(List<Statement> statements, SymbolTable table) {
		this.statements = statements;
		this.table = table;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public SymbolTable getSymbolTable() {
		return table;
	}
}
