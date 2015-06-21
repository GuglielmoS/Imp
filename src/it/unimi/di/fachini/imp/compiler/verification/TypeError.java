package it.unimi.di.fachini.imp.compiler.verification;

public class TypeError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	TypeError(String message) {
		super(message);
	}
}
