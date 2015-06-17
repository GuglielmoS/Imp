package it.unimi.di.fachini.imp.compiler;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	private final Map<String, Descriptor> values;

	public SymbolTable() {
		this.values = new HashMap<>();
	}

	public Descriptor addIdent(String ident) {
		if (!values.containsKey(ident)) {
			Descriptor descriptor = new Descriptor(ident);
			values.put(ident, descriptor);
		}
	
		return values.get(ident);
	}

	public boolean contains(String ident) {
		return values.containsKey(ident);
	}

	public Descriptor get(String ident) {
		return values.get(ident);
	}
}
