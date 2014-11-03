/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.*;

abstract public class ShapeSpec extends GraphicObject
{
    ShapeType type;
    Map<String, Place> tbl = null;
    Position from = null;
    Position to = null;
    Position at = null;
    Position by = null;
    Path with = null;
    TextItem text = null;
    List<Segment> segment_list = null;
    Position segment_pos = null;
    DirectionType dir = DirectionType.RIGHT;
    EnumSet<PropertyType> flags = EnumSet.noneOf(PropertyType.class);
    double dash_width = 0.0;
    double height = 0.0;
    double radius = 0.0;
    double width = 0.0;
    double segment_width = 0.0;
    double segment_height = 0.0;
    double start_chop = 0.0;
    double end_chop = 0.0;
    double thickness = 0.0;
    double fill = 0.0;
    double xslanted = 0.0;
    double yslanted = 0.0;
    String shaded = null;
    String outlined = null;
    boolean segment_is_absolute;

    public ShapeSpec()
    {
        // infer the type
	if(this instanceof BoxShape) this.type = ShapeType.BOX;
	else if(this instanceof CircleShape) this.type = ShapeType.CIRCLE;
	else if(this instanceof EllipseShape) this.type = ShapeType.BOX;
	else if(this instanceof ArcShape) this.type = ShapeType.BOX;
	else if(this instanceof SplineShape) this.type = ShapeType.BOX;
	else if(this instanceof LineShape) this.type = ShapeType.BOX;
	else if(this instanceof ArrowShape) this.type = ShapeType.BOX;
	else if(this instanceof TextShape) this.type = ShapeType.BOX;
	else if(this instanceof BlockShape) this.type = ShapeType.BOX;
	else assert(false);	
    }

    public ShapeSpec(ShapeType type)
    {
        this.type = type;
    }

    abstract public void update_bounding_box(BoundingBox bb);

    ShapeSpec
    make_object(Position curpos, DirectionType dir)
        throws JPicException
    {
        ShapeSpec obj = null;
        switch (type) {
        case BLOCK:
            obj = make_block(curpos, dir);
            break;
        case BOX:
            obj = make_box(curpos, dir);
            break;
        case TEXTBOX:
            obj = make_text(curpos, dir);
            break;
        case ELLIPSE:
            obj = make_ellipse(curpos, dir);
            break;
        case CIRCLE:
            obj = make_circle(curpos, dir);
            break;
        case ARC:
        case LINE:
        case SPLINE:
        case ARROW:
            obj = make_linear(curpos, dir);
            break;
        case MARK:
        case OTHER:
        default:
            assert (false);
            break;
        }
        if(obj != null) {
            if(flags.contains(PropertyType.INVISIBLE))
                obj.set_invisible();
            if(text != null)
                obj.add_text(text, (flags.contains(PropertyType.ALIGNED)));
            if(flags.contains(PropertyType.DOTTED))
                obj.set_dotted(dash_width);
            else if(flags.contains(PropertyType.DASHED))
                obj.set_dashed(dash_width);
            double th;
            if(flags.contains(PropertyType.THICKNESS))
                th = thickness;
            else {
                th = (Double)JPic.getValue("linethick");
            }
            obj.set_thickness(th);
            if(flags.contains(PropertyType.OUTLINED))
                obj.set_outline_color(outlined);
            if(flags.contains(PropertyType.XSLANTED))
                obj.set_xslanted(xslanted);
            if(flags.contains(PropertyType.YSLANTED))
                obj.set_yslanted(yslanted);
            if(flags.contains(PropertyType.DEFAULT_FILLED) || flags.contains(PropertyType.FILLED)) {
                if(flags.contains(PropertyType.SHADED))
                    obj.set_fill_color(shaded);
                else {
                    if(flags.contains(PropertyType.DEFAULT_FILLED)) {
                        Variable v = JPic.lookup("fillval");
                        fill = (Double) v.value;
                    }
                    if(fill < 0.0)
                        throw new JPicException("bad fill value " + fill);
                    else
                        obj.set_fill(fill);
                }
            }
        }
        return obj;
    }

