class line_object : public linear_object {
protected:
  position *v;
  int n;
public:
  line_object(const position &s, const position &e, position *, int);
  ~line_object();
  position origin() { return strt; }
  position center() { return (strt + en)/2.0; }
  position north() { return (en.y - strt.y) > 0 ? en : strt; }
  position south() { return (en.y - strt.y) < 0 ? en : strt; }
  position east() { return (en.x - strt.x) > 0 ? en : strt; }
  position west() { return (en.x - strt.x) < 0 ? en : strt; }
  object_type type() { return LINE_OBJECT; }
  void update_bounding_box(bounding_box *);
  void print();
  void move_by(const position &);
};
