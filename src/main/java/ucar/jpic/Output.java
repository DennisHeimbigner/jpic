/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

public abstract class Output extends Element
{

  String args;
  double desired_height;	// zero if no height specified
  double desired_width;		// zero if no depth specified

  public Output() {}

  double
  compute_scale(double d, Position pos1, Position pos2)
  {
    return 0;
  }

  void set_desired_width_height(double wid, double ht) {}
  void set_args(List<String> args) {}
  void set_location(String s, int i) {}

  abstract void start_picture(double sc, Position ll, Position ur);
  abstract void circle(Position pos, double rad, LineType linetype, double d);
  abstract void text(Position pos, TextPiece piece, int i);
  abstract void line(Position pos,  Position pos1, int n, LineType lt);
  abstract void polygon(Position pos, int n, LineType lt);
  abstract void spline(Position pos1, Position pos2, int n, LineType lt);
  abstract void arc(Position pos, Position pos1, Position pos2);
  abstract void ellipse(Position pos, Distance distance, LineType lt, double d);
  abstract void rounded_box(Position pos, Distance distance,
			    LineType lt, double d2, String s);
  abstract void command(String s1, String s2);
  abstract void set_color(String s1, String s2);
  abstract String get_last_filled();
  abstract String get();
  abstract boolean supports_filled_polygons();
  abstract void begin_block(Position ll);
  abstract void end_block();

}