    ShapeSpec make_box(Position curpos, DirectionType dir)
        throws JPicException
    {

        double last_box_height = 0.0;
        double last_box_width = 0.0;
        double last_box_radius = 0.0;
        boolean have_last_box = false;

        if(!flags.contains(PropertyType.HEIGHT)) {
            if(flags.contains(PropertyType.SAME) && have_last_box)
                height = last_box_height;
            else
                height = (Double) JPic.getValue("boxht");
        }
        if(!flags.contains(PropertyType.WIDTH)) {
            if(flags.contains(PropertyType.SAME) && have_last_box)
                width = last_box_width;
            else
                width = (Double) JPic.getValue("boxwid");
        }
        if(!flags.contains(PropertyType.RADIUS)) {
            if(flags.contains(PropertyType.SAME) && have_last_box)
                radius = last_box_radius;
            else
                radius = (Double) JPic.getValue("boxrad");
        }

        last_box_width = width;
        last_box_height = height;
        last_box_radius = radius;
        have_last_box = true;
        radius = Math.abs(radius);
        if(radius * 2.0 > Math.abs(width))
            radius = Math.abs(width / 2.0);
        if(radius * 2.0 > Math.abs(height))
            radius = Math.abs(height / 2.0);
        BoxShape p = new BoxShape().init(new Position(width, height), radius);
        if(position_rectangle(p, curpos, dir) == null)
            p = null;
        return p;
    }

    Position
    position_rectangle(RectangleShape p, Position curpos, DirectionType dirp)
    {
        Position pos;
        dir = dirp; // ignore any direction in attribute list
        Position motion;
        switch (dir) {
        case UP:
            motion.y = p.height() / 2.0;
            break;
        case DOWN:
            motion.y = -p.height() / 2.0;
            break;
        case LEFT:
            motion.x = -p.width() / 2.0;
            break;
        case RIGHT:
            motion.x = p.width() / 2.0;
            break;
        default:
            assert (0);
        }
        if(flags.contains(PropertyType.AT)) {
            pos = at;
            if(flags.contains(PropertyType.WITH)) {
                Place offset = new Place();
                Place here = new Place();
                here.obj = p;
                if(!with.follow(here, offset))
                    return null;
                pos.subtract(offset);
            }
        } else {
            pos = new Position(curpos);
            pos.add(motion);
        }
        p.move_by(pos);
        pos.add(motion);
        return pos;
    }


