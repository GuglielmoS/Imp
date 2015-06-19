package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class IOStatementFactory {
	public static Statement write(Expr expr) {
		return new WriteStatement(expr);
	}
	
	public static Statement writeMessage(String message) {
		return new WriteMessageStatement(message);
	}

	public static Statement read(Descriptor destination) {
		return new ReadStatement(destination);
	}
}
