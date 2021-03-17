package Model.ADT;
import java.util.Iterator;
import java.util.List;

public interface MyIList<T>
{
    void append(T value);
    Iterator<T> iterator();
    String toStringFile();
    List<T> getElements();
}
