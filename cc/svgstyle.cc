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

#include "pic.h"

#ifdef SVG_SUPPORT
#include "common.h"
#include <stdarg.h>

#include "svgstyle.h"

//////////////////////////////////////////////////

// Define some fill and color related constants
const double FILLNONE = -1.0;

// Conversion factor for inches to pixels: assume hi-dpi display
const double DEFAULTIN2PX = 100.0;

const char* DEFAULTFONTSIZE = "12";
const char* DEFAULTFONTSTYLE = "normal";
const char* DEFAULTFONTWEIGHT = "normal";
const char* DEFAULTFONTFAMILY = "Arial";
const char* DEFAULTFILL = "-1.0";
const char* DEFAULTPENSIZE = "1"; // pixels
const char* DEFAULTDOTSIZE = "2"; // pixels
const int DEFAULT_VERTICAL_FUDGEFACTOR = 4; // to get text to center properly

const char* DEFAULTCOLOR = "none";
const char* DEFAULTOUTLINE = "black";

//////////////////////////////////////////////////

svg_style::svg_style() {set_table(NULL);}
svg_style::~svg_style() {}

void svg_style::set_table(config* t) {table = t;}

void svg_style::set_defaults()
{
    table->set("font-size",DEFAULTFONTSIZE);
    table->set("font-style",DEFAULTFONTSTYLE);
    table->set("font-weight",DEFAULTFONTWEIGHT);
    table->set("font-family",DEFAULTFONTFAMILY);
    table->set("stroke-width",DEFAULTPENSIZE);
    table->set("dot-size",DEFAULTDOTSIZE);
    table->set("grayscale",DEFAULTFILL); // this the fill factor
//    table->set("color",NULL); //default is NULL
//    table->set("outline-color",NULL); //default is NULL

    // This is a factor to divide font size to get an approximation
    // of the interline spacing
    table->setint("interline",DEFAULT_VERTICAL_FUDGEFACTOR);
    // set the conversion from inches to pixels
    table->setdouble("in2px",DEFAULTIN2PX);
}

//////////////////////////////////////////////////

// common cases
double svg_style::get_fill() {return table->getdouble("grayscale");}
int svg_style::get_font_size() {return table->getint("font-size");}
char* svg_style::get_color() {return table->get("color");}
char* svg_style::get_outline_color() {return table->get("outline-color");}
//////////////////////////////////////////////////

// convert inches to pixels
int svg_style::pixels(double x)
{
  return int(x*table->getdouble("in2px") + .5);
}

position svg_style::transform(const position &pos)
{
  double tx = (pos.x - lower_left.x)/scale;
  double ty = (upper_right.y - pos.y)/scale;
  return position(tx,ty);
}

void svg_style::dot(const position &pos, const line_type &lt)
{
/*
  if (zero_length_line_flag) {
    line_type slt = lt;
    slt.type = line_type::solid;
    line(pos, &pos, 1, slt);
  }
  else {
    int dot_rad = int(lt.thickness*(1000.0/(72.0*2)) + .5);
    if (dot_rad == 0)
      dot_rad = 1;
    position p = transform(pos);
  }
*/
}
//////////////////////////////////////////////////

void svg_style::set_coords(double sc, const position &ll, const position &ur)
{
  upper_right.x = ur.x;
  upper_right.y = ur.y;
  lower_left.x = ll.x;
  lower_left.y = ll.y;
  scale = sc;
  height = (ur.y - ll.y)/scale;
  width = (ur.x - ll.x)/scale;
}


double svg_style::get_height() {return height;}
double svg_style::get_width() {return width;}
double svg_style::get_scale() {return scale;}

//////////////////////////////////////////////////

char* svg_style::get_shape_style(const line_type& lt, double fill)
{
  svector<char> props;
  char buf[4096];

  set_fill(fill);

  trace(2,"get_shape_style","Ld",&lt,fill);

  // Decide on the fill color, if any.
  // The precedence is as follows:
  // 1. isfillable == 0 takes precedence over everything else
  // 2. Specified color takes precedence over fill.

  if(get_color() != 0) {
    sprintf(buf,"fill=\"%s\"",get_color());
    props.append(strdup(buf));
  } else if(fill == FILLNONE) {
    sprintf(buf,"fill=\"none\"");
    props.append(strdup(buf));
  } else if(fill >= 0.0) {
    sprintf(buf,"fill=\"%s\"",get_fillcolor(fill));
    props.append(strdup(buf));
  } else
    assert(0);

  // Decide on the outline color, if any.
  // The precedence is as follows:
  // 1. Specified outline color takes precedence over fill.
  // 2. else outline = black

  if(get_outline_color() != 0) {
    sprintf(buf,"stroke=\"%s\"",get_outline_color());
    props.append(strdup(buf));
  } else {
    sprintf(buf,"stroke=\"%s\"",get_fillcolor(fill));
    props.append(strdup(buf));
  }

  sprintf(buf,"stroke-width=\"%d\"",pensize(lt.thickness));
  props.append(strdup(buf));

  int interval = pixels(lt.dash_width);
  int dot_size = table->getint("dot-size");
  switch(lt.type) {
    case line_type::dashed: {
      sprintf(buf,"stroke-dasharray=\"%d %d\"",interval,interval);
      props.append(strdup(buf));
    } ; break;
    case line_type::dotted: {
      sprintf(buf,"stroke-dasharray=\"%d %d\"",dot_size,interval);
      props.append(strdup(buf));
    } ; break;
    default: break;
  }

  return build_style(props);
}

