package Model.ADT;

import java.util.HashMap;

public interface MyIDictionary <TKey, TValue> extends Cloneable
{
    void add(TKey key, TValue value);
    void update(TKey key, TValue value);
    TValue lookup(TKey key);
    boolean isDefined(TKey key);
    public HashMap<TKey, TValue> getContent();

    String toStringFile();

    MyIDictionary<TKey,TValue> copy();
    Object clone();
}
