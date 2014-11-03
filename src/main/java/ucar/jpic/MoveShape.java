/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class MoveShape extends GraphicObject
{
  Position strt;
  Position en;

public move_object(Position s, Position e)
{
  this.strt = (s);
  this.en = (e);
}

void update_bounding_box(BoundingBox p)
{
  p.encompass(strt);
  p.encompass(en);
}

void move_by(Position a)
{
  strt.add(a);
  en.add(a);
}

}

