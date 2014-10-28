/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

public class Path extends Element
{
    Position pos = null;
    Corner corner = null;
    PathList label_list = null;
    Path ypath = null;
    boolean is_position = false;

    public Path(Corner corner)
    {
	this.corner = corner;
    }

    public Path(Position pos)
    {
	this.is_position = true;
	this.pos = new Position(pos);
    }

    public Path(String s, Corner corner)
    {
	this(corner);
	label_list = new PathList();
	label_list.add(s);
    }

    public void append(Corner corner)
    {
	this.corner = corner;
    }

    public void append(String s)
    {
	this.label_list.append(s);
    }

    public void set_ypath(Path path)
    {
	this.ypath = path;
    }

    public Place follow(Place pl);
	throws Exception
    {
	Place result = new Place();
	if(is_position)
	    return new Place(pos.x,pos,y,null);

	for(String l: label_list) {
	    if(p.obj == null || (p = p.obj.find_label(lb.str)) == 0) {
      lex_error("object does not contain a place `%1'", lb.str);
      return 0;
    }
  if(crn == 0 || p.obj == 0)
    *result = *p;
  else {
    position ps = ((p.obj).*(crn))();
    result.x = ps.x;
    result.y = ps.y;
    result.obj = 0;
  }
  if(ypath) {
    place tem;
    if(!ypath.follow(pl, &tem))
      return 0;
    result.y = tem.y;
    if(result.obj != tem.obj)
      result.obj = 0;
  }
  return 1;

    }
}
