/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class EllipseShape extends ClosedShape
{

    public EllipseShape()
    {
    }

    public void iit(Position d)
    {
        super.init(d);
    }

    @Override
    ShapeType type()
    {
        return ShapeType.ELLIPSE;
    }

    void print()
    {
        if(lt.type == PropertyType.INVISIBLE && fill < 0.0 && color_fill == null)
            return;
        out.set_color(color_fill, get_outline_color());
        out.ellipse(center, dim, lt, fill);
        out.reset_color();
    }

}
