package it.unim.di.fachini.imp.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class RuntimeTest {

	@Test
	public void testUninitializedVariables() throws Exception {
		assertEquals("000", Util.runProgram("var a, b, c; write a; write b; write c;", ""));
	}

	/*
	 * IO
	 */
	
	@Test
	public void testWrite() throws Exception {
		assertEquals("123456", Util.runProgram("write 123456;", ""));
	}

	@Test
	public void testWriteMessage() throws Exception {
		assertEquals("abc def \n\t\r\"", 
				Util.runProgram("writemsg \"abc def \\n\\t\\r\\\"\";", ""));
	}

	@Test
	public void testWriteln() throws Exception {
		assertEquals("\n", Util.runProgram("writeln;", ""));
	}

	@Test
	public void testRead() throws Exception {
		assertEquals("1234", Util.runProgram("var x; read x; write x;", "1234"));
	}

	/*
	 * Arithmetic operators
	 */

	@Test
	public void testAdd() throws Exception {
		assertEquals("7", Util.runProgram("write 3+4;", ""));
	}

	@Test
	public void testSub() throws Exception {
		assertEquals("3", Util.runProgram("write 7-4;", ""));
	}

	@Test
	public void testMul() throws Exception {
		assertEquals("28", Util.runProgram("write 7*4;", ""));
	}

	@Test
	public void testDivByZero() throws Exception {
		try {
			assertEquals("", Util.runProgram("write 1/0;", ""));
		} catch (InvocationTargetException e) {
			assertTrue(e.getTargetException() instanceof ArithmeticException);
		}
	}

	@Test
	public void testDiv() throws Exception {
		assertEquals("2", Util.runProgram("write 8/4;", ""));
	}

	@Test
	public void testDivRound() throws Exception {
		assertEquals("2", Util.runProgram("write 5/2;", ""));
	}

	@Test
	public void testMod() throws Exception {
		assertEquals("22",
				Util.runProgram("var a, b; a = 42; b = 5; write a-b*(a/b); write a%b;", ""));
	}

	/*
	 * IF Statement
	 */

	@Test
	public void testShortIf() throws Exception {
		assertEquals("OK1OK2", Util.runProgram("if (1==1) writemsg \"OK1\"; writemsg \"OK2\";", ""));
	}	
	
	@Test
	public void testIfEQ() throws Exception {
		assertEquals("OK", Util.runProgram("if (1==1) writemsg \"OK\"; else writemsg \"KO\";", ""));
	}

	@Test
	public void testIfNE() throws Exception {
		assertEquals("OK", Util.runProgram("if (1!=1) writemsg \"KO\"; else writemsg \"OK\";", ""));
	}

	@Test
	public void testIfLT() throws Exception {
		assertEquals("OK", Util.runProgram("if (1<1) writemsg \"KO\"; else writemsg \"OK\";", ""));
	}

	@Test
	public void testIfLE() throws Exception {
		assertEquals("OK", Util.runProgram("if (1<=1) writemsg \"OK\"; else writemsg \"KO\";", ""));
	}

	@Test
	public void testIfGT() throws Exception {
		assertEquals("OK", Util.runProgram("if (1>1) writemsg \"KO\"; else writemsg \"OK\";", ""));
	}

	@Test
	public void testIfGE() throws Exception {
		assertEquals("OK", Util.runProgram("if (1>=1) writemsg \"OK\"; else writemsg \"KO\";", ""));
	}

	/*
	 * WHILE Statement
	 */
	
	@Test
	public void testWhile() throws Exception {
		assertEquals("0123456789", Util.runProgram("var i;i=0;while(i<10){write i;i=i+1;}", ""));
	}

	/*
	 * DO WHILE Statement
	 */

	@Test
	public void testDoWhileAsWhile() throws Exception {
		assertEquals("0123456789", Util.runProgram("var i;i=0;do{write i;i=i+1;}while(i<10)", ""));
	}

	@Test
	public void testDoWhile() throws Exception {
		assertEquals("0", Util.runProgram("var i;i=0;do{write i;i=i+1;}while(i<0)", ""));
	}

	/*
	 * FOR Statement
	 */

	@Test
	public void testForWithoutStep() throws Exception {
		assertEquals("012345678910", Util.runProgram("var i;for(i=0,10)write i;", ""));
		assertEquals("55", Util.runProgram("var x,y,i;x=0;y=5;for(i=1,2*y){x=x+i;y=y-1;}write x;", ""));
	}

	@Test
	public void testForWithPositiveStep() throws Exception {
		assertEquals("0246810", Util.runProgram("var i;for(i=0,10,2)write i;", ""));
	}

	@Test
	public void testForWithNegativeStep() throws Exception {
		assertEquals("1086420", Util.runProgram("var i;for(i=10,0,-2)write i;", ""));
		assertEquals("2017", Util.runProgram("var i;for(i=20,15,-3)write i;", ""));
	}

	@Test
	public void testForWithoutBodyExecution() throws Exception {
		assertEquals("", Util.runProgram("var i;for(i=0,-1)write i;", ""));
		assertEquals("", Util.runProgram("var i;for(i=20,21,-1)write i;", ""));
	}
	
	/*
	 * BLOCK statement
	 */

	@Test
	public void testNestedBlocks() throws Exception {
		assertEquals("123",
				Util.runProgram("var x;x=1;{var y;y=2;{var z;z=3;}}write x; write y; write z;", ""));
	}
	
	/*
	 * ARRAYS
	 */

	@Test
	public void testNull() throws Exception {
		assertEquals("",
				Util.runProgram("ref a; a = null;", ""));
	}

	@Test
	public void testSimpleArray() throws Exception {
		assertEquals("0123456789",
				Util.runProgram("ref a; a = new 10; var i; for (i=0,9){a[i]=i;write a[i];}", ""));
	}

	@Test
	public void testSimpleArrayWithRead() throws Exception {
		assertEquals("0123456789",
				Util.runProgram("ref a; a = new 10; var i; for (i=0,9){read a[i];write a[i];}",
								"0\n1\n2\n3\n4\n5\n6\n7\n8\n9\n"));
	}

	@Test
	public void testArrayLength() throws Exception {
		assertEquals("1",
				Util.runProgram("ref a; a = new 1; write #a;", ""));
		assertEquals("3",
				Util.runProgram("ref a; a = new 3; write #a;", ""));
		assertEquals("13",
				Util.runProgram("ref a, b; a = new 3; b = new 10; write #a+#b;", ""));
	}

	@Test
	public void testArrayEquality() throws Exception {
		assertEquals("OK",
				Util.runProgram("ref a, b; a = new 3; b = new 3; if (a==b) ; else writemsg \"OK\";", ""));

		assertEquals("OK",
				Util.runProgram("ref a, b; a = new 3; b = a; if (a==b) writemsg \"OK\"; else ;", ""));

		assertEquals("OK",
				Util.runProgram("ref a, b; a = new 3; b = new 3; var i; for (i=0,2){a[i]=i;b[i]=i;} if (a==b) ; else writemsg \"OK\";", ""));
	}

	@Test
	public void testArrayInequality() throws Exception {
		assertEquals("OK",
				Util.runProgram("ref a, b; a = new 3; b = new 3; if (a!=b) writemsg \"OK\"; else ;", ""));

		assertEquals("OK",
				Util.runProgram("ref a, b; a = new 3; b = a; if (a!=b) ; else writemsg \"OK\";", ""));

		assertEquals("OK",
				Util.runProgram("ref a, b; a = new 3; b = new 3; var i; for (i=0,2){a[i]=i;b[i]=i;} if (a!=b) writemsg \"OK\"; else ;", ""));
	}
}
