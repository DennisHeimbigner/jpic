/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import static ucar.jpic.Element.*;

public class ShapeSpec extends Element
{
  int ntext = 0;
  TextPiece text = null;
  int aligned = 0;
  LineType lt = new LineType();
  String outline_color = null;
  String color_fill = null;

  public GraphicObject() {}


  abstract ObjectType type();

void set_dotted(double wid)
{
  lt.type = LineType.DOTTED;
  lt.width = wid;
}

void set_dashed(double wid)
{
  lt.type = LineType.DASHED;
  lt.width = wid;
}

void set_thickness(double th)
{
  lt.thickness = th;
}

void set_fill(double)
{
}

void set_xslanted(double)
{
}

void set_yslanted(double)
{
}

void set_fill_color(String c)
{
  color_fill = c;
}

void set_outline_color(String c)
{
  outline_color = c;
}

char get_outline_color()
{
  return outline_color;
}

void set_invisible()
{
  lt.type = LineType.INVISIBLE;
}

void add_text(TextItem t, int a)
{
  aligned = a;
  int len = 0;
  TextItem p;
  for(p = t; p != null; p = p.next)
    len++;
  if (len == 0)
    text = null;
  else {
    text = new TextPiece[len];
    for(p = t, len = 0; p != null; p = p.next, len++) {
      text[len].text = p.text;
      p.text = 0;
      text[len].adj = p.adj;
      text[len].filename = p.filename;
      text[len].lineno = p.lineno;
    }
  }
  ntext = len;
}

void print_text()
{
  double angle = 0.0;
  if(aligned != 0) {
    Position d = Position.subtract(end(),start());
    if(d.x != 0.0 || d.y != 0.0)
      angle = Math.atan2(d.y, d.x);
  }
  if(text != null) {
    out.set_color(color_fill, get_outline_color());
    out.text(center(), text, ntext, angle);
    out.reset_color();
  }
}

