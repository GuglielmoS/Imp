package it.unimi.di.fachini.imp.compiler.ast;

public abstract class Statement implements AstNode {
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object obj);
}
