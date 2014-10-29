class move_object : public graphic_object {
  position strt;
  position en;
public:
  move_object(const position &s, const position &e);
  position origin() { return en; }
  object_type type() { return MOVE_OBJECT; }
  void update_bounding_box(bounding_box *);
  void move_by(const position &);
};

move_object::move_object(const position &s, const position &e)
: strt(s), en(e)
{
}

void move_object::update_bounding_box(bounding_box *p)
{
  p->encompass(strt);
  p->encompass(en);
}

void move_object::move_by(const position &a)
{
  strt += a;
  en += a;
}

