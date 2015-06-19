package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.Descriptor;

import java.util.List;

public abstract class Declaration implements AstNode {
	public abstract List<Descriptor> getDeclaredIdentifiers();
}
