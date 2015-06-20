package it.unimi.di.fachini.imp.dynamic;

import it.unimi.di.fachini.imp.Compiler;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Runner {
	private final InputStream in;
	private final PrintStream out;
	private Reader code;

	public Runner(Reader code, InputStream in, PrintStream out) {
		this.code = code;
		this.in = in;
		this.out = out;
	}

	public void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Compiler compiler = new Compiler("CompiledProg", code);
		byte[] bytecode = compiler.compile();
		RuntimeClassLoader loader = new RuntimeClassLoader();
		Class<?> clazz = loader.defineClass(compiler.getProgramName(), bytecode);
		Method executeMethod = clazz.getMethod("execute", InputStream.class, PrintStream.class);
		executeMethod.invoke(null, new Object[] {in, out});
	}
}
