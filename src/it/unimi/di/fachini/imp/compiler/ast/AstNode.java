package it.unimi.di.fachini.imp.compiler.ast;

import org.objectweb.asm.MethodVisitor;

public interface AstNode {
	void compile(MethodVisitor methodWriter);
}
