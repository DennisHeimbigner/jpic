/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.io.*;
import java.nio.charset.Charset;

public class JPic
{
    //////////////////////////////////////////////////
    // Constants

    static final String VERSION = "1.0";

    static final Charset UTF8 = Charset.forName("UTF-8");

    //////////////////////////////////////////////////
    // Static Variables

    static boolean testing = false;

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

    //////////////////////////////////////////////////
    // Constructor(s)

    JPic(String filename)
    {
        this.filename = filename;
    }

    void run()
        throws IOException
    {
        this.picfile = readfile(this.filename);
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

    /**
     * Read the contents of a file.
     *
     * @param filename file to read, '-' => stdin.
     * @return The contents of the file as a string
     * @throws IOException
     */

    static String
    readfile(String filename)
        throws IOException
    {
        InputStream input = null;
        if(filename == null)
            input = System.in;
        else
            input = new FileInputStream(filename);
        InputStreamReader rdr = new InputStreamReader(input, UTF8);

        int c = 0;
        StringBuilder buf = new StringBuilder();
        while((c = rdr.read()) >= 0) {
            buf.append((char) c);
        }
        input.close();
        return buf.toString();
    }


    //////////////////////////////////////////////////
    // Main
    //////////////////////////////////////////////////

    /**
     * z     * Initialize and start the JPic program
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
            jpic.run();
        } catch (IOException ioe) {
            System.err.println("IO Error: " + ioe);
        }
    }

}


   
