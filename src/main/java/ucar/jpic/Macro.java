/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

public class Macro extends Element
{
    String name;
    String body;

    public Macro(String name, String body)
    {
	this.name = name;
	this.body = body;
    }

    static public String expand(Macro macro, List<String> args)
    {
	return null;
    }
}

