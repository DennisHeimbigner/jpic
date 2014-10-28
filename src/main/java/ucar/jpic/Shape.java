/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class Shape extends Element
{
  Shape prev;
  Shape next;

  public Shape() {}

  Position origin() {return null;}
  double width() {return 0;}
  double radius() {return 0;}
  double height() {return 0;}
  Position north() {return null;}
  Position south() {return null;}
  Position east() {return null;}
  Position west() {return null;}
  Position north_east() {return null;}
  Position north_west() {return null;}
  Position south_east() {return null;}
  Position south_west() {return null;}
  Position start() {return null;}
  Position end() {return null;}
  Position center() {return null;}
  Place find_label(String l) {return null;}
  void move_by(Position p) {}
  int blank() {return 0;}
  void update_bounding_box(BoundingBox b) {}
  ShapeType type() {return null;}
  void print() {}
  void print_text() {}
}

