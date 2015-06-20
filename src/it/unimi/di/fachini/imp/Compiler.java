package it.unimi.di.fachini.imp;

import it.unimi.di.fachini.imp.compiler.CodeGenerator;
import it.unimi.di.fachini.imp.compiler.Program;
import it.unimi.di.fachini.imp.compiler.error.CompilerError;
import it.unimi.di.fachini.imp.parser.Parser;
import it.unimi.di.fachini.imp.scanner.Scanner;

import java.io.Reader;

import java_cup.runtime.ComplexSymbolFactory;

public class Compiler {
	private final Reader code;
	private byte[] bytecode;
	private String programName;

	public Compiler(Reader code) {
		this.code = code;
	}
	
	public byte[] compile() throws CompilerError {
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
		Scanner scanner = new Scanner(code, sf);
		Parser parser = new Parser(scanner, sf);

		// try to parse from the given stream
		Program program = null;
		try {
			program = (Program) parser.parse().value;
		} catch (Exception e) {
			throw new CompilerError("Parsing error: " + e.getMessage());
		}

		// store the program name
		programName = program.getName();
		
		// compile the input code
		bytecode = new CodeGenerator().compile(program);
		return bytecode;
	}

	public byte[] getBytecode() {
		return bytecode;
	}
	
	public String getProgramName() {
		return programName;
	}
}
