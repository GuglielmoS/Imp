package it.unim.di.fachini.imp.runtime;

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

	@Test
	public void testExample3() throws Exception {
		String code = 
				"/* Somma di un array di interi */" +
				"" +
				"writemsg \"quanti numeri desideri sommare? \";" +
				"var quanti;" +
				"read quanti;" +
				"ref numeri;" +
				"numeri = new quanti;" +
				"" +
				"var i;" +
				"i = 0;" +
				"while (i < quanti) {" +
				"  writemsg \"numero di posizione \";" +
				"  write i + 1;" +
				"  writemsg \"? \";" +
				"  read numeri[i];" +
				"  i = i + 1;" +
				"}" +
				"var somma;" +
				"somma = 0;" +
				"i = 0;" +
				"while (i < quanti) {" +
				"  somma = somma + numeri[i];" +
				"  i = i + 1;" +
				"}" +
				"" +
				"writemsg \"La somma dei numeri letti e' \";" +
				"write somma;" +
				"writeln;";

		// run with (0) as input
		assertEquals("quanti numeri desideri sommare? " +
					 "La somma dei numeri letti e' 0\n", 
					 Util.runProgram(code, "0\n"));

		// run with (0,1,2,3,4) as input
		assertEquals("quanti numeri desideri sommare? " +
					 "numero di posizione 1? " +
					 "numero di posizione 2? " +
					 "numero di posizione 3? " +
					 "numero di posizione 4? " +
					 "La somma dei numeri letti e' 10\n", 
					 Util.runProgram(code, "4\n1\n2\n3\n4\n"));
	}
	
	@Test
	public void testExample4() throws Exception {
		String code = 
			"/* Il crivello di Eratostene */" +
			"" +
			"ref primi;" +
			"var nMax, x;" +
			"" +
			"writemsg \"Numero massimo da considerare? \";" +
			"read nMax;" +
			"" +
			"" +
			"//creazione e inizializzazione array\n" +
			"primi = new nMax + 2;" +
			"x = 2;" +
			"while (x <= nMax) {" +
			"  primi[x] = 1;  //1 per true\n" +
			"  x = x + 1;" +
			"}" +
			"" +
			"//setaccio\n" +
			"var numero, multiplo;" +
			"numero = 2;" +
			"while (numero <= nMax) {" +
			"  if (primi[numero] == 1) {" +
			"    multiplo = numero * 2;" +
			"    while (multiplo <= nMax) {   " +
			"      primi[multiplo] = 0; //assegna false\n" +
			"      multiplo = multiplo + numero;" +
			"    }" +
			"  }" +
			"  numero = numero + 1;" +
			"}" +
			"" +
			"//comunicazione risultato\n" +
			"writemsg \"Elenco primi: \";" +
			"numero = 2;" +
			"while (numero <= nMax) {" +
			"  if (primi[numero] == 1) {" +
			"    write numero;" +
			"    writemsg \" \";" +
			"  }" +
			"  numero = numero + 1;" +
			"}" +
			"writeln;";

		// run with (0) as input
		assertEquals("Numero massimo da considerare? Elenco primi: \n",
					 Util.runProgram(code, "0\n"));

		// run with (10) as input
		assertEquals("Numero massimo da considerare? Elenco primi: 2 3 5 7 \n",
					 Util.runProgram(code, "10\n"));

		// run with (100) as input
		assertEquals("Numero massimo da considerare? Elenco primi: " +
					 "2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 \n",
					 Util.runProgram(code, "100\n"));
	}
}
