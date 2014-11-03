/* Copyright (C) 1989, 1990, 1991, 1992, 2000, 2001, 2002, 2003, 2004, 2005,
                 2006, 2007, 2009
   Free Software Foundation, Inc.
     Written by James Clark (jjc@jclark.com)

This file is part of groff.

groff is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free
Software Foundation, either version 3 of the License, or
(at your option) any later version.

groff is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.

*/

%require "3.0" /* or later */
%language "Java"
%debug
%error-verbose
%locations

%define api.push-pull pull
%define api.position.type {JPicLexer.Pos}
%define package {ucar.jpic}
%define abstract
%define parser_class_name {JPicParserBody}
%define extends {JPicActions}
%define throws {Exception}
%define lex_throws {Exception}

%code imports {
import static ucar.jpic.JPicParserBody.Lexer.*;
}

%code {
    // Provide accessors for the parser lexer
    Lexer getLexer() {return this.yylexer;}
    void setLexer(Lexer lexer) {this.yylexer = lexer;}
}

%token <String> NAME
%token <Number> NUMBER
%token <String> VARIABLE
%token <String> TEXT

/* Shapes */
%token <ShapeType> TEXTBOX
%token <ShapeType> BOX
%token <ShapeType> CIRCLE
%token <ShapeType> ELLIPSE
%token <ShapeType> ARC
%token <ShapeType> LINE
%token <ShapeType> ARROW
%token <ShapeType> SPLINE

/* Commands */
%token <Command> MOVE
%token <Command> UP
%token <Command> DOWN
%token <Command> RIGHT
%token <Command> LEFT
%token <Command> FOR
%token <Command> IF

%token <Property> COLORED
%token <Property> OUTLINED
%token <Property> SHADED
%token <Property> XSLANTED
%token <Property> YSLANTED
%token <Property> ALIGNED
%token <Property> CW CCW LJUST RJUST
%token <Property> LEFT_ARROW_HEAD RIGHT_ARROW_HEAD DOUBLE_ARROW_HEAD

/* Multi-character operators */
%token ANDAND OROR
%token NOTEQUAL EQUALEQUAL
%token LESSEQUAL GREATEREQUAL

/* Keywords */
%token EOL
%token BY
%token THEN
%token DO
%token ELSE
%token FROM
%token TO
%token AT
%token WITH
%token LANGLES
%token RANGLES
%token ASSIGN
%token DEFINE
%token HEIGHT
%token RADIUS
%token WIDTH
%token DIAMETER
%token SOLID
%token DOTTED
%token DASHED
%token CHOP
%token SAME
%token INVISIBLE
%token ABOVE
%token BELOW
%token LAST
%token HERE
%token DOT_N
%token DOT_E	
%token DOT_W
%token DOT_S
%token DOT_NE
%token DOT_SE
%token DOT_NW
%token DOT_SW
%token DOT_C
%token DOT_START
%token DOT_END
%token DOT_X
%token DOT_Y
%token DOT_HT
%token DOT_WID
%token DOT_RAD
%token NEGATE
%token SIN
%token COS
%token ATAN2
%token LOG
%token EXP
%token SQRT
%token K_MAX
%token K_MIN
%token INT
%token RAND
%token SRAND
%token TOP
%token BOTTOM
%token UPPER
%token LOWER
%token LEFT_CORNER
%token RIGHT_CORNER
%token NORTH
%token SOUTH
%token EAST
%token WEST
%token CENTER
%token END
%token START
%token THICKNESS
%token FILL
%token UNDEF


%type <Expr> expr text_expr any_expr
%type <Element> element
%type <Pair> expr_pair position_not_place
%type <Corner> corner
%type <Position> position
%type <Path> path relative_path
%type <Place> place 
%type <ShapeSpec> shape_spec
%type <Property> property;
%type <Direction> direction
%type <Element> statement conditional forloop assignment undef move
%type <ShapeType> shape_type

%type <ByExpr> optional_by /* <BY> */ 

