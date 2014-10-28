/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class BoundingBox
{
  int blank;
  Position ll;
  Position ur;

  public BoundingBox() {}
  void encompass(Position pos) {}
}
