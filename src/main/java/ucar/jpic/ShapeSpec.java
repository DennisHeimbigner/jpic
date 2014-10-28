/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;
import java.util.Map;

import static ucar.jpic.Element.*;

public class ShapeSpec extends Element
{
  long flags;
  ShapeType type;
  List<Shape> oblist;
  Map<String,Place> tbl;
  double dash_width;
  Position from;
  Position to;
  Position at;
  Position by;
  Path with;
  TextItem text;
  double height;
  double radius;
  double width;
  double segment_width;
  double segment_height;
  double start_chop;
  double end_chop;
  double thickness;
  double fill;
  double xslanted;
  double yslanted;
  String shaded;
  String outlined;
  Direction dir;
  List<Segment> segment_list;
  Position segment_pos;
  boolean segment_is_absolute;

  public ShapeSpec(ShapeType type) {}

  Shape make_object(Position pos, Direction dir) {return null;}
  Shape make_box(Position pos, Direction dir ) {return null;}
  Shape make_block(Position pos, Direction dir ) {return null;}
  Shape make_text(Position pos, Direction dir ) {return null;}
  Shape make_ellipse(Position pos, Direction dir ) {return null;}
  Shape make_circle(Position pos, Direction dir ) {return null;}
  Shape make_line(Position pos, Direction dir ) {return null;}
  Shape make_arc(Position pos, Direction dir ) {return null;}
  Shape make_linear(Position pos, Direction dir ) {return null;}
  Shape make_move(Position pos, Direction dir ) {return null;}
  int position_rectangle(Shape p, Position pos, Position curpos,
			 Direction[] dirp) {return 0;}
}

