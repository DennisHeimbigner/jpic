/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.util.List;

public class Macro extends Element
{
    static public class MacroToken
    {
        public int token;
	public Object lval;
        public MacroToken(int token, Object lval)
        {
	    this.token = token;
	    this.lval = lval;
        }
    }

    static public Map<String,Macro> macros = new HashMap<>();

    String name;	
    List<Object> body;

    public Macro(String name, List<Object> body)
    {
	this.name = name;
	this.body = body;
    }

    static public String expand(Macro macro, List<String> args)
    {
	return null;
    }
}

