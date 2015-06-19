package it.unimi.di.fachini.imp.compiler.declaration;

import it.unimi.di.fachini.imp.compiler.Declaration;
import it.unimi.di.fachini.imp.compiler.Descriptor;

import java.util.List;

public class VariablesDeclaration extends Declaration {
	private final List<Descriptor> identifiers;

	VariablesDeclaration(List<Descriptor> identifiers) {
		this.identifiers = identifiers;
	}

	@Override
	public List<Descriptor> getDeclaredIdentifiers() {
		return identifiers;
	}
}