%type <PathList> label_path
%type <PropertyList> properties_list
%type <ElementList> element_list block compound
%left '.'

/* give text adjustments higher precedence than TEXT, so that
box "foo" above ljust == box ("foo" above ljust)
*/

%left LJUST RJUST ABOVE BELOW

%right ASSIGN

%left LEFT RIGHT
/* Give attributes that take an optional expression a higher
precedence than left and right, so that eg `line chop left'
parses properly. */
%left CHOP SOLID DASHED DOTTED UP DOWN FILL COLORED OUTLINED
%left XSLANTED YSLANTED
%left NAME

%left VARIABLE NUMBER '(' SIN COS ATAN2 LOG EXP SQRT K_MAX K_MIN INT RAND SRAND LAST 
%left HERE

%left BOX CIRCLE ELLIPSE ARC LINE ARROW SPLINE '['

/* these need to be lower than '-' */
%left HEIGHT RADIUS WIDTH DIAMETER FROM TO AT THICKNESS

/* these must have higher precedence than CHOP so that `label %prec CHOP'
works */
%left DOT_N DOT_E DOT_W DOT_S DOT_NE DOT_SE DOT_NW DOT_SW DOT_C
%left DOT_START DOT_END TOP BOTTOM LEFT_CORNER RIGHT_CORNER
%left UPPER LOWER NORTH SOUTH EAST WEST CENTER START END

%left ','
%left OROR
%left ANDAND
%left EQUALEQUAL NOTEQUAL
%left '<' '>' LESSEQUAL GREATEREQUAL

%left '+' '-'
%left '*' '/' '%'
%right '!'
%right '^'

%start picture

%%

picture:
	  %empty
	    {picture(null);}
	| element_list
		{picture($1);}
	;

element:
  	  shape_spec
		{$$=$1;}
	| statement
		{$$=$1;}
	| NAME ':'  element
		{setlabel($3,$1); $$=$1;}
	| NAME ':'  position_not_place
		{setlabel($3,$1); $$=$1;}
	| NAME ':'  place
		{setlabel($3,$1); $$=$1;}
	;

element_list:
	  element
		{$$=element_list(null,$1);}
	| element_list eol element
		{$$=element_list($1,$3);}
	;

statement:
	  eol {$$=null;}
	| macro_define {$$=null;}
	| assignment {$$=$1;}
	| direction {$$=$1;}
	| forloop {$$=$1;}
	| conditional {$$=$1;}
	| move {$$=$1;}
	| undef {$$=$1;}
	;

/*
The lexer will pull in the name and text
of this macro. Macro expansion is also
invisible to the parser.
*/
macro_define:
	DEFINE
	{macro_define();}
	;

undef:
	UNDEF NAME
		{$$=undef($2);}
	;

move:
	MOVE properties_list
		{$$=move($2);}
	;

assignment:
	VARIABLE ASSIGN any_expr
		{$$=assignment($1,$3);}
	;

direction:
	  UP {$$=current_direction(UP);}
	| DOWN {$$=current_direction(DOWN);}
	| LEFT {$$=current_direction(LEFT);}
	| RIGHT {$$=current_direction(RIGHT);}
	;

forloop:
	FOR VARIABLE '=' expr TO expr optional_by DO block
		{$$=forloop($2,$4,$6,$7,$9);}

optional_by:
	  %empty
		{$$=null;}
	| BY expr
		{$$=by('\0',$2);}
	| BY '*' expr
		{$$=by('*',$3);}
	;

conditional:
	  IF expr block
		{$$=conditional($2,$3,null);}
	| IF expr block ELSE block
		{$$=conditional($2,$3,$5);}

block:
	'{' element_list '}'
		{$$=$2;}
	;

compound:
	  '[' element_list ']'
		{$$=compound($2);}

shape_spec:
	shape_type properties_list
	    {$$=shape_spec($1,$2);}
	| compound
	    {$$ = $1;}
	;

