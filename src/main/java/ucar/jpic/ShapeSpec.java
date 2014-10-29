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
  ShapeType type;
  List<Shape> oblist = null;
  Map<String,Place> tbl = null;
  Position from = null;
  Position to = null;
  Position at = null;
  Position by = null;
  Path with = null;
  TextItem text = null;
  List<Segment> segment_list = null;
  Position segment_pos = null;
  DirectionType dir = DirectionType.RIGHT;
  EnumSet<PropertyType> flags = EnumSet.noneOf(PropertyType.class);
  double dash_width = 0.0;
  double height = 0.0;
  double radius = 0.0;
  double width = 0.0;
  double segment_width = 0.0;
  double segment_height = 0.0;
  double start_chop = 0.0;
  double end_chop = 0.0;
  double thickness = 0.0;
  double fill = 0.0;
  double xslanted = 0.0;
  double yslanted = 0.0;
  String shaded = null;
  String outlined = null;
  boolean segment_is_absolute;

  public ShapeSpec(ShapeType type)
  {
    this.type = t;
  }

  Shape make_object(Position pos, Direction dir)
{
  GraphicObject obj = null;
  switch (type) {
  case BLOCK:
    obj = make_block(curpos, dir);
    break;
  case BOX:
    obj = make_box(curpos, dir);
    break;
  case TEXT:
    obj = make_text(curpos, dir);
    break;
  case ELLIPSE:
    obj = make_ellipse(curpos, dir);
    break;
  case CIRCLE:
    obj = make_circle(curpos, dir);
    break;
  case MOVE:
    obj = make_move(curpos, dir);
    break;
  case ARC:
  case LINE:
  case SPLINE:
  case ARROW:
    obj = make_linear(curpos, dir);
    break;
  case MARK:
  case OTHER:
  default:
    assert(false);
    break;
  }
  if(obj != null) {
    if(flags.contains(INVISIBLE))
      obj.set_invisible();
    if(text != null)
      obj.add_text(text, (flags.contains(ALIGNED));)
    if(flags.contains(DOTTED))
      obj.set_dotted(dash_width);
    else if.contains(& IS_DASHED))
      obj.set_dashed(dash_width);
    double th;
    if(flags.contains(THICKNESS))
      th = thickness;
    else
      lookup_variable("linethick", &th);
    obj.set_thickness(th);
    if(flags.contains(OUTLINED))
      obj.set_outline_color(outlined);
    if(flags.contains(XSLANTED))
      obj.set_xslanted(xslanted);
    if(flags.contains(YSLANTED))
      obj.set_yslanted(yslanted);
    if(flags.contains(DEFAULT_FILLED) || flags.contains(FILLED)) {
      if(flags.contains(SHADED))
	obj.set_fill_color(shaded);
      else {
	if(flags.contains(DEFAULT_FILLED))
	  lookup_variable("fillval", &fill);
	if(fill < 0.0)
	  error("bad fill value %1", fill);
	else
	  obj.set_fill(fill);
      }
    }
  }
  return obj;
}

  Shape make_box(Position pos, Direction dir ) {return null;}
  static double last_box_height;
  static double last_box_width;
  static double last_box_radius;
  static int have_last_box = 0;
  if (!(flags & HAS_HEIGHT)) {
    if ((flags & IS_SAME) && have_last_box)
      height = last_box_height;
    else
      lookup_variable("boxht", &height);
  }
  if (!(flags & HAS_WIDTH)) {
    if ((flags & IS_SAME) && have_last_box)
      width = last_box_width;
    else
      lookup_variable("boxwid", &width);
  }
  if (!(flags & HAS_RADIUS)) {
    if ((flags & IS_SAME) && have_last_box)
      radius = last_box_radius;
    else
      lookup_variable("boxrad", &radius);
  }
  last_box_width = width;
  last_box_height = height;
  last_box_radius = radius;
  have_last_box = 1;
  radius = fabs(radius);
  if (radius*2.0 > fabs(width))
    radius = fabs(width/2.0);
  if (radius*2.0 > fabs(height))
    radius = fabs(height/2.0);
  box_object *p = new box_object(position(width, height), radius);
  if (!position_rectangle(p, curpos, dirp)) {
    delete p;
    p = 0;
  }
  return p;
}

