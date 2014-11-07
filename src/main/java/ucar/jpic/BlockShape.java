/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.HashMap;
import java.util.Map;

public class BlockShape extends RectangleShape
{
  ElementList oblist;
  Map<String,Place> tbl = new HashMap<>();

  public BlockShape() {}

  public BlockShape init(Position d, ElementList ol, Map<String,Place> t)
  {
    super.init(d);
    oblist = ol;
    tbl = t;  
  }

@Override
public void print()
{
  out.begin_block(south_west(), north_east());
  print_object_list(oblist);
  out.end_block();
}

@Override
public void adjust_objectless_places(Map<String,Place> tbl, Position a)
{
  // Adjust all the labels that aren't attached to objects.
  for(Map.Entry<String,Place> entry: tbl) {
    String key = entry.getKey();
    Place pl = entry.getValue();
    if(key.charAt(0).isUpperCase() && pl.obj == null) {
      pl.x += a.x;
      pl.y += a.y;
    }
  }
}

@Override
public void move_by(Position a)
{
  center.add(a);
  for(Shape p: oblist)
    p.move_by(a);
  adjust_objectless_places(tbl, a);
}

@Override
public Place find_label(String name)
{
  return tbl.get(name);
}

@Override
public ShapeType type() {return ShapeType.BLOCK;}

}
