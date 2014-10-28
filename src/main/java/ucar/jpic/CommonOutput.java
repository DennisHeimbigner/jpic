/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract class CommonOutput extends Output
{
  double dash_line(Position start,  Position end,
		  LineType lt, double dash_width, double gap_width)//double *offsetp
	{return 0;}
  double dash_arc(Position cent, double rad,
		double start_angle, double end_angle,  LineType lt,
		double dash_width, double gap_width)//double *offsetp
	{return 0;}
  double dot_line(Position start,  Position end,
		 LineType lt, double gap_width)//double *offsetp
	{return 0;}
  double dot_arc(Position cent, double rad,
	       double start_angle, double end_angle,  LineType lt,
	       double gap_width) //double *offsetp
	{return 0;}
  void ellipse_arc(Position pos1,  Position pos2, Position pos3,  Distance dist , LineType lt)
	{}
  void dashed_circle(Position pos, double rad,  LineType lt)
	{}
  void dotted_circle(Position pos, double rad,  LineType lt)
	{}
  void dashed_ellipse(Position pos,  Distance d,  LineType lt)
	{}
  void dotted_ellipse(Position pos,  Distance d,  LineType lt)
	{}
  void dashed_arc(Position pos,  Position pos1,  Position pos2, LineType lt)
	{}
  void dotted_arc(Position pos1,  Position pos2,  Position pos, LineType lt)
	{}
  void solid_arc(Position cent, double rad, double start_angle, double end_angle,  LineType lt)
	{}
  void dashed_rounded_box(Position pos,  Distance dist, double d, LineType lt)
	{}
  void dotted_rounded_box(Position pos,  Distance dist, double d, LineType lt)
	{}
  void solid_rounded_box(Position pos,  Distance dist, double d, LineType lt)
	{}
  void filled_rounded_box(Position pos,  Distance dist, double d, double d1)  {}
  String get_last_filled() {return null;}
  String get_outline_color() {return null;}

  abstract void start_picture(double sc,  Position ll,  Position ur);
  abstract void finish_picture();
  abstract void circle(Position pos, double rad,  LineType lt, double d);
  abstract void text(Position pos, TextPiece piece, int i, double d);
  abstract void line(Position pos,  Position pos1, int n,  LineType lt);
  abstract void polygon(Position pos, int n,  LineType lt, double d);
  abstract void spline(Position pos,  Position pos1, int n, LineType lt);
  abstract void arc(Position pos1,  Position pos2,  Position pos3, LineType lt);
  abstract void ellipse(Position pos,  Distance dist, LineType lt, double d);
  abstract void rounded_box(Position pos,  Distance dist, double d1, LineType lt, double d2, String s);
  abstract void set_color(String s1, String s2);
  abstract void reset_color();
  abstract void dot(Position pos,  LineType lt);
}
