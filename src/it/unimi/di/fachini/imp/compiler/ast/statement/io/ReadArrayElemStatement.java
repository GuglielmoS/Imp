package it.unimi.di.fachini.imp.compiler.ast.statement.io;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayElem;

public class ReadArrayElemStatement extends Statement {
	private final ArrayElem target;

	ReadArrayElemStatement(ArrayElem target) {
		this.target = target;
	}

	public ArrayElem getTarget() {
		return target;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visitReadArrayElem(this);
	}
}
