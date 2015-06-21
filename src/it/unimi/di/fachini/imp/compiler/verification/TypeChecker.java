package it.unimi.di.fachini.imp.compiler.verification;

import it.unimi.di.fachini.imp.compiler.Program;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

import static it.unimi.di.fachini.imp.compiler.verification.ImpType.*;

public class TypeChecker {
	private final Program program;

	public TypeChecker(Program program) {
		this.program = program;
	}

	public void check() {
		TypeCheckerVisitor tc = new TypeCheckerVisitor();
		for (Statement stmt : program.getStatements()) {
			tc.expected(STATEMENT);
			stmt.accept(tc);
		}
	}
}
