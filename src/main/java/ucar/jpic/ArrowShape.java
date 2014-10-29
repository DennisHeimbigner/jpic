class arrow_object : public line_object {
public:
  arrow_object(const position &, const position &, position *, int);
  object_type type() { return ARROW_OBJECT; }
};
