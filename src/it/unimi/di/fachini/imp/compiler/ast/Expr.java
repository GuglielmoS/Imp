package it.unimi.di.fachini.imp.compiler.ast;

public abstract class Expr extends AstNode {
	public boolean isRef() {
		return false;
	}
}
