class arc_object : public linear_object {
  int clockwise;
  position cent;
  double rad;
public:
  arc_object(int, const position &, const position &, const position &);
  position origin() { return cent; }
  position center() { return cent; }
  double radius() { return rad; }
  position north();
  position south();
  position east();
  position west();
  position north_east();
  position north_west();
  position south_east();
  position south_west();
  void update_bounding_box(bounding_box *);
  object_type type() { return ARC_OBJECT; }
  void print();
  void move_by(const position &pos);
};

arc_object::arc_object(int cw, const position &s, const position &e,
		       const position &c)
: linear_object(s, e), clockwise(cw), cent(c)
{
  rad = hypot(c - s);
}

void arc_object::move_by(const position &pos)
{
  linear_object::move_by(pos);
  cent += pos;
}

// we get arc corners from the corresponding circle

position arc_object::north()
{
  position result(cent);
  result.y += rad;
  return result;
}

position arc_object::south()
{
  position result(cent);
  result.y -= rad;
  return result;
}

position arc_object::east()
{
  position result(cent);
  result.x += rad;
  return result;
}

position arc_object::west()
{
  position result(cent);
  result.x -= rad;
  return result;
}

position arc_object::north_east()
{
  position result(cent);
  result.x += rad/M_SQRT2;
  result.y += rad/M_SQRT2;
  return result;
}

position arc_object::north_west()
{
  position result(cent);
  result.x -= rad/M_SQRT2;
  result.y += rad/M_SQRT2;
  return result;
}

position arc_object::south_east()
{
  position result(cent);
  result.x += rad/M_SQRT2;
  result.y -= rad/M_SQRT2;
  return result;
}

position arc_object::south_west()
{
  position result(cent);
  result.x -= rad/M_SQRT2;
  result.y -= rad/M_SQRT2;
  return result;
}


void arc_object::print()
{
  if (lt.type == line_type::invisible)
    return;
  out->set_color(0, graphic_object::get_outline_color());
  // handle arrow direction; make shorter line for arc
  position sp, ep, b;
  if (clockwise) {
    sp = en;
    ep = strt;
  } else {
    sp = strt;
    ep = en;
  }
  if (arrow_at_start) {
    double theta = aht.height / rad;
    if (clockwise)
      theta = - theta;
    b = strt - cent;
    b = position(b.x*cos(theta) - b.y*sin(theta),
		 b.x*sin(theta) + b.y*cos(theta)) + cent;
    if (clockwise)
      ep = b;
    else
      sp = b;
    if (aht.solid && out->supports_filled_polygons()) {
      draw_arrow(strt, strt - b, aht, lt,
		 graphic_object::get_outline_color());
    } else {
      position v = b;
      theta = fabs(lt.thickness) / 72 / 4 / rad;
      if (clockwise)
	theta = - theta;
      b = strt - cent;
      b = position(b.x*cos(theta) - b.y*sin(theta),
		   b.x*sin(theta) + b.y*cos(theta)) + cent;
      draw_arrow(b, b - v, aht, lt,
		 graphic_object::get_outline_color());
      out->line(b, &v, 1, lt);
    }
  }
  if (arrow_at_end) {
    double theta = aht.height / rad;
    if (!clockwise)
      theta = - theta;
    b = en - cent;
    b = position(b.x*cos(theta) - b.y*sin(theta),
                 b.x*sin(theta) + b.y*cos(theta)) + cent;
    if (clockwise)
      sp = b;
    else
      ep = b;
    if (aht.solid && out->supports_filled_polygons()) {
      draw_arrow(en, en - b, aht, lt,
		 graphic_object::get_outline_color());
    } else {
      position v = b;
      theta = fabs(lt.thickness) / 72 / 4 / rad;
      if (!clockwise)
	theta = - theta;
      b = en - cent;
      b = position(b.x*cos(theta) - b.y*sin(theta),
                   b.x*sin(theta) + b.y*cos(theta)) + cent;
      draw_arrow(b, b - v, aht, lt,
		 graphic_object::get_outline_color());
      out->line(b, &v, 1, lt);
    }
  }
  out->arc(sp, cent, ep, lt);
  out->reset_color();
}

inline double max(double a, double b)
{
  return a > b ? a : b;
}

void arc_object::update_bounding_box(bounding_box *p)
{
  p->encompass(strt);
  p->encompass(en);
  position start_offset = strt - cent;
  if (start_offset.x == 0.0 && start_offset.y == 0.0)
    return;
  position end_offset = en  - cent;
  if (end_offset.x == 0.0 && end_offset.y == 0.0)
    return;
  double start_quad = atan2(start_offset.y, start_offset.x)/(M_PI/2.0);
  double end_quad = atan2(end_offset.y, end_offset.x)/(M_PI/2.0);
  if (clockwise) {
    double temp = start_quad;
    start_quad = end_quad;
    end_quad = temp;
  }
  if (start_quad < 0.0)
    start_quad += 4.0;
  while (end_quad <= start_quad)
    end_quad += 4.0;
  double r = max(hypot(start_offset), hypot(end_offset));
  for (int q = int(start_quad) + 1; q < end_quad; q++) {
    position offset;
    switch (q % 4) {
    case 0:
      offset.x = r;
      break;
    case 1:
      offset.y = r;
      break;
    case 2:
      offset.x = -r;
      break;
    case 3:
      offset.y = -r;
      break;
    }
    p->encompass(cent + offset);
  }
}

// We ignore the with attribute. The at attribute always refers to the center.

