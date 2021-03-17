package Exceptions;
public class ADTException extends Exception
{
    public ADTException(String message){ super(message); }
    @Override
    public String getMessage() { return "!!!There is an ADT problem: " + super.getMessage(); }
}
