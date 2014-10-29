class circle_object : public ellipse_object {
public:
  circle_object(double);
  object_type type() { return CIRCLE_OBJECT; }
  void print();
};

circle_object::circle_object(double diam)
: ellipse_object(position(diam, diam))
{
}

void circle_object::print()
{
  if (lt.type == line_type::invisible && fill < 0.0 && color_fill == 0)
    return;
  out->set_color(color_fill, graphic_object::get_outline_color());
  out->circle(cent, dim.x/2.0, lt, fill);
  out->reset_color();
}

