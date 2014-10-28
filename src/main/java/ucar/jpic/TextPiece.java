/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class TextPiece extends Element
{
  String text;
  Adjustment adj;
  String filename;
  int lineno;

  public TextPiece()
  {
    this.text = null;
    this.filename = null;
    this.lineno = -1;
    this.adj = new Adjustment(CENTER_ADJUST,NONE_ADJUST);
  }
}

