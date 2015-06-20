package it.unim.di.fachini.imp.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.Program;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.atom.NumExpr;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.BlockStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.EmptyStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.IfStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.WhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteStatement;
import it.unimi.di.fachini.imp.compiler.declaration.Declaration;
import it.unimi.di.fachini.imp.compiler.declaration.VariablesDeclaration;
import it.unimi.di.fachini.imp.compiler.error.CompilerError;
import it.unimi.di.fachini.imp.parser.Parser;
import it.unimi.di.fachini.imp.scanner.Scanner;

import java.io.StringReader;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testEmptyProgram() {
		StringReader buf = new StringReader("");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertTrue(statements.isEmpty());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testEmptyStatement() {
		StringReader buf = new StringReader(";;;;;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(5, statements.size());
			assertTrue(statements.stream().allMatch(s -> s instanceof EmptyStatement));
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testVarDeclaration() {
		StringReader buf = new StringReader("var a, b, c;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			assertEquals(0, program.getStatements().size());
			assertEquals(1, program.getDeclarations().size());
			List<Declaration> declarations = program.getDeclarations();
			assertTrue(declarations.get(0) instanceof VariablesDeclaration);
			List<Descriptor> ids = ((VariablesDeclaration)declarations.get(0)).getDeclaredIdentifiers();
			assertEquals("a", ids.get(0).getId());
			assertEquals("b", ids.get(1).getId());
			assertEquals("c", ids.get(2).getId());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test(expected = CompilerError.class)
	public void testUndeclaredVar() throws Exception {
		StringReader buf = new StringReader("a = 42;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);
		parser.parse();
	}

	@Test
	public void testSymbolTableWithSimpleVarDeclaration() {
		StringReader buf = new StringReader("var a, b, c;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			assertEquals(0, program.getStatements().size());
			assertEquals(1, program.getDeclarations().size());
			assertTrue(program.getSymbolTable().contains("a"));
			assertTrue(program.getSymbolTable().contains("b"));
			assertTrue(program.getSymbolTable().contains("c"));
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testSymbolTableWithNestedBlockDeclarations() {
		StringReader buf = new StringReader("var a, b, c; {var e, f, g;}");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			assertEquals(1, program.getStatements().size());
			assertEquals(2, program.getDeclarations().size());
			assertTrue(program.getSymbolTable().contains("a"));
			assertTrue(program.getSymbolTable().contains("b"));
			assertTrue(program.getSymbolTable().contains("c"));
			assertTrue(program.getSymbolTable().contains("e"));
			assertTrue(program.getSymbolTable().contains("f"));
			assertTrue(program.getSymbolTable().contains("g"));
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testAssignment() {
		StringReader buf = new StringReader("var a; a = 10;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			assertEquals(1, program.getStatements().size());
			List<Statement> statements = program.getStatements();
			assertEquals(1, program.getDeclarations().size());
			List<Declaration> declarations = program.getDeclarations();
			assertTrue(declarations.get(0) instanceof VariablesDeclaration);
			List<Descriptor> ids = ((VariablesDeclaration)declarations.get(0)).getDeclaredIdentifiers();
			assertFalse(ids.isEmpty());
			assertEquals("a", ids.get(0).getId());
			assertTrue(statements.get(0) instanceof AssignStatement);
			AssignStatement assignment = (AssignStatement)statements.get(0);
			assertEquals("a", assignment.getTarget().getId());
			assertTrue(assignment.getValue() instanceof NumExpr);
			assertEquals(new Integer(10), ((NumExpr)assignment.getValue()).getValue());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testIfWithoutElse() {
		StringReader buf = new StringReader("if (1 == 1) ;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(1, statements.size());
			assertTrue(statements.get(0) instanceof IfStatement);
			IfStatement ifStmt = (IfStatement)statements.get(0);
			assertTrue(ifStmt.getCondition() instanceof Condition);
			assertTrue(ifStmt.getConsequent() instanceof EmptyStatement);
			assertFalse(ifStmt.hasAlternative());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testIfWithElse() {
		StringReader buf = new StringReader("if (1==1) ; else ;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(1, statements.size());
			assertTrue(statements.get(0) instanceof IfStatement);
			IfStatement ifStmt = (IfStatement)statements.get(0);
			assertTrue(ifStmt.getCondition() instanceof Condition);
			assertTrue(ifStmt.getConsequent() instanceof EmptyStatement);
			assertTrue(ifStmt.hasAlternative());
			assertTrue(ifStmt.getAlternative() instanceof EmptyStatement);
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testDanglingElse() {
		StringReader buf = new StringReader("if (1==1) if (2!=2) ; else ;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(1, statements.size());
			assertTrue(statements.get(0) instanceof IfStatement);
			// outer if
			IfStatement outerIf = (IfStatement)statements.get(0);
			assertTrue(outerIf.getCondition()instanceof Condition);
			assertTrue(outerIf.getConsequent() instanceof IfStatement);
			assertFalse(outerIf.hasAlternative());
			// inner if
			assertTrue(outerIf.getConsequent() instanceof IfStatement);
			IfStatement innerIf = (IfStatement)outerIf.getConsequent();
			assertTrue(innerIf.getCondition()instanceof Condition);
			assertTrue(innerIf.getConsequent() instanceof EmptyStatement);
			assertTrue(innerIf.hasAlternative());
			assertTrue(innerIf.getConsequent() instanceof EmptyStatement);
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testWhileWithEmptyBody() {
		StringReader buf = new StringReader("while (1==1);");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(1, statements.size());
			assertTrue(statements.get(0) instanceof WhileStatement);
			WhileStatement whileStmt = (WhileStatement)statements.get(0);
			assertTrue(whileStmt.getCondition() instanceof Condition);
			assertTrue(whileStmt.getBody() instanceof EmptyStatement);
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testEmptyBlock() {
		StringReader buf = new StringReader("{}");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(1, statements.size());
			assertTrue(statements.get(0) instanceof BlockStatement);
			BlockStatement block = (BlockStatement)statements.get(0);
			assertTrue(block.getStatements().isEmpty());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testWrite() {
		StringReader buf = new StringReader("write 1;");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program) parser.parse().value;
			List<Statement> statements = program.getStatements();
			assertEquals(1, statements.size());
			assertTrue(statements.get(0) instanceof WriteStatement);
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}
}
