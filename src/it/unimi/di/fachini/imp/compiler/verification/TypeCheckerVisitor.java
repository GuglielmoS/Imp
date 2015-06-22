package it.unimi.di.fachini.imp.compiler.verification;

import it.unimi.di.fachini.imp.compiler.ast.AstVisitor;
import it.unimi.di.fachini.imp.compiler.ast.Statement;
import it.unimi.di.fachini.imp.compiler.ast.arith.AddExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.DivExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.ModExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.MulExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.SubExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.UnaryMinusExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.UnaryPlusExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayElem;
import it.unimi.di.fachini.imp.compiler.ast.atom.ArrayLength;
import it.unimi.di.fachini.imp.compiler.ast.atom.NewArray;
import it.unimi.di.fachini.imp.compiler.ast.atom.NullRef;
import it.unimi.di.fachini.imp.compiler.ast.atom.Num;
import it.unimi.di.fachini.imp.compiler.ast.atom.Var;
import it.unimi.di.fachini.imp.compiler.ast.conditional.Condition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.ConditionType;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignArrayStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignVarStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.BlockStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.DoWhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.EmptyStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.ForEachStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.ForStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.IfStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.WhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.ReadArrayElemStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.ReadVarStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteMessageStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteStatement;
import static it.unimi.di.fachini.imp.compiler.verification.ImpType.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TypeCheckerVisitor implements AstVisitor {
	private final Stack<List<ImpType>> expectedTypes;
	
	TypeCheckerVisitor() {
		expectedTypes = new Stack<>();
	}

	void expected(ImpType... types) {
		expectedTypes.push(Arrays.asList(types));
	}

	private String prettifyExpected() {
		return expectedTypes.peek()
							.stream()
							.map(ImpType::toString)
							.collect(Collectors.joining(" or "));
	}

	private void found(ImpType type) {
		if (expectedTypes.peek().contains(type))
			expectedTypes.pop();
		else
			throw new TypeError("Expected " + prettifyExpected() + ", found " + type);
	}

	@Override
	public void visitNum(Num expr) {
		found(NUM);
	}

	@Override
	public void visitVar(Var expr) {
		if (expr.getDescriptor().isRef())
			found(REF);
		else
			found(NUM);
	}

	@Override
	public void visitNull(NullRef expr) {
		found(REF);
	}

	@Override
	public void visitNewArray(NewArray expr) {
		found(REF);
		expected(NUM);
		expr.getSize().accept(this);
	}

	@Override
	public void visitArrayElem(ArrayElem expr) {
		found(NUM);
		// check the descriptor
		if (!expr.getArrayRef().isRef())
			throw new TypeError("Invalid array access: non-reference found");
		// check the index
		expected(NUM);
		expr.getIndex().accept(this);
	}

	@Override
	public void visitArrayLength(ArrayLength expr) {
		found(NUM);
		if (!expr.getDescriptor().isRef())
			throw new TypeError("Invalid array access: non-reference found");
	}

	@Override
	public void visitAdd(AddExpr expr) {
		found(NUM);	
		expected(NUM);
		expr.getLeft().accept(this);
		expected(NUM);
		expr.getRight().accept(this);
	}

	@Override
	public void visitSub(SubExpr expr) {
		found(NUM);	
		expected(NUM);
		expr.getLeft().accept(this);
		expected(NUM);
		expr.getRight().accept(this);	
	}

	@Override
	public void visitMul(MulExpr expr) {
		found(NUM);
		expected(NUM);
		expr.getLeft().accept(this);
		expected(NUM);
		expr.getRight().accept(this);
	}

	@Override
	public void visitDiv(DivExpr expr) {
		found(NUM);
		expected(NUM);
		expr.getLeft().accept(this);
		expected(NUM);
		expr.getRight().accept(this);
	}

	@Override
	public void visitMod(ModExpr expr) {
		found(NUM);
		expected(NUM);
		expr.getLeft().accept(this);
		expected(NUM);
		expr.getRight().accept(this);
	}

	@Override
	public void visitUnaryPlus(UnaryPlusExpr expr) {
		found(NUM);
		expected(NUM);
		expr.getTarget().accept(this);
	}

	@Override
	public void visitUnaryMinus(UnaryMinusExpr expr) {
		found(NUM);
		expected(NUM);
		expr.getTarget().accept(this);
	}

	@Override
	public void visitWrite(WriteStatement writeStmt) {
		found(STATEMENT);
		expected(NUM);
		writeStmt.getExpr().accept(this);
	}

	@Override
	public void visitWriteMessage(WriteMessageStatement writeMsgStmt) {
		found(STATEMENT);
	}

	@Override
	public void visitReadVar(ReadVarStatement readStmt) {
		found(STATEMENT);
	}

	@Override
	public void visitReadArrayElem(ReadArrayElemStatement readStmt) {
		found(STATEMENT);
	}

	@Override
	public void visitAssignVar(AssignVarStatement assign) {
		found(STATEMENT);
		if (assign.getTarget().getDescriptor().isRef())
			expected(REF);
		else
			expected(NUM);
		assign.getValue().accept(this);
	}

	@Override
	public void visitAssignArray(AssignArrayStatement assign) {
		found(STATEMENT);
		expected(NUM);
		assign.getValue().accept(this);		
	}

	@Override
	public void visitBlock(BlockStatement block) {
		found(STATEMENT);
		for (Statement stmt : block.getStatements()) {
			expected(STATEMENT);
			stmt.accept(this);
		}
	}

	@Override
	public void visitEmpty(EmptyStatement empty) {
		found(STATEMENT);
	}

	@Override
	public void visitIf(IfStatement ifStmt) {
		found(STATEMENT);
		Condition condition = ifStmt.getCondition();
		// get the expected type
		ImpType expectedType = NUM;
		if (condition.getType() == ConditionType.AEQ ||
			condition.getType() == ConditionType.ANE)
			expectedType = REF;
		// check the condition
		expected(expectedType);
		condition.getLeft().accept(this);
		expected(expectedType);
		condition.getRight().accept(this);
		// check the 'then' branch
		expected(STATEMENT);
		ifStmt.getConsequent().accept(this);
		// check the 'else' branch (if needed)
		if (ifStmt.hasAlternative()) {
			expected(STATEMENT);
			ifStmt.getAlternative().accept(this);		
		}
	}

	@Override
	public void visitWhile(WhileStatement whileStmt) {
		found(STATEMENT);
		Condition condition = whileStmt.getCondition();
		// get the expected type
		ImpType expectedType = NUM;
		if (condition.getType() == ConditionType.AEQ ||
			condition.getType() == ConditionType.ANE)
			expectedType = REF;
		// check the condition
		expected(expectedType);
		condition.getLeft().accept(this);
		expected(expectedType);
		condition.getRight().accept(this);
		// check the body
		expected(STATEMENT);
		whileStmt.getBody().accept(this);
	}

	@Override
	public void visitDoWhile(DoWhileStatement whileStmt) {
		found(STATEMENT);
		Condition condition = whileStmt.getCondition();
		// get the expected type
		ImpType expectedType = NUM;
		if (condition.getType() == ConditionType.AEQ ||
			condition.getType() == ConditionType.ANE)
			expectedType = REF;
		// check the condition
		expected(expectedType);
		condition.getLeft().accept(this);
		expected(expectedType);
		condition.getRight().accept(this);
		// check the body
		expected(STATEMENT);
		whileStmt.getBody().accept(this);
	}
 
	@Override
	public void visitFor(ForStatement forStmt) {
		found(STATEMENT);
		if (forStmt.getIterVar().isRef())
			throw new TypeError("The iteration variable must not be a reference!");
		// check the 'start' expr
		expected(NUM);
		forStmt.getStart().accept(this);
		// check the 'end' expr
		expected(NUM);
		forStmt.getEnd().accept(this);
		// check the 'step' expr
		expected(NUM);
		forStmt.getStep().accept(this);
		// check the body
		expected(STATEMENT);
		forStmt.getBody().accept(this);
	}

	@Override
	public void visitForEach(ForEachStatement forEachStmt) {
		found(STATEMENT);
		// check the iteration var
		if (forEachStmt.getIterVar().isRef())
			throw new TypeError("The iteration variable must not be a reference!");
		// check the array ref
		if (!forEachStmt.getArray().isRef())
			throw new TypeError("The foreach wants a reference!");
		// check the body
		expected(STATEMENT);
		forEachStmt.getBody().accept(this);
	}
}
