class spline_object : public line_object {
public:
  spline_object(const position &, const position &, position *, int);
  object_type type() { return SPLINE_OBJECT; }
  void print();
  void update_bounding_box(bounding_box *);
};
