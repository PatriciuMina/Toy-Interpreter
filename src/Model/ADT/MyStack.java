package Model.ADT;

import Exceptions.ADTException;
import java.util.ArrayList;
import java.util.List;

public class MyStack<T> implements MyIStack<T>
{
    private final ArrayList<T> values;

    public MyStack(){ values= new ArrayList<>();}

    public List<T> getElements(){return values;}
    @Override
    public T pop() throws ADTException
    {
        if (values.isEmpty())
            throw new ADTException("Execution stack is empty \n");
        T StackTop = values.get(values.size()-1);
        values.remove(values.size()-1);
        return StackTop;
    }
    @Override
    public void push(T value){  values.add(value); }
    @Override
    public boolean isEmpty(){   return values.isEmpty(); }

    public String toString()
    {
        String result = "{";
        for (int poz = values.size()-1; poz>=0; poz--)
        {
            if(poz == values.size()-1)
                result +="" + values.get(poz);
            else
                result +="|" + values.get(poz);
        }
        result +="}";
        return result;
    }

    public String toStringFile()
    {
        String result = "ExeStack: ";
        for(int poz = values.size()-1;poz>=0;poz--)
        {
            result += "\n" + values.get(poz);
        }
        return result;
    }
}
