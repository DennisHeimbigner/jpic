/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

abstract public class Output extends Element
{

  String args;
  double desired_height;	// zero if no height specified
  double desired_width;		// zero if no depth specified

  public Output() {}

  double
  compute_scale(double sc, Position ll, Position ur)
	throws Exception
  {
    Distance dim = Position.minus(ur,ll);
    if(desired_width != 0.0 || desired_height != 0.0) {
        sc = 0.0;
	if(desired_width != 0.0) {
            if(dim.x == 0.0)
	        throw new Exception("width specified for picture with zero width");
        else
	    sc = dim.x/desired_width;
    }
    if(desired_height != 0.0) {
      if(dim.y == 0.0)
	throw new Exception("height specified for picture with zero height");
      else {
	double tem = dim.y/desired_height;
	if(tem > sc)
	  sc = tem;
      }
    }
    return sc == 0.0 ? 1.0 : sc;
  } else {
    if(sc <= 0.0)
      sc = 1.0;
    Distance sdim = Position.divide(dim,sc);
    double max_width = 0.0;
    max_width = lookup_variable("maxpswid");
    double max_height = 0.0;
    max_height = lookup_variable("maxpsht");
    if((max_width > 0.0 && sdim.x > max_width)
	|| (max_height > 0.0 && sdim.y > max_height)) {
	double xscale = dim.x/max_width;
	double yscale = dim.y/max_height;
	return xscale > yscale ? xscale : yscale;
    }
    else
      return sc;
  }

}

  void set_desired_width_height(double wid, double ht)
  {
	desired_width = wid;
	desired_height = ht;
  }

  void set_args(List<String> args) {}

  //////////////////////////////////////////////////

  abstract void set_location(String s, int i) {}
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