char* svg_style::get_line_style(const line_type& lt)
{
  svector<char> props;
  char buf[4096];

  trace(2,"get_line_style","L",&lt);

  // Decide on the line color, if any.
  // The precedence is as follows:
  // 1. Specified outline color takes precedence over fill.
  // 2. else outline = black

  double fill = get_fill();
  if(get_outline_color() != 0) {
    sprintf(buf,"stroke=\"%s\"",get_outline_color());
    props.append(strdup(buf));
  } else {
    sprintf(buf,"stroke=\"%s\"",get_fillcolor(fill));
    props.append(strdup(buf));
  }

  sprintf(buf,"stroke-width=\"%d\"",pensize(lt.thickness));
  props.append(strdup(buf));

  char* pattern = 0;
  switch(lt.type) {
    case line_type::dashed: pattern=table->get("dash-pattern"); break;
    case line_type::dotted: pattern=table->get("dot-pattern"); break;
  }

  if(pattern != 0) {
    sprintf(buf,"stroke-dasharray=\"%s\"",pattern);
    props.append(strdup(buf));
  }

  // never fill
  sprintf(buf,"fill=\"none\"");
  props.append(strdup(buf));

  return build_style(props);
}

char* svg_style::get_text_style(const text_piece &tp)
{
  svector<char> props;
  char buf[4096];

  trace(2,"get_text_style","T",&tp);

  // Decide on the text color, if any.
  // The precedence is as follows:
  // 1. Specified outline color takes precedence over fill.
  // 2. else outline = black

  double fill = get_fill();
  if(get_outline_color() != 0) {
    sprintf(buf,"stroke=\"%s\"",get_outline_color());
    props.append(strdup(buf));
  } else {
    sprintf(buf,"stroke=\"%s\"",get_fillcolor(fill));
    props.append(strdup(buf));
  }
  char* fontinfo = get_font_info();
  if(fontinfo != NULL) props.append(fontinfo);
  return build_style(props);
}

char* svg_style::get_text_anchor(text_piece &tp)
{
  char* just;
  switch (tp.adj.h) {
    case CENTER_ADJUST: just = "middle"; break;
    case LEFT_ADJUST: just = "start"; break;
    case RIGHT_ADJUST: just = "end"; break;
    default: assert(0);
  }
  return just;
}

char* svg_style::get_font_info()
{
  svector<char> props;
  char buf[4096];
  sprintf(buf,"font-size=\"%d\"",get_font_size());
  props.append(strdup(buf));
  sprintf(buf,"font-family=\"%s\"",table->get("font-family"));
  props.append(strdup(buf));
  sprintf(buf,"font-style=\"%s\"",table->get("font-style"));
  props.append(strdup(buf));
  sprintf(buf,"font-weight=\"%s\"",table->get("font-weight"));
  props.append(strdup(buf));
  return build_style(props);
}

char* svg_style::build_style(svector<char> &props)
{
  // compute style string size
  char* stylebuf = 0;
  int sz = props.size();
  int len = 0;
  for(int i = 0;i<sz;i++) len += strlen(props.ith(i));
  if(len == 0) {
    stylebuf = strdup("");
  } else {  
      stylebuf = (char*)malloc(len+sz+1);//allow for leading blanks
                                         // and trailing null
      stylebuf[0] = 0;
      for(int i = 0;i<sz;i++) {
	strcat(stylebuf," ");
	strcat(stylebuf,props.ith(i));
      }
  }
debug(2,"stylebuf=|%s|\n",stylebuf);
  return stylebuf;
}

int svg_style::get_font_height()
{
    int fsize = get_font_size();
    return (fsize - get_font_interline());
}

int svg_style::get_font_interline()
{
  int fsize = get_font_size();
  int dy = (fsize/table->getint("interline"));
  return dy;
}


int svg_style::get_vertical_adjustment(const text_piece &tp)
{
  int height = get_font_height();
  int dy;
  // svg starts with the equivalent of NONE for text.
  switch (tp.adj.v) {
    case NONE_ADJUST: dy = 0; break;
    case ABOVE_ADJUST: dy = -(height/2); break;
    case BELOW_ADJUST: dy = (height/2); break;
    default: assert(0);
  }
  return dy;
}

void svg_style::set_colors(char *c, char *o)
{
    trace(2,"set_color","ss",c,o);
    if(c == NULL) table->expunge("color"); else table->set("color",c);
    if(c == NULL) table->expunge("outline_color"); else table->set("outline_color",o);
}

void svg_style::reset_colors()
{
    set_colors(NULL,NULL);
}

/////////////////////////

void svg_style::set_fill(double f)
{
    trace(2,"set_fill","d",f);
    table->setdouble("grayscale",f);
}

char* svg_style::get_fillcolor(double f)
{
  // Since SVG supports 24 bit RGB colors,
  // convert fill to a gray scale color
  // compute (1.0 - f)*256=xx and set color to be #xxxxxx
  // sanity checks
  if(f > 1.0) f = 1.0;
  if(f < 0.0) f = 0.0;
  double f1 = 1.0 - f;
  int xx = (int)(f*256.00);
  char* color = strdup("#000000");
  sprintf(color,"#%02x%02x%02x",xx,xx,xx);
  return color;
}

char* svg_style::get_filloutline(double f)
{
  return strdup("#000000");
}    

void svg_style::reset_fill()
{
    set_fill(atof(DEFAULTFILL));
}

/////////////////////////

int svg_style::pensize(double thickness)
{
    if(thickness >= 0.0) return (int)thickness;
    return table->getint("stroke-width");
}

#endif /*SVG_SUPPORT*/
