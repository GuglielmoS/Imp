package it.unimi.di.fachini.imp.compiler;

public class CompilerError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CompilerError(String message) {
		super(message);
	}
}