shape_type:
	  BOX
		{$$ = ShapeType.BOX;}
	| CIRCLE
		{$$ = ShapeType.CIRCLE;}
	| ELLIPSE
		{$$ = ShapeType.ELLIPSE;}
	| ARC
		{$$ = ShapeType.ARC;}
	| LINE
		{$$ = ShapeType.LINE;}
	| ARROW
		{$$ = ShapeType.ARROW;}
	| SPLINE
		{$$ = ShapeType.SPLINE;}
	| TEXTBOX
		{$$ = ShapeType.TEXTBOX;}
	;

properties_list:
	  %empty
	    {$$=properties_list(null,null);}
	| properties_list property
	    {$$=properties_list($1,$2);}
	;

property:
	  HEIGHT expr
		{$$=property(PropertyType.HEIGHT,$2);}
	| RADIUS expr
		{$$=property(PropertyType.RADIUS,$2);}
	| WIDTH expr
		{$$=property(PropertyType.WIDTH,$2);}
	| DIAMETER expr
		{$$=property(PropertyType.DIAMETER,$2);}
	| FROM position
		{$$=property(PropertyType.FROM,$2);}
	| TO position
		{$$=property(PropertyType.TO,$2);}
	| AT position
		{$$=property(PropertyType.AT,$2);}
	| WITH path
		{$$=property(PropertyType.WITH,$2);}
	| WITH position %prec ','
		{$$=property(PropertyType.WITH,$2);}
	| BY expr_pair
		{$$=property(PropertyType.BY,$2);}
	| THEN
		{$$=property(PropertyType.THEN,null);}
	| SOLID
		{$$=property(PropertyType.SOLID,null);}
	| DOTTED
		{$$=property(PropertyType.DOTTED,null);}
	| DOTTED expr
		{$$=property(PropertyType.DOTTED,$2);}
	| DASHED
		{$$=property(PropertyType.DASHED,null);}
	| DASHED expr
		{$$=property(PropertyType.DASHED,$2);}
	| FILL
		{$$=property(PropertyType.FILL,null);}
	| FILL expr
		{$$=property(PropertyType.FILL,$2);}
	| XSLANTED expr
		{$$=property(PropertyType.XSLANTED,$2);}
	| YSLANTED expr
		{$$=property(PropertyType.YSLANTED,$2);}
	| SHADED TEXT
		{$$=property(PropertyType.SHADED,$2);}
	| COLORED TEXT
		{$$=property(PropertyType.COLORED,$2);}
	| OUTLINED TEXT
		{$$=property(PropertyType.OUTLINED,$2);}
	| CHOP
		{$$=property(PropertyType.CHOP,null);}
	| CHOP expr
		{$$=property(PropertyType.CHOP,$2);}
	| SAME
		{$$=property(PropertyType.SAME,null);}
	| INVISIBLE
		{$$=property(PropertyType.INVISIBLE,null);}
	| LEFT_ARROW_HEAD
		{$$=property(PropertyType.LEFT_ARROW_HEAD,null);}
	| RIGHT_ARROW_HEAD
		{$$=property(PropertyType.RIGHT_ARROW_HEAD,null);}
	| DOUBLE_ARROW_HEAD
		{$$=property(PropertyType.DOUBLE_ARROW_HEAD,null);}
	| CW
		{$$=property(PropertyType.CW,null);}
	| CCW
		{$$=property(PropertyType.CCW,null);}
	| LJUST
		{$$=property(PropertyType.LJUST,null);}
	| RJUST
		{$$=property(PropertyType.RJUST,null);}
	| ABOVE
		{$$=property(PropertyType.ABOVE,null);}
	| BELOW
		{$$=property(PropertyType.BELOW,null);}
	| THICKNESS expr
		{$$=property(PropertyType.THICKNESS,$2);}
	| ALIGNED
		{$$=property(PropertyType.ALIGNED,null);}
	| TEXT
		{$$=property(PropertyType.TEXT,$1);}
	| shape_spec expr %prec HEIGHT
		{$$=property(PropertyType.EXPR,$2);}
	;

