/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract public class GraphicObject extends Element
{
    protected int ntext = 0;
    protected TextPiece[] text = null;
    protected boolean aligned = false;
    protected Line lt = null;
    protected String outline_color = null;
    protected String color_fill = null;
    protected Output out = null;

    public GraphicObject()
    {
    }

    public void setOutput(Output out)
    {
        this.out = out;
    }

    abstract ShapeType type();

    Position origin()
    {
        return new Position(0.0, 0.0);
    }

    public Position start()
    {
        return origin();
    }

    public Position end()
    {
        return origin();
    }

    public Position center()
    {
        return origin();
    }

    public void set_dotted(double wid)
    {
        lt.type = PropertyType.DOTTED;
        lt.dash_width = wid;
    }

    public void set_dashed(double wid)
    {
        lt.type = PropertyType.DASHED;
        lt.dash_width = wid;
    }

    public void set_thickness(double th)
    {
        lt.thickness = th;
    }

    public void set_fill(double d)
    {
    }

    public void set_xslanted(double d)
    {
    }

    public void set_yslanted(double d)
    {
    }

    public void set_fill_color(String c)
    {
        color_fill = c;
    }

    public void set_outline_color(String c)
    {
        outline_color = c;
    }

    public String get_outline_color()
    {
        return outline_color;
    }

    public void set_invisible()
    {
        lt.type = PropertyType.INVISIBLE;
    }

    public void add_text(TextItem t, boolean a)
    {
        aligned = a;
        int len = 0;
        TextItem p;
        for(p = t;p != null;p = p.next)
            len++;
        if(len == 0)
            text = null;
        else {
            text = new TextPiece[len];
            for(p = t, len = 0;p != null;p = p.next, len++) {
                text[len].text = p.text;
                p.text = null;
                text[len].adj = p.adj;
                text[len].filename = p.filename;
                text[len].lineno = p.lineno;
            }
        }
        ntext = len;
    }

    public void print_text()
    {
        double angle = 0.0;
        if(aligned) {
            Position d = Position.subtract(end(), start());
            if(d.x != 0.0 || d.y != 0.0)
                angle = Math.atan2(d.y, d.x);
        }
        if(text != null) {
            out.set_color(color_fill, get_outline_color());
            out.text(center(), text, ntext, angle);
            out.reset_color();
        }
    }
}

