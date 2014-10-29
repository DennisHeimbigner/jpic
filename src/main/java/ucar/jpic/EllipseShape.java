class ellipse_object : public closed_object {
public:
  ellipse_object(const position &);
  position north_east() { return position(cent.x + dim.x/(M_SQRT2*2.0),
					  cent.y + dim.y/(M_SQRT2*2.0)); }
  position north_west() { return position(cent.x - dim.x/(M_SQRT2*2.0),
					  cent.y + dim.y/(M_SQRT2*2.0)); }
  position south_east() { return position(cent.x + dim.x/(M_SQRT2*2.0),
					  cent.y - dim.y/(M_SQRT2*2.0)); }
  position south_west() { return position(cent.x - dim.x/(M_SQRT2*2.0),
					  cent.y - dim.y/(M_SQRT2*2.0)); }
  double radius() { return dim.x/2.0; }
  object_type type() { return ELLIPSE_OBJECT; }
  void print();
};

ellipse_object::ellipse_object(const position &d)
: closed_object(d)
{
}

void ellipse_object::print()
{
  if (lt.type == line_type::invisible && fill < 0.0 && color_fill == 0)
    return;
  out->set_color(color_fill, graphic_object::get_outline_color());
  out->ellipse(cent, dim, lt, fill);
  out->reset_color();
}

