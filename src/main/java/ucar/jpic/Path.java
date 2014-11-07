/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

public class Path extends Element
{
    Position pos = null;
    Corner corner = null;
    PathList label_list = null;
    Path ypath = null;
    boolean is_position = false;

    public Path(Corner corner)
    {
        this.corner = corner;
    }

    public Path(Position pos)
    {
        this.is_position = true;
        this.pos = new Position(pos);
    }

    public Path(String s, Corner corner)
    {
        this(corner);
        label_list = new PathList();
        label_list.add(s);
    }

    public void append(Corner corner)
    {
        this.corner = corner;
    }

    public void append(String s)
    {
        this.label_list.add(s);
    }

    public void set_ypath(Path path)
    {
        this.ypath = path;
    }

    public Place
    follow(Place pl)
        throws JPicException
    {
        Place result;
        if(is_position)
            return new Place(pos.x, pos.y, null);

        for(String l : label_list) {
            if(pl.obj == null || (pl = pl.obj.find_label(l)) == null)
                throw new JPicException(String.format("object does not contain a place `%s'", l));
        }
        if(corner == null || pl.obj == null)
            result = pl;
        else {
            Position ps = new Position(pl.obj corner); ???
            result = new Place(ps.x, ps.y, null);
        }
        if(ypath != null) {
            Place temp;
            temp = ypath.follow(pl);
            if(temp == null)
                return null;
            result.y = temp.y;
            if(result.obj != temp.obj)
                result.obj = null;
        }
        return result;
    }
}
