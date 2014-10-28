%expect 2

%union {
	String str;
	int n;
	double x;
	struct { double x, y; } pair;
	struct { double x; String body; } if_data;
	struct { String str; const String filename; int lineno; } lstr;
	struct { double *v; int nv; int maxv; } dv;
	struct { double val; int is_multiplicative; } by;
	place pl;
	object *obj;
	corner crn;
	path *pth;
	object_spec *spec;
	saved_state *pstate;
	graphics_state state;
	object_type obtype;
}

%token <String> LABEL
%token <String> VARIABLE
%token <Double> NUMBER
%token <lstr> TEXT
%token <lstr> COMMAND_LINE
%token <String> DELIMITED
%token <n> ORDINAL
%token TH
%token LEFT_ARROW_HEAD
%token RIGHT_ARROW_HEAD
%token DOUBLE_ARROW_HEAD
%token LAST
%token BOX
%token CIRCLE
%token ELLIPSE
%token ARC
%token LINE
%token ARROW
%token MOVE
%token SPLINE
%token HEIGHT
%token RADIUS
%token FIGNAME
%token WIDTH
%token DIAMETER
%token UP
%token DOWN
%token RIGHT
%token LEFT
%token FROM
%token TO
%token AT
%token WITH
%token BY
%token THEN
%token SOLID
%token DOTTED
%token DASHED
%token CHOP
%token SAME
%token INVISIBLE
%token LJUST
%token RJUST
%token ABOVE
%token BELOW
%token OF
%token THE
%token WAY
%token BETWEEN
%token AND
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
%token COPY
%token THRU
%token TOP
%token BOTTOM
%token UPPER
%token LOWER
%token SH
%token PRINT
%token CW
%token CCW
%token FOR
%token DO
%token IF
%token ELSE
%token ANDAND
%token OROR
%token NOTEQUAL
%token EQUALEQUAL
%token LESSEQUAL
%token GREATEREQUAL
%token LEFT_CORNER
%token RIGHT_CORNER
%token NORTH
%token SOUTH
%token EAST
%token WEST
%token CENTER
%token END
%token START
%token RESET
%token UNTIL
%token PLOT
%token THICKNESS
%token FILL
%token COLORED
%token OUTLINED
%token SHADED
%token XSLANTED
%token YSLANTED
%token ALIGNED
%token SPRINTF
%token COMMAND

%token DEFINE
%token UNDEF

%left '.'

/* this ensures that plot 17 "%g" parses as (plot 17 "%g") */
%left PLOT
%left TEXT SPRINTF

/* give text adjustments higher precedence than TEXT, so that
box "foo" above ljust == box ("foo" above ljust)
*/

%left LJUST RJUST ABOVE BELOW

%left LEFT RIGHT
/* Give attributes that take an optional expression a higher
precedence than left and right, so that eg `line chop left'
parses properly. */
%left CHOP SOLID DASHED DOTTED UP DOWN FILL COLORED OUTLINED
%left XSLANTED YSLANTED
%left LABEL

%left VARIABLE NUMBER '(' SIN COS ATAN2 LOG EXP SQRT K_MAX K_MIN INT RAND SRAND LAST 
%left ORDINAL HERE '`'

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

%left BETWEEN OF
%left AND

%left '+' '-'
%left '*' '/' '%'
%right '!'
%right '^'

%type <Double> expr expr_lower_than expr_not_lower_than any_expr text_expr
%type <BY> optional_by
%type <Pair> expr_pair position_not_place
%type <IfData> simple_if
%type <Shape> nth_primitive
%type <Corner> corner
%type <Path> path label_path relative_path
%type <Place> place label element element_list middle_element_list
%type <ShapeSpec> object_spec
%type <Pair> position
%type <ShapeType> object_type
%type <Integer> optional_ordinal_last ordinal
%type <String> macro_name until
%type <DV> sprintf_args
%type <Lstr> text print_args print_arg

%%

picture:
	  /*empty*/
	    {picture(null);}
	| element_list
		{picture($1);}
	;

statement_list:
	  statement
		{$$=statement_list(null,$1);}
	| statement_list statement
		{$$=statement_list($1,$2);}
	;

