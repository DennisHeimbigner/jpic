/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

abstract public class Output extends Element
{

    String args;
    double desired_height;    // zero if no height specified
    double desired_width;        // zero if no depth specified

    public Output()
    {
    }

    double
    compute_scale(double sc, Position ll, Position ur)
        throws JPicException
    {
        Distance dim = Position.subtract(ur, ll);
        if(desired_width != 0.0 || desired_height != 0.0) {
            sc = 0.0;
            if(desired_width != 0.0) {
                if(dim.x == 0.0)
                    throw new Exception("width specified for picture with zero width");
                else
                    sc = dim.x / desired_width;
            }
            if(desired_height != 0.0) {
                if(dim.y == 0.0)
                    throw new Exception("height specified for picture with zero height");
                else {
                    double tem = dim.y / desired_height;
                    if(tem > sc)
                        sc = tem;
                }
            }
            return sc == 0.0 ? 1.0 : sc;
        } else {
            if(sc <= 0.0)
                sc = 1.0;
            Distance sdim = Position.divide(dim, sc);
            double max_width = 0.0;
            max_width = lookup_value("maxpswid");
            double max_height = 0.0;
            max_height = lookup_variable("maxpsht");
            if((max_width > 0.0 && sdim.x > max_width)
                || (max_height > 0.0 && sdim.y > max_height)) {
                double xscale = dim.x / max_width;
                double yscale = dim.y / max_height;
                return xscale > yscale ? xscale : yscale;
            } else
                return sc;
        }

    }

    void set_desired_width_height(double wid, double ht)
    {
        desired_width = wid;
        desired_height = ht;
    }

    void set_args(List<String> args)
    {
    }

    //////////////////////////////////////////////////

    abstract void set_location(String s, int i)
    {
    }

    abstract void start_picture(double sc, Position ll, Position ur);

    abstract void circle(Position pos, double rad, Line linetype, double d);

    abstract void text(Position pos, TextPiece[] pieces, int i, double angle);

    abstract void line(Position pos, Position pos1, int n, Line lt);

    abstract void polygon(Position[] points, int n, Line lt, double fill);

    abstract void spline(Position pos1, Position pos2, int n, Line lt);

    abstract void arc(Position pos, Position pos1, Position pos2);

    abstract void ellipse(Position pos, Distance distance, Line lt, double d);

    abstract void rounded_box(Position pos, Distance distance, double rad,
                              Line lt, double fill, String color);

    abstract void command(String s1, String s2);

    abstract void set_color(String s1, String s2);

    abstract void reset_color();

    abstract String get_last_filled();

    abstract String get();

    abstract boolean supports_filled_polygons();

    abstract void begin_block(Position ll);

    abstract void end_block();

    static void
    draw_arrow(Position pos, Distance dir,
		ArrowHead aht, Line lt,
		String outline_color_for_fill)
    {
  double hyp = hypot(dir);
  if(hyp == 0.0)
    throw new JPicException"cannot draw arrow on object with zero length");
  Position base = Position.negate(dir);
  base.multiply(aht.height/hyp);
  Position n = new Position(dir.y, -dir.x);
  n.multiply(aht.width/(hyp*2.0));
  Line slt = new Line(lt);
  slt.type = PropertyType.SOLID;
  Position[] v = new Position[3];
  v[0] = pos;
  v[1] = Position.add(pos,base).add(n);
  v[2] = Position.add(pos,base).subtract(n);
  if(aht.solid && out.supports_filled_polygons()) {
    // fill with outline color
    out.set_color(outline_color_for_fill, outline_color_for_fill);
    // make stroke thin to avoid arrow sticking
    slt.thickness = 0.1;
    out.polygon(v, 3, slt, 1);
  }
  else {
    // use two line segments to avoid arrow sticking
    out.line(v[2], pos, 1, slt);
    out.line(v[3], pos, 1, slt);
  }
}
