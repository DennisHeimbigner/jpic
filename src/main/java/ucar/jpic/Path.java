/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

public class Path extends Element
{
    Position pos;
    Corner crn;
    List<String> label_list;
    Path ypath;
    boolean is_position;

    public Path()
    {
    }

    public Path(Corner corner)
    {
    }

    public Path(Position pos)
    {
    }

    public Path(String s, Corner corner)
    {
    }

    public void append(Corner corner)
    {
    }

    public void append(String s)
    {
    }

    public void set_ypath(Path path)
    {
    }

    public int follow(Place place0, Place place1)
    {
        return 0;
    }
}
