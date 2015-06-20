package it.unimi.di.fachini.imp.compiler.declaration;

import it.unimi.di.fachini.imp.compiler.Descriptor;

import java.util.List;

public abstract class Declaration {
	public abstract List<Descriptor> getDeclaredIdentifiers();
}
