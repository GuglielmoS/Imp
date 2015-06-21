package it.unim.di.fachini.imp.runtime;

import it.unimi.di.fachini.imp.dynamic.Runner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

public class Util {
	public static String runProgram(String rawCode, String rawInput) throws Exception {
		// build the input stream
		ByteArrayInputStream input = new ByteArrayInputStream(rawInput.getBytes("UTF-8"));

		// build the output stream
		ByteArrayOutputStream outWrapper = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(outWrapper);
		
		// define the program code
		StringReader code = new StringReader(rawCode);

		// run the compiled program
		new Runner(code, input, output).execute();

		// return the program output
		return outWrapper.toString("UTF-8");
	}
}
