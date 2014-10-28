// -*- C++ -*-
/* Copyright (C) 1989, 1990, 1991, 1992 Free Software Foundation, Inc.
     Written by James Clark (jjc@jclark.com)

This file is part of groff.

groff is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free
Software Foundation; either version 2, or (at your option) any later
version.

groff is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License along
with groff; see the file COPYING.  If not, write to the Free Software
Foundation, 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA. */

/*
SVG (Scalable Vector Graphics) Output for PIC.
Developed by
    Dennis Heimbigner
    Computer Science Dept.
    University of Colorado, Boulder

Extensions:
1. The "command" command has been extended to support the capture
   of (key,value) pairs. Strings not beginning with ">" are passed
   thru unchanged.
  A string of the form 
      [>][ ]*[!=]+[=].*
  is treated as a key = value pair.
  The pair is inserted into a linked list and the style (and other)
  procedures look in that list for relevant parameters.
  This allows the .pic code to pass thru, for example, font info.
  The keys currently utilized are as follows:
    font-size, font-style, font-weight, font-family
    stroke-width
    dot-size
    grayscale (this is the "fill" value)
    color, outline-color,
    interline
    in2px

Issues:
1. Text layout is still not quite right for multi-line text.
2. Firefox and IE do not seem to recognize dimensions such as em,px,in.
   So, this code only outputs dimensionless values, which is equivalent
   to pixels. Since pic sends inch values, I use a conversion of
   100 pixels == 1in (defined by DEFAULTIN2PX, but modifiable
   using the "in2px" configuration parameter).
3. Arrowheads on the end of arcs or splines do not always appear
   with the correct rotation. See ex3.pic for an example.
4. For some reason, pic does not appear to compute the height and width
   of the picture correctly, which causes some svg viewers to cut off
   part of the picture.  See ex2.pic for an example. This can be corrected
   using the nested svg trick described below.
5. SVG does not really do dotted lines, so I currently simulate
   with short dashes.  It might be possible to do this by using
   text along a path.

Misc.
1. It appears that one can nest <svg>, which means that 
   one can reset the viewBox using the "command" command.
   See ex1.pic for an example

Issues in coordinate systems and units
PIC:
- positive x-axis is up
- positive y-axis is up
- coordinates may be positive or negative
- units are in inches
SVG:
- positive x-axis is up
- positive y-axis is down
- coordinates may be positive only
- currently, it appears that firefox and IE only understand
  unitless measures == user-unit == pixels

*/

#include "pic.h"

#ifdef SVG_SUPPORT
#include "common.h"

#include "svgstyle.h"

// For defaulting info see the set_defaults function below

int debug_level = 0;

class svg_output : public output {
private:
  svg_style* style;
  block_stack *blocks;
  config* table;
public:
  svg_output();
  ~svg_output();
  void start_picture(double, const position &ll, const position &ur);
  void finish_picture();
  void text(const position &, text_piece *, int, double);
  void line(const position &, const position *, int n,
	    const line_type &);
  void rounded_box(const position &, const distance &, double,
			   const line_type &, double);
  void polygon(const position *, int n,
	       const line_type &, double);
  void spline(const position &, const position *, int n,
	      const line_type &);
  void arc(const position &, const position &, const position &,
	   const line_type &);
  void circle(const position &, double rad, const line_type &, double);
  void ellipse(const position &, const distance &, const line_type &, double);
  void command(const char *, const char *, int);
  void set_color(char *, char *);
  void reset_color();
  char *get_last_filled();
  char *get_outline_color();
  int supports_filled_polygons();
  void begin_block(const position &ll, const position &ur);
  void end_block();
protected:
  void set_defaults();
  int pixels(double x);
  position transform(const position &);
};

void set_debug_level(int d) {debug_level=d;}

output *make_svg_output()
{
  return new svg_output();
}

svg_output::svg_output()
{
  blocks = new block_stack();
  style = new svg_style();
  table = new config();
  blocks->set_table(table);
  style->set_table(table);
  style->set_defaults();
}

svg_output::~svg_output()
{
}

void svg_output::set_defaults()
{
    style->set_defaults();
}

// convert inches to pixels
inline int svg_output::pixels(double x) {return style->pixels(x);}
inline position svg_output::transform(const position &pos)
	{return style->transform(pos);}

//////////////////////////////////////////////////

void svg_output::start_picture(double sc, const position &ll,
			       const position &ur)
{
  trace(1,"start","dPP",sc,&ll,&ur);
  style->set_coords(compute_scale(sc,ll,ur),ll,ur);
  printf("<?xml version=\"1.0\" standalone=\"no\"?>\n");
  printf("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n");
  printf("\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");
  printf("<svg width=\"%d\" height=\"%d\" version=\"1.0\"\n",
	  pixels(style->get_width()),pixels(style->get_height()));
  printf("xmlns=\"http://www.w3.org/2000/svg\">\n");
}

