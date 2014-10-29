class text_object : public rectangle_object {
public:
  text_object(const position &);
  object_type type() { return TEXT_OBJECT; }
};

text_object::text_object(const position &d)
: rectangle_object(d)
{
}

