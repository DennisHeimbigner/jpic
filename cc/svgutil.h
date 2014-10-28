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

#ifndef NULL
#define NULL 0
#endif

#include "svector.h"

//////////////////////////////////////////////////

struct pair {char* key; char* value;};

class config : public svector<pair>
{
public:
    config();
    ~config();
    char* expunge(const char* key);
    char* tostring();
    char* get(const char* key);
    int getint(const char* key);
    double getdouble(const char* key);
    void set(const char* key, const char* value);
    void setint(const char* key,int);
    void setdouble(const char* key,double);
};

//////////////////////////////////////////////////

struct blockmark
{
    int tablemark; // mark current point in the command table
};

class block_stack : public svector<blockmark>
{
protected:
    config* table;
public:
    block_stack();
    ~block_stack();
    void set_table(config* t);
    void push();
    void pop();    
};

extern int debug_level;

void debug(int, char* fmt,...);
void trace(int, char* fcn, char* fmt,...);

#endif /*SVG_SUPPORT*/
