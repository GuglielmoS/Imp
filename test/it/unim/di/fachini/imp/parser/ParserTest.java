package it.unim.di.fachini.imp.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.unimi.di.fachini.imp.compiler.Program;
import it.unimi.di.fachini.imp.compiler.ast.AtomFactory;
import it.unimi.di.fachini.imp.compiler.ast.BinaryOpFactory;
import it.unimi.di.fachini.imp.compiler.ast.Declaration;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
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
			Program program = (Program)parser.parse().value;
			List<Expr> expressions = program.getExpressions();
			assertTrue(expressions.isEmpty());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testSinleExprProgram() {
		StringReader buf = new StringReader("123*3+456-987");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program)parser.parse().value;
			List<Expr> expressions = program.getExpressions();
			assertEquals(1, expressions.size());
			Expr expected = BinaryOpFactory.sub(
					BinaryOpFactory.add(
							BinaryOpFactory.mul(AtomFactory.num(123), AtomFactory.num(3)),
							AtomFactory.num(456)),
					AtomFactory.num(987));
			assertEquals(expected, expressions.get(0));
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
			Program program = (Program)parser.parse().value;
			List<Expr> expressions = program.getExpressions();
			assertEquals(1, expressions.size());
			assertTrue(expressions.get(0) instanceof Declaration);
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testSymbolTable() {
		StringReader buf = new StringReader("var a, b, c; a + b - c");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program)parser.parse().value;
			List<Expr> expressions = program.getExpressions();
			assertEquals(2, expressions.size());
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}

	@Test
	public void testSequence() {
		StringReader buf = new StringReader("1*2 2+1");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);
		Parser parser = new Parser(scanner, sf);

		try {
			Program program = (Program)parser.parse().value;
			List<Expr> expressions = program.getExpressions();
			assertEquals(2, expressions.size());
			// check the first expression (1*2)
			Expr expectedFirst = BinaryOpFactory.mul(AtomFactory.num(1), AtomFactory.num(2));
			assertEquals(expectedFirst, expressions.get(0));
			// check the first expression (1*2)
			Expr expectedSecond = BinaryOpFactory.add(AtomFactory.num(2), AtomFactory.num(1));
			assertEquals(expectedSecond, expressions.get(1));
		} catch (Exception e) {
			fail("Parser error: " + e.getMessage());
		}
	}
}
