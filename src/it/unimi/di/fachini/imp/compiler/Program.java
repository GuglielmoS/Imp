package it.unimi.di.fachini.imp.compiler;

import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.declaration.Declaration;

import java.util.List;

public class Program {
	private final List<Declaration> declarations;
	private final List<Statement> statements;
	private final SymbolTable table;
	private String name;

	public Program(String name, List<Declaration> declarations,
			List<Statement> statements, SymbolTable table) {
		this.name = name;
		this.declarations = declarations;
		this.statements = statements;
		this.table = table;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
