package it.unimi.di.fachini.imp.compiler.ast;

import it.unimi.di.fachini.imp.compiler.ast.arith.AddExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.DivExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.ModExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.MulExpr;
import it.unimi.di.fachini.imp.compiler.ast.arith.SubExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.NumExpr;
import it.unimi.di.fachini.imp.compiler.ast.atom.VarExpr;
import it.unimi.di.fachini.imp.compiler.ast.conditional.EQCondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.GTCondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.GECondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.LTCondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.LECondition;
import it.unimi.di.fachini.imp.compiler.ast.conditional.NECondition;
import it.unimi.di.fachini.imp.compiler.ast.statement.AssignStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.BlockStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.EmptyStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.IfStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.WhileStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteMessageStatement;
import it.unimi.di.fachini.imp.compiler.ast.statement.io.WriteStatement;

public interface ASTVisitor {
	// atom
	void visitNum(NumExpr expr);
	void visitVar(VarExpr expr);

	// arith
	void visitAdd(AddExpr expr);
	void visitSub(SubExpr expr);
	void visitMul(MulExpr expr);
	void visitDiv(DivExpr expr);
	void visitMod(ModExpr expr);

	// conditionals
	void visitEQ(EQCondition eqCond);
	void visitNE(NECondition neqCond);
	void visitLT(LTCondition ltCond);
	void visitGT(GTCondition gtCond);
	void visitLE(LECondition leCond);
	void visitGE(GECondition geCond);

	// IO statements
	void visitWrite(WriteStatement writeStmt);
	void visitWriteMessage(WriteMessageStatement writeMsgStmt);

	// other statements
	void visitAssign(AssignStatement assign);
	void visitBlock(BlockStatement block);
	void visitEmpty(EmptyStatement empty);
	void visitIf(IfStatement ifStmt);
	void visitWhile(WhileStatement whileStmt);
}
