package it.unimi.di.fachini.imp.compiler.ast.statement;

import it.unimi.di.fachini.imp.compiler.ast.Expr;
import it.unimi.di.fachini.imp.compiler.ast.Statement;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class IfStatement extends Statement {
	private final Expr condition;
	private final Statement consequent;
	private final Statement alternative;

	IfStatement(Expr condition, Statement consequent) {
		this.condition = condition;
		this.consequent = consequent;
		this.alternative = null;
	}

	public IfStatement(Expr condition, Statement consequent,
			Statement alternative) {
		this.condition = condition;
		this.consequent = consequent;
		this.alternative = alternative;
	}

	public Expr getCondition() {
		return condition;
	}

	public Statement getConsequent() {
		return consequent;
	}

	public Statement getAlternative() {
		return alternative;
	}

	public boolean hasAlternative() {
		return alternative != null;
	}

	@Override
	public void compile(MethodVisitor mw) {
		// condition check & jump
		condition.compile(mw);
		Label alternativeOrEnd = new Label();
		mw.visitJumpInsn(IFEQ, alternativeOrEnd);

		// consequent branch
		consequent.compile(mw);
		Label end = new Label();
		mw.visitJumpInsn(GOTO, end);

		// alternative branch (if it exists)
		mw.visitLabel(alternativeOrEnd);
		mw.visitFrame(F_SAME, 0, null, 0, null);
		if (hasAlternative()) {
			alternative.compile(mw);
		}

		// if's end
		mw.visitLabel(end);
		mw.visitFrame(F_SAME, 0, null, 0, null);
	}
}
