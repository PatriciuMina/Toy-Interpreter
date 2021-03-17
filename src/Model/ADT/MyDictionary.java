package Model.ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MyDictionary<TKey, TValue> implements MyIDictionary<TKey, TValue>
{
    private final HashMap<TKey, TValue> elements;

    public MyDictionary(){ elements= new HashMap<>();}
    public MyDictionary(HashMap<TKey,TValue> elem){elements= elem;}

    @Override
    public void add(TKey key, TValue value) { elements.put(key, value); }
    @Override
    public void update(TKey key, TValue value) { elements.replace(key, value); }
    @Override
    public TValue lookup(TKey key) { return elements.get(key); }
    @Override
    public boolean isDefined(TKey key) { return elements.containsKey(key); }
    @Override
    public HashMap<TKey, TValue> getContent() {
        return elements;
    }

    @Override
    public MyIDictionary<TKey, TValue> copy() {
        Map<TKey,TValue> CopyDictionary = elements.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new MyDictionary<>((HashMap<TKey, TValue>) CopyDictionary);
    }

    public String toString()
    {
        String reuslt="{";
        int poz = 0;
        for(TKey key:elements.keySet())
        {
            if(poz == 0)
                reuslt += "" + key + "->" + elements.get(key);
            else
                reuslt += ", " + key + "->" + elements.get(key);
            poz++;
        }
        reuslt +="}";
        return reuslt;
    }
    public String toStringFile()
    {
        String result = "SymTable: ";

        for(TKey key: elements.keySet())
        {
            result+="\n" + key + "->" + elements.get(key);
        }
        return result;
    }

    @Override
    public Object clone() {
        try { return super.clone(); }
        catch(Exception ignored) {}
        return null;
    }
}
