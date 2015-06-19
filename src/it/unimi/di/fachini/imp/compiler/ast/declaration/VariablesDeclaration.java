package it.unimi.di.fachini.imp.compiler.ast.declaration;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Declaration;

import java.util.List;

import org.objectweb.asm.MethodVisitor;

public class VariablesDeclaration extends Declaration {
	private final List<Descriptor> identifiers;

	VariablesDeclaration(List<Descriptor> identifiers) {
		this.identifiers = identifiers;
	}

	@Override
	public List<Descriptor> getDeclaredIdentifiers() {
		return identifiers;
	}

	@Override
	public void compile(MethodVisitor methodWriter) {
		// TODO Auto-generated method stub
	}
}
