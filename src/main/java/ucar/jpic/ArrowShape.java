/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class ArrowShape extends LineShape
{
  public ArrowShape(Position s, Position e, Position p, int i)
  {
	super(s,e,p,i);
  }

  ShapeType type() { return ShapeType.ARROW; }
}

