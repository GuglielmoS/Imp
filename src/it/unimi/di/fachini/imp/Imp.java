package it.unimi.di.fachini.imp;

import it.unimi.di.fachini.imp.compiler.error.CompilerError;
import it.unimi.di.fachini.imp.compiler.verification.TypeError;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Imp {
	public static void main(String[] args) {
		// retrieve the file to compile from CLI arguments
		if (args.length != 1) {
			System.out.println("Usage: java -jar Imp.jar <ProgramFile>");
			System.exit(1);
		}

		// create a stream for the program's code
		FileReader code = null;
		try {
			code = new FileReader(args[0]);
		} catch (FileNotFoundException e) {
			System.err.println("[IO Error] input file not found");
			System.exit(2);
		}

		// determine the name of the final program
		String programName = args[0].substring(0, args[0].length() - 4);

		// compile the code
		Compiler compiler = new Compiler(programName, code);
		byte[] bytecode = null;
		try {
			bytecode = compiler.compile();
		} catch (CompilerError ce) {
			System.err.println("[Compiler Error] " + ce.getMessage());
			System.exit(3);
		} catch (TypeError te) {
			System.err.println("[Type Error] " + te.getMessage());
			System.exit(4);
		}

		// write the generated bytecode in a new .class file
		try {
			FileOutputStream output = new FileOutputStream(programName + ".class");
			output.write(bytecode);
			output.close();
		} catch (IOException e) {
			System.err
					.println("[IO Error] cannot write the generated bytecode because "
							+ e.getMessage());
			System.exit(5);
		}
	}
}