int object_spec::position_rectangle(rectangle_object *p,
				    position *curpos, direction *dirp)
{
  position pos;
  dir = *dirp;			// ignore any direction in attribute list
  position motion;
  switch (dir) {
  case UP_DIRECTION:
    motion.y = p->height()/2.0;
    break;
  case DOWN_DIRECTION:
    motion.y = -p->height()/2.0;
    break;
  case LEFT_DIRECTION:
    motion.x = -p->width()/2.0;
    break;
  case RIGHT_DIRECTION:
    motion.x = p->width()/2.0;
    break;
  default:
    assert(0);
  }
  if (flags & HAS_AT) {
    pos = at;
    if (flags & HAS_WITH) {
      place offset;
      place here;
      here.obj = p;
      if (!with->follow(here, &offset))
	return 0;
      pos -= offset;
    }
  }
  else {
    pos = *curpos;
    pos += motion;
  }
  p->move_by(pos);
  pos += motion;
  *curpos = pos;
  return 1;
}
}

graphic_object *object_spec::make_block(position *curpos, direction *dirp)
{
  bounding_box bb;
  for (object *p = oblist.head; p; p = p->next)
    p->update_bounding_box(&bb);
  position dim;
  if (!bb.blank) {
    position m = -(bb.ll + bb.ur)/2.0;
    for (object *p = oblist.head; p; p = p->next)
      p->move_by(m);
    adjust_objectless_places(tbl, m);
    dim = bb.ur - bb.ll;
  }
  if (flags & HAS_WIDTH)
    dim.x = width;
  if (flags & HAS_HEIGHT)
    dim.y = height;
  block_object *block = new block_object(dim, oblist, tbl);
  if (!position_rectangle(block, curpos, dirp)) {
    delete block;
    block = 0;
  }
  tbl = 0;
  oblist.head = oblist.tail = 0;
  return block;
}


  Shape make_text(Position pos, Direction dir ) {return null;}
graphic_object *object_spec::make_text(position *curpos, direction *dirp)
{
  if (!(flags & HAS_HEIGHT)) {
    lookup_variable("textht", &height);
    int nitems = 0;
    for (text_item *t = text; t; t = t->next)
      nitems++;
    height *= nitems;
  }
  if (!(flags & HAS_WIDTH))
    lookup_variable("textwid", &width);
  text_object *p = new text_object(position(width, height));
  if (!position_rectangle(p, curpos, dirp)) {
    delete p;
    p = 0;
  }
  return p;
}

  Shape make_ellipse(Position pos, Direction dir ) {return null;}
graphic_object *object_spec::make_ellipse(position *curpos, direction *dirp)
{
  static double last_ellipse_height;
  static double last_ellipse_width;
  static int have_last_ellipse = 0;
  if (!(flags & HAS_HEIGHT)) {
    if ((flags & IS_SAME) && have_last_ellipse)
      height = last_ellipse_height;
    else
      lookup_variable("ellipseht", &height);
  }
  if (!(flags & HAS_WIDTH)) {
    if ((flags & IS_SAME) && have_last_ellipse)
      width = last_ellipse_width;
    else
      lookup_variable("ellipsewid", &width);
  }
  last_ellipse_width = width;
  last_ellipse_height = height;
  have_last_ellipse = 1;
  ellipse_object *p = new ellipse_object(position(width, height));
  if (!position_rectangle(p, curpos, dirp)) {
    delete p;
    return 0;
  }
  return p;
}
  Shape make_circle(Position pos, Direction dir ) {return null;}
graphic_object *object_spec::make_circle(position *curpos, direction *dirp)
{
  static double last_circle_radius;
  static int have_last_circle = 0;
  if (!(flags & HAS_RADIUS)) {
    if ((flags & IS_SAME) && have_last_circle)
      radius = last_circle_radius;
    else
      lookup_variable("circlerad", &radius);
  }
  last_circle_radius = radius;
  have_last_circle = 1;
  circle_object *p = new circle_object(radius*2.0);
  if (!position_rectangle(p, curpos, dirp)) {
    delete p;
    return 0;
  }
  return p;
}

  Shape make_line(Position pos, Direction dir ) {return null;}
