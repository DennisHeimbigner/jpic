/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class TextShape extends RectangleShape
{
  public text_object(Position d) {super(d);}

  @Override
  public ShapeType type() { return ShapeType.TEXT; }
}
