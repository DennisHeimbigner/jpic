/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract public class LinearShape extends ShapeSpec
{
    boolean arrow_at_start = false;
    boolean arrow_at_end = false;
    ArrowHead aht = null;
    Position strt = null;
    Position en = null;

    public LinearShape() {}

    public void init(Position s, Position e)
    {
        this.strt = s;
        this.en = e;
    }

    void move_by(Position a)
    {
        strt.add(a);
        en.add(a);
    }

    void add_arrows(boolean at_start, boolean at_end,
                    ArrowHead a)
    {
        arrow_at_start = at_start;
        arrow_at_end = at_end;
        aht = a;
    }

}