linear_object *object_spec::make_line(position *curpos, direction *dirp)
{
  static position last_line;
  static int have_last_line = 0;
  *dirp = dir;
  // We handle `at' only in conjunction with `with', otherwise it is
  // the same as the `from' attribute.
  position startpos;
  if ((flags & HAS_AT) && (flags & HAS_WITH))
    // handled later -- we need the end position
    startpos = *curpos;
  else if (flags & HAS_FROM)
    startpos = from;
  else
    startpos = *curpos;
  if (!(flags & HAS_SEGMENT)) {
    if ((flags & IS_SAME) && (type == LINE_OBJECT || type == ARROW_OBJECT)
	&& have_last_line)
      segment_pos = last_line;
    else 
      switch (dir) {
      case UP_DIRECTION:
	segment_pos.y = segment_height;
	break;
      case DOWN_DIRECTION:
	segment_pos.y = -segment_height;
	break;
      case LEFT_DIRECTION:
	segment_pos.x = -segment_width;
	break;
      case RIGHT_DIRECTION:
	segment_pos.x = segment_width;
	break;
      default:
	assert(0);
      }
  }
  segment_list = new segment(segment_pos, segment_is_absolute, segment_list);
  // reverse the segment_list so that it's in forward order
  segment *old = segment_list;
  segment_list = 0;
  while (old != 0) {
    segment *tem = old->next;
    old->next = segment_list;
    segment_list = old;
    old = tem;
  }
  // Absolutise all movements
  position endpos = startpos;
  int nsegments = 0;
  segment *s;
  for (s = segment_list; s; s = s->next, nsegments++)
    if (s->is_absolute)
      endpos = s->pos;
    else {
      endpos += s->pos;
      s->pos = endpos;
      s->is_absolute = 1;	// to avoid confusion
    }
  if ((flags & HAS_AT) && (flags & HAS_WITH)) {
    // `tmpobj' works for arrows and splines too -- we only need positions
    line_object tmpobj(startpos, endpos, 0, 0);
    position pos = at;
    place offset;
    place here;
    here.obj = &tmpobj;
    if (!with->follow(here, &offset))
      return 0;
    pos -= offset;
    for (s = segment_list; s; s = s->next)
      s->pos += pos;
    startpos += pos;
    endpos += pos;
  }
  // handle chop
  line_object *p = 0;
  position *v = new position[nsegments];
  int i = 0;
  for (s = segment_list; s; s = s->next, i++)
    v[i] = s->pos;
  if (flags & IS_DEFAULT_CHOPPED) {
    lookup_variable("circlerad", &start_chop);
    end_chop = start_chop;
    flags |= IS_CHOPPED;
  }
  if (flags & IS_CHOPPED) {
    position start_chop_vec, end_chop_vec;
    if (start_chop != 0.0) {
      start_chop_vec = v[0] - startpos;
      start_chop_vec *= start_chop / hypot(start_chop_vec);
    }
    if (end_chop != 0.0) {
      end_chop_vec = (v[nsegments - 1]
		      - (nsegments > 1 ? v[nsegments - 2] : startpos));
      end_chop_vec *= end_chop / hypot(end_chop_vec);
    }
    startpos += start_chop_vec;
    v[nsegments - 1] -= end_chop_vec;
    endpos -= end_chop_vec;
  }
  switch (type) {
  case SPLINE_OBJECT:
    p = new spline_object(startpos, endpos, v, nsegments);
    break;
  case ARROW_OBJECT:
    p = new arrow_object(startpos, endpos, v, nsegments);
    break;
  case LINE_OBJECT:
    p = new line_object(startpos, endpos, v, nsegments);
    break;
  default:
    assert(0);
  }
  have_last_line = 1;
  last_line = endpos - startpos;
  *curpos = endpos;
  return p;
}
  Shape make_arc(Position pos, Direction dir ) {return null;}
// We ignore the with attribute. The at attribute always refers to the center.

