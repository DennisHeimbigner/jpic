/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class SplineShape extends LineShape
{
    public SplineShape() {}

    public void init(Position s, Position e, Position p, int i)
    {
        super.init(s, e, p, i);
    }

    public ShapeType type()
    {
        return ShapeType.SPLINE;
    }

    public void
    print()
        throws JPicException
    {
        if(lt.type == PropertyType.INVISIBLE)
            return;
        out.set_color(null, get_outline_color());
        // shorten line length for spline to avoid arrow sticking
        Position sp = new Position(strt);
        if(arrow_at_start) {
            Position base = Position.subtract(v[0],strt);
            double hyp = hypot(base);
            if(hyp == 0.0) {
                throw new JPicException("cannot draw arrow on object with zero length");
            }
            if(aht.solid && out.supports_filled_polygons()) {
                base.times(aht.height / hyp);
                Output.draw_arrow(strt, Position.subtract(strt,v[0]), aht, lt,
                    get_outline_color());
                sp = Position.add(strt,Position.times(base,0.1)); // to reserve spline shape
            } else {
                base.times(Math.abs(lt.thickness) / hyp / 72 / 4);
                sp = Position.add(strt,base);
                out.draw_arrow(sp, Position.subtract(sp,v[0]), aht, lt,
                    get_outline_color());
            }
        }
        if(arrow_at_end) {
            Position base = Position.subtract(v[n - 1],(n > 1 ? v[n - 2] : strt));
            double hyp = hypot(base);
            if(hyp == 0.0) {
                throw new JPicException("cannot draw arrow on object with zero length");
            }
            if(aht.solid && out.supports_filled_polygons()) {
                base.times(aht.height / hyp);
                out.draw_arrow(en, Position.subtract(v[n - 1],(n > 1 ? v[n - 2] : strt)), aht, lt,
                    get_outline_color());
                v[n - 1] = Position.subtract(en,Position.times(base,0.1)); // to reserve spline shape
            } else {
                base.times(Math.abs(lt.thickness) / hyp / 72 / 4);
                v[n - 1] = Position.subtract(en,base);
                out.draw_arrow(v[n - 1], Position.subtract(v[n - 1],(n > 1 ? v[n - 2] : strt)), aht, lt,
                    get_outline_color());
            }
        }
        out.spline(sp, v, n, lt);
        out.reset_color();
    }

    public void update_bounding_box(BoundingBox p)
    {
        p.encompass(strt);
        p.encompass(en);
  /*

  If

  p1 = q1/2 + q2/2
  p2 = q1/6 + q2*5/6
  p3 = q2*5/6 + q3/6
  p4 = q2/2 + q3/2
  [ the points for the Bezier cubic ]

  and

  t = .5

  then

  (1-t)^3*p1 + 3*t*(t - 1)^2*p2 + 3*t^2*(1-t)*p3 + t^3*p4
  [ the equation for the Bezier cubic ]

  = .125*q1 + .75*q2 + .125*q3

  */
        for(int i = 1;i < n;i++)
            p.encompass(Position.add(Position.times(i == 1 ? strt : v[i - 2],.125),
                    Position.add(Position.times(v[i - 1],.75),Position.times(v[i],.125))));
    }

}
