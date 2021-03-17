package Exceptions;
public class ExpressionException extends Exception
{
    public ExpressionException(String message) { super(message); }
    @Override
    public String getMessage() { return "!!!There is an Expresion Error : " + super.getMessage(); }
}