linear_object *object_spec::make_arc(position *curpos, direction *dirp)
{
  *dirp = dir;
  int cw = (flags & IS_CLOCKWISE) != 0;
  // compute the start
  position startpos;
  if (flags & HAS_FROM)
    startpos = from;
  else
    startpos = *curpos;
  if (!(flags & HAS_RADIUS))
    lookup_variable("arcrad", &radius);
  // compute the end
  position endpos;
  if (flags & HAS_TO)
    endpos = to;
  else {
    position m(radius, radius);
    // Adjust the signs.
    if (cw) {
      if (dir == DOWN_DIRECTION || dir == LEFT_DIRECTION)
	m.x = -m.x;
      if (dir == DOWN_DIRECTION || dir == RIGHT_DIRECTION)
	m.y = -m.y;
      *dirp = direction((dir + 3) % 4);
    }
    else {
      if (dir == UP_DIRECTION || dir == LEFT_DIRECTION)
	m.x = -m.x;
      if (dir == DOWN_DIRECTION || dir == LEFT_DIRECTION)
	m.y = -m.y;
      *dirp = direction((dir + 1) % 4);
    }
    endpos = startpos + m;
  }
  // compute the center
  position centerpos;
  if (flags & HAS_AT)
    centerpos = at;
  else if (startpos == endpos)
    centerpos = startpos;
  else {
    position h = (endpos - startpos)/2.0;
    double d = hypot(h);
    if (radius <= 0)
      radius = .25;
    // make the radius big enough
    if (radius < d)
      radius = d;
    double alpha = acos(d/radius);
    double theta = atan2(h.y, h.x);
    if (cw)
      theta -= alpha;
    else
      theta += alpha;
    centerpos = position(cos(theta), sin(theta))*radius + startpos;
  }
  arc_object *p = new arc_object(cw, startpos, endpos, centerpos);
  *curpos = endpos;
  return p;
}
  Shape make_linear(Position pos, Direction dir ) {return null;}
graphic_object *object_spec::make_linear(position *curpos, direction *dirp)
{
  linear_object *obj;
  if (type == ARC_OBJECT)
    obj = make_arc(curpos, dirp);
  else
    obj = make_line(curpos, dirp);
  if (type == ARROW_OBJECT
      && (flags & (HAS_LEFT_ARROW_HEAD|HAS_RIGHT_ARROW_HEAD)) == 0)
    flags |= HAS_RIGHT_ARROW_HEAD;
  if (obj && (flags & (HAS_LEFT_ARROW_HEAD|HAS_RIGHT_ARROW_HEAD))) {
    arrow_head_type a;
    int at_start = (flags & HAS_LEFT_ARROW_HEAD) != 0;
    int at_end = (flags & HAS_RIGHT_ARROW_HEAD) != 0;
    if (flags & HAS_HEIGHT)
      a.height = height;
    else
      lookup_variable("arrowht", &a.height);
    if (flags & HAS_WIDTH)
      a.width = width;
    else
      lookup_variable("arrowwid", &a.width);
    double solid;
    lookup_variable("arrowhead", &solid);
    a.solid = solid != 0.0;
    obj->add_arrows(at_start, at_end, a);
  }
  return obj;
}
graphic_object *object_spec::make_move(position *curpos, direction *dirp)
{
  static position last_move;
  static int have_last_move = 0;
  *dirp = dir;
  // No need to look at at since `at' attribute sets `from' attribute.
  position startpos = (flags & HAS_FROM) ? from : *curpos;
  if (!(flags & HAS_SEGMENT)) {
    if ((flags & IS_SAME) && have_last_move)
      segment_pos = last_move;
    else {
      switch (dir) {
      case UP_DIRECTION:
	segment_pos.y = segment_height;
	break;
      case DOWN_DIRECTION:
	segment_pos.y = -segment_height;
	break;
      case LEFT_DIRECTION:
	segment_pos.x = -segment_width;
	break;
      case RIGHT_DIRECTION:
	segment_pos.x = segment_width;
	break;
      default:
	assert(0);
      }
    }
  }
  segment_list = new segment(segment_pos, segment_is_absolute, segment_list);
  // Reverse the segment_list so that it's in forward order.
  segment *old = segment_list;
  segment_list = 0;
  while (old != 0) {
    segment *tem = old->next;
    old->next = segment_list;
    segment_list = old;
    old = tem;
  }
  // Compute the end position.
  position endpos = startpos;
  for (segment *s = segment_list; s; s = s->next)
    if (s->is_absolute)
      endpos = s->pos;
    else 
      endpos += s->pos;
  have_last_move = 1;
  last_move = endpos - startpos;
  move_object *p = new move_object(startpos, endpos);
  *curpos = endpos;
  return p;
}

  Shape make_move(Position pos, Direction dir ) {return null;}
