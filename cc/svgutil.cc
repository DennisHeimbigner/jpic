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

#include "svgutil.h"

//////////////////////////////////////////////////

config::config() {}
config::~config() {}

char* config::expunge(const char* key)
{
    char* value = NULL;
    pair* p = NULL;
    int sz = size();
    for(int i=0;i<sz;i++) {
	p = (pair*)ith(i);
	if(strcmp(key,p->key)==0) {
	    svector<pair>::remove(i);
	    break;
	}
    }
    return value;
}

char* config::get(const char* key)
{
    int sz = size();
    char* value = NULL;
    pair* p = NULL;
    for(int i=0;i<sz;i++) {
	p = (pair*)ith(i);
	if(strcmp(key,p->key)==0) {value = p->value; break;}
    }
    return value;
}

void config::set(const char* key, const char* val)
{
    pair* p = new pair();
    p->key = strdup(key);
    p->value = strdup(val);
    svector<pair>::append(p);
}

void config::setint(const char* key,int i)
{
    char s[64];
    sprintf(s,"%d",i);
    set(key,s);
}

void config::setdouble(const char* key,double d)
{
    char s[64];
    sprintf(s,"%.3f",d);
    set(key,s);
}

int config::getint(const char* key) {return atoi(get(key));}
double config::getdouble(const char* key) {return atof(get(key));}

char* config::tostring()
{
    char* s;
    int sz = size();
    // compute required return space
    int len = (sz-1)+sz+1; // leading blanks + "=" + trailing null
    for(int i=0;i<sz;i++) {
	pair* p = (pair*)ith(i);	
	len += strlen(p->key);   
	len += strlen(p->value);
    }
    s = (char*)malloc(len);
    s[0] = '\0';
    int first = 1;
    for(int i=0;i<sz;i++) {
	pair* p = (pair*)ith(i);	
	if(!first) strcat(s,"; ");
	strcat(s,p->key);
	strcat(s,"=");
	strcat(s,p->value);
        first = 0;
    }
    return s;
}

//////////////////////////////////////////////////

block_stack::block_stack(){table = 0;}
block_stack::~block_stack() {}

void block_stack::set_table(config* t) {table = t;}

void block_stack::pop()
{
  if(size() == 0) return;
  blockmark* bm = (blockmark*)ith(size()-1);
  setsize(size()-1);
  table->setsize(bm->tablemark);
}

void block_stack::push()
{
    blockmark* bm = new blockmark();
    bm->tablemark = table->size();
    append(bm);
}

//////////////////////////////////////////////////

/*
Format characters
b - boolean (as integer)
d - double
i - integer
s - string
v - vector of next fmt char
D - distance
L - line_type
P - position
T - text piece
*/

const char* legal = "bdisvDLPT";

void trace(int level, char* fcn, char* fmt,...)
{
    va_list argp;
    va_start(argp,fmt);
    if(debug_level < level) return;
    fprintf(stderr,"report: %s",fcn);
    if(fmt == 0) fmt = "";
    char* p = fmt;
    void* v;
    int n;
    int c;
    int iconfig = 0;
    while(c=*p++) {
	if(strchr(legal,c) == 0) {
	    fprintf(stderr,"report: unknown format character '%c'\n",c);
	    break;
	}
	fputs(" ",stderr);
        switch (c) {
	case 'v':
	    iconfig = 1;
	    continue;
	case 'b':
	    int b = va_arg(argp,int);
	    fprintf(stderr,"%s",(b==0?"false":"true"));
	    break;	
	case 'd':
	    double d = va_arg(argp,double);
	    fprintf(stderr,"%g",d);
	    break;	
	case 'i':
	    int i = va_arg(argp,int);
	    fprintf(stderr,"%d",i);
	    break;	
	case 's':
	    char* s;
	    char** sp;
	    if(iconfig) {
		n = va_arg(argp,int);
		fprintf(stderr,"[%d]",n);
		sp = va_arg(argp,char**);
	    } else {
		n=1;
		s = va_arg(argp,char*);
		sp = &s;
	    }
	    for(int i=0;i<n;i++)
		fprintf(stderr," \"%s\"",sp[i]);
	    break;	
	case 'D':
	    if(iconfig) {n = va_arg(argp,int);} else {n=1;}
	    distance* dis = va_arg(argp,distance*);
	    if(iconfig) fprintf(stderr,"[%d]",n);
	    for(int i=0;i<n;i++)
		fprintf(stderr," dist(%f,%f)",dis[i].x,dis[i].y);
	    break;	
	case 'P':
	    if(iconfig) {n = va_arg(argp,int);} else {n=1;}
	    position* pos = va_arg(argp,position*);
	    if(iconfig) fprintf(stderr,"[%d]",n);
	    for(int i=0;i<n;i++)
		fprintf(stderr," pos(%f,%f)",pos[i].x,pos[i].y);
	    break;	
	case 'L':
	    if(iconfig) {n = va_arg(argp,int);} else {n=1;}
	    line_type* lt = va_arg(argp,line_type*);
	    if(iconfig) fprintf(stderr,"[%d]",n);
	    for(int i=0;i<n;i++) {
		char* typ = "unknown";
	        switch (lt[i].type) {
	        case line_type::invisible: typ="invisible"; break;
	        case line_type::solid: typ="solid"; break;
		case line_type::dotted: typ="dotted"; break;
	        case line_type::dashed: typ="dashed"; break;
		};
	        fprintf(stderr," linetype(%s,thickness=%.1f,dash=%.1f)",typ,lt[i].thickness,lt[i].dash_width);
	    }
	    break;	
	case 'T':
	    if(iconfig) {n = va_arg(argp,int);} else {n=1;}
	    text_piece* tp = va_arg(argp,text_piece*);
	    if(iconfig) fprintf(stderr,"[%d]",n);
  	      for(int i=0;i<n;i++) {
		char* hjust;
		char* vjust;
	        switch (tp[i].adj.h) {
	          case CENTER_ADJUST: hjust = "middle"; break;
                  case LEFT_ADJUST: hjust = "start"; break;
                  case RIGHT_ADJUST: hjust = "end"; break;
                  default: hjust="unknown"; break;
                }
	        switch (tp[i].adj.v) {
	          case NONE_ADJUST: vjust = "none"; break;
                  case ABOVE_ADJUST: vjust = "above"; break;
                  case BELOW_ADJUST: vjust = "below"; break;
                  default: vjust="unknown"; break;
                }
	        fprintf(stderr," text_piece(\"%s\",h=%s,v=%s)",
				tp[i].text,hjust,vjust);
	    }
	    break;	
 	}
	iconfig = 0;
   }
    va_end(argp);
    fprintf(stderr,"\n");
    fflush(stderr);
}

void debug(int level,char* fmt,...)
{
    va_list argp;
    va_start(argp,fmt);
    if(debug_level < level) return;
    vfprintf(stderr,fmt,argp);
    fflush(stderr);
}

#endif /*SVG_SUPPORT*/
