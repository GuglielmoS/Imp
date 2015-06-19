package it.unimi.di.fachini.imp.dynamic;

public class TestingClassLoader extends ClassLoader {
	public Class<?> defineClass(String name, byte[] bytecode) {
		return defineClass(name, bytecode, 0, bytecode.length);
	}
}
