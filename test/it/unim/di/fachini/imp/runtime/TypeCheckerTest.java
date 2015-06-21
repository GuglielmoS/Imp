package it.unim.di.fachini.imp.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.unimi.di.fachini.imp.compiler.verification.TypeError;

import org.junit.Test;

public class TypeCheckerTest {
	@Test
	public void assignNullToVariableMustFail() throws Exception {
		try {
			assertEquals("", Util.runProgram("var a; a = null;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
	}

	@Test
	public void assignRefToVariableMustFail() throws Exception {
		try {
			assertEquals("", Util.runProgram("var a; ref b; b = null; a = b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
	}

	@Test
	public void arithOperationsWithRefMustFail() throws Exception {
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = a + b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = a * b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = a / b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = a - b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = a % b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = -b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
		try {
			assertEquals("", Util.runProgram("var a; ref b; a = +b;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
	}

	@Test
	public void assignVarToRefMustFail() throws Exception {
		try {
			assertEquals("", Util.runProgram("var a; ref b; b = a;", ""));
			fail();
		} catch (TypeError te) {
			// DO NOTHING
		}
	}
}
