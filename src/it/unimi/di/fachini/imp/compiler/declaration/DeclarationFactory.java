package it.unimi.di.fachini.imp.compiler.declaration;

import it.unimi.di.fachini.imp.compiler.Declaration;
import it.unimi.di.fachini.imp.compiler.Descriptor;

import java.util.List;

public class DeclarationFactory {
	public static Declaration var(List<Descriptor> identifiers) {
		return new VariablesDeclaration(identifiers);
	}
}