statement:
	  /* empty */ ';' {$$=null;}
	| macro_define ';' {$$=$1;}
	| var_define ';' {$$=$1;}
	| assignment ';' {$$=$1;}
	| direction ';' {$$=$1;}
	| print ';' {$$=$1;}
	| forloop ';' {$$=$1;}
	| conditional ';' {$$=$1;}
	| compound ';' {$$=$1;}
	| block ';' {$$=$1;}
	| reset_variables ';' {$$=$1;}
	| reset ';' {$$=$1;}
		{$$=$1;} | direction ';' {$$=$1;} | print ';'
	;

macro_define:
	FIGNAME '=' macro_name
		{$$=macro_define($3);}
	;

var_define:
	VARIABLE '=' any_expr
		{var_define($1,$3);}
	;

assignment:
	VARIABLE ':' '=' any_expr
		{$$=assignment($1,$4);}

direction:
	  UP { $$=current_direction(UP_DIRECTION); }
	| DOWN { $$=current_direction(DOWN_DIRECTION); }
	| LEFT { $$=current_direction(LEFT_DIRECTION); }
	| RIGHT { $$=current_direction(RIGHT_DIRECTION); }
	;

print:
	PRINT print_args
		{$$=print($2);}
	;

forloop:
	FOR VARIABLE '=' expr TO expr optional_by DO block
		{$$=forloop($2,$4,$6,$7,$9);}

optional_by:
	/* empty */
		{$$=null;}
	| BY expr
		{$$=by_plus($2);}
	| BY '*' expr
		{$$=by_times($3);}
	;

conditional:
	  IF expression block
		{$$=conditional($2,$3,null);}
	| IF expression block ELSE block
		{$$=conditional($2,$3,$5);}


reset:
	RESET
	    {$$=reset();}
	;

macro_name:
	VARIABLE
	| LABEL
	;

reset_variables:
	RESET variable_list

variable_list:
	  /*empty*/
		{$$=variable_list();}
	| variable_list VARIABLE
		{$$=variable_list($1,$2);}
	| variable_list ',' VARIABLE
		{$$=variable_list($1,$3);}
	;

print_args:
	  print_arg
		{$$ = print_args(null,$1);}
	| print_args print_arg
		{$$ = print_args($1,$2);}
	;

print_arg:
	  expr %prec ','
		{$$=$1;}
	| text
		{$$=$1;}
	| position %prec ','
		{$$=$1;}
	;

any_expr:
	  expr
		{ $$ = $1; }
	| text_expr
		{ $$ = $1; }
	;
	
