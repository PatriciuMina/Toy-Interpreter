package Model.ADT;
import Exceptions.ADTException;

import java.util.List;

public interface MyIStack<T>
{

    T pop() throws ADTException;
    void push(T value);
    boolean isEmpty();
    String toStringFile();
    List<T> getElements();
}
