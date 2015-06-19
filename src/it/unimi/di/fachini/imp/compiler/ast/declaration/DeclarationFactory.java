package it.unimi.di.fachini.imp.compiler.ast.declaration;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Declaration;

import java.util.List;

public class DeclarationFactory {
	public static Declaration var(List<Descriptor> identifiers) {
		return new VariablesDeclaration(identifiers);
	}
}
