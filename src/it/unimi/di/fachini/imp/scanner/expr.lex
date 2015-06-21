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
	protected StringBuffer stringBuffer = new StringBuffer();
	
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

/*
 * States 
 */

%state STRING

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
	 * Array operators
	 */
	"#" {return sf.newSymbol("SHARP", ParserSym.SHARP);}

	/*
	 * Conditional operators
	 */

	"<"  {return sf.newSymbol("LT", ParserSym.LT);}
	"<=" {return sf.newSymbol("LE", ParserSym.LE);}
	">"  {return sf.newSymbol("GT", ParserSym.GT);}
	">=" {return sf.newSymbol("GE", ParserSym.GE);}
	"==" {return sf.newSymbol("EQ", ParserSym.EQ);}
	"!=" {return sf.newSymbol("NE", ParserSym.NE);}

	/*
	 * Parenthesis
	 */

	"(" {return sf.newSymbol("OPEN_PAREN", ParserSym.OPEN_PAREN);}
	")" {return sf.newSymbol("CLOSE_PAREN", ParserSym.CLOSE_PAREN);}
	"[" {return sf.newSymbol("OPEN_SQUARE_BRACKET", ParserSym.OPEN_SQUARE_BRACKET);}
	"]" {return sf.newSymbol("CLOSE_SQUARE_BRACKET", ParserSym.CLOSE_SQUARE_BRACKET);}
	"{" {return sf.newSymbol("OPEN_CURLY_BRACE", ParserSym.OPEN_CURLY_BRACE);}
	"}" {return sf.newSymbol("ClOSE_CURLY_BRACE", ParserSym.ClOSE_CURLY_BRACE);}

	/*
	 * Assignments 
	 */

	"=" {return sf.newSymbol("ASSIGNTO", ParserSym.ASSIGNTO);}

	/*
	 * Reserved keywords
	 */

	"var"		{return sf.newSymbol("VAR", ParserSym.VAR);}
	"ref"		{return sf.newSymbol("REF", ParserSym.REF);}
	"new"		{return sf.newSymbol("NEW", ParserSym.NEW);}
	"null"		{return sf.newSymbol("NULL", ParserSym.NULL);}
	"if"		{return sf.newSymbol("IF", ParserSym.IF);}
	"else"		{return sf.newSymbol("ELSE", ParserSym.ELSE);}
	"while"		{return sf.newSymbol("WHILE", ParserSym.WHILE);}
	"do"		{return sf.newSymbol("DO", ParserSym.DO);}
	"for"		{return sf.newSymbol("FOR", ParserSym.FOR);}
	"write"		{return sf.newSymbol("WRITE", ParserSym.WRITE);}
	"writemsg"	{return sf.newSymbol("WRITEMSG", ParserSym.WRITEMSG);}
	"writeln"	{return sf.newSymbol("WRITELN", ParserSym.WRITELN);}
	"read"		{return sf.newSymbol("READ", ParserSym.READ);}

	/*
	 * Numbers
	 */
	
	{Digit}+ {return sf.newSymbol("NUM", ParserSym.NUM, new Integer(yytext()));}
	
	/*
	 * Identifiers
	 */
	
	{Letter}({Letter}|{Digit})* {return sf.newSymbol("IDENT", ParserSym.IDENT, yytext());}
	
	/*
	 * Strings
	 */
	\" {stringBuffer.setLength(0); yybegin(STRING);}
	
	/*
	 * Other
	 */
	
	{WhiteSpace} { }
}

<STRING> {
	  // string's end
      \" 			{yybegin(YYINITIAL); 
           			 return sf.newSymbol("STRING", ParserSym.STRING, stringBuffer.toString());}

      // string simple characters
      [^\n\r\"\\]+  {stringBuffer.append(yytext());}
      
      // escaped characters
      \\t           {stringBuffer.append('\t');}
      \\n           {stringBuffer.append('\n');}
      \\r           {stringBuffer.append('\r');}
      \\\"          {stringBuffer.append('\"');}
      \\            {stringBuffer.append('\\');}
}

/*
 * Other
 */

.        {return sf.newSymbol("err", ParserSym.error);}

<<EOF>>  {return sf.newSymbol("EOF", ParserSym.EOF);}
