package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.MethodVisitor;

public class AssignStatement extends Statement {
	private final Descriptor target;
	private final Expr value;

	AssignStatement(Descriptor target, Expr value) {
		this.target = target;
		this.value = value;
	}

	public Descriptor getTarget() {
		return target;
	}

	public Expr getValue() {
		return value;
	}

	@Override
	public void compile(MethodVisitor mw) {
		value.compile(mw);
		mw.visitVarInsn(ISTORE, target.getIndex());
	}
}