position:
  	position_not_place
		{$$=$1;}
	| place
  		{$$=$1;}
	| '(' place ')'
  		{$$=$2;}
	;

position_not_place:
  	  expr_pair
		{$$ = $1;}
	| position '+' expr_pair
		{$$=position_expr($1,$3,'+');}
	| '(' position '+' expr_pair ')'
		{$$ = position_expr($2,$4,'+');}
	| position '-' expr_pair
		{$$ = position_expr($1,$3,'-');}
	| '(' position '-' expr_pair ')'
		{$$ = position_expr($2,$4,'-');}
	| '(' position ',' position ')'
		{$$=pair($2,$4);}
	| expr LANGLES position ',' position RANGLES
		{$$=between($1,$3,$5);}
	| '(' expr LANGLES position ',' position RANGLES ')'
		{$$=between($2,$4,$6);}
	;

expr_pair:
	expr ',' expr
	    {$$=expr_pair($1,$3);}
	| '(' expr_pair ')'
	    {$$ = $2;}
	;

place:
	/* line at A left == line (at A) left */
	label_path %prec CHOP
	    {$$ = place($1,null);}
	| label_path '.' corner
	    {$$=place($1,$3);}
	| HERE
	    {$$=here();}
	| LAST
	    {$$=last();}
	;

label_path:
	  NAME
	    {$$=label_path($1,null);}
	| label_path '.' NAME
	    {$$=label_path($3,$1);}
	;

relative_path:
	'.' corner %prec CHOP
	    {$$ = relative_path($2,null);}
	/* give this a lower precedence than LEFT and RIGHT so that
	   [A: box] with .A left == [A: box] with (.A left) */
  	| '.' label_path %prec TEXT
		{$$ = relative_path(null,$2);}
	| '.' label_path '.' corner
		{$$=relative_path($4,$2);}
	;

path:
	relative_path
		{$$ = $1;}
	| '(' relative_path ',' relative_path ')'
		{$$=pathpair($2,$4);}
	;

corner:
	  DOT_N
		{$$ = CornerType.NORTH;}
	| DOT_E	
		{$$ = CornerType.EAST;}
	| DOT_W
		{$$ = CornerType.WEST;}
	| DOT_S
		{$$ = CornerType.SOUTH;}
	| DOT_NE
		{$$ = CornerType.NORTH_EAST;}
	| DOT_SE
		{$$ = CornerType. SOUTH_EAST;}
	| DOT_NW
		{$$ = CornerType.NORTH_WEST;}
	| DOT_SW
		{$$ = CornerType.SOUTH_WEST;}
	| DOT_C
		{$$ = CornerType.CENTER;}
	| DOT_START
		{$$ = CornerType.START;}
	| DOT_END
		{$$ = CornerType.END;}
  	| TOP
		{$$ = CornerType.NORTH;}
	| BOTTOM
		{$$ = CornerType.SOUTH;}
	| LEFT
		{$$ = CornerType.WEST;}
	| RIGHT
		{$$ = CornerType.EAST;}
	| UPPER LEFT
		{$$ = CornerType.NORTH_WEST;}
	| LOWER LEFT
		{$$ = CornerType.SOUTH_WEST;}
	| UPPER RIGHT
		{$$ = CornerType.NORTH_EAST;}
	| LOWER RIGHT
		{$$ = CornerType.SOUTH_EAST;}
	| LEFT_CORNER
		{$$ = CornerType.WEST;}
	| RIGHT_CORNER
		{$$ = CornerType.EAST;}
	| UPPER LEFT_CORNER
		{$$ = CornerType.NORTH_WEST;}
	| LOWER LEFT_CORNER
		{$$ = CornerType.SOUTH_WEST;}
	| UPPER RIGHT_CORNER
		{$$ = CornerType.NORTH_EAST;}
	| LOWER RIGHT_CORNER
		{$$ = CornerType.SOUTH_EAST;}
	| NORTH
		{$$ = CornerType.NORTH;}
	| SOUTH
		{$$ = CornerType.SOUTH;}
	| EAST
		{$$ = CornerType.EAST;}
	| WEST
		{$$ = CornerType.WEST;}
	| CENTER
		{$$ = CornerType.CENTER;}
	| START
		{$$ = CornerType.START;}
	| END
		{$$ = CornerType.END;}
	;

