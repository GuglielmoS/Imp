package it.unimi.di.fachini.imp.compiler.ast;

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

public interface AstVisitor {
	// atom
	void visitNum(Num expr);
	void visitVar(Var expr);
	void visitNull(NullRef expr);
	void visitNewArray(NewArray expr);
	void visitArrayElem(ArrayElem expr);
	void visitArrayLength(ArrayLength expr);

	// arithmetic
	void visitAdd(AddExpr expr);
	void visitSub(SubExpr expr);
	void visitMul(MulExpr expr);
	void visitDiv(DivExpr expr);
	void visitMod(ModExpr expr);
	void visitUnaryPlus(UnaryPlusExpr expr);
	void visitUnaryMinus(UnaryMinusExpr expr);

	// IO statements
	void visitWrite(WriteStatement writeStmt);
	void visitWriteMessage(WriteMessageStatement writeMsgStmt);
	void visitReadVar(ReadVarStatement readStmt);
	void visitReadArrayElem(ReadArrayElemStatement readStmt);

	// other statements
	void visitAssignVar(AssignVarStatement assign);
	void visitAssignArray(AssignArrayStatement assign);
	void visitBlock(BlockStatement block);
	void visitEmpty(EmptyStatement empty);
	void visitIf(IfStatement ifStmt);
	void visitWhile(WhileStatement whileStmt);
	void visitDoWhile(DoWhileStatement whileStmt);
	void visitFor(ForStatement forStmt);
	void visitForEach(ForEachStatement forEachStmt);
}
