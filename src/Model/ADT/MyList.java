package Model.ADT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList<T> implements MyIList<T>
{
    private final ArrayList<T> values;

    public MyList() { values = new ArrayList<>();}

    @Override
    public void append(T newVal) { values.add(newVal); }
    @Override
    public List<T> getElements() {
        return values;
    }
    @Override
    public Iterator<T> iterator() {return values.iterator();}

    public String toString()
    {
        String result = "{ ";
        int poz = 0;
        for(T value: values)
        {
            if( poz==0 )
                result += "" + value;
            else
                result += ", " + value;
            poz++;
        }
        result+="}";
        return result;
    }
    public String toStringFile()
    {
        String result = "OutPut: ";
        for(T value: values)
        {
            result+="\n" + value;
        }
        return result;
    }
}
