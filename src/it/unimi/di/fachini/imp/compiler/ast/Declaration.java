package it.unimi.di.fachini.imp.compiler.ast;

import java.util.List;

public class Declaration extends Expr {
	private final List<String> identifiers;

	public Declaration(List<String> identifiers) {
		this.identifiers = identifiers;
	}

	public List<String> getDeclaredIdentifiers() {
		return identifiers;
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
		Declaration other = (Declaration) obj;
		if (identifiers == null) {
			if (other.identifiers != null)
				return false;
		} else if (!identifiers.equals(other.identifiers))
			return false;
		return true;
	}
}