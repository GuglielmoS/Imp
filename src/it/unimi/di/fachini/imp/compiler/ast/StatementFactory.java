package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignStatement;

public class StatementFactory {
	public static Statement assign(Descriptor ident, Expr value) {
		return new AssignStatement(ident, value);
	}
}
