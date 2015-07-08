package it.unimi.di.fachini.imp.scanner;

import it.unimi.di.fachini.imp.parser.ParserSym;

import java_cup.runtime.*;

import java.util.Map;
import java.util.HashMap;

%% 

%cup
%line
%public
%class Scanner

%ignorecase

%{
	protected ComplexSymbolFactory sf;
	protected StringBuffer stringBuffer;
	protected Map<String, Integer> reservedKeywords;
	
    public Scanner(java.io.Reader in, ComplexSymbolFactory sf) {
		this(in);
		this.sf = sf;
		this.stringBuffer = new StringBuffer();
		this.reservedKeywords = new HashMap<>();
		initReservedKeywords();
    }

	protected void initReservedKeywords() {
		reservedKeywords.put("var", ParserSym.VAR);
		reservedKeywords.put("ref", ParserSym.REF);
		reservedKeywords.put("new", ParserSym.NEW);
		reservedKeywords.put("null", ParserSym.NULL);
		reservedKeywords.put("if", ParserSym.IF);
		reservedKeywords.put("else", ParserSym.ELSE);
		reservedKeywords.put("while", ParserSym.WHILE);
		reservedKeywords.put("do", ParserSym.DO);
		reservedKeywords.put("for", ParserSym.FOR);
		reservedKeywords.put("write", ParserSym.WRITE);
		reservedKeywords.put("writemsg", ParserSym.WRITEMSG);
		reservedKeywords.put("writeln", ParserSym.WRITELN);
		reservedKeywords.put("read", ParserSym.READ);
	}

	protected boolean isReservedKeyword(String ident) {
		return reservedKeywords.containsKey(ident.toLowerCase());
	}

	protected Symbol getReservedKeywordToken(String ident) {
		return sf.newSymbol(ident, reservedKeywords.get(ident));
	}

	public int currentLineNumber() {
		return yyline + 1;
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
	
	":" {return sf.newSymbol("COLON", ParserSym.COLON);}
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
	 * Numbers
	 */

	{Digit}+ {return sf.newSymbol("NUM", ParserSym.NUM, new Integer(yytext()));}

	/*
	 * Identifiers & Reserved Keywords
	 */

	{Letter}({Letter}|{Digit})* {
		String ident = yytext();
		if (isReservedKeyword(ident)) {
			return getReservedKeywordToken(ident);
		} else {
			return sf.newSymbol("IDENT", ParserSym.IDENT, ident);
		}
	}

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
