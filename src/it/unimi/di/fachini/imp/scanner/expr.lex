package it.unimi.di.fachini.imp.scanner;

import it.unimi.di.fachini.imp.parser.ParserSym;

import java_cup.runtime.*;

%% 

%cup
%public
%class Scanner

%ignorecase

%{
	protected ComplexSymbolFactory sf;
    public Scanner(java.io.Reader in, ComplexSymbolFactory sf) {
		this(in);
		this.sf = sf;
    }
%}

Digit = [:digit:]
Letter = [:letter:]
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

%%

<YYINITIAL> {
	/*
	 * Comments
	 */

	"//" {InputCharacter}* {LineTerminator}? { /* DO NOTHING (eat the comment) */ }
	"/*" [^*] ~"*/" | "/*" "*"+ "/" { /* DO NOTHING (eat the comment) */ }

	/*
	 * Delimiters
	 */
	
	";" {return sf.newSymbol("SEMI_COLON", ParserSym.SEMI_COLON);}
	"," {return sf.newSymbol("COMMA", ParserSym.COMMA);}

	/*
	 * Arithmetic operators
	 */

	"/" {return sf.newSymbol("DIV", ParserSym.DIV);}
	"%" {return sf.newSymbol("MOD", ParserSym.MOD);}
	"-" {return sf.newSymbol("MINUS", ParserSym.MINUS);}
	"*" {return sf.newSymbol("MUL", ParserSym.MUL);}
	"+" {return sf.newSymbol("PLUS", ParserSym.PLUS);}

	/*
	 * Parenthesis
	 */

	"(" {return sf.newSymbol("OPEN_PAREN", ParserSym.OPEN_PAREN);}
	")" {return sf.newSymbol("CLOSE_PAREN", ParserSym.CLOSE_PAREN);}

	/*
	 * Assignments 
	 */

	"=" {return sf.newSymbol("ASSIGNTO", ParserSym.ASSIGNTO);}

	/*
	 * Numbers
	 */
	
	{Digit}+ {return sf.newSymbol("NUM", ParserSym.NUM, new Integer(yytext()));}
	
	/*
	 * Reserved keywords.
	 */
	"var" {return sf.newSymbol("VAR", ParserSym.VAR);}
	
	/*
	 * Identifiers
	 */
	
	{Letter}({Letter}|{Digit})* {return sf.newSymbol("IDENT", ParserSym.IDENT, yytext());}
	
	/*
	 * Other
	 */
	
	{WhiteSpace} { }
}

/*
 * Other
 */

.        {return sf.newSymbol("err", ParserSym.error);}

<<EOF>>  {return sf.newSymbol("EOF", ParserSym.EOF);}
