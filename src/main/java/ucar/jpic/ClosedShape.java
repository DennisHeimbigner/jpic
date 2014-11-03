/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract public class ClosedShape extends RectangleShape
{
    double fill = -1.0;            // < 0 if not filled
    double xslanted = 0.0;        // !=0 if x is slanted
    double yslanted = 0.0;        // !=0 if y is slanted
    String color_fill = null;        // = 0 if not colored

    public ClosedShape()
    {
    }

    public void set_fill(double f)
    {
        assert (f >= 0.0);
        fill = f;
    }

    /* accept positive and negative values */
    public void set_xslanted(double s)
    {
        xslanted = s;
    }

    /* accept positive and negative values */
    public void set_yslanted(double s)
    {
        yslanted = s;
    }

    public void set_fill_color(String f)
    {
        color_fill = f;
    }

}

