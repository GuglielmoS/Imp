package it.unimi.di.fachini.imp;

import it.unimi.di.fachini.imp.dynamic.RuntimeClassLoader;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;

public class CompilerMain {
	public static void main(String[] args) {
		/*StringReader buf = new StringReader("var answer; answer = 42;" + 
											"writemsg \"The ultimate answer is \";" +
											"write answer;" +
											"writeln;");*/
		/*StringReader buf = new StringReader(
				"var answer;" +
				"answer = 42;" +
				"if (answer) {" +
					"writemsg \"The ultimate answer is \";" +
					"write answer;" +
					"writeln;" +
				"} else {" +
					"writemsg \"NO ULTIME ANSWER!\"" +
					"writeln;" +
				"}");*/
		/*StringReader buf = new StringReader(
				"var i;" +
				"i = 10;" +
				"while (i) {" + 
					"write i;" +
					"i = i - 1;" +
				"}");*/
		//StringReader buf = new StringReader("write ((8 % 5) % 2); writeln; write (9 % 4); writeln;");
		//StringReader buf = new StringReader("if (1==1) if (2!=2) writemsg \"KO\"; else writemsg \"OK\";");
		//StringReader buf = new StringReader("if (1<1) ; if (2>2) writemsg \"KO\"; else writemsg \"OK\";");
		//StringReader buf = new StringReader("if (1<=1) writemsg \"OK\"; else writemsg \"KO\";");
		//StringReader buf = new StringReader("if (1>=1) writemsg \"OK\"; else writemsg \"KO\";");
		StringReader buf = new StringReader(
				"var dividendo, divisore;" +
				"writemsg \"Dividendo? \";" +
				"read dividendo;" +
				"writemsg \"Divisore? \";" +
				"read divisore;" +
				"while (divisore != 0) {" +
					"var resto;" +
					"resto = dividendo % divisore;" +
					"dividendo = divisore;" +
					"divisore = resto;" +
				"}" +
				"writemsg \"MCD = \";" +
				"write dividendo;" +
				"writeln;");
		//StringReader buf = new StringReader("var i; i = 10; while (i >= 0) {write i; writeln; i = i - 1;}");
		//StringReader buf = new StringReader("var i; read i; write i; writeln;");

		// compile the program
		Compiler compiler = new Compiler(buf);
		byte[] bytecode = compiler.compile();
	}
}
