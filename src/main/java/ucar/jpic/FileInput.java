/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

import java.io.OutputStream;
import java.io.PrintWriter;

public class FileInput extends Input
{
  OutputStream fp;
  PrintWriter printer;
  String filename;
  int lineno;
  String line;
  int ptr;

  public FileInput(OutputStream fp, String s) {}

  int get() {return 0;}
  int peek() {return 0;}
  int get_location(String[] sp, int[] ip) {return 0;}
}

