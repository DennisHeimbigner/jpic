/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class JPic implements Types
{
    //////////////////////////////////////////////////
    // Constants

    static final String VERSION = "1.0";

    static final Charset UTF8 = Charset.forName("UTF-8");

    // Mnemonic
    static final boolean LOCALONLY = true;

    //////////////////////////////////////////////////
    // State of the Execution: Macros and Variable scopes

    static {
        scope = new ArrayList<VariableList>();
        pushScope(); // initial scope
    }

    //////////
    // Manage all created objects

    static List<Element> oblist = new ArrayList<>();

    static Element
    create(ShapeType kind)
    {
	Element e = null;
	switch (kind) {
	case BOX: e = new BoxShape(); break;
	case CIRCLE: e = new CircleShape(); break;
	case ELLIPSE: e = new EllipseShape(); break;
	case ARC: e = new ArcShape(); break;
	case SPLINE: e = new SplineShape(); break;
	case LINE: e = new LineShape(); break;
	case ARROW: e = new ArrowShape(); break;
	case TEXTBOX: e = new TextShape(); break;
	case BLOCK: e = new BlockShape(); break;
	case MARK:
        case OTHER:
	default:
	    assert false;
	}
	if(e != null)
	    oblist.add(e);
	return e;
    }

    //////////
    // Define Macros
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

    static public Map<String, Macro> macros = new HashMap<>();

    static public String expand(Macro macro, List<String> args)
    {
        return null;
    }

    //////////
    // Scoped Variables

    static List<VariableList> scope;

    static void pushScope()
    {
        scope.add(0, new VariableList());
    }

    static void popScope()
    {
        scope.remove(0);
    }

    static Object
    getValue(String vname)
        throws JPicException
    {
        Variable v = lookup(vname);
        if(v == null)
            throw new Exception("Variable not defined: " + vname);
        return v.value;
    }


    static Variable
    lookup(String vname, boolean localonly)
    {
        int count = (localonly ? 1 : scope.size());
        for(int i = 0;i < count;i++) {
            VariableList current = scope.get(i);
            for(Variable v : current) {
                if(v.name.equals(vname))
                    return v;
            }
        }
        return null;
    }

    static Variable
    lookup(String vname)
    {
        return lookup(vname, !LOCALONLY);
    }

    static void defVar(Variable v)
    {
        Variable v2 = lookup(v.name, LOCALONLY);
        if(v2 == null) {
            VariableList top = scope.get(0);
            top.add(v);
        } else
            v2.value = v.value;
    }

    static Variable
    undef(String vname, boolean localonly)
    {
        int count = (localonly ? 1 : scope.size());
        for(int i = 0;i < count;i++) {
            VariableList current = scope.get(i);
            for(Variable v : current) {
                if(v.name.equals(vname)) {
                    current.remove(v);
                    return v;
                }
            }
        }
        return null;
    }

    static void assign(String vname, Object value)
    {
        Variable v = lookup(vname);
        if(v == null) {
            v = new Variable(vname, value);
            defVar(v);
        } else
            v.value = value;
    }

    //////////////////////////////////////////////////
    // Misc. Static Variables

    static boolean testing = false; // ps

    //////////////////////////////////////////////////
    // Instance variables
    String filename;
    String picfile;
    Output out;
    int flyback_flag;
    int command_char;
    // zero_length_line_flag is true if zero-length lines are drawn 
    // as dots by the output device
    boolean zero_length_line_flag;
    boolean driver_extension_flag;
    boolean compatible_flag;
    boolean safer_flag;
    String graphname;
    ElementList picture;

    //////////////////////////////////////////////////
    // Constructor(s)

    JPic(String filename)
    {
        this.filename = filename;
    }

    public void generate()
        throws JPicException
    {
        this.picfile = readfile(this.filename);
        JPicParser parser = new JPicParser();
        if(!parser.parse(picfile))
            throw new Exception("Parse Failed");
        this.picture = parser.getPicture();
    }

    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    // Main() Support functions

    /**
     * Print a usage message and exit
     *
     * @param msg msg to print along with usage info
     */
    static void
    usage(final String msg)
    {
        if(msg != null)
            System.err.printf("%s\n", msg);
        System.err.printf(
            "usage: java"
                + "[-Ddebug=string]"
                + "[-Dversion]"
                + "[-Dquiet]"
                + "[-DX=tag:value,...]"
                + " -jar jar [arg...]\n");
        if(msg != null) System.exit(1);
        else System.exit(0);
    }


    //////////////////////////////////////////////////
    // Main
    //////////////////////////////////////////////////

    /**
     * Initialize and start the JPic program
     *
     * @param argv the argv from the static main
     */

    static public void
    main(String[] argv)
    {
        // Get the -D flags from command line
        boolean quiet = (System.getProperty("quiet") != null);

        String version = System.getProperty("version");
        if(version != null) {
            System.err.println("JPic version = " + VERSION);
            return;
        }

        // Process the -Ddebug flags
        String debugflags = System.getProperty("debug");
        if(debugflags != null) { // -Ddebug=<debugflags>
            for(int i = 0;i < debugflags.length();i++) {
                char c = debugflags.charAt(i);
                switch (c) {
                case 't':
                    JPic.testing = true;
                    break;
                default:
                    usage("Illegal -Ddebug option: " + c);
                }
            }
        }

        String xoption = System.getProperty("X");
        if(xoption != null) { // -DX=<xoption>
            String[] pieces = xoption.split("[ \t]*,[ \t]*");
            for(String piece : pieces) {
                String[] tagparts = piece.split("[ \t]*:[ \t]*");
                long size = -1;
                switch (tagparts[0].charAt(0)) {
                default:
                    usage("Illegal -DX option: " + piece);
                }
            }
        }

        // Get the input file name
        String input = null;
        if(argv.length > 0)
            input = argv[0];
        // Create the interpreter
        JPic jpic = new JPic(input);
        try {
            jpic.generate();
        } catch (Exception ioe) {
            System.err.println("IO Error: " + ioe);
        }
    }

}


   