void svg_output::finish_picture()
{
  trace(1,"finish",0);
  style->reset_fill();	// force it to be reset for each picture
  style->reset_colors();
  printf("</svg>\n");
}

void svg_output::text(const position &center, text_piece *v, int n, double angle)
{
  // angle argument currently ignored
  trace(1,"text","PvT",&center,n,v);
debug(2,"\tinter=%d\n",style->get_font_interline());
debug(2,"\theight=%d\n",style->get_font_height());
debug(2,"\tsize=%d\n",style->get_font_size());
  position tc = transform(center);
  // treat single line different from multiple lines
  if(n == 1) {
      int i = 0;
      // adjust text to start centered vertically
      int dy = style->get_font_height()/2;
      int vjust = style->get_vertical_adjustment(v[i]);
      dy += vjust;
      char* lstyle = style->get_text_style(v[i]);
      char* anchor = style->get_text_anchor(v[i]);
      printf("<text x=\"%d\" y=\"%d\" dy=\"%d\"",pixels(tc.x),pixels(tc.y),dy);
      if(angle != 0.0) printf(" rotate=%.3f",angle);
      printf(lstyle);
      printf(" text-anchor=\"%s\"",anchor);
      printf(">");
      printf(v[i].text);  
      printf("</text>\n");
  } else { 
      // adjust text to start centered vertically
      // (this still is not quite correct)
      int inter = style->get_font_interline();
      int sz = style->get_font_size();;
      int txtht = (n*sz) + inter;
      int dy0 = - (txtht/2);
debug(2,"\tdy0=%d\n",dy0);
      printf("<text x=\"%d\" y=\"%d\"",
		pixels(tc.x),pixels(tc.y)+dy0);
      printf(">\n");
      for(int i=0;i<n;i++) {
        char* lstyle = style->get_text_style(v[i]);
debug(2,"text[%d]: style=|%s|\n",i,lstyle);
	int dy1 = sz;
        int vjust = style->get_vertical_adjustment(v[i]);
	dy1 += vjust;
	char* anchor = style->get_text_anchor(v[i]);
	printf("<tspan x=\"%d\" dy=\"%d\"",pixels(tc.x),dy1);
        if(angle != 0.0) printf(" rotate=%.3f",angle);
        printf(lstyle);
	printf(" text-anchor=\"%s\"",anchor);
        printf(">");
        printf(v[i].text);  
	printf("</tspan>\n");
debug(2,"text[%d]: vjust=%d y=%d dy0=%d \n",i,vjust,pixels(tc.y),dy0);
      }
      printf("</text>\n"); 
  }
}

