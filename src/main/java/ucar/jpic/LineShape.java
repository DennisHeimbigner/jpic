/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class LineShape extends LinearShape
{
  Position v = null;
  int n = 0;

    public LineShape() {}

  public void init(Position s, Position e, Position p, int i)
  {
    super.init(s,e);
    this.v = new Position(p);
    this.n = i;
  }

  Position origin() { return strt; }
  Position center() { return Position.add(strt,en).divide(2.0); }
  Position north() { return (en.y - strt.y) > 0 ? en : strt; }
  Position south() { return (en.y - strt.y) < 0 ? en : strt; }
  Position east() { return (en.x - strt.x) > 0 ? en : strt; }
  Position west() { return (en.x - strt.x) < 0 ? en : strt; }
  ShapeType type() { return ShapeType.LINE; }

void print()
  throws JPicException
{
  if(lt.type == LineType.INVISIBLE)
    return;
  out.set_color(0, GraphicObject.get_outline_color());
  // shorten line length to avoid arrow sticking.
  Position sp = new Position(strt);
  if(arrow_at_start) {
    Position base = Position.subtract(v[0],strt);
    double hyp = hypot(base);
    if(hyp == 0.0)
      throw new Exception("cannot draw arrow on object with zero length");
    if(aht.solid && out.supports_filled_polygons()) {
      base.multiply(aht.height / hyp);
      draw_arrow(strt, Position.subtract(strt,v[0]), aht, lt,
		 GraphicObject.get_outline_color());
      sp = Position.add(strt,base);
    } else {
      base.multiply(Math.abs(lt.thickness) / hyp / 72 / 4);
      sp = Position.add(strt,base);
      draw_arrow(sp, Position.subtract(sp,v[0]), aht, lt,
		 GraphicObject.get_outline_color());
    }
  }
  if(arrow_at_end) {
    Position base = Position.substract(v[n-1],(n > 1 ? v[n-2] : strt));
    double hyp = hypot(base);
    if(hyp == 0.0)
      throw new Exception("cannot draw arrow on object with zero length");
    if(aht.solid && out.supports_filled_polygons()) {
      base.multiple(aht.height / hyp);
      draw_arrow(en, Position.subtract(v[n-1],(n > 1 ? v[n-2] : strt)), aht, lt,
		 GraphicObject.get_outline_color());
      v[n-1] = Position.subtract(en,base);
    } else {
      base.multiply(Math.abs(lt.thickness) / hyp / 72 / 4);
      v[n-1] = Position.subtract(en,base);
      draw_arrow(v[n-1], Position.subtract(v[n-1],(n > 1 ? v[n-2] : strt)), aht, lt,
		 GraphicObject.get_outline_color());
    }
  }
  out.line(sp, v, n, lt);
  out.reset_color();
}

void update_bounding_box(BoundingBox p)
{
  p.encompass(strt);
  for(int i = 0; i < n; i++)
    p.encompass(v[i]);
}

void move_by(Position pos)
{
  super.move_by(pos);
  for(int i = 0; i < n; i++)
    v[i].add(pos);
}

}
