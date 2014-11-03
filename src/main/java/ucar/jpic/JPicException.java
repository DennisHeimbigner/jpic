/*
This software is released under the Licence terms
described in the file LICENSE.txt.
*/

package ucar.jpic;

public class JPicException extends java.lang.Exception
{
    public JPicException(String msg) {super(msg);}
    public JPicException(Throwable e) {super(e);}
    public JPicException(String msg, Throwable e) {super(msg,e);}
}
