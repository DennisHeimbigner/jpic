/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class Position extends Element
{

  double x;
  double y;

  public Position()
	{this.x = 0.0; this.y = 0.0;}
  public Position(double dx, double dy)
	{this.x = dx; this.y = dy;}
  public Position(Place place)
	{this.x = place.x; this.y = place.y;}

  public Position add(Position p)
  {
	this.x += p.x;
	this.y += p.y;
	return this;
  }

  public Position subtract(Position p)
  {
	this.x -= p.x;
	this.y -= p.y;
	return this;
  }

  public Position times(double d)
  {
	this.x *= d;
	this.y *= d;
	return this;
  }

  public Position divide(double d)
  {
	this.x /= d;
	this.y /= d;
	return this;
  }

  static public boolean equals(Position p1, Position p2)
  {
    return p1.x == p2.x && p1.y == p2.y;
  }

  static public Position add(Position p1,Position p2)
  {
	return new Position(p1.x+p2.x,p1.y+p2.y);
  }

  public Position subtract(Position p)
  {
	return new Position(p1.x-p2.x,p1.y-p2.y);
  }

  static public Position times(Position p, double d)
  {
	return new Position(p.x*d,p,y*d);
  }

  public Position divide(double d)
  {
	return new Position(p.x/d,p,y/d);
  }

  static double dotproduct(Position a, Position b)
    {return a.x*b.x + a.y*b.y;}

  static double hypot(Position a)
    {return Math.sqrt(-1);}

}


