package Exceptions;
public class FileException extends Exception
{
    public FileException(String message) { super(message); }
    public String getMessage() { return "!!!There is a File Error : " + super.getMessage(); }
}
