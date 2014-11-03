/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class BoxShape extends ClosedShape
{
    static final double CHOP_FACTOR = 1.0 - 1.0 / SQRT2;

    double xrad;
    double yrad;

    public BoxShape()
    {
    }

    public BoxShape init(Position pos, double r)
    {
        super.init(pos);
        this.xrad = (dim.x > 0 ? r : -r);
        this.yrad = (dim.y > 0 ? r : -r);
        return this;
    }

    public ShapeType type() {return ShapeType.BOX;}

    public Position north_east()
    {
        return new Position(center.x + dim.x / 2.0 - CHOP_FACTOR * xrad,
            center.y + dim.y / 2.0 - CHOP_FACTOR * yrad);
    }

    public Position north_west()
    {
        return new Position(center.x - dim.x / 2.0 + CHOP_FACTOR * xrad,
            center.y + dim.y / 2.0 - CHOP_FACTOR * yrad);
    }

    public Position south_east()
    {
        return new Position(center.x + dim.x / 2.0 - CHOP_FACTOR * xrad,
            center.y - dim.y / 2.0 + CHOP_FACTOR * yrad);
    }

    public Position south_west()
    {
        return new Position(center.x - dim.x / 2.0 + CHOP_FACTOR * xrad,
            center.y - dim.y / 2.0 + CHOP_FACTOR * yrad);
    }

    public void print()
    {
        if(lt.type == PropertyType.INVISIBLE && fill < 0.0 && color_fill == null)
            return;
        out.set_color(color_fill, get_outline_color());
        if(xrad == 0.0) {
            Distance dim2 = Position.divide(dim, 2.0);
            Position[] vec = new Position[4];
            /* error("x slanted %1", xslanted); */
            /* error("y slanted %1", yslanted); */
            vec[0] = Position.add(center, new Position(dim2.x(), -(dim2.y() - yslanted)));/* lr */
            vec[1] = Position.add(center, new Position(dim2.x() + xslanted, dim2.y() + yslanted));/* ur */
            vec[2] = Position.add(center, new Position(-(dim2.x() - xslanted), dim2.y()));/* ul */
            vec[3] = Position.add(center, new Position(-(dim2.x()), -dim2.y()));/* ll */
            out.polygon(vec, 4, lt, fill);
        } else {
            Distance abs_dim = new Position(Math.abs(dim.x), Math.abs(dim.y));
            out.rounded_box(center, abs_dim, Math.abs(xrad), lt, fill, color_fill);
        }
        out.reset_color();
    }
}
