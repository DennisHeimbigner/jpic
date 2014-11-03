/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class CircleShape extends EllipseShape
{

    public CircleShape()
    {
    }

    public void init(double diam)
    {
        super.init(new Position(diam, diam));
    }

    @Override
    ShapeType type()
    {
        return ShapeType.CIRCLE;
    }

    void print()
        throws JPicException
    {
        if(lt.type == PropertyType.INVISIBLE && fill < 0.0 && color_fill == null)
            return;
        out.set_color(color_fill, super.get_outline_color());
        out.circle(center(), dim.getX() / 2.0, lt, fill);
        out.reset_color();
    }
}

