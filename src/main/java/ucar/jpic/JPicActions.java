/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import static ucar.jpic.Element.*;

abstract public class JPicActions
{

    //////////////////////////////////////////////////
    // Constructors

    public JPicActions()
    {
    }

    //////////////////////////////////////////////////
    // Abstract Parser actions

    abstract void picture(ElementList elements);
    abstract void setlabel(Element element, String label);
    abstract Element macro_define();
    abstract Element undef(String name);
    abstract Element assignment(String name, Element.Expr expr);
    abstract Element current_direction(int dirtoken);
    abstract Element forloop(String var, Element.Expr start, Element.Expr stop, Element.ByExpr by, Element.ElementList body);
    abstract Element.ByExpr by(int optoken, Element.Expr expr);
    abstract Element conditional(Element.Expr test, ElementList thenblock, ElementList elseblock);
    abstract Element compound(ElementList body); 
    abstract Element shape_spec(ShapeType shapetype, PropertyList props);
    abstract Property property(PropertyType type, Object value);

    abstract Element move(PropertyList props);
    abstract ElementList element_list(ElementList list, Element element);
    abstract PropertyList properties_list(PropertyList list, Property prop);
    abstract Position position_expr(Position left, Position right, int optoken);
    abstract Position pair(Position p1, Position p2);
    abstract Position between(Expr expr, Position p1, Position p2);
    abstract Position place(PathList label, Corner corner);
    abstract Position here();
    abstract Position last();
    abstract PathList label_path(String name, PathList path);
    abstract Path relative_path(Corner corner, PathList path);
    abstract Path pathpair(Path p1, Path p2); 
    abstract Expr var(String name);
    abstract Expr number(Number num);
    abstract Expr text(String text);
    abstract Expr expr_position(Place place, int token);
    abstract Expr compute(Expr lhs, Expr rhs, int optoken);
    abstract Expr expr_pair(Expr e1, Expr e2);
}
