/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract public class RectangleShape extends ShapeSpec
{
    Position center = null;
    Position dim = null;

    public RectangleShape()
    {
    }

    public void init(Position d)
    {
        this.dim = d;
    }

    double width()
    {
        return dim.x;
    }

    double height()
    {
        return dim.y;
    }

    public Position origin()
    {
        return center;
    }

    public Position center()
    {
        return center;
    }

    public Position north()
    {
        return new Position(center.x, center.y + dim.y / 2.0);
    }

    public Position south()
    {
        return new Position(center.x, center.y - dim.y / 2.0);
    }

    public Position east()
    {
        return new Position(center.x + dim.x / 2.0, center.y);
    }

    public Position west()
    {
        return new Position(center.x - dim.x / 2.0, center.y);
    }

    public Position north_east()
    {
        return new Position(center.x + dim.x / 2.0, center.y + dim.y / 2.0);
    }

    public Position north_west()
    {
        return new Position(center.x - dim.x / 2.0, center.y + dim.y / 2.0);
    }

    public Position south_east()
    {
        return new Position(center.x + dim.x / 2.0, center.y - dim.y / 2.0);
    }

    public Position south_west()
    {
        return new Position(center.x - dim.x / 2.0, center.y - dim.y / 2.0);
    }

    public void update_bounding_box(BoundingBox p)
    {
        p.encompass(Position.subtract(center, Position.divide(dim, 2.0)));
        p.encompass(Position.add(center, Position.divide(dim, 2.0)));
    }

    public void move_by(Position a)
    {
        center.add(a);
    }

}
