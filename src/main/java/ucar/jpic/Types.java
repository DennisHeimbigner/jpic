/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.*;

/**
 * Put all the relatively simple type declarations here.
 */
public interface Types
{
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

    static enum DirectionType
    {
        RIGHT,
        UP,
        LEFT,
        DOWN;
    }

    static public enum Supported
    {
        SVG;
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
        FILLED,
        DEFAULT_FILLED,
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

    static public class Line
    {
        PropertyType type = PropertyType.SOLID;
        double dash_width = 1.0;
        double thickness = 1.0;        // the thickness is in points

        public Line(PropertyType type, double dash_width, double thickness)
        {
            this.type = type;
            this.dash_width = dash_width;
            this.thickness = thickness;
        }

        public Line(Line lt)
        {
            this(lt.type, lt.dash_width, lt.thickness);
        }

    }

    static public interface Corner
    {
    }

    static public interface Distance
    {
        public double x();

        public double y();
    }

    static public class ArrowHead
    {
        double height = 0.0;
        double width = 0.0;
        boolean solid = true;
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
        DirectionType dir;
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
        public ShapeSpec obj;
        public double x, y;
        public Place(double x, double y, ShapeSpec obj)
        {this.obj = obj; this.x = x; this.y = y;}
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

    static public class Variable
    {
        public String name;
        public Object value;

        public Variable(String name, Object value)
        {
            this.name = name;
            this.value = value;
        }
    }

    static public class VariableList extends ArrayList<Variable>
    {
    }


    static public class Macro extends Element
    {
        String name;
        List<JPic.MacroToken> body;

        public Macro(String name, List<JPic.MacroToken> body)
        {
            this.name = name;
            this.body = body;
        }
    }


}



