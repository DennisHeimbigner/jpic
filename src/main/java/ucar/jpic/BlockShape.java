class block_object : public rectangle_object {
  object_list oblist;
  PTABLE(place) *tbl;
public:
  block_object(const position &, const object_list &ol, PTABLE(place) *t);
  ~block_object();
  place *find_label(const char *);
  object_type type();
  void move_by(const position &);
  void print();
};

block_object::block_object(const position &d, const object_list &ol,
			   PTABLE(place) *t)
: rectangle_object(d), oblist(ol), tbl(t)
{
}

block_object::~block_object()
{
  delete tbl;
  object *p = oblist.head;
  while (p != 0) {
    object *tem = p;
    p = p->next;
    delete tem;
  }
}

void block_object::print()
{
  out->begin_block(south_west(), north_east());
  print_object_list(oblist.head);
  out->end_block();
}

static void adjust_objectless_places(PTABLE(place) *tbl, const position &a)
{
  // Adjust all the labels that aren't attached to objects.
  PTABLE_ITERATOR(place) iter(tbl);
  const char *key;
  place *pl;
  while (iter.next(&key, &pl))
    if (key && csupper(key[0]) && pl->obj == 0) {
      pl->x += a.x;
      pl->y += a.y;
    }
}

void block_object::move_by(const position &a)
{
  cent += a;
  for (object *p = oblist.head; p; p = p->next)
    p->move_by(a);
  adjust_objectless_places(tbl, a);
}


place *block_object::find_label(const char *name)
{
  return tbl->lookup(name);
}

object_type block_object::type()
{
  return BLOCK_OBJECT;
}

