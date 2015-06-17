package it.unimi.di.fachini.imp.compiler;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	private final Map<String, Descriptor> values;

	public SymbolTable() {
		this.values = new HashMap<>();
	}

	public Descriptor addIdent(String id) {
		if (!values.containsKey(id)) {
			Descriptor descriptor = new Descriptor(id);
			values.put(id, descriptor);
		}
	
		return values.get(id);
	}

	public boolean contains(Descriptor descriptor) {
		return values.containsKey(descriptor.getId());
	}
}