void svg_output::line(const position &start, const position *v, int n,
		      const line_type &lt)
{
  trace(1,"line","iLPvP",n,&lt,&start,n,v);
  printf("<polyline points=\"");
  position p = transform(start);
  printf("%d,%d", pixels(p.x),pixels(p.y));
  for(int i = 0; i < n; i++) {
    p = transform(v[i]);
    printf(" %d,%d", pixels(p.x),pixels(p.y));
  }
  printf("\"");
  char* lstyle = style->get_line_style(lt);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::rounded_box(const position &center, const distance &dim,
                             double xrad, const line_type &lt, double fill)
{
  trace(1,"rect","PDdLd",&center,&dim,xrad,&lt,fill);
  // convert center+(h,w) to corner+(h,w)
  position corner;
  corner.x = center.x - (dim.x/2.0);
  corner.y = center.y + (dim.y/2.0);
  position tc = transform(corner);
  printf("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" rx=\"%d\"",
		pixels(tc.x),pixels(tc.y),
		pixels(dim.x),pixels(dim.y),pixels(xrad));
  char* lstyle = style->get_shape_style(lt,fill);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::polygon(const position *v, int n,
			 const line_type &lt, double fill)
{
  trace(1,"polygon","diLvP",fill,n,&lt,n,v);
  printf("<polygon points=\"");
  for(int i = 0; i < n; i++) {
    position p = transform(v[i]);
    if(i != 0) putchar(' ');
    printf("%d,%d", pixels(p.x),pixels(p.y));
  }
  printf("\"");
  char* lstyle = style->get_shape_style(lt,fill);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::spline(const position &start, const position *v, int n,
			const line_type &lt)
{
  trace(1,"spline","PvPL",&start,n,v,&lt);
  if(n < 2) {debug(2,"Spline: too few points\n"); return;}
  printf("<path d=\"");
  position p = transform(start);
  printf("M%d %d", pixels(p.x),pixels(p.y));
  // Check for simple cubic or quadratic spline
  if(n > 3) {
    debug(2,"Spline: %d control points not implemented\n",n-1);
    return;
  }
  printf(" %s",(n==3?"C":"Q")); // cubic vs quadratic spline
  for(int i = 0; i < n; i++) {
    p = transform(v[i]);
    if(i > 0) printf(" ");
    printf("%d %d", pixels(p.x),pixels(p.y));
  }
  printf("\"");
  char* lstyle = style->get_line_style(lt);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::arc(const position &start0, const position &cent0,
		     const position &end0, const line_type &lt)
{
  trace(1,"arc","PPPL",&start0,&cent0,&end0,&lt);

  // transform coordinates
  position start = transform(start0);
  position cent = transform(cent0);
  position end = transform(end0);

  // Compute the |distance| between start and end
  double d0 = hypot(end.x - start.x,end.y - start.y);
  // Compute the |distance| between start and center
  double d1 = hypot(cent.x - start.x,cent.y - start.y);

  // compute the angle of rotation from start to end
  double theta0 = atan2(end.y - start.y,end.x - start.x);
  // compute the angle of rotation from start to center
  double theta1 = atan2(cent.y - start.y,cent.x - start.x);

debug(2,"\td0=%g d1=%g theta0=%g theta1=%g\n",d0,d1,theta0,theta1);

  // (elliptical) arc in svg takes seven parameters:
  //     rx ry x-axis-rotation large-arc-flag sweep-flag x y

  int largearc = 0; // we always want the small arc
//  int sweep = (theta1 >= 0 && theta1 <= PI/2.0?1:0);
  int sweep = 0; // apparently svg does this correctly
  double rx = d1;
  double ry = rx;  // We assume rx == ry.
  // build a path
  printf("<path d=\"");
  // First move to the start point
  printf("M%d %d",pixels(start.x),pixels(start.y));
  // Now do an arc to the end point: rx,ry,rotation,large-arc,sweep,x,y
  printf(" A%d %d %.3f %d %d %d %d\"",
		pixels(rx),pixels(ry),
		theta0,
		largearc, sweep,
		pixels(end.x),pixels(end.y));
  char* lstyle = style->get_line_style(lt);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::circle(const position &cent, double rad,
			const line_type &lt, double fill)
{
  trace(1,"circle","PdLd",&cent,rad,&lt,fill);
  position tc = transform(cent);
  printf("<circle cx=\"%d\" cy=\"%d\" r=\"%d\"",
		pixels(tc.x),pixels(tc.y),pixels(rad));
  char* lstyle = style->get_shape_style(lt,fill);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::ellipse(const position &cent, const distance &dim,
			 const line_type &lt, double fill)
{
  trace(1,"ellipse","PDLd",&cent,&dim,&lt,fill);
  position tc = transform(cent);
  printf("<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\"",
		pixels(tc.x),pixels(tc.y),pixels(dim.x/2.0),pixels(dim.y/2.0));
  char* lstyle = style->get_shape_style(lt,fill);
  printf(lstyle);
  printf("/>\n");
}

void svg_output::begin_block(const position &ll, const position &ur)
{
  blocks->push();
}

void svg_output::end_block()
{
  blocks->pop();
}

void svg_output::command(const char *s, const char *filename, int lineno)
{
  // filename and lineno are ignored
  // Strings beginning with ">" are treated special;
  // test is passed thru unchanged.
  // A string of the form 
  //	 [>][ ]*[!=]+[=].*
  // is treated as a name = value pair.
  // The pair is inserted into a linked list and the style
  // procedures look in that list  for relevant style parameters
  // This allows the .pic code to pass thru, for example, font info.  
  if(s == 0) return;
  trace(1,"command","s",s);
  if(*s != '>') {
    // pass thru with trailing newline
    printf("%s\n",s);
    return;
  }
  // parse name,value pair
  s++; // skip leading '>'
  while(*s == ' ') s++; // skip leading blanks
  const char* p = strchr(s,'=');
  if(p == 0) p = s+strlen(s);
  int len = (p - s);
  char* name = (char*)malloc(len+1);
  strncpy(name,s,len);
  p++; // skip =
  name[len]='\0'; // make sure
  len = strlen(p);
  char* value = (char*)malloc(len+1);
  strncpy(value,p,len);
  value[len]='\0'; // make sure
  table->set(name,value);
debug(2,"cmd: {%s}\n",table->tostring());
}

int svg_output::supports_filled_polygons()
{
  return 1;
}

/////////////////////////

char *svg_output::get_last_filled() {return style->get_color();}

char *svg_output::get_outline_color() {return style->get_outline_color();}

void svg_output::set_color(char *c, char *o)
{
  trace(1,"set_color","ss",c,o);
  style->set_colors(c,o);
}

void svg_output::reset_color()
{
    style->reset_colors();
}

/////////////////////////

#endif

