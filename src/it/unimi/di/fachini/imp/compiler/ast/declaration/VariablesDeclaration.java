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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifiers == null) ? 0 : identifiers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariablesDeclaration other = (VariablesDeclaration) obj;
		if (identifiers == null) {
			if (other.identifiers != null)
				return false;
		} else if (!identifiers.equals(other.identifiers))
			return false;
		return true;
	}
}
