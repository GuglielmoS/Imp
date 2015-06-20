package it.unimi.di.fachini.imp.dynamic;

public class RuntimeClassLoader extends ClassLoader {
	Class<?> defineClass(String name, byte[] bytecode) {
		return defineClass(name, bytecode, 0, bytecode.length);
	}
}
