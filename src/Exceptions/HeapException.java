package Exceptions;

public class HeapException extends Exception{

    public HeapException(String message) {super(message);}
    @Override
    public String getMessage(){ return "!!!There is a Heap problem: " + super.getMessage();}


}
