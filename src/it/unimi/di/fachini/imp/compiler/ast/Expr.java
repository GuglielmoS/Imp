package it.unimi.di.fachini.imp.compiler.ast;

public abstract class Expr implements AstNode {
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object obj);
}
