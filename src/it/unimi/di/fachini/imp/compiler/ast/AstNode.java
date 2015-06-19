package it.unimi.di.fachini.imp.compiler.ast;

public interface AstNode {
	void accept(ASTVisitor v);
}
