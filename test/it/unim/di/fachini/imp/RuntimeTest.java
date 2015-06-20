package it.unim.di.fachini.imp;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class RuntimeTest {
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

	@Test(expected=InvocationTargetException.class)
	public void testDivByZero() throws Exception {
		assertEquals("", Util.runProgram("write 1/0;", ""));
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
	 * BLOCK statement
	 */

	@Test
	public void testNestedBlocks() throws Exception {
		assertEquals("123",
				Util.runProgram("var x;x=1;{var y;y=2;{var z;z=3;}}write x; write y; write z;", ""));
	}
}
