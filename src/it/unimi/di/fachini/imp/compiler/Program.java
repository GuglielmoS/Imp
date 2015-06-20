package it.unimi.di.fachini.imp.compiler;

import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.declaration.Declaration;

import java.util.List;

public class Program {
	private final List<Declaration> declarations;
	private final List<Statement> statements;
	private final SymbolTable table;

	public Program(List<Declaration> declarations, List<Statement> statements, SymbolTable table) {
		this.declarations = declarations;
		this.statements = statements;
		this.table = table;
	}

	public List<Declaration> getDeclarations() {
		return declarations;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public SymbolTable getSymbolTable() {
		return table;
	}
}
