
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20150326 (SVN rev 63)
//----------------------------------------------------

package it.unimi.di.fachini.imp.parser;

import it.unimi.di.fachini.imp.compiler.*;
import it.unimi.di.fachini.imp.compiler.ast.*;
import java_cup.runtime.*;
import java.util.List;
import java.util.ArrayList;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20150326 (SVN rev 63) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return ParserSym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\022\000\002\002\004\000\002\002\003\000\002\003" +
    "\004\000\002\003\004\000\002\003\002\000\002\004\005" +
    "\000\002\005\003\000\002\005\005\000\002\006\005\000" +
    "\002\006\005\000\002\006\005\000\002\006\005\000\002" +
    "\006\005\000\002\006\003\000\002\006\003\000\002\006" +
    "\004\000\002\006\004\000\002\006\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\037\000\020\002\ufffd\006\ufffd\007\ufffd\014\ufffd\016" +
    "\ufffd\017\ufffd\020\ufffd\001\002\000\020\002\000\006\012" +
    "\007\014\014\013\016\010\017\007\020\015\001\002\000" +
    "\004\002\006\001\002\000\004\002\001\001\002\000\030" +
    "\002\ufff4\006\ufff4\007\ufff4\010\ufff4\011\ufff4\012\ufff4\014" +
    "\ufff4\015\ufff4\016\ufff4\017\ufff4\020\ufff4\001\002\000\004" +
    "\020\036\001\002\000\020\002\uffff\006\uffff\007\uffff\014" +
    "\uffff\016\uffff\017\uffff\020\uffff\001\002\000\014\006\012" +
    "\007\014\014\013\017\007\020\015\001\002\000\014\006" +
    "\012\007\014\014\013\017\007\020\015\001\002\000\014" +
    "\006\012\007\014\014\013\017\007\020\015\001\002\000" +
    "\030\002\ufff3\006\ufff3\007\ufff3\010\ufff3\011\ufff3\012\ufff3" +
    "\014\ufff3\015\ufff3\016\ufff3\017\ufff3\020\ufff3\001\002\000" +
    "\026\002\ufffe\006\020\007\022\010\021\011\023\012\017" +
    "\014\ufffe\016\ufffe\017\ufffe\020\ufffe\001\002\000\014\006" +
    "\012\007\014\014\013\017\007\020\015\001\002\000\014" +
    "\006\012\007\014\014\013\017\007\020\015\001\002\000" +
    "\014\006\012\007\014\014\013\017\007\020\015\001\002" +
    "\000\014\006\012\007\014\014\013\017\007\020\015\001" +
    "\002\000\014\006\012\007\014\014\013\017\007\020\015" +
    "\001\002\000\030\002\ufff6\006\ufff6\007\ufff6\010\ufff6\011" +
    "\ufff6\012\ufff6\014\ufff6\015\ufff6\016\ufff6\017\ufff6\020\ufff6" +
    "\001\002\000\030\002\ufff8\006\ufff8\007\ufff8\010\021\011" +
    "\023\012\017\014\ufff8\015\ufff8\016\ufff8\017\ufff8\020\ufff8" +
    "\001\002\000\030\002\ufff7\006\ufff7\007\ufff7\010\ufff7\011" +
    "\ufff7\012\ufff7\014\ufff7\015\ufff7\016\ufff7\017\ufff7\020\ufff7" +
    "\001\002\000\030\002\ufff9\006\ufff9\007\ufff9\010\021\011" +
    "\023\012\017\014\ufff9\015\ufff9\016\ufff9\017\ufff9\020\ufff9" +
    "\001\002\000\030\002\ufff5\006\ufff5\007\ufff5\010\ufff5\011" +
    "\ufff5\012\ufff5\014\ufff5\015\ufff5\016\ufff5\017\ufff5\020\ufff5" +
    "\001\002\000\030\002\ufff2\006\ufff2\007\ufff2\010\ufff2\011" +
    "\ufff2\012\ufff2\014\ufff2\015\ufff2\016\ufff2\017\ufff2\020\ufff2" +
    "\001\002\000\016\006\020\007\022\010\021\011\023\012" +
    "\017\015\033\001\002\000\030\002\ufff0\006\ufff0\007\ufff0" +
    "\010\ufff0\011\ufff0\012\ufff0\014\ufff0\015\ufff0\016\ufff0\017" +
    "\ufff0\020\ufff0\001\002\000\030\002\ufff1\006\ufff1\007\ufff1" +
    "\010\ufff1\011\ufff1\012\ufff1\014\ufff1\015\ufff1\016\ufff1\017" +
    "\ufff1\020\ufff1\001\002\000\006\004\037\005\040\001\002" +
    "\000\006\004\ufffb\005\ufffb\001\002\000\004\020\041\001" +
    "\002\000\020\002\ufffc\006\ufffc\007\ufffc\014\ufffc\016\ufffc" +
    "\017\ufffc\020\ufffc\001\002\000\006\004\ufffa\005\ufffa\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\037\000\006\002\004\003\003\001\001\000\006\004" +
    "\010\006\015\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\005\034\001\001\000\002\001" +
    "\001\000\004\006\033\001\001\000\004\006\031\001\001" +
    "\000\004\006\030\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\006\027\001\001\000\004\006\026\001\001" +
    "\000\004\006\025\001\001\000\004\006\024\001\001\000" +
    "\004\006\023\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {


	protected SymbolTable symbolTable = new SymbolTable();

  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= prog EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Program start_val = (Program)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // prog ::= seq 
            {
              Program RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<Expr> s = (List<Expr>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = new Program(s, symbolTable); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("prog",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // seq ::= seq decl 
            {
              List<Expr> RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<Expr> s = (List<Expr>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Declaration d = (Declaration)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 s.add(d); RESULT = s; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("seq",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // seq ::= seq expr 
            {
              List<Expr> RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<Expr> s = (List<Expr>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  s.add(e); RESULT = s; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("seq",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // seq ::= 
            {
              List<Expr> RESULT =null;
		 RESULT = new ArrayList<>(); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("seq",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // decl ::= VAR identSeq SEMI_COLON 
            {
              Declaration RESULT =null;
		int identifiersleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int identifiersright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<String> identifiers = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = new Declaration(identifiers); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decl",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // identSeq ::= IDENT 
            {
              List<String> RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 List<String> lst = new ArrayList<>();
				lst.add(id);
				RESULT = lst; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("identSeq",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // identSeq ::= identSeq COMMA IDENT 
            {
              List<String> RESULT =null;
		int identifiersleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int identifiersright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		List<String> identifiers = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 identifiers.add(id); RESULT = identifiers; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("identSeq",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // expr ::= expr PLUS expr 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = BinaryOpFactory.add(e1, e2); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expr ::= expr MINUS expr 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = BinaryOpFactory.sub(e1, e2); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // expr ::= expr MUL expr 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = BinaryOpFactory.mul(e1, e2); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // expr ::= expr DIV expr 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = BinaryOpFactory.div(e1, e2); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // expr ::= expr MOD expr 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = BinaryOpFactory.mod(e1, e2); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // expr ::= NUM 
            {
              Expr RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Integer n = (Integer)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = AtomFactory.num(n); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // expr ::= IDENT 
            {
              Expr RESULT =null;
		int identleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int identright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String ident = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
			/* throw an exception if an identifier has not been previously declared
			if (!symbolTable.contains(ident)) {
				throw new CompilerError("Undeclared identifier found: " + ident);	
			}
			Descriptor d = symbolTable.addIdent(ident);
			*/
			RESULT = AtomFactory.var(ident);
		 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // expr ::= MINUS expr 
            {
              Expr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = UnaryOpFactory.minus(e); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // expr ::= PLUS expr 
            {
              Expr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 RESULT = UnaryOpFactory.plus(e); 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // expr ::= OPEN_PAREN expr CLOSE_PAREN 
            {
              Expr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 RESULT = e; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
