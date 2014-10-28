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

#ifdef SVG_SUPPORT

#ifndef SVGSTYLE_H
#define SVGSTYLE_H

#include "svgutil.h"

#ifndef NULL
#define NULL 0
#endif

//////////////////////////////////////////////////

class svg_style
{
protected:
    config* table;
    position lower_left;
    position upper_right;
    double height;
    double width;
    double scale;
public:
    svg_style();
    ~svg_style();
    void set_defaults();
    void set_table(config*);
    void set_coords(double sc, const position &ll, const position &ur);
    char* get_shape_style(const line_type& lt, double f);
    char* get_line_style(const line_type& lt);
    char* get_text_style(const text_piece& tp);
    char* get_text_anchor(text_piece &tp);
    char* get_font_info();
    void set_colors(char *,char*);
    void reset_colors();
    char *get_color();
    char *get_outline_color();
    void begin_block();
    void end_block();
    void set_fill(double);
    double get_fill();
    void reset_fill();
    char* get_fillcolor(double);
    char* get_filloutline(double);
    int get_font_height(); // approximate
    int get_font_interline(); // approximage
    int get_vertical_adjustment(const text_piece&);
public:
    int pensize(double thickness);
    int get_font_size(); // convenience
    void dot(const position &, const line_type &);
    position transform(const position &);
    int pixels(double x);
    double get_height();
    double get_width();
    double get_scale();
protected:
    char* build_style(svector<char>&);
};

#endif /*SVGSTYLE_H*/

#endif /*SVG_SUPPORT*/