/*
We separate out text expressions
because a TEXT object can be either
a property or and expr. And we
can't make expr a property without causing
other conflicts.
*/

any_expr:
	  text_expr
	    {$$=$1;}
	| expr
	    {$$=$1;}
	;

text_expr:
	  TEXT
	    {$$ = text($1);}
	| TEXT EQUALEQUAL TEXT
  	    {$$ = compute(text($1),text($3),EQUALEQUAL);}
	| TEXT NOTEQUAL TEXT
  	    {$$ = compute(text($1),text($3),NOTEQUAL);}
	;

expr:
	  VARIABLE
	    {$$=var($1);}
	| NUMBER
		{$$ = number($1);}
	| place DOT_X
  		{$$=expr_position($1,DOT_X);}
	| place DOT_Y
  		{$$=expr_position($1,DOT_Y);}
	| place DOT_HT
  		{$$=expr_position($1,DOT_HT);}
	| place DOT_WID
  		{$$=expr_position($1,DOT_WID);}
	| place DOT_RAD
  		{$$=expr_position($1,DOT_RAD);}
	| expr '+' expr
		{$$ = compute($1,$3,'+');}
	| expr '-' expr
		{$$ = compute($1,$3,'-');}
	| expr '*' expr
		{$$ = compute($1,$3,'*');}
	| expr '/' expr
		{$$ = compute($1,$3,'/');}
	| expr '%' expr
		{$$ = compute($1,$3,'%');}
	| expr '^' expr
		{$$ = compute($1,$3,'^');}
	| '-' expr %prec '!'
		{$$ = compute($2,null,NEGATE);}
	| '(' any_expr ')'
		{$$ = $2;}
	| SIN '(' any_expr ')'
		{$$ = compute($3,null,SIN);}
	| COS '(' any_expr ')'
		{$$ = compute($3,null,COS);}
	| ATAN2 '(' any_expr ',' any_expr ')'
		{$$ = compute($3,$5,ATAN2);}
	| LOG '(' any_expr ')'
		{$$ = compute($3,null,LOG);}
	| EXP '(' any_expr ')'
		{$$ = compute($3,null,'^');}
	| SQRT '(' any_expr ')'
		{$$ = compute($3,null,SQRT);}
	| K_MAX '(' any_expr ',' any_expr ')'
		{$$ = compute($3,$5,K_MAX);}
	| K_MIN '(' any_expr ',' any_expr ')'
		{$$ = compute($3,$5,K_MIN);}
	| INT '(' any_expr ')'
		{$$ = compute($3,null,INT);}
	| RAND '(' any_expr ')'
		{$$ = compute($3,null,RAND);}
	| RAND '(' ')'
		{$$ = compute(null,null,RAND);}
	| SRAND '(' any_expr ')'
		{$$ = compute($3,null,SRAND);}
	| expr '<' expr
		{$$ = compute($1,$3,'<');}
	| expr LESSEQUAL expr
		{$$ = compute($1,$3,LESSEQUAL);}
	| expr '>' expr
		{$$ = compute($1,$3,'>');}
	| expr GREATEREQUAL expr
		{$$ = compute($1,$3,GREATEREQUAL);}
	| expr EQUALEQUAL expr
		{$$ = compute($1,$3,EQUALEQUAL);}
	| expr NOTEQUAL expr
		{$$ = compute($1,$3,NOTEQUAL);}
	| expr ANDAND expr
		{$$ = compute($1,$3,ANDAND);}
	| expr OROR expr
		{$$ = compute($1,$3,OROR);}
	| '!' expr
		{$$ = compute($2,null,'!');}
	;

eol: ';' | EOL ;
