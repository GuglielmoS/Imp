/* The following code was generated by JFlex 1.6.1 */

package it.unimi.di.fachini.imp.scanner;

import it.unimi.di.fachini.imp.parser.ParserSym;

import java_cup.runtime.*;

import java.util.Map;
import java.util.HashMap;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>src/it/unimi/di/fachini/imp/scanner/expr.lex</tt>
 */
public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\5\1\3\1\36\1\5\1\4\22\0\1\5\1\22\1\31"+
    "\1\16\1\0\1\13\2\0\1\23\1\24\1\7\1\15\1\12\1\14"+
    "\1\0\1\6\12\1\1\10\1\11\1\17\1\20\1\21\2\0\15\2"+
    "\1\34\3\2\1\35\1\2\1\33\6\2\1\25\1\32\1\26\3\0"+
    "\15\2\1\34\3\2\1\35\1\2\1\33\6\2\1\27\1\0\1\30"+
    "\7\0\1\36\44\0\1\2\12\0\1\2\4\0\1\2\5\0\27\2"+
    "\1\0\37\2\1\0\u01ca\2\4\0\14\2\16\0\5\2\7\0\1\2"+
    "\1\0\1\2\201\0\5\2\1\0\2\2\2\0\4\2\1\0\1\2"+
    "\6\0\1\2\1\0\3\2\1\0\1\2\1\0\24\2\1\0\123\2"+
    "\1\0\213\2\10\0\246\2\1\0\46\2\2\0\1\2\7\0\47\2"+
    "\110\0\33\2\5\0\3\2\55\0\53\2\25\0\12\1\4\0\2\2"+
    "\1\0\143\2\1\0\1\2\17\0\2\2\7\0\2\2\12\1\3\2"+
    "\2\0\1\2\20\0\1\2\1\0\36\2\35\0\131\2\13\0\1\2"+
    "\16\0\12\1\41\2\11\0\2\2\4\0\1\2\5\0\26\2\4\0"+
    "\1\2\11\0\1\2\3\0\1\2\27\0\31\2\107\0\23\2\121\0"+
    "\66\2\3\0\1\2\22\0\1\2\7\0\12\2\4\0\12\1\1\0"+
    "\20\2\4\0\10\2\2\0\2\2\2\0\26\2\1\0\7\2\1\0"+
    "\1\2\3\0\4\2\3\0\1\2\20\0\1\2\15\0\2\2\1\0"+
    "\3\2\4\0\12\1\2\2\23\0\6\2\4\0\2\2\2\0\26\2"+
    "\1\0\7\2\1\0\2\2\1\0\2\2\1\0\2\2\37\0\4\2"+
    "\1\0\1\2\7\0\12\1\2\0\3\2\20\0\11\2\1\0\3\2"+
    "\1\0\26\2\1\0\7\2\1\0\2\2\1\0\5\2\3\0\1\2"+
    "\22\0\1\2\17\0\2\2\4\0\12\1\25\0\10\2\2\0\2\2"+
    "\2\0\26\2\1\0\7\2\1\0\2\2\1\0\5\2\3\0\1\2"+
    "\36\0\2\2\1\0\3\2\4\0\12\1\1\0\1\2\21\0\1\2"+
    "\1\0\6\2\3\0\3\2\1\0\4\2\3\0\2\2\1\0\1\2"+
    "\1\0\2\2\3\0\2\2\3\0\3\2\3\0\14\2\26\0\1\2"+
    "\25\0\12\1\25\0\10\2\1\0\3\2\1\0\27\2\1\0\20\2"+
    "\3\0\1\2\32\0\2\2\6\0\2\2\4\0\12\1\25\0\10\2"+
    "\1\0\3\2\1\0\27\2\1\0\12\2\1\0\5\2\3\0\1\2"+
    "\40\0\1\2\1\0\2\2\4\0\12\1\1\0\2\2\22\0\10\2"+
    "\1\0\3\2\1\0\51\2\2\0\1\2\20\0\1\2\21\0\2\2"+
    "\4\0\12\1\12\0\6\2\5\0\22\2\3\0\30\2\1\0\11\2"+
    "\1\0\1\2\2\0\7\2\37\0\12\1\21\0\60\2\1\0\2\2"+
    "\14\0\7\2\11\0\12\1\47\0\2\2\1\0\1\2\2\0\2\2"+
    "\1\0\1\2\2\0\1\2\6\0\4\2\1\0\7\2\1\0\3\2"+
    "\1\0\1\2\1\0\1\2\2\0\2\2\1\0\4\2\1\0\2\2"+
    "\11\0\1\2\2\0\5\2\1\0\1\2\11\0\12\1\2\0\4\2"+
    "\40\0\1\2\37\0\12\1\26\0\10\2\1\0\44\2\33\0\5\2"+
    "\163\0\53\2\24\0\1\2\12\1\6\0\6\2\4\0\4\2\3\0"+
    "\1\2\3\0\2\2\7\0\3\2\4\0\15\2\14\0\1\2\1\0"+
    "\12\1\6\0\46\2\1\0\1\2\5\0\1\2\2\0\53\2\1\0"+
    "\u014d\2\1\0\4\2\2\0\7\2\1\0\1\2\1\0\4\2\2\0"+
    "\51\2\1\0\4\2\2\0\41\2\1\0\4\2\2\0\7\2\1\0"+
    "\1\2\1\0\4\2\2\0\17\2\1\0\71\2\1\0\4\2\2\0"+
    "\103\2\45\0\20\2\20\0\125\2\14\0\u026c\2\2\0\21\2\1\0"+
    "\32\2\5\0\113\2\6\0\10\2\7\0\15\2\1\0\4\2\16\0"+
    "\22\2\16\0\22\2\16\0\15\2\1\0\3\2\17\0\64\2\43\0"+
    "\1\2\4\0\1\2\3\0\12\1\46\0\12\1\6\0\130\2\10\0"+
    "\51\2\1\0\1\2\5\0\106\2\12\0\37\2\47\0\12\1\36\2"+
    "\2\0\5\2\13\0\54\2\25\0\7\2\10\0\12\1\46\0\27\2"+
    "\11\0\65\2\53\0\12\1\6\0\12\1\15\0\1\2\135\0\57\2"+
    "\21\0\7\2\4\0\12\1\51\0\36\2\15\0\2\2\12\1\54\2"+
    "\32\0\44\2\34\0\12\1\3\0\3\2\12\1\44\2\153\0\4\2"+
    "\1\0\4\2\3\0\2\2\11\0\300\2\100\0\u0116\2\2\0\6\2"+
    "\2\0\46\2\2\0\6\2\2\0\10\2\1\0\1\2\1\0\1\2"+
    "\1\0\1\2\1\0\37\2\2\0\65\2\1\0\7\2\1\0\1\2"+
    "\3\0\3\2\1\0\7\2\3\0\4\2\2\0\6\2\4\0\15\2"+
    "\5\0\3\2\1\0\7\2\53\0\1\36\1\36\107\0\1\2\15\0"+
    "\1\2\20\0\15\2\145\0\1\2\4\0\1\2\2\0\12\2\1\0"+
    "\1\2\3\0\5\2\6\0\1\2\1\0\1\2\1\0\1\2\1\0"+
    "\4\2\1\0\13\2\2\0\4\2\5\0\5\2\4\0\1\2\64\0"+
    "\2\2\u0a7b\0\57\2\1\0\57\2\1\0\205\2\6\0\4\2\3\0"+
    "\2\2\14\0\46\2\1\0\1\2\5\0\1\2\2\0\70\2\7\0"+
    "\1\2\20\0\27\2\11\0\7\2\1\0\7\2\1\0\7\2\1\0"+
    "\7\2\1\0\7\2\1\0\7\2\1\0\7\2\1\0\7\2\120\0"+
    "\1\2\u01d5\0\2\2\52\0\5\2\5\0\2\2\4\0\126\2\6\0"+
    "\3\2\1\0\132\2\1\0\4\2\5\0\51\2\3\0\136\2\21\0"+
    "\33\2\65\0\20\2\u0200\0\u19b6\2\112\0\u51cd\2\63\0\u048d\2\103\0"+
    "\56\2\2\0\u010d\2\3\0\20\2\12\1\2\2\24\0\57\2\20\0"+
    "\37\2\2\0\106\2\61\0\11\2\2\0\147\2\2\0\4\2\1\0"+
    "\36\2\2\0\2\2\105\0\13\2\1\0\3\2\1\0\4\2\1\0"+
    "\27\2\35\0\64\2\16\0\62\2\34\0\12\1\30\0\6\2\3\0"+
    "\1\2\4\0\12\1\34\2\12\0\27\2\31\0\35\2\7\0\57\2"+
    "\34\0\1\2\12\1\6\0\5\2\1\0\12\2\12\1\5\2\1\0"+
    "\51\2\27\0\3\2\1\0\10\2\4\0\12\1\6\0\27\2\3\0"+
    "\1\2\3\0\62\2\1\0\1\2\3\0\2\2\2\0\5\2\2\0"+
    "\1\2\1\0\1\2\30\0\3\2\2\0\13\2\7\0\3\2\14\0"+
    "\6\2\2\0\6\2\2\0\6\2\11\0\7\2\1\0\7\2\1\0"+
    "\53\2\1\0\4\2\4\0\2\2\132\0\43\2\15\0\12\1\6\0"+
    "\u2ba4\2\14\0\27\2\4\0\61\2\u2104\0\u016e\2\2\0\152\2\46\0"+
    "\7\2\14\0\5\2\5\0\1\2\1\0\12\2\1\0\15\2\1\0"+
    "\5\2\1\0\1\2\1\0\2\2\1\0\2\2\1\0\154\2\41\0"+
    "\u016b\2\22\0\100\2\2\0\66\2\50\0\14\2\164\0\5\2\1\0"+
    "\207\2\23\0\12\1\7\0\32\2\6\0\32\2\13\0\131\2\3\0"+
    "\6\2\2\0\6\2\2\0\6\2\2\0\3\2\43\0\14\2\1\0"+
    "\32\2\1\0\23\2\1\0\2\2\1\0\17\2\2\0\16\2\42\0"+
    "\173\2\u0185\0\35\2\3\0\61\2\57\0\40\2\20\0\21\2\1\0"+
    "\10\2\6\0\46\2\12\0\36\2\2\0\44\2\4\0\10\2\60\0"+
    "\236\2\2\0\12\1\126\0\50\2\10\0\64\2\234\0\u0137\2\11\0"+
    "\26\2\12\0\10\2\230\0\6\2\2\0\1\2\1\0\54\2\1\0"+
    "\2\2\3\0\1\2\2\0\27\2\12\0\27\2\11\0\37\2\141\0"+
    "\26\2\12\0\32\2\106\0\70\2\6\0\2\2\100\0\1\2\17\0"+
    "\4\2\1\0\3\2\1\0\33\2\54\0\35\2\3\0\35\2\43\0"+
    "\10\2\1\0\34\2\33\0\66\2\12\0\26\2\12\0\23\2\15\0"+
    "\22\2\156\0\111\2\u03ba\0\65\2\56\0\12\1\23\0\55\2\40\0"+
    "\31\2\7\0\12\1\11\0\44\2\17\0\12\1\20\0\43\2\3\0"+
    "\1\2\14\0\60\2\16\0\4\2\13\0\12\1\1\2\45\0\22\2"+
    "\1\0\31\2\204\0\57\2\21\0\12\1\13\0\10\2\2\0\2\2"+
    "\2\0\26\2\1\0\7\2\1\0\2\2\1\0\5\2\3\0\1\2"+
    "\37\0\5\2\u011e\0\60\2\24\0\2\2\1\0\1\2\10\0\12\1"+
    "\246\0\57\2\121\0\60\2\24\0\1\2\13\0\12\1\46\0\53\2"+
    "\25\0\12\1\u01d6\0\100\2\12\1\25\0\1\2\u01c0\0\71\2\u0507\0"+
    "\u0399\2\u0c67\0\u042f\2\u33d1\0\u0239\2\7\0\37\2\1\0\12\1\146\0"+
    "\36\2\22\0\60\2\20\0\4\2\14\0\12\1\11\0\25\2\5\0"+
    "\23\2\u0370\0\105\2\13\0\1\2\102\0\15\2\u4060\0\2\2\u0bfe\0"+
    "\153\2\5\0\15\2\3\0\11\2\7\0\12\2\u1766\0\125\2\1\0"+
    "\107\2\1\0\2\2\2\0\1\2\2\0\2\2\2\0\4\2\1\0"+
    "\14\2\1\0\1\2\1\0\7\2\1\0\101\2\1\0\4\2\2\0"+
    "\10\2\1\0\7\2\1\0\34\2\1\0\4\2\1\0\5\2\1\0"+
    "\1\2\3\0\7\2\1\0\u0154\2\2\0\31\2\1\0\31\2\1\0"+
    "\37\2\1\0\31\2\1\0\37\2\1\0\31\2\1\0\37\2\1\0"+
    "\31\2\1\0\37\2\1\0\31\2\1\0\10\2\2\0\62\1\u1000\0"+
    "\305\2\u053b\0\4\2\1\0\33\2\1\0\2\2\1\0\1\2\2\0"+
    "\1\2\1\0\12\2\1\0\4\2\1\0\1\2\1\0\1\2\6\0"+
    "\1\2\4\0\1\2\1\0\1\2\1\0\1\2\1\0\3\2\1\0"+
    "\2\2\1\0\1\2\2\0\1\2\1\0\1\2\1\0\1\2\1\0"+
    "\1\2\1\0\1\2\1\0\2\2\1\0\1\2\2\0\4\2\1\0"+
    "\7\2\1\0\4\2\1\0\4\2\1\0\1\2\1\0\12\2\1\0"+
    "\21\2\5\0\3\2\1\0\5\2\1\0\21\2\u1144\0\ua6d7\2\51\0"+
    "\u1035\2\13\0\336\2\u3fe2\0\u021e\2\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05f0\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\2\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\1\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\0\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\2\33\3\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[45];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\37\0\76\0\135\0\174\0\76\0\233\0\272"+
    "\0\76\0\76\0\76\0\76\0\76\0\76\0\76\0\76"+
    "\0\331\0\370\0\u0117\0\u0136\0\76\0\76\0\76\0\76"+
    "\0\76\0\76\0\76\0\u0155\0\76\0\u0174\0\u0193\0\u01b2"+
    "\0\76\0\76\0\76\0\76\0\76\0\76\0\76\0\76"+
    "\0\76\0\u01d1\0\u01f0\0\u020f\0\u022e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[45];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\7\1\6\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\3\3\5\1\0\3\34\2\0\24\34"+
    "\1\35\1\36\4\34\40\0\1\4\36\0\2\5\30\0"+
    "\3\5\4\0\1\6\41\0\1\37\1\40\47\0\1\41"+
    "\36\0\1\42\36\0\1\43\36\0\1\44\16\0\3\34"+
    "\2\0\24\34\2\0\4\34\31\0\1\45\1\0\1\46"+
    "\1\47\1\50\1\0\3\37\1\51\1\52\32\37\7\53"+
    "\1\54\27\53\3\0\1\51\33\0\7\53\1\55\27\53"+
    "\6\0\1\51\1\54\27\0\6\53\1\51\1\55\27\53";

  private static int [] zzUnpackTrans() {
    int [] result = new int[589];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\2\1\1\11\2\1\10\11\4\1\7\11"+
    "\1\1\1\11\2\1\1\0\11\11\1\1\3\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[45];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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
		return reservedKeywords.containsKey(ident);
	}

	protected Symbol getReservedKeywordToken(String ident) {
		return sf.newSymbol(ident, reservedKeywords.get(ident));
	}

	public int currentLineNumber() {
		return yyline + 1;
	}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2440) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
              {
                return sf.newSymbol("EOF", ParserSym.EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return sf.newSymbol("err", ParserSym.error);
            }
          case 36: break;
          case 2: 
            { return sf.newSymbol("NUM", ParserSym.NUM, new Integer(yytext()));
            }
          case 37: break;
          case 3: 
            { String ident = yytext();
		if (isReservedKeyword(ident)) {
			return getReservedKeywordToken(ident);
		} else {
			return sf.newSymbol("IDENT", ParserSym.IDENT, ident);
		}
            }
          case 38: break;
          case 4: 
            { 
            }
          case 39: break;
          case 5: 
            { return sf.newSymbol("DIV", ParserSym.DIV);
            }
          case 40: break;
          case 6: 
            { return sf.newSymbol("MUL", ParserSym.MUL);
            }
          case 41: break;
          case 7: 
            { return sf.newSymbol("COLON", ParserSym.COLON);
            }
          case 42: break;
          case 8: 
            { return sf.newSymbol("SEMI_COLON", ParserSym.SEMI_COLON);
            }
          case 43: break;
          case 9: 
            { return sf.newSymbol("COMMA", ParserSym.COMMA);
            }
          case 44: break;
          case 10: 
            { return sf.newSymbol("MOD", ParserSym.MOD);
            }
          case 45: break;
          case 11: 
            { return sf.newSymbol("MINUS", ParserSym.MINUS);
            }
          case 46: break;
          case 12: 
            { return sf.newSymbol("PLUS", ParserSym.PLUS);
            }
          case 47: break;
          case 13: 
            { return sf.newSymbol("SHARP", ParserSym.SHARP);
            }
          case 48: break;
          case 14: 
            { return sf.newSymbol("LT", ParserSym.LT);
            }
          case 49: break;
          case 15: 
            { return sf.newSymbol("ASSIGNTO", ParserSym.ASSIGNTO);
            }
          case 50: break;
          case 16: 
            { return sf.newSymbol("GT", ParserSym.GT);
            }
          case 51: break;
          case 17: 
            { return sf.newSymbol("OPEN_PAREN", ParserSym.OPEN_PAREN);
            }
          case 52: break;
          case 18: 
            { return sf.newSymbol("CLOSE_PAREN", ParserSym.CLOSE_PAREN);
            }
          case 53: break;
          case 19: 
            { return sf.newSymbol("OPEN_SQUARE_BRACKET", ParserSym.OPEN_SQUARE_BRACKET);
            }
          case 54: break;
          case 20: 
            { return sf.newSymbol("CLOSE_SQUARE_BRACKET", ParserSym.CLOSE_SQUARE_BRACKET);
            }
          case 55: break;
          case 21: 
            { return sf.newSymbol("OPEN_CURLY_BRACE", ParserSym.OPEN_CURLY_BRACE);
            }
          case 56: break;
          case 22: 
            { return sf.newSymbol("ClOSE_CURLY_BRACE", ParserSym.ClOSE_CURLY_BRACE);
            }
          case 57: break;
          case 23: 
            { stringBuffer.setLength(0); yybegin(STRING);
            }
          case 58: break;
          case 24: 
            { stringBuffer.append(yytext());
            }
          case 59: break;
          case 25: 
            { yybegin(YYINITIAL); 
           			 return sf.newSymbol("STRING", ParserSym.STRING, stringBuffer.toString());
            }
          case 60: break;
          case 26: 
            { stringBuffer.append('\\');
            }
          case 61: break;
          case 27: 
            { /* DO NOTHING (eat the comment) */
            }
          case 62: break;
          case 28: 
            { return sf.newSymbol("LE", ParserSym.LE);
            }
          case 63: break;
          case 29: 
            { return sf.newSymbol("EQ", ParserSym.EQ);
            }
          case 64: break;
          case 30: 
            { return sf.newSymbol("GE", ParserSym.GE);
            }
          case 65: break;
          case 31: 
            { return sf.newSymbol("NE", ParserSym.NE);
            }
          case 66: break;
          case 32: 
            { stringBuffer.append('\"');
            }
          case 67: break;
          case 33: 
            { stringBuffer.append('\t');
            }
          case 68: break;
          case 34: 
            { stringBuffer.append('\n');
            }
          case 69: break;
          case 35: 
            { stringBuffer.append('\r');
            }
          case 70: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
