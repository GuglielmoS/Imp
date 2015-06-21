package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.Variable;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayElem;
import it.unimi.di.fachini.imp.compiler.ast.atom.Var;

public class IOStatementFactory {
	public static Statement write(Expr expr) {
		return new WriteStatement(expr);
	}
	
	public static Statement writeMessage(String message) {
		return new WriteMessageStatement(message);
	}

	public static Statement read(Variable var) {
		if (var.isArrayRef()) {
			return new ReadArrayElemStatement((ArrayElem)var);
		} else {
			return new ReadVarStatement((Var)var);
		}
	}
}
