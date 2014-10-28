/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

abstract public class Input extends Element
{
  public Input() {}

  abstract int get();
  abstract int peek();
  abstract int get_location(String[] sp, int[] ip);
}

