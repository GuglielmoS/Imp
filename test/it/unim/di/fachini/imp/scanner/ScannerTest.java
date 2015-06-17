package it.unim.di.fachini.imp.scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.unimi.di.fachini.imp.parser.ParserSym;
import it.unimi.di.fachini.imp.scanner.Scanner;

import java.io.IOException;
import java.io.StringReader;

import java_cup.runtime.ComplexSymbolFactory;

import org.junit.Test;

public class ScannerTest {
	@Test
	public void testNumbers() {
		StringReader buf = new StringReader("0 1 2 3 4 5 6 7 8 9 12 123 1234 12345");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		// check numbers
		try {
			assertEquals(0, scanner.next_token().value);
			assertEquals(1, scanner.next_token().value);
			assertEquals(2, scanner.next_token().value);
			assertEquals(3, scanner.next_token().value);
			assertEquals(4, scanner.next_token().value);
			assertEquals(5, scanner.next_token().value);
			assertEquals(6, scanner.next_token().value);
			assertEquals(7, scanner.next_token().value);
			assertEquals(8, scanner.next_token().value);
			assertEquals(9, scanner.next_token().value);
			assertEquals(12, scanner.next_token().value);
			assertEquals(123, scanner.next_token().value);
			assertEquals(1234, scanner.next_token().value);
			assertEquals(12345, scanner.next_token().value);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}

		// check EOF
		try {
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testIdentifiers() {
		StringReader buf = new StringReader("a abc abc123");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals("a", scanner.next_token().value);
			assertEquals("abc", scanner.next_token().value);
			assertEquals("abc123", scanner.next_token().value);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testArithmeticOperators() {
		StringReader buf = new StringReader("+ - * / %");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.PLUS, scanner.next_token().sym);
			assertEquals(ParserSym.MINUS, scanner.next_token().sym);
			assertEquals(ParserSym.MUL, scanner.next_token().sym);
			assertEquals(ParserSym.DIV, scanner.next_token().sym);
			assertEquals(ParserSym.MOD, scanner.next_token().sym);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testDelimiters() {
		StringReader buf = new StringReader(",;()");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.COMMA, scanner.next_token().sym);
			assertEquals(ParserSym.SEMI_COLON, scanner.next_token().sym);
			assertEquals(ParserSym.OPEN_PAREN, scanner.next_token().sym);
			assertEquals(ParserSym.CLOSE_PAREN, scanner.next_token().sym);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testAssignments() {
		StringReader buf = new StringReader("=");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.ASSIGNTO, scanner.next_token().sym);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testReservedKeywords() {
		StringReader buf = new StringReader("var");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.VAR, scanner.next_token().sym);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testInlineComment() {
		StringReader buf = new StringReader(
				"a+b//this is an inline comment\nb+a");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals("a", scanner.next_token().value);
			assertEquals(ParserSym.PLUS, scanner.next_token().sym);
			assertEquals("b", scanner.next_token().value);
			assertEquals("b", scanner.next_token().value);
			assertEquals(ParserSym.PLUS, scanner.next_token().sym);
			assertEquals("a", scanner.next_token().value);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testEmptyInlineComment() {
		StringReader buf = new StringReader(
				"//\n");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}


	@Test
	public void testMultilineCommentInExpression() {
		StringReader buf = new StringReader(
				"a+/*this\nis\na\nmultiline\ncomment*/-b");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals("a", scanner.next_token().value);
			assertEquals(ParserSym.PLUS, scanner.next_token().sym);
			assertEquals(ParserSym.MINUS, scanner.next_token().sym);
			assertEquals("b", scanner.next_token().value);
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testMultilineCommentWithMultipleStars() {
		StringReader buf = new StringReader(
				"/**********/");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}

	@Test
	public void testEmptyMultilineComment() {
		StringReader buf = new StringReader(
				"/**/");
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(buf, sf);

		try {
			assertEquals(ParserSym.EOF, scanner.next_token().sym);
		} catch (IOException e) {
			fail("Scanner error: " + e.getMessage());
		}
	}
}
