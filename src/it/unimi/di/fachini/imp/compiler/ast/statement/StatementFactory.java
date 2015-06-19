package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Condition;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

import java.util.List;

public class StatementFactory {
	public static Statement nop() {
		return new EmptyStatement();
	}
	
	public static Statement assign(Descriptor ident, Expr value) {
		return new AssignStatement(ident, value);
	}

	public static Statement ifStmt(Condition condition, Statement consequent) {
		return new IfStatement(condition, consequent);
	}

	public static Statement ifStmt(Condition condition, Statement consequent, Statement alternative) {
		return new IfStatement(condition, consequent, alternative);
	}

	public static Statement whileStmt(Condition condition, Statement body) {
		return new WhileStatement(condition, body);
	}

	public static Statement block(List<Statement> statements) {
		return new BlockStatement(statements);
	}
}
