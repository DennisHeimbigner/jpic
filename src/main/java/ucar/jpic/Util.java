/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import static ucar.jpic.Element.*;

abstract public class Util
{

    static Shape make_object(ShapeSpec spec, Position pos, Direction dir)
    {
        return null;
    }

    static Shape make_mark_object()
    {
        return null;
    }

    static Shape make_command_object(String s0, String s1, int i)
    {
        return null;
    }

    static int lookup_variable(String name, double val)
    {
        return 0;
    }

    static void define_variable(String name, double val)
    {
    }

    static void print_picture(Shape shape)
    {
    }

    static Position compute_arc_center(Position start, Position cent, Position end)
    {
        return null;
    }

    static void do_copy(String file)
    {
    }

}