text_expr:
	  text '=' '=' text
		{$$=text_expr(Operator.EQ,$1,$4);
	| text '!' '=' text
		{$$=text_expr(Operator.NEQ,$1,$4);
	| text_expr '&' '&' text_expr
		{$$=text_expr(Operator.AND,$1,$4);
	| text_expr '&' '&' expr
		{$$=text_expr(Operator.AND,$1,$4);
	| expr '&' '&' text_expr
		{$$=text_expr(Operator.AND,$1,$4);
	| text_expr '|' '|' text_expr
		{$$=text_expr(Operator.OR,$1,$4);
	| text_expr '|' '|' expr
		{$$=text_expr(Operator.OR,$1,$4);
	| expr '|' '|' text_expr
		{$$=text_expr(Operator.OR,$1,$4);
	| '!' text_expr
		{$$=text_expr(Operator.NOT,$2);}
	;

element:
	object_spec
		{$$=element($1);}
	| LABEL ':'  element
		{$$=label($1,$3);}
	| LABEL ':'  position_not_place
		{$$=label($1,$3);}
	| LABEL ':'  place
		{$$=label($1,$3);}
	| statement
		{$$=$1;}

block:
	'{' element_list '}'
		{$$=block($2);}
	;

compound
	  '[' element_list ']'
		{$$=compound($2);}

object_spec:
	shape properties_list
	    {$$=object_spec($1,$2);}
	;

properties_list:
	  /*empty*/
	    {$$=properties_list(null);}
	| properties_list property
	    {$$=properties_list($1,$2);}
	;

shape:
	  BOX
		{ $$ = shape(ShapeType.BOX_OBJECT); }
	| CIRCLE
		{ $$ = shape(ShapeType.CIRCLE_OBJECT); }
	| ELLIPSE
		{ $$ = shape(ShapeType.ELLIPSE_OBJECT); }
	| ARC
		{$$ = shape(ShapeType.ARC_OBJECT);}
	| LINE
		{$$ = shape(ShapeType.LINE_OBJECT);}
	| ARROW
		{$$ = shape(ARROW_OBJECT);}
	| MOVE
		{$$ = shape(MOVE_OBJECT);}
	| SPLINE
		{$$ = shape(SPLINE_OBJECT);}
	| text %prec TEXT
		{$$ = shape(TEXT_OBJECT,$1);}
	| compound
		{$$ = $1;}

property:
	  HEIGHT expr
		{$$=property(Property.HEIGHT,$2);}
	| RADIUS expr
		{$$=property(Property.RADIUS,$2;}
	| WIDTH expr
		{$$=property(Property.WIDTH,$2);}
	| DIAMETER expr
		{$$=property(Property.DIAMETER,$2);}
	| object_spec expr %prec HEIGHT
		{$$=property(Property.EXPR,$2);}
	| UP
		{$$=property(Property.UP,null);}
	| UP expr
		{$$=property(Property.UP,$2);}
	| DOWN
		{$$=property(Property.DOWN,null);}
	| DOWN expr
		{$$=property(Property.DOWN,$2);}
	| RIGHT
		{$$=property(Property.RIGHT,null);}
	| RIGHT expr
		{$$=property(Property.RIGHT,$2);}
	| LEFT
		{$$=property(Property.LEFT,null);}
	| LEFT expr
		{$$=property(Property.LEFT,$2);}
	| FROM position
		{$$=property(Property.FROM,$2);}
	| TO position
		{$$=property(Property.TO,$2);}
	| AT position
		{$$=property(Property.AT,$2);}
	| WITH path
		{$$=property(Property.WITH,$2);}
	| WITH position %prec ','
		{$$=property(Property.WITH,$2);}
	| BY expr_pair
		{$$=property(Property.BY,$2);}
	| THEN
		{$$=property(Property.THEN,null);}
	| SOLID
		{$$=property(Property.SOLID,null);}
	| DOTTED
		{$$=property(Property.DOTTED,null);}
	| DOTTED expr
		{$$=property(Property.DOTTED,$2);}
	| DASHED
		{$$=property(Property.DASHED,null);}
	| DASHED expr
		{$$=property(Property.DASHED,$2);}
	| FILL
		{$$=property(Property.FILL,null);}
	| FILL expr
		{$$=property(Property.FILL,$2);}
	| XSLANTED expr
		{$$=property(Property.XSLANTED,$2);}
	| YSLANTED expr
		{$$=property(Property.YSLANTED,$2);}
	| SHADED text
		{$$=property(Property.SHADED,$2);}
	| COLORED text
		{$$=property(Property.COLORED,$2);}
	| OUTLINED text
		{$$=property(Property.OUTLINED,$2);}
	| CHOP
		{$$=property(Property.CHOP,null);}
	| CHOP expr
		{$$=property(Property.CHOP,$2);}
	| SAME
		{$$=property(Property.SAME,null);}
	| INVISIBLE
		{$$=property(Property.INVISIBLE,null);}
	| LEFT_ARROW_HEAD
		{$$=property(Property.LEFT_ARROW_HEAD,null);}
	| RIGHT_ARROW_HEAD
		{$$=property(Property.RIGHT_ARROW_HEAD,null);}
	| DOUBLE_ARROW_HEAD
		{$$=property(Property.DOUBLE_ARROW_HEAD,null);}
	| CW
		{$$=property(Property.CW,null);}
	| CCW
		{$$=property(Property.CCW,null);}
	| text %prec TEXT
		{$$=property(Property.TEXT,$1);}
	| LJUST
		{$$=property(Property.LJUST,null);}
	| RJUST
		{$$=property(Property.RJUST,null);}
	| ABOVE
		{$$=property(Property.ABOVE,null);}
	| BELOW
		{$$=property(Property.BELOW,null);}
	| THICKNESS expr
		{$$=property(Property.THICKNESS,$2);}
	| ALIGNED
		{$$=property(Property.ALIGNED,null);}
	;

text:
	TEXT
		{ $$ = $1; }
	| SPRINTF '(' TEXT sprintf_args ')'
		{
		  $$.filename = $3.filename;
		  $$.lineno = $3.lineno;
		  $$.str = do_sprintf($3.str, $4.v, $4.nv);
		  a_delete $4.v;
		  a_delete $3.str;
		}
	;

sprintf_args:
	/* empty */
		{
		  $$.v = 0;
		  $$.nv = 0;
		  $$.maxv = 0;
		}
	| sprintf_args ',' expr
		{
		  $$ = $1;
		  if ($$.nv >= $$.maxv) {
		    if ($$.nv == 0) {
		      $$.v = new double[4];
		      $$.maxv = 4;
		    }
		    else {
		      double *oldv = $$.v;
		      $$.maxv *= 2;
#if 0
		      $$.v = new double[$$.maxv];
		      memcpy($$.v, oldv, $$.nv*sizeof(double));
#else
		      // workaround for bug in Compaq C++ V6.5-033
		      // for Compaq Tru64 UNIX V5.1A (Rev. 1885)
		      double *foo = new double[$$.maxv];
		      memcpy(foo, oldv, $$.nv*sizeof(double));
		      $$.v = foo;
#endif
		      a_delete oldv;
		    }
		  }
		  $$.v[$$.nv] = $3;
		  $$.nv += 1;
		}
	;

position:
  	position_not_place
		{ $$ = $1; }
	| place
  		{
		  position pos = $1;
		  $$.x = pos.x;
		  $$.y = pos.y;
		}
	| '(' place ')'
		{
		  position pos = $2;
		  $$.x = pos.x;
		  $$.y = pos.y;
		}
	;

position_not_place:
	expr_pair
		{ $$ = $1; }
	| position '+' expr_pair
		{
		  $$.x = $1.x + $3.x;
		  $$.y = $1.y + $3.y;
		}
	| '(' position '+' expr_pair ')'
		{
		  $$.x = $2.x + $4.x;
		  $$.y = $2.y + $4.y;
		}
	| position '-' expr_pair
		{
		  $$.x = $1.x - $3.x;
		  $$.y = $1.y - $3.y;
		}
	| '(' position '-' expr_pair ')'
		{
		  $$.x = $2.x - $4.x;
		  $$.y = $2.y - $4.y;
		}
	| '(' position ',' position ')'
		{
		  $$.x = $2.x;
		  $$.y = $4.y;
		}
	| expr between position AND position
		{
		  $$.x = (1.0 - $1)*$3.x + $1*$5.x;
		  $$.y = (1.0 - $1)*$3.y + $1*$5.y;
		}
	| '(' expr between position AND position ')'
		{
		  $$.x = (1.0 - $2)*$4.x + $2*$6.x;
		  $$.y = (1.0 - $2)*$4.y + $2*$6.y;
		}
	/* the next two rules cause harmless shift/reduce warnings */
	| expr_not_lower_than '<' position ',' position '>'
		{
		  $$.x = (1.0 - $1)*$3.x + $1*$5.x;
		  $$.y = (1.0 - $1)*$3.y + $1*$5.y;
		}
	| '(' expr_not_lower_than '<' position ',' position '>' ')'
		{
		  $$.x = (1.0 - $2)*$4.x + $2*$6.x;
		  $$.y = (1.0 - $2)*$4.y + $2*$6.y;
		}
	;

between:
	BETWEEN
	| OF THE WAY BETWEEN
	;

expr_pair:
	expr ',' expr
		{
		  $$.x = $1;
		  $$.y = $3;
		}
	| '(' expr_pair ')'
		{ $$ = $2; }
	;

place:
	/* line at A left == line (at A) left */
	label							%prec CHOP
		{ $$ = $1; }
	| label corner
		{
		  path pth($2);
		  if (!pth.follow($1, & $$))
		    YYABORT;
		}
	| corner label
		{
		  path pth($1);
		  if (!pth.follow($2, & $$))
		    YYABORT;
		}
	| corner OF label
		{
		  path pth($1);
		  if (!pth.follow($3, & $$))
		    YYABORT;
		}
	| HERE
		{
		  $$.x = current_position.x;
		  $$.y = current_position.y;
		  $$.obj = 0;
		}
	;

label:
	LABEL
		{
		  place *p = lookup_label($1);
		  if (!p) {
		    lex_error("there is no place `%1'", $1);
		    YYABORT;
		  }
		  $$ = *p;
		  a_delete $1;
		}
	| nth_primitive
		{ $$.obj = $1; }
	| label '.' LABEL
		{
		  path pth($3);
		  if (!pth.follow($1, & $$))
		    YYABORT;
		}
	;

ordinal:
	ORDINAL
		{ $$ = $1; }
	| '`' any_expr TH
		{
		  // XXX Check for overflow (and non-integers?).
		  $$ = (int)$2;
		}
	;

optional_ordinal_last:
	LAST
		{ $$ = 1; }
  	| ordinal LAST
		{ $$ = $1; }
	;

nth_primitive:
	ordinal object_type
		{
		  int count = 0;
		  object *p;
		  for (p = olist.head; p != 0; p = p->next)
		    if (p->type() == $2 && ++count == $1) {
		      $$ = p;
		      break;
		    }
		  if (p == 0) {
		    lex_error("there is no %1%2 %3", $1, ordinal_postfix($1),
			      object_type_name($2));
		    YYABORT;
		  }
		}
	| optional_ordinal_last object_type
		{
		  int count = 0;
		  object *p;
		  for (p = olist.tail; p != 0; p = p->prev)
		    if (p->type() == $2 && ++count == $1) {
		      $$ = p;
		      break;
		    }
		  if (p == 0) {
		    lex_error("there is no %1%2 last %3", $1,
			      ordinal_postfix($1), object_type_name($2));
		    YYABORT;
		  }
		}
	;

object_type:
	BOX
  		{ $$ = BOX_OBJECT; }
	| CIRCLE
		{ $$ = CIRCLE_OBJECT; }
	| ELLIPSE
		{ $$ = ELLIPSE_OBJECT; }
	| ARC
		{ $$ = ARC_OBJECT; }
	| LINE
		{ $$ = LINE_OBJECT; }
	| ARROW
		{ $$ = ARROW_OBJECT; }
	| SPLINE
		{ $$ = SPLINE_OBJECT; }
	| '[' ']'
		{ $$ = BLOCK_OBJECT; }
	| TEXT
		{ $$ = TEXT_OBJECT; }
	;

label_path:
 	'.' LABEL
		{ $$ = new path($2); }
	| label_path '.' LABEL
		{
		  $$ = $1;
		  $$->append($3);
		}
	;

relative_path:
	corner							%prec CHOP
		{ $$ = new path($1); }
	/* give this a lower precedence than LEFT and RIGHT so that
	   [A: box] with .A left == [A: box] with (.A left) */
  	| label_path						%prec TEXT
		{ $$ = $1; }
	| label_path corner
		{
		  $$ = $1;
		  $$->append($2);
		}
	;

path:
	relative_path
		{ $$ = $1; }
	| '(' relative_path ',' relative_path ')'
		{
		  $$ = $2;
		  $$->set_ypath($4);
		}
	/* The rest of these rules are a compatibility sop. */
	| ORDINAL LAST object_type relative_path
		{
		  lex_warning("`%1%2 last %3' in `with' argument ignored",
			      $1, ordinal_postfix($1), object_type_name($3));
		  $$ = $4;
		}
	| LAST object_type relative_path
		{
		  lex_warning("`last %1' in `with' argument ignored",
			      object_type_name($2));
		  $$ = $3;
		}
	| ORDINAL object_type relative_path
		{
		  lex_warning("`%1%2 %3' in `with' argument ignored",
			      $1, ordinal_postfix($1), object_type_name($2));
		  $$ = $3;
		}
	| LABEL relative_path
		{
		  lex_warning("initial `%1' in `with' argument ignored", $1);
		  a_delete $1;
		  $$ = $2;
		}
	;

corner:
	DOT_N
		{ $$ = &object::north; }
	| DOT_E	
		{ $$ = &object::east; }
	| DOT_W
		{ $$ = &object::west; }
	| DOT_S
		{ $$ = &object::south; }
	| DOT_NE
		{ $$ = &object::north_east; }
	| DOT_SE
		{ $$ = &object:: south_east; }
	| DOT_NW
		{ $$ = &object::north_west; }
	| DOT_SW
		{ $$ = &object::south_west; }
	| DOT_C
		{ $$ = &object::center; }
	| DOT_START
		{ $$ = &object::start; }
	| DOT_END
		{ $$ = &object::end; }
  	| TOP
		{ $$ = &object::north; }
	| BOTTOM
		{ $$ = &object::south; }
	| LEFT
		{ $$ = &object::west; }
	| RIGHT
		{ $$ = &object::east; }
	| UPPER LEFT
		{ $$ = &object::north_west; }
	| LOWER LEFT
		{ $$ = &object::south_west; }
	| UPPER RIGHT
		{ $$ = &object::north_east; }
	| LOWER RIGHT
		{ $$ = &object::south_east; }
	| LEFT_CORNER
		{ $$ = &object::west; }
	| RIGHT_CORNER
		{ $$ = &object::east; }
	| UPPER LEFT_CORNER
		{ $$ = &object::north_west; }
	| LOWER LEFT_CORNER
		{ $$ = &object::south_west; }
	| UPPER RIGHT_CORNER
		{ $$ = &object::north_east; }
	| LOWER RIGHT_CORNER
		{ $$ = &object::south_east; }
	| NORTH
		{ $$ = &object::north; }
	| SOUTH
		{ $$ = &object::south; }
	| EAST
		{ $$ = &object::east; }
	| WEST
		{ $$ = &object::west; }
	| CENTER
		{ $$ = &object::center; }
	| START
		{ $$ = &object::start; }
	| END
		{ $$ = &object::end; }
	;

expr:
	expr_lower_than
		{ $$ = $1; }
	| expr_not_lower_than
		{ $$ = $1; }
	;

expr_lower_than:
	expr '<' expr
		{ $$ = ($1 < $3); }
	;

expr_not_lower_than:
	VARIABLE
		{
		  if (!lookup_variable($1, & $$)) {
		    lex_error("there is no variable `%1'", $1);
		    YYABORT;
		  }
		  a_delete $1;
		}
	| NUMBER
		{ $$ = $1; }
	| place DOT_X
  		{
		  if ($1.obj != 0)
		    $$ = $1.obj->origin().x;
		  else
		    $$ = $1.x;
		}			
	| place DOT_Y
		{
		  if ($1.obj != 0)
		    $$ = $1.obj->origin().y;
		  else
		    $$ = $1.y;
		}
	| place DOT_HT
		{
		  if ($1.obj != 0)
		    $$ = $1.obj->height();
		  else
		    $$ = 0.0;
		}
	| place DOT_WID
		{
		  if ($1.obj != 0)
		    $$ = $1.obj->width();
		  else
		    $$ = 0.0;
		}
	| place DOT_RAD
		{
		  if ($1.obj != 0)
		    $$ = $1.obj->radius();
		  else
		    $$ = 0.0;
		}
	| expr '+' expr
		{ $$ = $1 + $3; }
	| expr '-' expr
		{ $$ = $1 - $3; }
	| expr '*' expr
		{ $$ = $1 * $3; }
	| expr '/' expr
		{
		  if ($3 == 0.0) {
		    lex_error("division by zero");
		    YYABORT;
		  }
		  $$ = $1/$3;
		}
	| expr '%' expr
		{
		  if ($3 == 0.0) {
		    lex_error("modulus by zero");
		    YYABORT;
		  }
		  $$ = fmod($1, $3);
		}
	| expr '^' expr
		{
		  errno = 0;
		  $$ = pow($1, $3);
		  if (errno == EDOM) {
		    lex_error("arguments to `^' operator out of domain");
		    YYABORT;
		  }
		  if (errno == ERANGE) {
		    lex_error("result of `^' operator out of range");
		    YYABORT;
		  }
		}
	| '-' expr						%prec '!'
		{ $$ = -$2; }
	| '(' any_expr ')'
		{ $$ = $2; }
	| SIN '(' any_expr ')'
		{
		  errno = 0;
		  $$ = sin($3);
		  if (errno == ERANGE) {
		    lex_error("sin result out of range");
		    YYABORT;
		  }
		}
	| COS '(' any_expr ')'
		{
		  errno = 0;
		  $$ = cos($3);
		  if (errno == ERANGE) {
		    lex_error("cos result out of range");
		    YYABORT;
		  }
		}
	| ATAN2 '(' any_expr ',' any_expr ')'
		{
		  errno = 0;
		  $$ = atan2($3, $5);
		  if (errno == EDOM) {
		    lex_error("atan2 argument out of domain");
		    YYABORT;
		  }
		  if (errno == ERANGE) {
		    lex_error("atan2 result out of range");
		    YYABORT;
		  }
		}
	| LOG '(' any_expr ')'
		{
		  errno = 0;
		  $$ = log10($3);
		  if (errno == ERANGE) {
		    lex_error("log result out of range");
		    YYABORT;
		  }
		}
	| EXP '(' any_expr ')'
		{
		  errno = 0;
		  $$ = pow(10.0, $3);
		  if (errno == ERANGE) {
		    lex_error("exp result out of range");
		    YYABORT;
		  }
		}
	| SQRT '(' any_expr ')'
		{
		  errno = 0;
		  $$ = sqrt($3);
		  if (errno == EDOM) {
		    lex_error("sqrt argument out of domain");
		    YYABORT;
		  }
		}
	| K_MAX '(' any_expr ',' any_expr ')'
		{ $$ = $3 > $5 ? $3 : $5; }
	| K_MIN '(' any_expr ',' any_expr ')'
		{ $$ = $3 < $5 ? $3 : $5; }
	| INT '(' any_expr ')'
		{ $$ = $3 < 0 ? -floor(-$3) : floor($3); }
	| RAND '(' any_expr ')'
		{ $$ = 1.0 + floor(((rand()&0x7fff)/double(0x7fff))*$3); }
	| RAND '(' ')'
		{
		  /* return a random number in the range [0,1) */
		  /* portable, but not very random */
		  $$ = (rand() & 0x7fff) / double(0x8000);
		}
	| SRAND '(' any_expr ')'
		{
		  $$ = 0;
		  srand((unsigned int)$3);
		}
	| expr LESSEQUAL expr
		{ $$ = ($1 <= $3); }
	| expr '>' expr
		{ $$ = ($1 > $3); }
	| expr GREATEREQUAL expr
		{ $$ = ($1 >= $3); }
	| expr EQUALEQUAL expr
		{ $$ = ($1 == $3); }
	| expr NOTEQUAL expr
		{ $$ = ($1 != $3); }
	| expr ANDAND expr
		{ $$ = ($1 != 0.0 && $3 != 0.0); }
	| expr OROR expr
		{ $$ = ($1 != 0.0 || $3 != 0.0); }
	| '!' expr
		{ $$ = ($2 == 0.0); }

	;

%%

/* bison defines const to be empty unless __STDC__ is defined, which it
isn't under cfront */

#ifdef const
#undef const
#endif

static struct {
  const String name;
  double val;
  int scaled;		     // non-zero if val should be multiplied by scale
} defaults_table[] = {
  { "arcrad", .25, 1 },
  { "arrowht", .1, 1 },
  { "arrowwid", .05, 1 },
  { "circlerad", .25, 1 },
  { "boxht", .5, 1 },
  { "boxwid", .75, 1 },
  { "boxrad", 0.0, 1 },
  { "dashwid", .05, 1 },
  { "ellipseht", .5, 1 },
  { "ellipsewid", .75, 1 },
  { "moveht", .5, 1 },
  { "movewid", .5, 1 },
  { "lineht", .5, 1 },
  { "linewid", .5, 1 },
  { "textht", 0.0, 1 },
  { "textwid", 0.0, 1 },
  { "scale", 1.0, 0 },
  { "linethick", -1.0, 0 },		// in points
  { "fillval", .5, 0 },
  { "arrowhead", 1.0, 0 },
  { "maxpswid", 8.5, 0 },
  { "maxpsht", 11.0, 0 },
};

place *lookup_label(const String label)
{
  saved_state *state = current_saved_state;
  PTABLE(place) *tbl = current_table;
  for (;;) {
    place *pl = tbl->lookup(label);
    if (pl)
      return pl;
    if (!state)
      return 0;
    tbl = state->tbl;
    state = state->prev;
  }
}

void define_label(const String label, const place *pl)
{
  place *p = new place[1];
  *p = *pl;
  current_table->define(label, p);
}

int lookup_variable(const String name, double *val)
{
  place *pl = lookup_label(name);
  if (pl) {
    *val = pl->x;
    return 1;
  }
  return 0;
}

void define_variable(const String name, double val)
{
  place *p = new place[1];
  p->obj = 0;
  p->x = val;
  p->y = 0.0;
  current_table->define(name, p);
  if (strcmp(name, "scale") == 0) {
    // When the scale changes, reset all scaled pre-defined variables to
    // their default values.
    for (unsigned int i = 0;
	 i < sizeof(defaults_table)/sizeof(defaults_table[0]); i++) 
      if (defaults_table[i].scaled)
	define_variable(defaults_table[i].name, val*defaults_table[i].val);
  }
}

// called once only (not once per parse)

void parse_init()
{
  current_direction = RIGHT_DIRECTION;
  current_position.x = 0.0;
  current_position.y = 0.0;
  // This resets everything to its default value.
  reset_all();
}

void reset(const String nm)
{
  for (unsigned int i = 0;
       i < sizeof(defaults_table)/sizeof(defaults_table[0]); i++)
    if (strcmp(nm, defaults_table[i].name) == 0) {
      double val = defaults_table[i].val;
      if (defaults_table[i].scaled) {
	double scale;
	lookup_variable("scale", &scale);
	val *= scale;
      }
      define_variable(defaults_table[i].name, val);
      return;
    }
  lex_error("`%1' is not a predefined variable", nm);
}

void reset_all()
{
  // We only have to explicitly reset the pre-defined variables that
  // aren't scaled because `scale' is not scaled, and changing the
  // value of `scale' will reset all the pre-defined variables that
  // are scaled.
  for (unsigned int i = 0;
       i < sizeof(defaults_table)/sizeof(defaults_table[0]); i++)
    if (!defaults_table[i].scaled)
      define_variable(defaults_table[i].name, defaults_table[i].val);
}

// called after each parse

void parse_cleanup()
{
  while (current_saved_state != 0) {
    delete current_table;
    current_table = current_saved_state->tbl;
    saved_state *tem = current_saved_state;
    current_saved_state = current_saved_state->prev;
    delete tem;
  }
  assert(current_table == &top_table);
  PTABLE_ITERATOR(place) iter(current_table);
  const String key;
  place *pl;
  while (iter.next(&key, &pl))
    if (pl->obj != 0) {
      position pos = pl->obj->origin();
      pl->obj = 0;
      pl->x = pos.x;
      pl->y = pos.y;
    }
  while (olist.head != 0) {
    object *tem = olist.head;
    olist.head = olist.head->next;
    delete tem;
  }
  olist.tail = 0;
  current_direction = RIGHT_DIRECTION;
  current_position.x = 0.0;
  current_position.y = 0.0;
}

const String ordinal_postfix(int n)
{
  if (n < 10 || n > 20)
    switch (n % 10) {
    case 1:
      return "st";
    case 2:
      return "nd";
    case 3:
      return "rd";
    }
  return "th";
}

const String object_type_name(object_type type)
{
  switch (type) {
  case BOX_OBJECT:
    return "box";
  case CIRCLE_OBJECT:
    return "circle";
  case ELLIPSE_OBJECT:
    return "ellipse";
  case ARC_OBJECT:
    return "arc";
  case SPLINE_OBJECT:
    return "spline";
  case LINE_OBJECT:
    return "line";
  case ARROW_OBJECT:
    return "arrow";
  case MOVE_OBJECT:
    return "move";
  case TEXT_OBJECT:
    return "\"\"";
  case BLOCK_OBJECT:
    return "[]";
  case OTHER_OBJECT:
  case MARK_OBJECT:
  default:
    break;
  }
  return "object";
}

static char sprintf_buf[1024];

String format_number(const String form, double n)
{
  if (form == 0)
    form = "%g";
  return do_sprintf(form, &n, 1);
}

String do_sprintf(const String form, const double *v, int nv)
{
  String result;
  int i = 0;
  String one_format;
  while (*form) {
    if (*form == '%') {
      one_format += *form++;
      for (; *form != '\0' && strchr("#-+ 0123456789.", *form) != 0; form++)
	one_format += *form;
      if (*form == '\0' || strchr("eEfgG%", *form) == 0) {
	lex_error("bad sprintf format");
	result += one_format;
	result += form;
	break;
      }
      if (*form == '%') {
	one_format += *form++;
	one_format += '\0';
	snprintf(sprintf_buf, sizeof(sprintf_buf),
		 "%s", one_format.contents());
      }
      else {
	if (i >= nv) {
	  lex_error("too few arguments to snprintf");
	  result += one_format;
	  result += form;
	  break;
	}
	one_format += *form++;
	one_format += '\0';
	snprintf(sprintf_buf, sizeof(sprintf_buf),
		 one_format.contents(), v[i++]);
      }
      one_format.clear();
      result += sprintf_buf;
    }
    else
      result += *form++;
  }
  result += '\0';
  return strsave(result.contents());
}
