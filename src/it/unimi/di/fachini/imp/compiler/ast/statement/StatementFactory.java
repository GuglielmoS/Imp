package it.unimi.di.fachini.imp.compiler.ast.statement;

import java.util.List;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

public class StatementFactory {
	public static Statement nop() {
		return new EmptyStatement();
	}

	public static Statement declaration(List<Descriptor> identifiers) {
		return new DeclarationStatement(identifiers);
	}
	
	public static Statement assign(Descriptor ident, Expr value) {
		return new AssignStatement(ident, value);
	}

	public static Statement ifStmt(Expr condition, Statement consequent) {
		return new IfStatement(condition, consequent);
	}

	public static Statement ifStmt(Expr condition, Statement consequent, Statement alternative) {
		return new IfStatement(condition, consequent, alternative);
	}

	public static Statement whileStmt(Expr condition, Statement body) {
		return new WhileStatement(condition, body);
	}

	public static Statement block(List<Statement> statements) {
		return new BlockStatement(statements);
	}
}
