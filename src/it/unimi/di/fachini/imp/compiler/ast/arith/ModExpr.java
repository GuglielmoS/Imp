package it.unimi.di.fachini.imp.compiler.ast.arith;

import org.objectweb.asm.MethodVisitor;

import it.unimi.di.fachini.imp.compiler.ast.Expr;

public class ModExpr extends Expr {
	private final Expr left;
	private final Expr right;

	ModExpr(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}

	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}

	@Override
	public void compile(MethodVisitor methodWriter) {
		// TODO Auto-generated method stub
	}
}