    BlockShape
    make_block(Position curpos, DirectionType dirp)
    {
        BoundingBox bb;
        for(Element p : JPic.oblist)
            if(p instanceof ShapeSpec)
                ((ShapeSpec) p).update_bounding_box(bb);
        Position dim;
        if(!bb.blank) {
            Position m = Position.add((bb.ll, bb.ur).divide(2.0).negate();
            for(Element p : oblist)
                if(p instanceof ShapeSpec)
                    ((ShapeSpec) p).move_by(m);
            adjust_objectless_places(tbl, m);
            dim = bb.ur - bb.ll;
        }
        if(flags.contains(PropertyType.WIDTH))
            dim.x = width;
        if(flags.contains(PropertyType.HEIGHT))
            dim.y = height;
        BlockShape block = new block_object(dim, oblist, tbl);

        if(!

            position_rectangle(block, curpos, dirp)

            )

        {
            delete block;
            block = 0;
        }

        tbl = 0;
        oblist.head = oblist.tail = 0;
        return block;
    }


    ShapeSpec
    make_text(Position curpos, DirectionType dirp)
    {
        if(!flags.contains(PropertyType.HEIGHT)) {
            height = (Double) JPic.getValue("textht");
            int nitems = 0;
            for(text_item * t = text;t;t = t.next)
                nitems++;
            height *= nitems;
        }
        if(!flags.contains(PropertyType.WIDTH))
            width = (Double) JPic.getValue("textwid");
        text_object * p = new text_object(position(width, height));
        if(!position_rectangle(p, curpos, dirp)) {
            delete p;
            p = 0;
        }
        return p;
    }

    ShapeSpec
    make_ellipse(Position curpos, DirectionType dirp)
    {
        double last_ellipse_height;
        double last_ellipse_width;
        int have_last_ellipse = 0;
        if(!flags.contains(PropertyType.HEIGHT)) {
            if(flags.contains(PropertyType.SAME) && have_last_ellipse)
                height = last_ellipse_height;
            else
                height = (Double) JPic.getValue("ellipseht");
        }
        if(!flags.contains(PropertyType.WIDTH)) {
            if(flags.contains(PropertyType.SAME) && have_last_ellipse)
                width = last_ellipse_width;
            else
                width = (Double) JPic.getValue("ellipsewid");
        }
        last_ellipse_width = width;
        last_ellipse_height = height;
        have_last_ellipse = 1;
        ellipse_object * p = new ellipse_object(position(width, height));
        if(!position_rectangle(p, curpos, dirp)) {
            delete p;
            return 0;
        }
        return p;
    }

    ShapeSpec
    make_circle(Position curpos, DirectionType dirp)
    {
        static double last_circle_radius;
        static int have_last_circle = 0;
        if(!flags.contains(PropertyType.RADIUS)) {
            if((flags.contains(PropertyType.SAME) && have_last_circle)
            radius = last_circle_radius;
            else
            radius = (Double) JPic.getValue("circlerad");
        }
        last_circle_radius = radius;
        have_last_circle = 1;
        circle_object * p = new circle_object(radius * 2.0);
        if(!position_rectangle(p, curpos, dirp)) {
            delete p;
            return 0;
        }
        return p;
    }

    ShapeSpec
    make_line(Position curpos, DirectionType dirp)
    {
        static position last_line;
        static int have_last_line = 0;
        *dirp = dir;
        // We handle `at' only in conjunction with `with', otherwise it is
        // the same as the `from' attribute.
        position startpos;
        if(flags.contains(PropertyType.AT) && flags.contains(PropertyType.WITH))
            // handled later -- we need the end position
            startpos =*curpos;
        else if(flags.contains(PropertyType.FROM))
        startpos = from;
    else
        startpos =*curpos;
        if(!flags.contains(PropertyType.SEGMENT)) {
            if(flags.contains(PropertyType.SAME) && (type == LINE_OBJECT || type == ARROW_OBJECT) && have_last_line)
                segment_pos = last_line;
            else
                switch (dir) {
                case UP_DIRECTION:
                    segment_pos.y = segment_height;
                    break;
                case DOWN_DIRECTION:
                    segment_pos.y = -segment_height;
                    break;
                case LEFT_DIRECTION:
                    segment_pos.x = -segment_width;
                    break;
                case RIGHT_DIRECTION:
                    segment_pos.x = segment_width;
                    break;
                default:
                    assert (0);
                }
        }
        segment_list = new segment(segment_pos, segment_is_absolute, segment_list);
        // reverse the segment_list so that it's in forward order
        segment * old = segment_list;
        segment_list = 0;
        while(old != 0) {
            segment * tem = old.next;
            old.next = segment_list;
            segment_list = old;
            old = tem;
        }
        // Absolutise all movements
        position endpos = startpos;
        int nsegments = 0;
        segment * s;
        for(s = segment_list;s;s = s.next, nsegments++)
            if(s.is_absolute)
                endpos = s.pos;
            else {
                endpos += s.pos;
                s.pos = endpos;
                s.is_absolute = 1;    // to avoid confusion
            }
        if(flags.contains(PropertyType.AT) && flags.contains(PropertyType.WITH)) {
            // `tmpobj' works for arrows and splines too -- we only need positions
            line_object tmpobj (startpos, endpos, 0, 0);
            position pos = at;
            place offset;
            place here;
            here.obj =&tmpobj;
            if(!with.follow(here, & offset))
            return 0;
            pos -= offset;
            for(s = segment_list;s;s = s.next)
                s.pos += pos;
            startpos += pos;
            endpos += pos;
        }
        // handle chop
        line_object * p = 0;
        Position v = new position[nsegments];
        int i = 0;
        for(s = segment_list;s;s = s.next, i++)
            v[i] = s.pos;
        if(flags.contains(PropertyType.DEFAULT_CHOPPED)) {
            chop = (Double) JPic.getValue("circlerad");
            end_chop = start_chop;
            flags |= IS_CHOPPED;
        }
        if(flags.contains(PropertyType.CHOPPED)) {
            position start_chop_vec, end_chop_vec;
            if(start_chop != 0.0) {
                start_chop_vec = v[0] - startpos;
                start_chop_vec *= start_chop / hypot(start_chop_vec);
            }
            if(end_chop != 0.0) {
                end_chop_vec = (v[nsegments - 1]
                    - (nsegments > 1 ? v[nsegments - 2] : startpos));
                end_chop_vec *= end_chop / hypot(end_chop_vec);
            }
            startpos += start_chop_vec;
            v[nsegments - 1] -= end_chop_vec;
            endpos -= end_chop_vec;
        }
        switch (type) {
        case SPLINE_OBJECT:
            p = new spline_object(startpos, endpos, v, nsegments);
            break;
        case ARROW_OBJECT:
            p = new arrow_object(startpos, endpos, v, nsegments);
            break;
        case LINE_OBJECT:
            p = new line_object(startpos, endpos, v, nsegments);
            break;
        default:
            assert (0);
        }
        have_last_line = 1;
        last_line = endpos - startpos;
        *curpos = endpos;
        return p;
    }

    // We ignore the with attribute. The at attribute always refers to the center.
     ShapeSpec
    make_arc(Position curpos, DirectionType dirp)
    {
        *dirp = dir;
        boolean cw = flags.contains(PropertyType.CLOCKWISE);
        // compute the start
        position startpos;
        if(flags.contains(PropertyType.FROM))
            startpos = from;
        else
            startpos =*curpos;
        if(!flags.contains(PropertyType.RADIUS))
            radius = (Double) JPic.getValue("arcrad");
        // compute the end
        position endpos;
        if(flags.contains(PropertyType.TO))
            endpos = to;
        else {
            position m (radius, radius);
            // Adjust the signs.
            if(cw) {
                if(dir == DOWN || dir == LEFT)
                    m.x = -m.x;
                if(dir == DOWN || dir == RIGHT)
                    m.y = -m.y;
                *dirp = direction((dir + 3) % 4);
            } else {
                if(dir == UP || dir == LEFT)
                    m.x = -m.x;
                if(dir == DOWN || dir == LEFT)
                    m.y = -m.y;
                *dirp = direction((dir + 1) % 4);
            }
            endpos = startpos + m;
        }
        // compute the center
        position centerpos;
        if(flags.contains(PropertyType.AT))
            centerpos = at;
        else if(startpos == endpos)
            centerpos = startpos;
        else {
            position h = (endpos - startpos) / 2.0;
            double d = hypot(h);
            if(radius <= 0)
                radius = .25;
            // make the radius big enough
            if(radius < d)
                radius = d;
            double alpha = acos(d / radius);
            double theta = atan2(h.y, h.x);
            if(cw)
                theta -= alpha;
            else
                theta += alpha;
            centerpos = position(cos(theta), sin(theta)) * radius + startpos;
        }
        arc_object * p = new arc_object(cw, startpos, endpos, centerpos);
        *curpos = endpos;
        return p;
    }

    ShapeSpec
    make_linear(Position curpos, DirectionType dirp)
    {
        linear_object * obj;
        if(type == ARC_OBJECT)
            obj = make_arc(curpos, dirp);
        else
            obj = make_line(curpos, dirp);
        if(type == ARROW_OBJECT
            && (flags.contains(PropertyType.LEFT_ARROW_HEAD)
            || flags.contains(RIGHT_ARROW_HEAD)))
            flags.add(HAS_RIGHT_ARROW_HEAD);
        if(obj != null
            && (flags.contains(PropertyType.LEFT_ARROW_HEAD)
            || flags.contains(RIGHT_ARROW_HEAD))) {
            arrow_head_type a;
            boolean at_start = (flags.contains(PropertyType.LEFT_ARROW_HEAD));
            boolean at_end = (flags.contains(PropertyType.RIGHT_ARROW_HEAD));
            if(flags.contains(PropertyType.HEIGHT))
                a.height = height;
            else
                height = (Double) JPic.getValue("arrowht");
            if(flags.contains(PropertyType.WIDTH))
                a.width = width;
            else
                width = (Double) JPic.getValue("arrowwid");
            double solid;
            solid = (Double) JPic.getValue("arrowhead");
            a.solid = solid != 0.0;
            obj.add_arrows(at_start, at_end, a);
        }
        return obj;
    }

    GraphicObject*object_spec::

    make_move(Position curpos, direction*dirp)
    {
        static position last_move;
        static int have_last_move = 0;
        *dirp = dir;
        // No need to look at at since `at' attribute sets `from' attribute.
        position startpos = (flags.contains(PropertyType.FROM) ? from : curpos);
        if(!flags.contains(PropertyType.SEGMENT)) {
            if(flags.contains(PropertyType.SAME) && have_last_move)
                segment_pos = last_move;
            else {
                switch (dir) {
                case UP:
                    segment_pos.y = segment_height;
                    break;
                case DOWN:
                    segment_pos.y = -segment_height;
                    break;
                case LEFT:
                    segment_pos.x = -segment_width;
                    break;
                case RIGHT:
                    segment_pos.x = segment_width;
                    break;
                default:
                    assert (0);
                }
            }
        }
        segment_list = new segment(segment_pos, segment_is_absolute, segment_list);
        // Reverse the segment_list so that it's in forward order.
        segment * old = segment_list;
        segment_list = 0;
        while(old != 0) {
            segment * tem = old.next;
            old.next = segment_list;
            segment_list = old;
            old = tem;
        }
        // Compute the end position.
        position endpos = startpos;
        for(segment * s = segment_list;s;s = s.next)
            if(s.is_absolute)
                endpos = s.pos;
            else
                endpos += s.pos;
        have_last_move = 1;
        last_move = endpos - startpos;
        move_object * p = new move_object(startpos, endpos);
        *curpos = endpos;
        return p;
    }

    ShapeSpec
    make_move(Position pos, DirectionType dir)
    {
        return null;
    }

}