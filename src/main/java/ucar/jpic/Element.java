/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.*;

public class Element  implements Types
{

    //////////////////////////////////////////////////
    // Constants

    static final double  SQRT2 = Math.sqrt(2.0);

    //////////////////////////////////////////////////
    // Static Methods

    static double hypot(double x, double y) {return Math.hypot(x,y);}

    static double hypot(Position p)
    {
	return hypot(p.x,p.y);
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


