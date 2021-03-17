package Exceptions;
public class TypecheckException extends Exception
{
    public TypecheckException(String message) {super(message);}
    @Override
    public String getMessage(){ return "!!!Typecheck EXCEPTION" + super.getMessage();}
}
