class box_object : public closed_object {
  double xrad;
  double yrad;
public:
  box_object(const position &, double);
  object_type type() { return BOX_OBJECT; }
  void print();
  position north_east();
  position north_west();
  position south_east();
  position south_west();
};

box_object::box_object(const position &pos, double r)
: closed_object(pos), xrad(dim.x > 0 ? r : -r), yrad(dim.y > 0 ? r : -r)
{
}

const double CHOP_FACTOR = 1.0 - 1.0/M_SQRT2;

position box_object::north_east()
{
  return position(cent.x + dim.x/2.0 - CHOP_FACTOR*xrad,
		  cent.y + dim.y/2.0 - CHOP_FACTOR*yrad);
}

position box_object::north_west()
{
  return position(cent.x - dim.x/2.0 + CHOP_FACTOR*xrad,
		  cent.y + dim.y/2.0 - CHOP_FACTOR*yrad);
}

position box_object::south_east()
{
  return position(cent.x + dim.x/2.0 - CHOP_FACTOR*xrad,
		  cent.y - dim.y/2.0 + CHOP_FACTOR*yrad);
}

position box_object::south_west()
{
  return position(cent.x - dim.x/2.0 + CHOP_FACTOR*xrad,
		  cent.y - dim.y/2.0 + CHOP_FACTOR*yrad);
}

void box_object::print()
{
  if (lt.type == line_type::invisible && fill < 0.0 && color_fill == 0)
    return;
  out->set_color(color_fill, graphic_object::get_outline_color());
  if (xrad == 0.0) {
    distance dim2 = dim/2.0;
    position vec[4];
    /* error("x slanted %1", xslanted); */
    /* error("y slanted %1", yslanted); */
    vec[0] = cent + position(dim2.x, -(dim2.y - yslanted));	     /* lr */
    vec[1] = cent + position(dim2.x + xslanted, dim2.y + yslanted);  /* ur */
    vec[2] = cent + position(-(dim2.x - xslanted), dim2.y);	     /* ul */
    vec[3] = cent + position(-(dim2.x), -dim2.y);		     /* ll */
    out->polygon(vec, 4, lt, fill);
  }
  else {
    distance abs_dim(fabs(dim.x), fabs(dim.y));
    out->rounded_box(cent, abs_dim, fabs(xrad), lt, fill, color_fill);
  }
  out->reset_color();
}
