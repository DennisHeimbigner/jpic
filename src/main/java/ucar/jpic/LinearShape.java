class linear_object : public graphic_object {
protected:
  char arrow_at_start;
  char arrow_at_end;
  arrow_head_type aht;
  position strt;
  position en;
public:
  linear_object(const position &s, const position &e);
  position start() { return strt; }
  position end() { return en; }
  void move_by(const position &);
  void update_bounding_box(bounding_box *) = 0;
  object_type type() = 0;
  void add_arrows(int at_start, int at_end, const arrow_head_type &);
};
