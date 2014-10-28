/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class Position extends Element
{

  static double dotproduct(Position p1, Position p2) {return 0;}
  static double hypot(Position a) {return 0;}

  double x;
  double y;

  public Position() {}
  public Position(double d1, double d2) {}
  public Position(Corner corner) {}
  public Position(Place place) {}

  public Position add(Position p) {return null;}
  public Position minus(Position p) {return null;}
  public Position times(Position p) {return null;}
  public Position divide(Position p) {return null;}

  public boolean equals(Position other) {return false;}

}


