package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.atom.AtomFactory;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;

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

	public static Statement doWhileStmt(Statement body, Condition condition) {
		return new DoWhileStatement(body, condition);
	}

	public static Statement forStmt(Statement body, Descriptor iterVar, Expr start, Expr end) {
		return forStmt(body, iterVar, start, end, AtomFactory.num(1));
	}

	public static Statement forStmt(Statement body, Descriptor iterVar, Expr start, Expr end, Expr step) {
		return new ForStatement(body, iterVar, start, end, step);
	}

	public static Statement block(List<Statement> statements) {
		return new BlockStatement(statements);
	}
}
