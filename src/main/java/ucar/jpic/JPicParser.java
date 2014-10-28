/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import static ucar.jpic.Element.*;

public class JPicParser extends JPicParserBody
{

    //////////////////////////////////////////////////
    // Constructors

    public JPicParser()
        throws Exception
    {
        super(null);
        JPicLexer lexer = new JPicLexer(this);
        setLexer(lexer);
    }

    //////////////////////////////////////////////////
    // Get/Set

    //////////////////////////////////////////////////
    // Parser API

    public boolean
    parse(String document)
        throws Exception
    {
        ((JPicLexer) getLexer()).setText(document);
        return super.parse();
    }

    //////////////////////////////////////////////////
    // Abstract Parser actions

    @Override
    void
    picture(ElementList elements)
    {
    }

    @Override
    void
    setlabel(Element element, String label)
    {
    }

    @Override
    Element
    macro_define()
    {
        return null;
    }

    @Override
    Element
    undef(String name)
    {
        return null;
    }

    @Override
    Element
    assignment(String name, Expr expr)
    {
        return null;
    }

    {
    }

    @Override
    Element
    current_direction(int dirtoken)
    {
        return null;
    }

    @Override
    Element
    forloop(String var, Expr start, Expr stop, ByExpr by, ElementList body)
    {
        return null;
    }

    @Override
    ByExpr
    by(int optoken, Expr expr)
    {
        return null;
    }

    @Override
    Element
    conditional(Expr test, ElementList thenblock, ElementList elseblock)
    {
        return null;
    }

    @Override
    Element
    compound(ElementList body)
    {
        return null;
    }

    @Override
    Element
    shape_spec(ShapeType shapetype, PropertyList props)
    {
        return null;
    }

    @Override
    Property
    property(PropertyType type, Object value)
    {
        return null;
    }

    @Override
    ElementList
    element_list(ElementList list, Element element)
    {
        return null;
    }

    @Override
    PropertyList
    properties_list(PropertyList list, Property prop)
    {
        return null;
    }

    @Override
    Position
    position_expr(Position left, Position right, int optoken)
    {
        return null;
    }

    @Override
    Position
    pair(Position p1, Position p2)
    {
        return null;
    }

    @Override
    Position
    between(Expr expr, Position p1, Position p2)
    {
        return null;
    }

    @Override
    Position
    place(PathList label, Corner corner)
    {
        return null;
    }

    @Override
    Position
    here()
    {
        return null;
    }

    @Override
    PathList
    label_path(String name, PathList path)
    {
        return null;
    }

    @Override
    Path
    relative_path(Corner corner, PathList path)
    {
        return null;
    }

    @Override
    Path
    pathpair(Path p1, Path p2)
    {
        return null;
    }

    @Override
    Expr
    var(String name)
    {
        return null;
    }

    @Override
    Expr
    number(Number num)
    {
        return null;
    }

    @Override
    Expr
    text(String text)
    {
        return null;
    }

    @Override
    Expr
    expr_position(Place place, int token)
    {
        return null;
    }

    @Override
    Expr
    compute(Expr lhs, Expr rhs, int optoken)
    {
        return null;
    }

    @Override
    Position
    last()
    {
        return null;
    }

    @Override
    Element
    move(PropertyList props)
    {
        return null;
    }

    @Override
    Expr
    expr_pair(Expr e1, Expr e2)
    {
        return null;
    }

}

    
