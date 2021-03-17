package Exceptions;
public class StatementException extends Exception
{
    public StatementException(String message) {super(message);}
    @Override
    public String getMessage(){ return "!!!There is a Statement problem: " + super.getMessage();}
}
