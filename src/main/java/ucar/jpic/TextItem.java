/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class TextItem extends Element
{
  TextItem next;
  String text;
  Adjustment adj;
  String filename;
  int lineno;

  public TextItem(String t, String fn, int ln)
  {
    next = 0;
    text = t;
    filename = fn;
    lineno = ln;
    adj = new Adjustment(CENTER_ADJUST,NONE_ADJUST);
  }
}

