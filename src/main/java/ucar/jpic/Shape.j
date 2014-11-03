/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class Shape extends Element
{

  public Shape() {}

    abstract Position origin();
    abstract double width();
    abstract double radius();
    abstract double height();
    abstract Position north();
    abstract Position south();
    abstract Position east();
    abstract Position west();
    abstract Position north_east();
    abstract Position north_west();
    abstract Position south_east();
    abstract Position south_west();
    abstract Position start();
    abstract Position end();
    abstract Position center();
    abstract Place find_label(String l);
    abstract void move_by(Position p);
    abstract int blank();
    abstract void update_bounding_box(BoundingBox b);
    abstract ShapeType type();
    abstract void print();
    abstract void print_text();
}

