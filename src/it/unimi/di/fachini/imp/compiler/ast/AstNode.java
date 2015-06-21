package it.unimi.di.fachini.imp.compiler.ast;

public abstract class AstNode {
	public abstract void accept(AstVisitor v);
}
