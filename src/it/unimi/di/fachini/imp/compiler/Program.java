package it.unimi.di.fachini.imp.compiler;

import it.unimi.di.fachini.imp.compiler.ast.Expr;

import java.util.List;

public class Program {
	private final List<Expr> expressions;
	private final SymbolTable table;
	
	public Program(List<Expr> expressions, SymbolTable table) {
		this.expressions = expressions;
		this.table = table;
	}

	public List<Expr> getExpressions() {
		return expressions;
	}

	public SymbolTable getSymbolTable() {
		return table;
	}
}
