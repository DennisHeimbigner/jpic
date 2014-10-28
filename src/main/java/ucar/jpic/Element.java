/*
Thish software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.ArrayList;
import java.util.EnumSet;

class Element
{

    //////////////////////////////////////////////////
    // Constants

    static public final long IS_DOTTED = 01L;
    static public final long IS_DASHED = 02L;
    static public final long IS_CLOCKWISE = 04L;
    static public final long IS_INVISIBLE = 020L;
    static public final long HAS_LEFT_ARROW_HEAD = 040L;
    static public final long HAS_RIGHT_ARROW_HEAD = 0100L;
    static public final long HAS_SEGMENT = 0200L;
    static public final long IS_SAME = 0400L;
    static public final long HAS_FROM = 01000L;
    static public final long HAS_AT = 02000L;
    static public final long HAS_WITH = 04000L;
    static public final long HAS_HEIGHT = 010000L;
    static public final long HAS_WIDTH = 020000L;
    static public final long HAS_RADIUS = 040000L;
    static public final long HAS_TO = 0100000L;
    static public final long IS_CHOPPED = 0200000L;
    static public final long IS_DEFAULT_CHOPPED = 0400000L;
    static public final long HAS_THICKNESS = 01000000L;
    static public final long IS_FILLED = 02000000L;
    static public final long IS_DEFAULT_FILLED = 04000000L;
    static public final long IS_ALIGNED = 010000000L;
    static public final long IS_SHADED = 020000000L;
    static public final long IS_OUTLINED = 040000000L;
    static public final long IS_XSLANTED = 0100000000L;
    static public final long IS_YSLANTED = 0200000000L;

    //////////////////////////////////////////////////,
    // Type Declarations

    static enum HAdjustment
    {
        CENTER, LEFT, RIGHT;
    }

    static enum VAdjustment
    {
        NONE, ABOVE, BELOW;
    }

    static enum Direction
    {
        RIGHT,
        UP,
        LEFT,
        DOWN;
    }

    static enum LineType
    {
        INVISIBLE, SOLID, DOTTED, DASHED;
    }

    static public enum Supported
    {
        FIG, TEXT, SVG;
    }

    static EnumSet<Supported> SUPPORTED = EnumSet.of(Supported.SVG);

    static public enum ShapeType
    {
        OTHER,
        BOX,
        CIRCLE,
        ELLIPSE,
        ARC,
        SPLINE,
        LINE,
        ARROW,
        TEXTBOX,
        BLOCK,
        MARK;
    }

    static enum PropertyType
    {
        HEIGHT,
        RADIUS,
        WIDTH,
        DIAMETER,
        FROM,
        TO,
        AT,
        WITH,
        BY,
        THEN,
        SOLID,
        DOTTED,
        DASHED,
        FILL,
        XSLANTED,
        YSLANTED,
        SHADED,
        COLORED,
        OUTLINED,
        CHOP,
        SAME,
        INVISIBLE,
        LEFT,
        RIGHT,
        DOUBLE,
        CW,
        CCW,
        LJUST,
        RJUST,
        ABOVE,
        BELOW,
        THICKNESS,
        ALIGNED,
        LEFT_ARROW_HEAD,
        RIGHT_ARROW_HEAD,
        DOUBLE_ARROW_HEAD,
        EXPR,
        TEXT
    }

    static enum Operator
    {
        ANDAND,
        OROR,
        NOTEQUAL,
        EQUALEQUAL,
        LESSEQUAL,
        GREATEREQUAL,
        ADD,
        MINUS,
        TIMES,
        DIV,
        MOD,
        SIN,
        COS,
        ATAN2,
        LOG,
        EXP,
        SQRT,
        K_MAX,
        K_MIN,
        RAND,
        SRAND,
        INT;
    }

    static enum CommandType
    {
        MOVE,
        UP,
        DOWN,
        RIGHT,
        LEFT,
        FOR,
        IF;
    }

    static enum CornerType
    {
        NORTH,
        EAST,
        WEST,
        SOUTH,
        NORTH_EAST,
        SOUTH_EAST,
        NORTH_WEST,
        SOUTH_WEST,
        CENTER,
        START,
        END,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        UPPER_LEFT,
        LOWER_LEFT,
        UPPER_RIGHT,
        LOWER_RIGHT,
        LEFT_CORNER,
        RIGHT_CORNER,
        UPPER_LEFT_CORNER,
        LOWER_LEFT_CORNER,
        UPPER_RIGHT_CORNER,
        LOWER_RIGHT_CORNER;
    }

    static public class Corner extends Position {}

    static public class LineInfo
    {
        double dash_width;
        double thickness;        // the thickness is in points
    }

    static public class Segment extends Element
    {
        boolean is_absolute;
        Position pos;

        public Segment(Position pos, boolean isabs)
        {
            this.is_absolute = isabs;
            this.pos = pos;
        }
    }

    static public class GraphicState extends Element
    {
        double x, y;
        Direction dir;
    }


    static public class Distance extends Position
    {
    }

    static public class Adjustment extends Element
    {
        HAdjustment h;
        VAdjustment v;
    }

    static public class Expr
    {
    }

    static public class ByExpr
    {
        public Expr expr;
        public Operator op;
    }

    static public class Place extends Element
    {
        Shape obj;
        double x, y;
    }

    static public class Pair extends Position
    {
        public double x;
        public double y;
    }

    static public class Property
    {
        public PropertyType type;
        public Object value;
    }

    static public class Label
    {

    }

    static public class ElementList extends ArrayList<Element>
    {
    }

    static public class PropertyList extends ArrayList<Property>
    {
    }

    static public class PlaceList extends ArrayList<Place>
    {
    }

    static public class PathList extends ArrayList<String>
    {
    }

    //////////////////////////////////////////////////
    // Static variables

    //////////////////////////////////////////////////
    // Instance Variables

    String label = null;

    //////////////////////////////////////////////////
    // Constructor(s)

    public Element()
    {
    }

    //////////////////////////////////////////////////
    // Accessors

    public void setLabel(String label)
    {
        this.label = label;
    }

}


