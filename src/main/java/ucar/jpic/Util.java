/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract public class Util implements Types
{

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

    static Shape make_object(ShapeSpec spec, Position pos, Direction dir)
    {
        return null;
    }

    static Shape make_mark_object()
    {
        return null;
    }

    static Shape make_command_object(String s0, String s1, int i)
    {
        return null;
    }

    static int lookup_variable(String name, double val)
    {
        return 0;
    }

    static void define_variable(String name, double val)
    {
    }

    static void print_picture(Shape shape)
    {
    }

    static Position compute_arc_center(Position start, Position cent, Position end)
    {
        return null;
    }

    static void do_copy(String file)
    {
    }




}
