/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class BoundingBox
{
  boolean blank = true;
  Position ll = null;
  Position ur = null;

  public BoundingBox() {}

  void encompass(Position pos)
  {
    if(!blank) {
        ll = pos;
        ur = pos;
	blank = false;
    } else {
      if(pos.x < ll.x)
        ll.x = pos.x;
      if(pos.y < ll.y)
        ll.y = pos.y;
      if(pos.x > ur.x)
        ur.x = pos.x;
      if(pos.y > ur.y)
        ur.y = pos.y;
    }
  }
}
