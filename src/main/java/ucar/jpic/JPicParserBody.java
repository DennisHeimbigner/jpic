/* A Bison parser, made by GNU Bison 3.0.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2013 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

package ucar.jpic;
/* First part of user declarations.  */

/* "JPicParserBody.java":37  */ /* lalr1.java:91  */

/* "JPicParserBody.java":39  */ /* lalr1.java:92  */
/* "%code imports" blocks.  */
/* "jpic.y":38  */ /* lalr1.java:93  */

import static ucar.jpic.Element.*;
import static ucar.jpic.JPicParserBody.Lexer.*;

/* "JPicParserBody.java":46  */ /* lalr1.java:93  */

/**
 * A Bison parser, automatically generated from <tt>jpic.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
abstract class JPicParserBody extends JPicActions
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Return whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }



  /**
   * A class defining a pair of positions.  Positions, defined by the
   * <code>JPicLexer.Pos</code> class, denote a point in the input.
   * Locations represent a part of the input through the beginning
   * and ending positions.
   */
  public class Location {
    /**
     * The first, inclusive, position in the range.
     */
    public JPicLexer.Pos begin;

    /**
     * The first position beyond the range.
     */
    public JPicLexer.Pos end;

    /**
     * Create a <code>Location</code> denoting an empty range located at
     * a given point.
     * @param loc The position at which the range is anchored.
     */
    public Location (JPicLexer.Pos loc) {
      this.begin = this.end = loc;
    }

    /**
     * Create a <code>Location</code> from the endpoints of the range.
     * @param begin The first position included in the range.
     * @param end   The first position beyond the range.
     */
    public Location (JPicLexer.Pos begin, JPicLexer.Pos end) {
      this.begin = begin;
      this.end = end;
    }

    /**
     * Print a representation of the location.  For this to be correct,
     * <code>JPicLexer.Pos</code> should override the <code>equals</code>
     * method.
     */
    public String toString () {
      if (begin.equals (end))
        return begin.toString ();
      else
        return begin.toString () + "-" + end.toString ();
    }
  }



  
  private Location yylloc (YYStack rhs, int n)
  {
    if (n > 0)
      return new Location (rhs.locationAt (n-1).begin, rhs.locationAt (0).end);
    else
      return new Location (rhs.locationAt (0).end);
  }

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>JPicParserBody</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int NAME = 258;
    /** Token number,to be returned by the scanner.  */
    static final int NUMBER = 259;
    /** Token number,to be returned by the scanner.  */
    static final int VARIABLE = 260;
    /** Token number,to be returned by the scanner.  */
    static final int TEXT = 261;
    /** Token number,to be returned by the scanner.  */
    static final int TEXTBOX = 262;
    /** Token number,to be returned by the scanner.  */
    static final int BOX = 263;
    /** Token number,to be returned by the scanner.  */
    static final int CIRCLE = 264;
    /** Token number,to be returned by the scanner.  */
    static final int ELLIPSE = 265;
    /** Token number,to be returned by the scanner.  */
    static final int ARC = 266;
    /** Token number,to be returned by the scanner.  */
    static final int LINE = 267;
    /** Token number,to be returned by the scanner.  */
    static final int ARROW = 268;
    /** Token number,to be returned by the scanner.  */
    static final int SPLINE = 269;
    /** Token number,to be returned by the scanner.  */
    static final int MOVE = 270;
    /** Token number,to be returned by the scanner.  */
    static final int UP = 271;
    /** Token number,to be returned by the scanner.  */
    static final int DOWN = 272;
    /** Token number,to be returned by the scanner.  */
    static final int RIGHT = 273;
    /** Token number,to be returned by the scanner.  */
    static final int LEFT = 274;
    /** Token number,to be returned by the scanner.  */
    static final int FOR = 275;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 276;
    /** Token number,to be returned by the scanner.  */
    static final int COLORED = 277;
    /** Token number,to be returned by the scanner.  */
    static final int OUTLINED = 278;
    /** Token number,to be returned by the scanner.  */
    static final int SHADED = 279;
    /** Token number,to be returned by the scanner.  */
    static final int XSLANTED = 280;
    /** Token number,to be returned by the scanner.  */
    static final int YSLANTED = 281;
    /** Token number,to be returned by the scanner.  */
    static final int ALIGNED = 282;
    /** Token number,to be returned by the scanner.  */
    static final int CW = 283;
    /** Token number,to be returned by the scanner.  */
    static final int CCW = 284;
    /** Token number,to be returned by the scanner.  */
    static final int LJUST = 285;
    /** Token number,to be returned by the scanner.  */
    static final int RJUST = 286;
    /** Token number,to be returned by the scanner.  */
    static final int LEFT_ARROW_HEAD = 287;
    /** Token number,to be returned by the scanner.  */
    static final int RIGHT_ARROW_HEAD = 288;
    /** Token number,to be returned by the scanner.  */
    static final int DOUBLE_ARROW_HEAD = 289;
    /** Token number,to be returned by the scanner.  */
    static final int ANDAND = 290;
    /** Token number,to be returned by the scanner.  */
    static final int OROR = 291;
    /** Token number,to be returned by the scanner.  */
    static final int NOTEQUAL = 292;
    /** Token number,to be returned by the scanner.  */
    static final int EQUALEQUAL = 293;
    /** Token number,to be returned by the scanner.  */
    static final int LESSEQUAL = 294;
    /** Token number,to be returned by the scanner.  */
    static final int GREATEREQUAL = 295;
    /** Token number,to be returned by the scanner.  */
    static final int EOL = 296;
    /** Token number,to be returned by the scanner.  */
    static final int BY = 297;
    /** Token number,to be returned by the scanner.  */
    static final int THEN = 298;
    /** Token number,to be returned by the scanner.  */
    static final int DO = 299;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 300;
    /** Token number,to be returned by the scanner.  */
    static final int FROM = 301;
    /** Token number,to be returned by the scanner.  */
    static final int TO = 302;
    /** Token number,to be returned by the scanner.  */
    static final int AT = 303;
    /** Token number,to be returned by the scanner.  */
    static final int WITH = 304;
    /** Token number,to be returned by the scanner.  */
    static final int LANGLES = 305;
    /** Token number,to be returned by the scanner.  */
    static final int RANGLES = 306;
    /** Token number,to be returned by the scanner.  */
    static final int ASSIGN = 307;
    /** Token number,to be returned by the scanner.  */
    static final int DEFINE = 308;
    /** Token number,to be returned by the scanner.  */
    static final int HEIGHT = 309;
    /** Token number,to be returned by the scanner.  */
    static final int RADIUS = 310;
    /** Token number,to be returned by the scanner.  */
    static final int WIDTH = 311;
    /** Token number,to be returned by the scanner.  */
    static final int DIAMETER = 312;
    /** Token number,to be returned by the scanner.  */
    static final int SOLID = 313;
    /** Token number,to be returned by the scanner.  */
    static final int DOTTED = 314;
    /** Token number,to be returned by the scanner.  */
    static final int DASHED = 315;
    /** Token number,to be returned by the scanner.  */
    static final int CHOP = 316;
    /** Token number,to be returned by the scanner.  */
    static final int SAME = 317;
    /** Token number,to be returned by the scanner.  */
    static final int INVISIBLE = 318;
    /** Token number,to be returned by the scanner.  */
    static final int ABOVE = 319;
    /** Token number,to be returned by the scanner.  */
    static final int BELOW = 320;
    /** Token number,to be returned by the scanner.  */
    static final int LAST = 321;
    /** Token number,to be returned by the scanner.  */
    static final int HERE = 322;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_N = 323;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_E = 324;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_W = 325;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_S = 326;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_NE = 327;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_SE = 328;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_NW = 329;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_SW = 330;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_C = 331;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_START = 332;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_END = 333;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_X = 334;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_Y = 335;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_HT = 336;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_WID = 337;
    /** Token number,to be returned by the scanner.  */
    static final int DOT_RAD = 338;
    /** Token number,to be returned by the scanner.  */
    static final int NEGATE = 339;
    /** Token number,to be returned by the scanner.  */
    static final int SIN = 340;
    /** Token number,to be returned by the scanner.  */
    static final int COS = 341;
    /** Token number,to be returned by the scanner.  */
    static final int ATAN2 = 342;
    /** Token number,to be returned by the scanner.  */
    static final int LOG = 343;
    /** Token number,to be returned by the scanner.  */
    static final int EXP = 344;
    /** Token number,to be returned by the scanner.  */
    static final int SQRT = 345;
    /** Token number,to be returned by the scanner.  */
    static final int K_MAX = 346;
    /** Token number,to be returned by the scanner.  */
    static final int K_MIN = 347;
    /** Token number,to be returned by the scanner.  */
    static final int INT = 348;
    /** Token number,to be returned by the scanner.  */
    static final int RAND = 349;
    /** Token number,to be returned by the scanner.  */
    static final int SRAND = 350;
    /** Token number,to be returned by the scanner.  */
    static final int TOP = 351;
    /** Token number,to be returned by the scanner.  */
    static final int BOTTOM = 352;
    /** Token number,to be returned by the scanner.  */
    static final int UPPER = 353;
    /** Token number,to be returned by the scanner.  */
    static final int LOWER = 354;
    /** Token number,to be returned by the scanner.  */
    static final int LEFT_CORNER = 355;
    /** Token number,to be returned by the scanner.  */
    static final int RIGHT_CORNER = 356;
    /** Token number,to be returned by the scanner.  */
    static final int NORTH = 357;
    /** Token number,to be returned by the scanner.  */
    static final int SOUTH = 358;
    /** Token number,to be returned by the scanner.  */
    static final int EAST = 359;
    /** Token number,to be returned by the scanner.  */
    static final int WEST = 360;
    /** Token number,to be returned by the scanner.  */
    static final int CENTER = 361;
    /** Token number,to be returned by the scanner.  */
    static final int END = 362;
    /** Token number,to be returned by the scanner.  */
    static final int START = 363;
    /** Token number,to be returned by the scanner.  */
    static final int THICKNESS = 364;
    /** Token number,to be returned by the scanner.  */
    static final int FILL = 365;
    /** Token number,to be returned by the scanner.  */
    static final int UNDEF = 366;


    /**
     * Method to retrieve the beginning position of the last scanned token.
     * @return the position at which the last scanned token starts.
     */
    JPicLexer.Pos getStartPos ();

    /**
     * Method to retrieve the ending position of the last scanned token.
     * @return the first position beyond the last scanned token.
     */
    JPicLexer.Pos getEndPos ();

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * and beginning/ending positions of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws Exception;

    /**
     * Entry point for error reporting.  Emits an error
     * referring to the given location in a user-defined way.
     *
     * @param loc The location of the element to which the
     *                error message is related
     * @param msg The string for the error message.
     */
     void yyerror (Location loc, String msg);
  }

  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public JPicParserBody (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  /**
   * Print an error message via the lexer.
   * Use a <code>null</code> location.
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror ((Location)null, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param loc The location associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Location loc, String msg)
  {
    yylexer.yyerror (loc, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param pos The position associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (JPicLexer.Pos pos, String msg)
  {
    yylexer.yyerror (new Location (pos), msg);
  }

  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private Location[] locStack = new Location[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            , Location loc) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          
          Location[] newLocStack = new Location[size * 2];
          System.arraycopy (locStack, 0, newLocStack, 0, height);
          locStack = newLocStack;

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      locStack[height] = loc;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        java.util.Arrays.fill (locStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Location locationAt (int i) {
      return locStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out)
    {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) throws Exception
  {
    Object yyval;
    Location yyloc = yylloc (yystack, yylen);

    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "jpic.y":228  */ /* lalr1.java:476  */
    {picture(null);};
  break;
    

  case 3:
  if (yyn == 3)
    /* "jpic.y":230  */ /* lalr1.java:476  */
    {picture(((ElementList)(yystack.valueAt (1-(1)))));};
  break;
    

  case 4:
  if (yyn == 4)
    /* "jpic.y":235  */ /* lalr1.java:476  */
    {yyval=((ShapeSpec)(yystack.valueAt (1-(1))));};
  break;
    

  case 5:
  if (yyn == 5)
    /* "jpic.y":237  */ /* lalr1.java:476  */
    {yyval=((Element)(yystack.valueAt (1-(1))));};
  break;
    

  case 6:
  if (yyn == 6)
    /* "jpic.y":239  */ /* lalr1.java:476  */
    {setlabel(((Element)(yystack.valueAt (3-(3)))),((String)(yystack.valueAt (3-(1))))); yyval=((String)(yystack.valueAt (3-(1))));};
  break;
    

  case 7:
  if (yyn == 7)
    /* "jpic.y":241  */ /* lalr1.java:476  */
    {setlabel(((Pair)(yystack.valueAt (3-(3)))),((String)(yystack.valueAt (3-(1))))); yyval=((String)(yystack.valueAt (3-(1))));};
  break;
    

  case 8:
  if (yyn == 8)
    /* "jpic.y":243  */ /* lalr1.java:476  */
    {setlabel(((Place)(yystack.valueAt (3-(3)))),((String)(yystack.valueAt (3-(1))))); yyval=((String)(yystack.valueAt (3-(1))));};
  break;
    

  case 9:
  if (yyn == 9)
    /* "jpic.y":248  */ /* lalr1.java:476  */
    {yyval=element_list(null,((Element)(yystack.valueAt (1-(1)))));};
  break;
    

  case 10:
  if (yyn == 10)
    /* "jpic.y":250  */ /* lalr1.java:476  */
    {yyval=element_list(((ElementList)(yystack.valueAt (3-(1)))),((Element)(yystack.valueAt (3-(3)))));};
  break;
    

  case 11:
  if (yyn == 11)
    /* "jpic.y":254  */ /* lalr1.java:476  */
    {yyval=null;};
  break;
    

  case 12:
  if (yyn == 12)
    /* "jpic.y":255  */ /* lalr1.java:476  */
    {yyval=null;};
  break;
    

  case 13:
  if (yyn == 13)
    /* "jpic.y":256  */ /* lalr1.java:476  */
    {yyval=((Element)(yystack.valueAt (1-(1))));};
  break;
    

  case 14:
  if (yyn == 14)
    /* "jpic.y":257  */ /* lalr1.java:476  */
    {yyval=((Direction)(yystack.valueAt (1-(1))));};
  break;
    

  case 15:
  if (yyn == 15)
    /* "jpic.y":258  */ /* lalr1.java:476  */
    {yyval=((Element)(yystack.valueAt (1-(1))));};
  break;
    

  case 16:
  if (yyn == 16)
    /* "jpic.y":259  */ /* lalr1.java:476  */
    {yyval=((Element)(yystack.valueAt (1-(1))));};
  break;
    

  case 17:
  if (yyn == 17)
    /* "jpic.y":260  */ /* lalr1.java:476  */
    {yyval=((Element)(yystack.valueAt (1-(1))));};
  break;
    

  case 18:
  if (yyn == 18)
    /* "jpic.y":261  */ /* lalr1.java:476  */
    {yyval=((Element)(yystack.valueAt (1-(1))));};
  break;
    

  case 19:
  if (yyn == 19)
    /* "jpic.y":271  */ /* lalr1.java:476  */
    {macro_define();};
  break;
    

  case 20:
  if (yyn == 20)
    /* "jpic.y":276  */ /* lalr1.java:476  */
    {yyval=undef(((String)(yystack.valueAt (2-(2)))));};
  break;
    

  case 21:
  if (yyn == 21)
    /* "jpic.y":281  */ /* lalr1.java:476  */
    {yyval=move(((PropertyList)(yystack.valueAt (2-(2)))));};
  break;
    

  case 22:
  if (yyn == 22)
    /* "jpic.y":286  */ /* lalr1.java:476  */
    {yyval=assignment(((String)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))));};
  break;
    

  case 23:
  if (yyn == 23)
    /* "jpic.y":290  */ /* lalr1.java:476  */
    {yyval=current_direction(UP);};
  break;
    

  case 24:
  if (yyn == 24)
    /* "jpic.y":291  */ /* lalr1.java:476  */
    {yyval=current_direction(DOWN);};
  break;
    

  case 25:
  if (yyn == 25)
    /* "jpic.y":292  */ /* lalr1.java:476  */
    {yyval=current_direction(LEFT);};
  break;
    

  case 26:
  if (yyn == 26)
    /* "jpic.y":293  */ /* lalr1.java:476  */
    {yyval=current_direction(RIGHT);};
  break;
    

  case 27:
  if (yyn == 27)
    /* "jpic.y":298  */ /* lalr1.java:476  */
    {yyval=forloop(((String)(yystack.valueAt (9-(2)))),((Expr)(yystack.valueAt (9-(4)))),((Expr)(yystack.valueAt (9-(6)))),((ByExpr)(yystack.valueAt (9-(7)))),((ElementList)(yystack.valueAt (9-(9)))));};
  break;
    

  case 28:
  if (yyn == 28)
    /* "jpic.y":302  */ /* lalr1.java:476  */
    {yyval=null;};
  break;
    

  case 29:
  if (yyn == 29)
    /* "jpic.y":304  */ /* lalr1.java:476  */
    {yyval=by('\0',((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 30:
  if (yyn == 30)
    /* "jpic.y":306  */ /* lalr1.java:476  */
    {yyval=by('*',((Expr)(yystack.valueAt (3-(3)))));};
  break;
    

  case 31:
  if (yyn == 31)
    /* "jpic.y":311  */ /* lalr1.java:476  */
    {yyval=conditional(((Expr)(yystack.valueAt (3-(2)))),((ElementList)(yystack.valueAt (3-(3)))),null);};
  break;
    

  case 32:
  if (yyn == 32)
    /* "jpic.y":313  */ /* lalr1.java:476  */
    {yyval=conditional(((Expr)(yystack.valueAt (5-(2)))),((ElementList)(yystack.valueAt (5-(3)))),((ElementList)(yystack.valueAt (5-(5)))));};
  break;
    

  case 33:
  if (yyn == 33)
    /* "jpic.y":317  */ /* lalr1.java:476  */
    {yyval=((ElementList)(yystack.valueAt (3-(2))));};
  break;
    

  case 34:
  if (yyn == 34)
    /* "jpic.y":322  */ /* lalr1.java:476  */
    {yyval=compound(((ElementList)(yystack.valueAt (3-(2)))));};
  break;
    

  case 35:
  if (yyn == 35)
    /* "jpic.y":326  */ /* lalr1.java:476  */
    {yyval=shape_spec(((ShapeType)(yystack.valueAt (2-(1)))),((PropertyList)(yystack.valueAt (2-(2)))));};
  break;
    

  case 36:
  if (yyn == 36)
    /* "jpic.y":328  */ /* lalr1.java:476  */
    {yyval = ((ElementList)(yystack.valueAt (1-(1))));};
  break;
    

  case 37:
  if (yyn == 37)
    /* "jpic.y":333  */ /* lalr1.java:476  */
    {yyval = ShapeType.BOX;};
  break;
    

  case 38:
  if (yyn == 38)
    /* "jpic.y":335  */ /* lalr1.java:476  */
    {yyval = ShapeType.CIRCLE;};
  break;
    

  case 39:
  if (yyn == 39)
    /* "jpic.y":337  */ /* lalr1.java:476  */
    {yyval = ShapeType.ELLIPSE;};
  break;
    

  case 40:
  if (yyn == 40)
    /* "jpic.y":339  */ /* lalr1.java:476  */
    {yyval = ShapeType.ARC;};
  break;
    

  case 41:
  if (yyn == 41)
    /* "jpic.y":341  */ /* lalr1.java:476  */
    {yyval = ShapeType.LINE;};
  break;
    

  case 42:
  if (yyn == 42)
    /* "jpic.y":343  */ /* lalr1.java:476  */
    {yyval = ShapeType.ARROW;};
  break;
    

  case 43:
  if (yyn == 43)
    /* "jpic.y":345  */ /* lalr1.java:476  */
    {yyval = ShapeType.SPLINE;};
  break;
    

  case 44:
  if (yyn == 44)
    /* "jpic.y":347  */ /* lalr1.java:476  */
    {yyval = ShapeType.TEXTBOX;};
  break;
    

  case 45:
  if (yyn == 45)
    /* "jpic.y":352  */ /* lalr1.java:476  */
    {yyval=properties_list(null,null);};
  break;
    

  case 46:
  if (yyn == 46)
    /* "jpic.y":354  */ /* lalr1.java:476  */
    {yyval=properties_list(((PropertyList)(yystack.valueAt (2-(1)))),((Property)(yystack.valueAt (2-(2)))));};
  break;
    

  case 47:
  if (yyn == 47)
    /* "jpic.y":359  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.HEIGHT,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 48:
  if (yyn == 48)
    /* "jpic.y":361  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.RADIUS,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 49:
  if (yyn == 49)
    /* "jpic.y":363  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.WIDTH,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 50:
  if (yyn == 50)
    /* "jpic.y":365  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.DIAMETER,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 51:
  if (yyn == 51)
    /* "jpic.y":367  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.FROM,((Position)(yystack.valueAt (2-(2)))));};
  break;
    

  case 52:
  if (yyn == 52)
    /* "jpic.y":369  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.TO,((Position)(yystack.valueAt (2-(2)))));};
  break;
    

  case 53:
  if (yyn == 53)
    /* "jpic.y":371  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.AT,((Position)(yystack.valueAt (2-(2)))));};
  break;
    

  case 54:
  if (yyn == 54)
    /* "jpic.y":373  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.WITH,((Path)(yystack.valueAt (2-(2)))));};
  break;
    

  case 55:
  if (yyn == 55)
    /* "jpic.y":375  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.WITH,((Position)(yystack.valueAt (2-(2)))));};
  break;
    

  case 56:
  if (yyn == 56)
    /* "jpic.y":377  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.BY,((Pair)(yystack.valueAt (2-(2)))));};
  break;
    

  case 57:
  if (yyn == 57)
    /* "jpic.y":379  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.THEN,null);};
  break;
    

  case 58:
  if (yyn == 58)
    /* "jpic.y":381  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.SOLID,null);};
  break;
    

  case 59:
  if (yyn == 59)
    /* "jpic.y":383  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.DOTTED,null);};
  break;
    

  case 60:
  if (yyn == 60)
    /* "jpic.y":385  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.DOTTED,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 61:
  if (yyn == 61)
    /* "jpic.y":387  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.DASHED,null);};
  break;
    

  case 62:
  if (yyn == 62)
    /* "jpic.y":389  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.DASHED,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 63:
  if (yyn == 63)
    /* "jpic.y":391  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.FILL,null);};
  break;
    

  case 64:
  if (yyn == 64)
    /* "jpic.y":393  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.FILL,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 65:
  if (yyn == 65)
    /* "jpic.y":395  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.XSLANTED,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 66:
  if (yyn == 66)
    /* "jpic.y":397  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.YSLANTED,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 67:
  if (yyn == 67)
    /* "jpic.y":399  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.SHADED,((String)(yystack.valueAt (2-(2)))));};
  break;
    

  case 68:
  if (yyn == 68)
    /* "jpic.y":401  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.COLORED,((String)(yystack.valueAt (2-(2)))));};
  break;
    

  case 69:
  if (yyn == 69)
    /* "jpic.y":403  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.OUTLINED,((String)(yystack.valueAt (2-(2)))));};
  break;
    

  case 70:
  if (yyn == 70)
    /* "jpic.y":405  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.CHOP,null);};
  break;
    

  case 71:
  if (yyn == 71)
    /* "jpic.y":407  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.CHOP,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 72:
  if (yyn == 72)
    /* "jpic.y":409  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.SAME,null);};
  break;
    

  case 73:
  if (yyn == 73)
    /* "jpic.y":411  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.INVISIBLE,null);};
  break;
    

  case 74:
  if (yyn == 74)
    /* "jpic.y":413  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.LEFT_ARROW_HEAD,null);};
  break;
    

  case 75:
  if (yyn == 75)
    /* "jpic.y":415  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.RIGHT_ARROW_HEAD,null);};
  break;
    

  case 76:
  if (yyn == 76)
    /* "jpic.y":417  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.DOUBLE_ARROW_HEAD,null);};
  break;
    

  case 77:
  if (yyn == 77)
    /* "jpic.y":419  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.CW,null);};
  break;
    

  case 78:
  if (yyn == 78)
    /* "jpic.y":421  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.CCW,null);};
  break;
    

  case 79:
  if (yyn == 79)
    /* "jpic.y":423  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.LJUST,null);};
  break;
    

  case 80:
  if (yyn == 80)
    /* "jpic.y":425  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.RJUST,null);};
  break;
    

  case 81:
  if (yyn == 81)
    /* "jpic.y":427  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.ABOVE,null);};
  break;
    

  case 82:
  if (yyn == 82)
    /* "jpic.y":429  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.BELOW,null);};
  break;
    

  case 83:
  if (yyn == 83)
    /* "jpic.y":431  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.THICKNESS,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 84:
  if (yyn == 84)
    /* "jpic.y":433  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.ALIGNED,null);};
  break;
    

  case 85:
  if (yyn == 85)
    /* "jpic.y":435  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.TEXT,((String)(yystack.valueAt (1-(1)))));};
  break;
    

  case 86:
  if (yyn == 86)
    /* "jpic.y":437  */ /* lalr1.java:476  */
    {yyval=property(PropertyType.EXPR,((Expr)(yystack.valueAt (2-(2)))));};
  break;
    

  case 87:
  if (yyn == 87)
    /* "jpic.y":442  */ /* lalr1.java:476  */
    {yyval=((Pair)(yystack.valueAt (1-(1))));};
  break;
    

  case 88:
  if (yyn == 88)
    /* "jpic.y":444  */ /* lalr1.java:476  */
    {yyval=((Place)(yystack.valueAt (1-(1))));};
  break;
    

  case 89:
  if (yyn == 89)
    /* "jpic.y":446  */ /* lalr1.java:476  */
    {yyval=((Place)(yystack.valueAt (3-(2))));};
  break;
    

  case 90:
  if (yyn == 90)
    /* "jpic.y":451  */ /* lalr1.java:476  */
    {yyval = ((Pair)(yystack.valueAt (1-(1))));};
  break;
    

  case 91:
  if (yyn == 91)
    /* "jpic.y":453  */ /* lalr1.java:476  */
    {yyval=position_expr(((Position)(yystack.valueAt (3-(1)))),((Pair)(yystack.valueAt (3-(3)))),'+');};
  break;
    

  case 92:
  if (yyn == 92)
    /* "jpic.y":455  */ /* lalr1.java:476  */
    {yyval = position_expr(((Position)(yystack.valueAt (5-(2)))),((Pair)(yystack.valueAt (5-(4)))),'+');};
  break;
    

  case 93:
  if (yyn == 93)
    /* "jpic.y":457  */ /* lalr1.java:476  */
    {yyval = position_expr(((Position)(yystack.valueAt (3-(1)))),((Pair)(yystack.valueAt (3-(3)))),'-');};
  break;
    

  case 94:
  if (yyn == 94)
    /* "jpic.y":459  */ /* lalr1.java:476  */
    {yyval = position_expr(((Position)(yystack.valueAt (5-(2)))),((Pair)(yystack.valueAt (5-(4)))),'-');};
  break;
    

  case 95:
  if (yyn == 95)
    /* "jpic.y":461  */ /* lalr1.java:476  */
    {yyval=pair(((Position)(yystack.valueAt (5-(2)))),((Position)(yystack.valueAt (5-(4)))));};
  break;
    

  case 96:
  if (yyn == 96)
    /* "jpic.y":463  */ /* lalr1.java:476  */
    {yyval=between(((Expr)(yystack.valueAt (6-(1)))),((Position)(yystack.valueAt (6-(3)))),((Position)(yystack.valueAt (6-(5)))));};
  break;
    

  case 97:
  if (yyn == 97)
    /* "jpic.y":465  */ /* lalr1.java:476  */
    {yyval=between(((Expr)(yystack.valueAt (8-(2)))),((Position)(yystack.valueAt (8-(4)))),((Position)(yystack.valueAt (8-(6)))));};
  break;
    

  case 98:
  if (yyn == 98)
    /* "jpic.y":470  */ /* lalr1.java:476  */
    {yyval=expr_pair(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))));};
  break;
    

  case 99:
  if (yyn == 99)
    /* "jpic.y":472  */ /* lalr1.java:476  */
    {yyval = ((Pair)(yystack.valueAt (3-(2))));};
  break;
    

  case 100:
  if (yyn == 100)
    /* "jpic.y":478  */ /* lalr1.java:476  */
    {yyval = place(((PathList)(yystack.valueAt (1-(1)))),null);};
  break;
    

  case 101:
  if (yyn == 101)
    /* "jpic.y":480  */ /* lalr1.java:476  */
    {yyval=place(((PathList)(yystack.valueAt (3-(1)))),((Corner)(yystack.valueAt (3-(3)))));};
  break;
    

  case 102:
  if (yyn == 102)
    /* "jpic.y":482  */ /* lalr1.java:476  */
    {yyval=here();};
  break;
    

  case 103:
  if (yyn == 103)
    /* "jpic.y":484  */ /* lalr1.java:476  */
    {yyval=last();};
  break;
    

  case 104:
  if (yyn == 104)
    /* "jpic.y":489  */ /* lalr1.java:476  */
    {yyval=label_path(((String)(yystack.valueAt (1-(1)))),null);};
  break;
    

  case 105:
  if (yyn == 105)
    /* "jpic.y":491  */ /* lalr1.java:476  */
    {yyval=label_path(((String)(yystack.valueAt (3-(3)))),((PathList)(yystack.valueAt (3-(1)))));};
  break;
    

  case 106:
  if (yyn == 106)
    /* "jpic.y":496  */ /* lalr1.java:476  */
    {yyval = relative_path(((Corner)(yystack.valueAt (2-(2)))),null);};
  break;
    

  case 107:
  if (yyn == 107)
    /* "jpic.y":500  */ /* lalr1.java:476  */
    {yyval = relative_path(null,((PathList)(yystack.valueAt (2-(2)))));};
  break;
    

  case 108:
  if (yyn == 108)
    /* "jpic.y":502  */ /* lalr1.java:476  */
    {yyval=relative_path(((Corner)(yystack.valueAt (4-(4)))),((PathList)(yystack.valueAt (4-(2)))));};
  break;
    

  case 109:
  if (yyn == 109)
    /* "jpic.y":507  */ /* lalr1.java:476  */
    {yyval = ((Path)(yystack.valueAt (1-(1))));};
  break;
    

  case 110:
  if (yyn == 110)
    /* "jpic.y":509  */ /* lalr1.java:476  */
    {yyval=pathpair(((Path)(yystack.valueAt (5-(2)))),((Path)(yystack.valueAt (5-(4)))));};
  break;
    

  case 111:
  if (yyn == 111)
    /* "jpic.y":514  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH;};
  break;
    

  case 112:
  if (yyn == 112)
    /* "jpic.y":516  */ /* lalr1.java:476  */
    {yyval = CornerType.EAST;};
  break;
    

  case 113:
  if (yyn == 113)
    /* "jpic.y":518  */ /* lalr1.java:476  */
    {yyval = CornerType.WEST;};
  break;
    

  case 114:
  if (yyn == 114)
    /* "jpic.y":520  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH;};
  break;
    

  case 115:
  if (yyn == 115)
    /* "jpic.y":522  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH_EAST;};
  break;
    

  case 116:
  if (yyn == 116)
    /* "jpic.y":524  */ /* lalr1.java:476  */
    {yyval = CornerType. SOUTH_EAST;};
  break;
    

  case 117:
  if (yyn == 117)
    /* "jpic.y":526  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH_WEST;};
  break;
    

  case 118:
  if (yyn == 118)
    /* "jpic.y":528  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH_WEST;};
  break;
    

  case 119:
  if (yyn == 119)
    /* "jpic.y":530  */ /* lalr1.java:476  */
    {yyval = CornerType.CENTER;};
  break;
    

  case 120:
  if (yyn == 120)
    /* "jpic.y":532  */ /* lalr1.java:476  */
    {yyval = CornerType.START;};
  break;
    

  case 121:
  if (yyn == 121)
    /* "jpic.y":534  */ /* lalr1.java:476  */
    {yyval = CornerType.END;};
  break;
    

  case 122:
  if (yyn == 122)
    /* "jpic.y":536  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH;};
  break;
    

  case 123:
  if (yyn == 123)
    /* "jpic.y":538  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH;};
  break;
    

  case 124:
  if (yyn == 124)
    /* "jpic.y":540  */ /* lalr1.java:476  */
    {yyval = CornerType.WEST;};
  break;
    

  case 125:
  if (yyn == 125)
    /* "jpic.y":542  */ /* lalr1.java:476  */
    {yyval = CornerType.EAST;};
  break;
    

  case 126:
  if (yyn == 126)
    /* "jpic.y":544  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH_WEST;};
  break;
    

  case 127:
  if (yyn == 127)
    /* "jpic.y":546  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH_WEST;};
  break;
    

  case 128:
  if (yyn == 128)
    /* "jpic.y":548  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH_EAST;};
  break;
    

  case 129:
  if (yyn == 129)
    /* "jpic.y":550  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH_EAST;};
  break;
    

  case 130:
  if (yyn == 130)
    /* "jpic.y":552  */ /* lalr1.java:476  */
    {yyval = CornerType.WEST;};
  break;
    

  case 131:
  if (yyn == 131)
    /* "jpic.y":554  */ /* lalr1.java:476  */
    {yyval = CornerType.EAST;};
  break;
    

  case 132:
  if (yyn == 132)
    /* "jpic.y":556  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH_WEST;};
  break;
    

  case 133:
  if (yyn == 133)
    /* "jpic.y":558  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH_WEST;};
  break;
    

  case 134:
  if (yyn == 134)
    /* "jpic.y":560  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH_EAST;};
  break;
    

  case 135:
  if (yyn == 135)
    /* "jpic.y":562  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH_EAST;};
  break;
    

  case 136:
  if (yyn == 136)
    /* "jpic.y":564  */ /* lalr1.java:476  */
    {yyval = CornerType.NORTH;};
  break;
    

  case 137:
  if (yyn == 137)
    /* "jpic.y":566  */ /* lalr1.java:476  */
    {yyval = CornerType.SOUTH;};
  break;
    

  case 138:
  if (yyn == 138)
    /* "jpic.y":568  */ /* lalr1.java:476  */
    {yyval = CornerType.EAST;};
  break;
    

  case 139:
  if (yyn == 139)
    /* "jpic.y":570  */ /* lalr1.java:476  */
    {yyval = CornerType.WEST;};
  break;
    

  case 140:
  if (yyn == 140)
    /* "jpic.y":572  */ /* lalr1.java:476  */
    {yyval = CornerType.CENTER;};
  break;
    

  case 141:
  if (yyn == 141)
    /* "jpic.y":574  */ /* lalr1.java:476  */
    {yyval = CornerType.START;};
  break;
    

  case 142:
  if (yyn == 142)
    /* "jpic.y":576  */ /* lalr1.java:476  */
    {yyval = CornerType.END;};
  break;
    

  case 143:
  if (yyn == 143)
    /* "jpic.y":589  */ /* lalr1.java:476  */
    {yyval=((Expr)(yystack.valueAt (1-(1))));};
  break;
    

  case 144:
  if (yyn == 144)
    /* "jpic.y":591  */ /* lalr1.java:476  */
    {yyval=((Expr)(yystack.valueAt (1-(1))));};
  break;
    

  case 145:
  if (yyn == 145)
    /* "jpic.y":596  */ /* lalr1.java:476  */
    {yyval = text(((String)(yystack.valueAt (1-(1)))));};
  break;
    

  case 146:
  if (yyn == 146)
    /* "jpic.y":598  */ /* lalr1.java:476  */
    {yyval = compute(text(((String)(yystack.valueAt (3-(1))))),text(((String)(yystack.valueAt (3-(3))))),EQUALEQUAL);};
  break;
    

  case 147:
  if (yyn == 147)
    /* "jpic.y":600  */ /* lalr1.java:476  */
    {yyval = compute(text(((String)(yystack.valueAt (3-(1))))),text(((String)(yystack.valueAt (3-(3))))),NOTEQUAL);};
  break;
    

  case 148:
  if (yyn == 148)
    /* "jpic.y":605  */ /* lalr1.java:476  */
    {yyval=var(((String)(yystack.valueAt (1-(1)))));};
  break;
    

  case 149:
  if (yyn == 149)
    /* "jpic.y":607  */ /* lalr1.java:476  */
    {yyval = number(((Number)(yystack.valueAt (1-(1)))));};
  break;
    

  case 150:
  if (yyn == 150)
    /* "jpic.y":609  */ /* lalr1.java:476  */
    {yyval=expr_position(((Place)(yystack.valueAt (2-(1)))),DOT_X);};
  break;
    

  case 151:
  if (yyn == 151)
    /* "jpic.y":611  */ /* lalr1.java:476  */
    {yyval=expr_position(((Place)(yystack.valueAt (2-(1)))),DOT_Y);};
  break;
    

  case 152:
  if (yyn == 152)
    /* "jpic.y":613  */ /* lalr1.java:476  */
    {yyval=expr_position(((Place)(yystack.valueAt (2-(1)))),DOT_HT);};
  break;
    

  case 153:
  if (yyn == 153)
    /* "jpic.y":615  */ /* lalr1.java:476  */
    {yyval=expr_position(((Place)(yystack.valueAt (2-(1)))),DOT_WID);};
  break;
    

  case 154:
  if (yyn == 154)
    /* "jpic.y":617  */ /* lalr1.java:476  */
    {yyval=expr_position(((Place)(yystack.valueAt (2-(1)))),DOT_RAD);};
  break;
    

  case 155:
  if (yyn == 155)
    /* "jpic.y":619  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'+');};
  break;
    

  case 156:
  if (yyn == 156)
    /* "jpic.y":621  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'-');};
  break;
    

  case 157:
  if (yyn == 157)
    /* "jpic.y":623  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'*');};
  break;
    

  case 158:
  if (yyn == 158)
    /* "jpic.y":625  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'/');};
  break;
    

  case 159:
  if (yyn == 159)
    /* "jpic.y":627  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'%');};
  break;
    

  case 160:
  if (yyn == 160)
    /* "jpic.y":629  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'^');};
  break;
    

  case 161:
  if (yyn == 161)
    /* "jpic.y":631  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (2-(2)))),null,NEGATE);};
  break;
    

  case 162:
  if (yyn == 162)
    /* "jpic.y":633  */ /* lalr1.java:476  */
    {yyval = ((Expr)(yystack.valueAt (3-(2))));};
  break;
    

  case 163:
  if (yyn == 163)
    /* "jpic.y":635  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,SIN);};
  break;
    

  case 164:
  if (yyn == 164)
    /* "jpic.y":637  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,COS);};
  break;
    

  case 165:
  if (yyn == 165)
    /* "jpic.y":639  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (6-(3)))),((Expr)(yystack.valueAt (6-(5)))),ATAN2);};
  break;
    

  case 166:
  if (yyn == 166)
    /* "jpic.y":641  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,LOG);};
  break;
    

  case 167:
  if (yyn == 167)
    /* "jpic.y":643  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,'^');};
  break;
    

  case 168:
  if (yyn == 168)
    /* "jpic.y":645  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,SQRT);};
  break;
    

  case 169:
  if (yyn == 169)
    /* "jpic.y":647  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (6-(3)))),((Expr)(yystack.valueAt (6-(5)))),K_MAX);};
  break;
    

  case 170:
  if (yyn == 170)
    /* "jpic.y":649  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (6-(3)))),((Expr)(yystack.valueAt (6-(5)))),K_MIN);};
  break;
    

  case 171:
  if (yyn == 171)
    /* "jpic.y":651  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,INT);};
  break;
    

  case 172:
  if (yyn == 172)
    /* "jpic.y":653  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,RAND);};
  break;
    

  case 173:
  if (yyn == 173)
    /* "jpic.y":655  */ /* lalr1.java:476  */
    {yyval = compute(null,null,RAND);};
  break;
    

  case 174:
  if (yyn == 174)
    /* "jpic.y":657  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (4-(3)))),null,SRAND);};
  break;
    

  case 175:
  if (yyn == 175)
    /* "jpic.y":659  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'<');};
  break;
    

  case 176:
  if (yyn == 176)
    /* "jpic.y":661  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),LESSEQUAL);};
  break;
    

  case 177:
  if (yyn == 177)
    /* "jpic.y":663  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),'>');};
  break;
    

  case 178:
  if (yyn == 178)
    /* "jpic.y":665  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),GREATEREQUAL);};
  break;
    

  case 179:
  if (yyn == 179)
    /* "jpic.y":667  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),EQUALEQUAL);};
  break;
    

  case 180:
  if (yyn == 180)
    /* "jpic.y":669  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),NOTEQUAL);};
  break;
    

  case 181:
  if (yyn == 181)
    /* "jpic.y":671  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),ANDAND);};
  break;
    

  case 182:
  if (yyn == 182)
    /* "jpic.y":673  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (3-(1)))),((Expr)(yystack.valueAt (3-(3)))),OROR);};
  break;
    

  case 183:
  if (yyn == 183)
    /* "jpic.y":675  */ /* lalr1.java:476  */
    {yyval = compute(((Expr)(yystack.valueAt (2-(2)))),null,'!');};
  break;
    


/* "JPicParserBody.java":1902  */ /* lalr1.java:476  */
        default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval, yyloc);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    yyn = yyr1_[yyn];
    int yystate = yypgoto_[yyn - yyntokens_] + yystack.stateAt (0);
    if (0 <= yystate && yystate <= yylast_
        && yycheck_[yystate] == yystack.stateAt (0))
      yystate = yytable_[yystate];
    else
      yystate = yydefgoto_[yyn - yyntokens_];

    yystack.push (yystate, yyval, yyloc);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
                                 Object yyvaluep                                 , Object yylocationp)
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
              + yytname_[yytype] + " ("
              + yylocationp + ": "
              + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
   public boolean parse () throws Exception, Exception

  {
    /* @$.  */
    Location yyloc;


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    /* The location where the error started.  */
    Location yyerrloc = null;

    /* Location. */
    Location yylloc = new Location (null, null);

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval , yylloc);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {


            yycdebug ("Reading a token: ");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();
            yylloc = new Location (yylexer.getStartPos (),
                            yylexer.getEndPos ());

          }

        /* Convert token to internal form.  */
        if (yychar <= Lexer.EOF)
          {
            yychar = yytoken = Lexer.EOF;
            yycdebug ("Now at end of input.\n");
          }
        else
          {
            yytoken = yytranslate_ (yychar);
            yy_symbol_print ("Next token is", yytoken,
                             yylval, yylloc);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yy_table_value_is_error_ (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            yy_symbol_print ("Shifting", yytoken,
                             yylval, yylloc);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval, yylloc);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yylloc, yysyntax_error (yystate, yytoken));
          }

        yyerrloc = yylloc;
        if (yyerrstatus_ == 3)
          {
        /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

        if (yychar <= Lexer.EOF)
          {
          /* Return failure if at end of input.  */
          if (yychar == Lexer.EOF)
            return false;
          }
        else
            yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:

        yyerrloc = yystack.locationAt (yylen - 1);
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                yyn += yyterror_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            yyerrloc = yystack.locationAt (0);
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (yydebug > 0)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;


        /* Muck with the stack to setup for yylloc.  */
        yystack.push (0, null, yylloc);
        yystack.push (0, null, yyerrloc);
        yyloc = yylloc (yystack, 2);
        yystack.pop (2);

        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
                         yylval, yyloc);

        yystate = yyn;
        yystack.push (yyn, yylval, yyloc);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -228;
  private static final short yytable_ninf_ = -89;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     164,  -114,   -33,  -228,  -228,  -228,  -228,  -228,  -228,  -228,
    -228,  -228,  -228,  -228,  -228,  -228,    22,   580,  -228,  -228,
      26,   164,  -228,    38,  -228,   -10,  -228,  -228,  -228,  -228,
    -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,   306,   427,
     756,   -73,  -228,  -228,  -228,  -228,  -228,   -54,   -47,   -42,
     -24,    23,    28,    31,    41,    46,    47,    52,   427,   580,
     580,   -19,   -64,   212,  -228,   -35,  -228,   164,   756,  -114,
     -33,   468,  -228,   -78,    33,  -228,   107,   757,   120,  -228,
    -228,   915,  -228,    74,    96,   160,   580,   580,  -228,  -228,
    -228,  -228,  -228,  -228,  -228,  -228,   597,  -228,   638,   638,
     638,   485,   580,   580,   580,   580,  -228,   580,   580,   580,
    -228,  -228,  -228,  -228,   580,   580,   580,  -228,   580,   427,
     427,   427,   427,   427,   427,   427,   427,   427,   357,   427,
      62,    69,    69,  -228,  -228,  -228,  -228,  -228,   755,   580,
     580,   580,   580,   580,   580,   580,   580,   580,   580,   580,
     580,   580,   580,   164,   123,  -228,  -228,   -69,  -228,    66,
     -65,   863,   597,   597,   638,   580,   191,   192,  -228,  -228,
    -228,   915,   915,   526,  -228,   893,   -78,   -19,   -78,   -78,
     819,   372,   -78,  -228,  -228,   915,   915,   915,   915,   915,
     915,   915,   915,   915,   915,   902,    70,    71,    84,    72,
      73,    77,    89,    91,    78,  -228,    79,    80,  -228,  -228,
    -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,
    -228,  -228,  -228,  -228,  -228,    39,    49,  -228,  -228,  -228,
    -228,  -228,  -228,  -228,  -228,  -228,  -228,   266,   234,   382,
     382,   -85,   -85,   -85,   -85,   -98,   -98,    69,    69,    69,
      69,   -28,    85,   638,   597,   597,  -228,  -228,   638,  -228,
    -228,   -63,   915,  -228,  -228,    66,   893,    99,  -228,    98,
     580,  -228,  -228,   427,  -228,  -228,  -228,   427,   427,  -228,
    -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,  -228,
    -228,  -228,  -110,    86,    88,    76,   638,   755,   102,   872,
      90,    92,    93,  -228,  -228,  -228,   638,   -41,  -228,    94,
     539,   171,  -228,  -228,  -228,    44,  -228,  -228,   580,   915,
      85,   100,   915,  -228,  -228
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final short yydefact_[] = yydefact_init();
  private static final short[] yydefact_init()
  {
    return new short[]
    {
       2,     0,     0,    44,    37,    38,    39,    40,    41,    42,
      43,    45,    23,    24,    26,    25,     0,     0,   185,    19,
       0,     0,   184,     0,     9,     3,     5,    12,    18,    17,
      13,    14,    15,    16,    36,     4,    45,    11,     0,     0,
      21,     0,   104,   149,   148,   103,   102,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   100,     0,    20,     0,     1,     0,    35,   104,
     148,     0,     6,     0,     7,    90,     8,     0,   145,    22,
     143,   144,    85,     0,     0,     0,     0,     0,    84,    77,
      78,    79,    80,    74,    75,    76,     0,    57,     0,     0,
       0,     0,     0,     0,     0,     0,    58,    59,    61,    70,
      72,    73,    81,    82,     0,    63,     0,    46,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   161,   183,   150,   151,   152,   153,   154,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    31,    34,    10,     0,    87,    90,
      88,   144,     0,     0,     0,     0,     0,     0,    68,    69,
      67,    65,    66,     0,    56,     0,    51,    88,    52,    53,
       0,     0,    55,   109,    54,    47,    48,    49,    50,    60,
      62,    71,    83,    64,    86,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   173,     0,     0,   162,   105,
     125,   124,   111,   112,   113,   114,   115,   116,   117,   118,
     119,   120,   121,   122,   123,     0,     0,   130,   131,   136,
     137,   138,   139,   140,   142,   141,   101,   181,   182,   180,
     179,   176,   178,   175,   177,   155,   156,   157,   158,   159,
     160,     0,     0,     0,     0,     0,    99,    89,     0,    91,
      93,     0,    98,   147,   146,     0,   144,   107,   106,     0,
       0,   163,   164,     0,   166,   167,   168,     0,     0,   171,
     172,   174,   128,   126,   132,   134,   129,   127,   133,   135,
      33,    32,     0,    91,    93,     0,     0,     0,     0,    28,
       0,     0,     0,    95,    92,    94,     0,     0,   108,     0,
       0,     0,   165,   169,   170,     0,    96,   110,     0,    29,
       0,    96,    30,    27,    97
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -228,  -228,   -37,   -16,  -228,  -228,  -228,  -228,  -228,  -228,
    -228,  -228,  -228,  -227,  -228,   -36,  -228,   183,  -228,     6,
     190,   -20,   -26,    51,  -179,  -228,  -177,   -11,  -228,   -17,
     -18
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,   311,    33,   154,    34,    35,    36,    40,   117,   157,
     158,    75,    61,    62,   183,   184,   236,   130,    80,    81,
      37
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short yytable_[] = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      63,    72,   269,   268,   116,    65,    18,    67,   162,   163,
     316,    38,    76,    18,   133,   134,   135,   136,   137,    39,
     303,    77,   149,   150,   151,   291,   152,    41,    79,    64,
     156,    18,   116,   147,   148,   149,   150,   151,    66,   152,
     162,   163,   131,   132,    73,   160,   253,    67,   138,   254,
     255,   159,   296,   118,   161,   162,   163,   282,   283,   119,
     133,   134,   135,   136,   137,   257,   120,   286,   287,   171,
     172,   121,   177,   177,   177,   177,   174,   162,   163,   175,
     168,    77,    77,    77,    77,   185,   186,   187,   188,   122,
     189,   190,   191,   323,   155,   321,    22,   192,   193,   194,
     290,   195,   169,    22,   176,   178,   179,   182,   196,   197,
     198,   199,   200,   201,   202,   203,   204,   206,   207,   309,
     308,    22,   237,   238,   239,   240,   241,   242,   243,   244,
     245,   246,   247,   248,   249,   250,   123,   251,   177,   284,
     285,   124,   259,   260,   125,   175,   175,    77,   262,   288,
     289,   -87,   -87,   265,   126,   160,   266,   166,   167,   127,
     128,   159,   162,   163,   161,   129,   170,     1,   252,     2,
     261,     3,     4,     5,     6,     7,     8,     9,    10,    11,
      12,    13,    14,    15,    16,    17,   133,   134,   135,   136,
     137,   306,   208,   152,   162,   163,   256,   263,   264,   273,
     271,   272,   274,   275,   277,    18,   278,   276,   279,   280,
     281,   297,   153,   298,   180,   320,   304,    19,   305,    68,
     312,     0,   313,   314,   317,   -88,   -88,   177,    74,     0,
     324,   267,   177,    67,   293,   294,    77,   175,   175,     0,
       0,    77,     0,     0,     0,     0,     0,   139,   140,   141,
     142,   143,   144,   299,     0,     0,     0,     0,     0,   292,
       0,     0,   300,     0,   295,     0,   301,   302,     0,   139,
     177,   141,   142,   143,   144,    20,     0,     0,    21,    77,
     177,     0,     0,     0,     0,     0,     0,     0,     0,    77,
       0,     0,     0,   319,     0,    22,     0,     0,     0,     0,
       0,   322,   307,   141,   142,   143,   144,     0,     0,    69,
      43,    70,   315,     3,     4,     5,     6,     7,     8,     9,
      10,    11,    12,    13,    14,    15,    16,    17,   145,   146,
     147,   148,   149,   150,   151,     0,   152,     0,     0,   153,
       0,     0,     0,     0,     0,     0,     0,    18,     0,     0,
     145,   146,   147,   148,   149,   150,   151,     0,   152,    19,
      42,    43,    44,    78,     0,     0,     0,     0,     0,     0,
       0,     0,    45,    46,     0,    42,    43,    44,    78,     0,
       0,     0,   145,   146,   147,   148,   149,   150,   151,     0,
     152,    47,    48,    49,    50,    51,    52,    53,    54,    55,
      56,    57,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    20,     0,    71,
      21,   143,   144,    45,    46,    59,     0,     0,     0,    60,
      42,    43,    44,    78,     0,     0,     0,    22,    45,    46,
       0,     0,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,     0,     0,     0,     0,    47,    48,    49,
      50,    51,    52,    53,    54,    55,    56,    57,     0,     0,
      58,    42,    43,    44,    78,     0,    59,     0,     0,     0,
      60,     0,     0,     0,   180,    71,     0,   205,    42,    43,
      44,    59,     0,    45,    46,    60,     0,     0,   145,   146,
     147,   148,   149,   150,   151,     0,   152,     0,     0,     0,
       0,     0,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,     0,     0,     0,     0,     0,     0,    42,
      43,    44,    78,     0,    45,    46,     0,     0,     0,     0,
      58,     0,    42,    43,    44,     0,    59,     0,     0,     0,
      60,    45,    46,    47,    48,    49,    50,    51,    52,    53,
      54,    55,    56,    57,     0,     0,     0,     0,     0,     0,
      47,    48,    49,    50,    51,    52,    53,    54,    55,    56,
      57,    71,     0,    42,    43,    44,     0,    59,     0,     0,
       0,    60,    45,    46,     0,     0,     0,   180,   181,     0,
      42,    43,    44,     0,    59,    45,    46,     0,    60,     0,
       0,    47,    48,    49,    50,    51,    52,    53,    54,    55,
      56,    57,     0,     0,    47,    48,    49,    50,    51,    52,
      53,    54,    55,    56,    57,     0,     0,     0,     0,   173,
       0,    42,    43,    44,     0,    59,    45,    46,     0,    60,
       0,     0,    58,     0,     0,     0,     0,     0,    59,   318,
       0,     0,    60,    45,    46,    47,    48,    49,    50,    51,
      52,    53,    54,    55,    56,    57,     0,     0,     0,     0,
       0,     0,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,     0,     0,     0,     0,     0,    59,
       0,     0,     0,    60,    45,    46,     0,     0,     0,     0,
     173,     0,     0,     0,     0,     0,    59,     0,     0,     0,
      60,     0,     0,    47,    48,    49,    50,    51,    52,    53,
      54,    55,    56,    57,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    71,     0,     0,     0,     0,     0,    59,   209,     0,
       0,    60,    82,     3,     4,     5,     6,     7,     8,     9,
      10,     0,     0,   210,   211,     0,     0,     0,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,     0,   139,   140,   141,   142,   143,   144,    96,    97,
       0,     0,    98,    99,   100,   101,     0,   164,     0,     0,
     102,   103,   104,   105,   106,   107,   108,   109,   110,   111,
     112,   113,    42,   212,   213,   214,   215,   216,   217,   218,
     219,   220,   221,   222,     0,     0,     0,   210,   211,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   223,   224,   225,   226,   227,   228,   229,   230,   231,
     232,   233,   234,   235,     0,   114,   115,     0,     0,     0,
      21,     0,   165,   145,   146,   147,   148,   149,   150,   151,
       0,   152,     0,     0,     0,     0,     0,   212,   213,   214,
     215,   216,   217,   218,   219,   220,   221,   222,   139,   140,
     141,   142,   143,   144,     0,     0,     0,   139,   140,   141,
     142,   143,   144,   258,   310,   223,   224,   225,   226,   227,
     228,   229,   230,   231,   232,   233,   234,   235,   139,   140,
     141,   142,   143,   144,     0,     0,     0,   139,   140,   141,
     142,   143,   144,     0,     0,     0,     0,     0,     0,   270,
     139,   140,   141,   142,   143,   144,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   165,   145,
     146,   147,   148,   149,   150,   151,     0,   152,   145,   146,
     147,   148,   149,   150,   151,     0,   152,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   165,   145,
     146,   147,   148,   149,   150,   151,     0,   152,   145,   146,
     147,   148,   149,   150,   151,     0,   152,     0,     0,     0,
       0,   145,   146,   147,   148,   149,   150,   151,     0,   152
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
      17,    38,   181,   180,    40,    21,    41,    25,   118,   119,
      51,   125,    38,    41,    79,    80,    81,    82,    83,    52,
     130,    38,   120,   121,   122,   252,   124,     5,    39,     3,
      67,    41,    68,   118,   119,   120,   121,   122,     0,   124,
     118,   119,    59,    60,    38,    71,   115,    65,   112,   118,
     119,    71,   115,   126,    71,   118,   119,    18,    19,   113,
      79,    80,    81,    82,    83,   130,   113,    18,    19,    86,
      87,   113,    98,    99,   100,   101,    96,   118,   119,    96,
       6,    98,    99,   100,   101,   102,   103,   104,   105,   113,
     107,   108,   109,   320,   129,    51,   131,   114,   115,   116,
     128,   118,     6,   131,    98,    99,   100,   101,   119,   120,
     121,   122,   123,   124,   125,   126,   127,   128,   129,   298,
     297,   131,   139,   140,   141,   142,   143,   144,   145,   146,
     147,   148,   149,   150,   151,   152,   113,   153,   164,   100,
     101,   113,   162,   163,   113,   162,   163,   164,   165,   100,
     101,   118,   119,   173,   113,   181,   173,    37,    38,   113,
     113,   181,   118,   119,   181,   113,     6,     3,    45,     5,
     164,     7,     8,     9,    10,    11,    12,    13,    14,    15,
      16,    17,    18,    19,    20,    21,    79,    80,    81,    82,
      83,   115,   130,   124,   118,   119,   130,     6,     6,   115,
     130,   130,   130,   130,   115,    41,   115,   130,   130,   130,
     130,   112,   127,   115,   112,    44,   130,    53,   130,    36,
     130,    -1,   130,   130,   130,   118,   119,   253,    38,    -1,
     130,   180,   258,   251,   254,   255,   253,   254,   255,    -1,
      -1,   258,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,
      38,    39,    40,   270,    -1,    -1,    -1,    -1,    -1,   253,
      -1,    -1,   273,    -1,   258,    -1,   277,   278,    -1,    35,
     296,    37,    38,    39,    40,   111,    -1,    -1,   114,   296,
     306,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   306,
      -1,    -1,    -1,   310,    -1,   131,    -1,    -1,    -1,    -1,
      -1,   318,   296,    37,    38,    39,    40,    -1,    -1,     3,
       4,     5,   306,     7,     8,     9,    10,    11,    12,    13,
      14,    15,    16,    17,    18,    19,    20,    21,   116,   117,
     118,   119,   120,   121,   122,    -1,   124,    -1,    -1,   127,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,
     116,   117,   118,   119,   120,   121,   122,    -1,   124,    53,
       3,     4,     5,     6,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    66,    67,    -1,     3,     4,     5,     6,    -1,
      -1,    -1,   116,   117,   118,   119,   120,   121,   122,    -1,
     124,    85,    86,    87,    88,    89,    90,    91,    92,    93,
      94,    95,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   111,    -1,   113,
     114,    39,    40,    66,    67,   119,    -1,    -1,    -1,   123,
       3,     4,     5,     6,    -1,    -1,    -1,   131,    66,    67,
      -1,    -1,    85,    86,    87,    88,    89,    90,    91,    92,
      93,    94,    95,    -1,    -1,    -1,    -1,    85,    86,    87,
      88,    89,    90,    91,    92,    93,    94,    95,    -1,    -1,
     113,     3,     4,     5,     6,    -1,   119,    -1,    -1,    -1,
     123,    -1,    -1,    -1,   112,   113,    -1,   130,     3,     4,
       5,   119,    -1,    66,    67,   123,    -1,    -1,   116,   117,
     118,   119,   120,   121,   122,    -1,   124,    -1,    -1,    -1,
      -1,    -1,    85,    86,    87,    88,    89,    90,    91,    92,
      93,    94,    95,    -1,    -1,    -1,    -1,    -1,    -1,     3,
       4,     5,     6,    -1,    66,    67,    -1,    -1,    -1,    -1,
     113,    -1,     3,     4,     5,    -1,   119,    -1,    -1,    -1,
     123,    66,    67,    85,    86,    87,    88,    89,    90,    91,
      92,    93,    94,    95,    -1,    -1,    -1,    -1,    -1,    -1,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,   113,    -1,     3,     4,     5,    -1,   119,    -1,    -1,
      -1,   123,    66,    67,    -1,    -1,    -1,   112,   113,    -1,
       3,     4,     5,    -1,   119,    66,    67,    -1,   123,    -1,
      -1,    85,    86,    87,    88,    89,    90,    91,    92,    93,
      94,    95,    -1,    -1,    85,    86,    87,    88,    89,    90,
      91,    92,    93,    94,    95,    -1,    -1,    -1,    -1,   113,
      -1,     3,     4,     5,    -1,   119,    66,    67,    -1,   123,
      -1,    -1,   113,    -1,    -1,    -1,    -1,    -1,   119,   120,
      -1,    -1,   123,    66,    67,    85,    86,    87,    88,    89,
      90,    91,    92,    93,    94,    95,    -1,    -1,    -1,    -1,
      -1,    -1,    85,    86,    87,    88,    89,    90,    91,    92,
      93,    94,    95,   113,    -1,    -1,    -1,    -1,    -1,   119,
      -1,    -1,    -1,   123,    66,    67,    -1,    -1,    -1,    -1,
     113,    -1,    -1,    -1,    -1,    -1,   119,    -1,    -1,    -1,
     123,    -1,    -1,    85,    86,    87,    88,    89,    90,    91,
      92,    93,    94,    95,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   113,    -1,    -1,    -1,    -1,    -1,   119,     3,    -1,
      -1,   123,     6,     7,     8,     9,    10,    11,    12,    13,
      14,    -1,    -1,    18,    19,    -1,    -1,    -1,    22,    23,
      24,    25,    26,    27,    28,    29,    30,    31,    32,    33,
      34,    -1,    35,    36,    37,    38,    39,    40,    42,    43,
      -1,    -1,    46,    47,    48,    49,    -1,    50,    -1,    -1,
      54,    55,    56,    57,    58,    59,    60,    61,    62,    63,
      64,    65,     3,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    -1,    -1,    -1,    18,    19,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,    -1,   109,   110,    -1,    -1,    -1,
     114,    -1,   115,   116,   117,   118,   119,   120,   121,   122,
      -1,   124,    -1,    -1,    -1,    -1,    -1,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    35,    36,
      37,    38,    39,    40,    -1,    -1,    -1,    35,    36,    37,
      38,    39,    40,    50,    42,    96,    97,    98,    99,   100,
     101,   102,   103,   104,   105,   106,   107,   108,    35,    36,
      37,    38,    39,    40,    -1,    -1,    -1,    35,    36,    37,
      38,    39,    40,    -1,    -1,    -1,    -1,    -1,    -1,    47,
      35,    36,    37,    38,    39,    40,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   115,   116,
     117,   118,   119,   120,   121,   122,    -1,   124,   116,   117,
     118,   119,   120,   121,   122,    -1,   124,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   115,   116,
     117,   118,   119,   120,   121,   122,    -1,   124,   116,   117,
     118,   119,   120,   121,   122,    -1,   124,    -1,    -1,    -1,
      -1,   116,   117,   118,   119,   120,   121,   122,    -1,   124
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final short yystos_[] = yystos_init();
  private static final short[] yystos_init()
  {
    return new short[]
    {
       0,     3,     5,     7,     8,     9,    10,    11,    12,    13,
      14,    15,    16,    17,    18,    19,    20,    21,    41,    53,
     111,   114,   131,   133,   134,   135,   136,   137,   138,   139,
     140,   141,   142,   144,   146,   147,   148,   162,   125,    52,
     149,     5,     3,     4,     5,    66,    67,    85,    86,    87,
      88,    89,    90,    91,    92,    93,    94,    95,   113,   119,
     123,   154,   155,   161,     3,   135,     0,   162,   149,     3,
       5,   113,   134,   151,   152,   153,   154,   161,     6,   159,
     160,   161,     6,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    42,    43,    46,    47,
      48,    49,    54,    55,    56,    57,    58,    59,    60,    61,
      62,    63,    64,    65,   109,   110,   147,   150,   126,   113,
     113,   113,   113,   113,   113,   113,   113,   113,   113,   113,
     159,   161,   161,    79,    80,    81,    82,    83,   112,    35,
      36,    37,    38,    39,    40,   116,   117,   118,   119,   120,
     121,   122,   124,   127,   145,   129,   134,   151,   152,   153,
     154,   161,   118,   119,    50,   115,    37,    38,     6,     6,
       6,   161,   161,   113,   153,   161,   151,   154,   151,   151,
     112,   113,   151,   156,   157,   161,   161,   161,   161,   161,
     161,   161,   161,   161,   161,   161,   159,   159,   159,   159,
     159,   159,   159,   159,   159,   130,   159,   159,   130,     3,
      18,    19,    68,    69,    70,    71,    72,    73,    74,    75,
      76,    77,    78,    96,    97,    98,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   158,   161,   161,   161,
     161,   161,   161,   161,   161,   161,   161,   161,   161,   161,
     161,   135,    45,   115,   118,   119,   130,   130,    50,   153,
     153,   151,   161,     6,     6,   153,   161,   155,   158,   156,
      47,   130,   130,   115,   130,   130,   130,   115,   115,   130,
     130,   130,    18,    19,   100,   101,    18,    19,   100,   101,
     128,   145,   151,   153,   153,   151,   115,   112,   115,   161,
     159,   159,   159,   130,   130,   130,   115,   151,   158,   156,
      42,   143,   130,   130,   130,   151,    51,   130,   120,   161,
      44,    51,   161,   145,   130
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,   132,   133,   133,   134,   134,   134,   134,   134,   135,
     135,   136,   136,   136,   136,   136,   136,   136,   136,   137,
     138,   139,   140,   141,   141,   141,   141,   142,   143,   143,
     143,   144,   144,   145,   146,   147,   147,   148,   148,   148,
     148,   148,   148,   148,   148,   149,   149,   150,   150,   150,
     150,   150,   150,   150,   150,   150,   150,   150,   150,   150,
     150,   150,   150,   150,   150,   150,   150,   150,   150,   150,
     150,   150,   150,   150,   150,   150,   150,   150,   150,   150,
     150,   150,   150,   150,   150,   150,   150,   151,   151,   151,
     152,   152,   152,   152,   152,   152,   152,   152,   153,   153,
     154,   154,   154,   154,   155,   155,   156,   156,   156,   157,
     157,   158,   158,   158,   158,   158,   158,   158,   158,   158,
     158,   158,   158,   158,   158,   158,   158,   158,   158,   158,
     158,   158,   158,   158,   158,   158,   158,   158,   158,   158,
     158,   158,   158,   159,   159,   160,   160,   160,   161,   161,
     161,   161,   161,   161,   161,   161,   161,   161,   161,   161,
     161,   161,   161,   161,   161,   161,   161,   161,   161,   161,
     161,   161,   161,   161,   161,   161,   161,   161,   161,   161,
     161,   161,   161,   161,   162,   162
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     0,     1,     1,     1,     3,     3,     3,     1,
       3,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       2,     2,     3,     1,     1,     1,     1,     9,     0,     2,
       3,     3,     5,     3,     3,     2,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     0,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     1,     1,     1,
       2,     1,     2,     1,     2,     2,     2,     2,     2,     2,
       1,     2,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     2,     1,     1,     2,     1,     1,     3,
       1,     3,     5,     3,     5,     5,     6,     8,     3,     3,
       1,     3,     1,     1,     1,     3,     2,     2,     4,     1,
       5,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     2,     2,     2,     2,
       1,     1,     2,     2,     2,     2,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     3,     3,     1,     1,
       2,     2,     2,     2,     2,     3,     3,     3,     3,     3,
       3,     2,     3,     4,     4,     6,     4,     4,     4,     6,
       6,     4,     4,     3,     4,     3,     3,     3,     3,     3,
       3,     3,     3,     2,     1,     1
    };
  }

  /* YYTOKEN_NUMBER[YYLEX-NUM] -- Internal symbol number corresponding
      to YYLEX-NUM.  */
  private static final short yytoken_number_[] = yytoken_number_init();
  private static final short[] yytoken_number_init()
  {
    return new short[]
    {
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308,   309,   310,   311,   312,   313,   314,
     315,   316,   317,   318,   319,   320,   321,   322,   323,   324,
     325,   326,   327,   328,   329,   330,   331,   332,   333,   334,
     335,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   348,   349,   350,   351,   352,   353,   354,
     355,   356,   357,   358,   359,   360,   361,   362,   363,   364,
     365,   366,    46,    40,    91,    44,    60,    62,    43,    45,
      42,    47,    37,    33,    94,    58,    61,   123,   125,    93,
      41,    59
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "NAME", "NUMBER", "VARIABLE", "TEXT",
  "TEXTBOX", "BOX", "CIRCLE", "ELLIPSE", "ARC", "LINE", "ARROW", "SPLINE",
  "MOVE", "UP", "DOWN", "RIGHT", "LEFT", "FOR", "IF", "COLORED",
  "OUTLINED", "SHADED", "XSLANTED", "YSLANTED", "ALIGNED", "CW", "CCW",
  "LJUST", "RJUST", "LEFT_ARROW_HEAD", "RIGHT_ARROW_HEAD",
  "DOUBLE_ARROW_HEAD", "ANDAND", "OROR", "NOTEQUAL", "EQUALEQUAL",
  "LESSEQUAL", "GREATEREQUAL", "EOL", "BY", "THEN", "DO", "ELSE", "FROM",
  "TO", "AT", "WITH", "LANGLES", "RANGLES", "ASSIGN", "DEFINE", "HEIGHT",
  "RADIUS", "WIDTH", "DIAMETER", "SOLID", "DOTTED", "DASHED", "CHOP",
  "SAME", "INVISIBLE", "ABOVE", "BELOW", "LAST", "HERE", "DOT_N", "DOT_E",
  "DOT_W", "DOT_S", "DOT_NE", "DOT_SE", "DOT_NW", "DOT_SW", "DOT_C",
  "DOT_START", "DOT_END", "DOT_X", "DOT_Y", "DOT_HT", "DOT_WID", "DOT_RAD",
  "NEGATE", "SIN", "COS", "ATAN2", "LOG", "EXP", "SQRT", "K_MAX", "K_MIN",
  "INT", "RAND", "SRAND", "TOP", "BOTTOM", "UPPER", "LOWER", "LEFT_CORNER",
  "RIGHT_CORNER", "NORTH", "SOUTH", "EAST", "WEST", "CENTER", "END",
  "START", "THICKNESS", "FILL", "UNDEF", "'.'", "'('", "'['", "','", "'<'",
  "'>'", "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'^'", "':'", "'='",
  "'{'", "'}'", "']'", "')'", "';'", "$accept", "picture", "element",
  "element_list", "statement", "macro_define", "undef", "move",
  "assignment", "direction", "forloop", "optional_by", "conditional",
  "block", "compound", "shape_spec", "shape_type", "properties_list",
  "property", "position", "position_not_place", "expr_pair", "place",
  "label_path", "relative_path", "path", "corner", "any_expr", "text_expr",
  "expr", "eol", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   227,   227,   229,   234,   236,   238,   240,   242,   247,
     249,   254,   255,   256,   257,   258,   259,   260,   261,   270,
     275,   280,   285,   290,   291,   292,   293,   297,   301,   303,
     305,   310,   312,   316,   321,   325,   327,   332,   334,   336,
     338,   340,   342,   344,   346,   351,   353,   358,   360,   362,
     364,   366,   368,   370,   372,   374,   376,   378,   380,   382,
     384,   386,   388,   390,   392,   394,   396,   398,   400,   402,
     404,   406,   408,   410,   412,   414,   416,   418,   420,   422,
     424,   426,   428,   430,   432,   434,   436,   441,   443,   445,
     450,   452,   454,   456,   458,   460,   462,   464,   469,   471,
     477,   479,   481,   483,   488,   490,   495,   499,   501,   506,
     508,   513,   515,   517,   519,   521,   523,   525,   527,   529,
     531,   533,   535,   537,   539,   541,   543,   545,   547,   549,
     551,   553,   555,   557,   559,   561,   563,   565,   567,   569,
     571,   573,   575,   588,   590,   595,   597,   599,   604,   606,
     608,   610,   612,   614,   616,   618,   620,   622,   624,   626,
     628,   630,   632,   634,   636,   638,   640,   642,   644,   646,
     648,   650,   652,   654,   656,   658,   660,   662,   664,   666,
     668,   670,   672,   674,   678,   678
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
                       yystos_[yystack.stateAt(yynrhs - (yyi + 1))],
                       ((yystack.valueAt (yynrhs-(yyi + 1)))),
                       yystack.locationAt (yynrhs-(yyi + 1)));
  }

  /* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
  private static final short yytranslate_table_[] = yytranslate_table_init();
  private static final short[] yytranslate_table_init()
  {
    return new short[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,   123,     2,     2,     2,   122,     2,     2,
     113,   130,   120,   118,   115,   119,   112,   121,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,   125,   131,
     116,   126,   117,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,   114,     2,   129,   124,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,   127,     2,   128,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    81,    82,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   109,   110,   111
    };
  }

  private static final short yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 1039;
  private static final int yynnts_ = 31;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 66;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 132;

  private static final int yyuser_token_number_max_ = 366;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "jpic.y":43  */ /* lalr1.java:1060  */

    // Provide accessors for the parser lexer
    Lexer getLexer() {return this.yylexer;}
    void setLexer(Lexer lexer) {this.yylexer = lexer;}

/* "JPicParserBody.java":2953  */ /* lalr1.java:1060  */

}

