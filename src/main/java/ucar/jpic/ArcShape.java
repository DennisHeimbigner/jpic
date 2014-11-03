/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;


public class ArcShape extends LinearShape
{
    boolean clockwise = false;
    Position center = null;
    double rad = 0.0;

    public ArcShape(boolean cw, Position s, Position e, Position c)
    {
        super(s, e);
        clockwise = cw;
        center = c;
        rad = hypot(Position.subtract(c, s));
    }

    Position origin()
    {
        return center;
    }

    Position center()
    {
        return center;
    }

    double radius()
    {
        return rad;
    }

    void move_by(Position pos)
    {
        super.move_by(pos);
        center.add(pos);
    }

// we get arc corners from the corresponding circle

    Position north()
    {
        Position result = new Position(center);
        result.y += rad;
        return result;
    }

    position south()
    {
        Position result = new Position(center);
        result.y -= rad;
        return result;
    }

    position east()
    {
        Position result = new Position(center);
        result.x += rad;
        return result;
    }

    position west()
    {
        Position result = new Position(center);
        result.x -= rad;
        return result;
    }

    position north_east()
    {
        Position result = new Position(center);
        result.x += rad / M_SQRT2;
        result.y += rad / M_SQRT2;
        return result;
    }

    position north_west()
    {
        Position result = new Position(center);
        result.x -= rad / M_SQRT2;
        result.y += rad / M_SQRT2;
        return result;
    }

    position south_east()
    {
        Position result = new Position(center);
        result.x += rad / M_SQRT2;
        result.y -= rad / M_SQRT2;
        return result;
    }

    position south_west()
    {
        Position result = new Position(center);
        result.x -= rad / M_SQRT2;
        result.y -= rad / M_SQRT2;
        return result;
    }

    void
    print()
    {
        if(lt.type == LineType.INVISIBLE)
            return;
        out.set_color(0, GraphicObject.get_outline_color());
        // handle arrow direction; make shorter line for arc
        Position sp, ep, b;
        if(clockwise) {
            sp = en;
            ep = strt;
        } else {
            sp = strt;
            ep = en;
        }
        if(arrow_at_start) {
            double theta = aht.height / rad;
            if(clockwise)
                theta = -theta;
            b = Position.substract(strt, center);
            b = new Position(b.x * Math.cos(theta) - b.y * Math.sin(theta),
                b.x * Math.sin(theta) + b.y * Math.cos(theta));
            b.add(center);
            if(clockwise)
                ep = b;
            else
                sp = b;
            if(aht.solid && out.supports_filled_polygons()) {
                draw_arrow(strt, Pstrt - b, aht, lt,
                    GraphicObject.get_outline_color());
            } else {
                Position v = new Position(b);
                theta = Math.abs(lt.thickness) / 72 / 4 / rad;
                if(clockwise)
                    theta = -theta;
                b = strt - cent;
                b = Position(b.x * cos(theta) - b.y * sin(theta),
                    b.x * sin(theta) + b.y * cos(theta)) + cent;
                draw_arrow(b, Position.substract(b, v), aht, lt,
                    GraphicObject.get_outline_color());
                out.line(b, v, 1, lt);
            }
        }
        if(arrow_at_end) {
            double theta = aht.height / rad;
            if(!clockwise)
                theta = -theta;
            b = Position.subtract(en, center);
            b = Position(b.x * Math.cos(theta) - b.y * Math.sin(theta),
                b.x * Math.sin(theta) + b.y * Math.cos(theta));
            b.add(center);
            if(clockwise)
                sp = b;
            else
                ep = b;
            if(aht.solid && out.supports_filled_polygons()) {
                draw_arrow(en, Position.subtract(en, b), aht, lt,
                    get_outline_color());
            } else {
                Position v = new Position(b);
                theta = Math.abs(lt.thickness) / 72 / 4 / rad;
                if(!clockwise)
                    theta = -theta;
                b = Position.subtract(en, center);
                b = new Position(b.x * Math.cos(theta) - b.y * Math.sin(theta),
                    b.x * Math.sin(theta) + b.y * Math.cos(theta));
                b.add(center);
                draw_arrow(b, Position.subtract(b, v), aht, lt,
                    GraphicObject.get_outline_color());
                out.line(b, v, 1, lt);
            }
        }
        out.arc(sp, cent, ep, lt);
        out.reset_color();
    }

    void update_bounding_box(BoundingBox p)
    {
        p.encompass(strt);
        p.encompass(en);
        Position start_offset = Position.subtract(strt, cent);
        if(start_offset.x == 0.0 && start_offset.y == 0.0)
            return;
        Position end_offset = Position.subtract(en, cent);
        if(end_offset.x == 0.0 && end_offset.y == 0.0)
            return;
        double start_quad = Math.atan2(start_offset.y, start_offset.x) / (M_PI / 2.0);
        double end_quad = Math.atan2(end_offset.y, end_offset.x) / (M_PI / 2.0);
        if(clockwise) {
            double temp = start_quad;
            start_quad = end_quad;
            end_quad = temp;
        }
        if(start_quad < 0.0)
            start_quad += 4.0;
        while(end_quad <= start_quad)
            end_quad += 4.0;
        double r = Math.max(hypot(start_offset), hypot(end_offset));
        for(int q = ((int) start_quad) + 1;q < end_quad;q++) {
            Position offset = new Position();
            switch (q % 4) {
            case 0:
                offset.x = r;
                break;
            case 1:
                offset.y = r;
                break;
            case 2:
                offset.x = -r;
                break;
            case 3:
                offset.y = -r;
                break;
            }
            p.encompass(Position.add(center, offset));
        }
    }
}

