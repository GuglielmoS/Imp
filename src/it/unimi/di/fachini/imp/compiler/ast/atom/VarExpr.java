package it.unimi.di.fachini.imp.compiler.ast.atom;

import it.unimi.di.fachini.imp.compiler.Descriptor;
import it.unimi.di.fachini.imp.compiler.ast.Expr;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.MethodVisitor;

public class VarExpr extends Expr {
	private Descriptor descriptor;

	VarExpr(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public void compile(MethodVisitor mw) {
		mw.visitVarInsn(ILOAD, descriptor.getIndex());
	}
}
