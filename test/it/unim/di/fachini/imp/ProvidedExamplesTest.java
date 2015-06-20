package it.unim.di.fachini.imp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProvidedExamplesTest {
	@Test
	public void testExample1() throws Exception {
		assertEquals("Hello, world!\n",
				Util.runProgram("writemsg \"Hello, world!\";\nwriteln;", ""));
	}

	@Test
	public void testExample2() throws Exception {
		String code =
				"var dividendo, divisore;" +
				"writemsg \"Dividendo? \";" +
				"read dividendo;" +
				"writemsg \"Divisore? \";" +
				"read divisore;" +
				"while (divisore != 0) {" +
					"var resto;" +
					"resto = dividendo % divisore;" +
					"dividendo = divisore;" +
					"divisore = resto;" +
				"}" +
				"writemsg \"il massimo comun divisore e' \";" +
				"write dividendo;" +
				"writeln;";

		// run with (1,1) as input
		assertEquals("Dividendo? Divisore? il massimo comun divisore e' 1\n", 
				Util.runProgram(code, "1\n1\n"));
		// run with (4,2) as input
		assertEquals("Dividendo? Divisore? il massimo comun divisore e' 2\n", 
				Util.runProgram(code, "4\n2\n"));
		// run with (4,3) as input
		assertEquals("Dividendo? Divisore? il massimo comun divisore e' 1\n", 
				Util.runProgram(code, "4\n3\n"));
		// run with (9,6) as input
		assertEquals("Dividendo? Divisore? il massimo comun divisore e' 3\n", 
				Util.runProgram(code, "9\n6\n"));
		// run with (54,24) as input
		assertEquals("Dividendo? Divisore? il massimo comun divisore e' 6\n", 
				Util.runProgram(code, "54\n24\n"));
	}
}
