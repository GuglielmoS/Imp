package it.unimi.di.fachini.imp.compiler;

public class CompilerError extends RuntimeException {
	public CompilerError(String message) {
		super(message);
	}
}
