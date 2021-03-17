package Model.ADT;

import Model.Value.Value;

import java.util.HashMap;
import java.util.Map;
public class MyHeap implements MyIHeap{
    private HashMap<Integer, Value> map;
    private Integer memory;

    public MyHeap()
    {
        this.map = new HashMap<>();
        this.memory = 1;
    }


    @Override
    public Value getValue(int address){return map.get(address);}

    @Override
    public int add(Value value)
    {
        int address = memory;
        set(memory,value);
        memory++;
        return address;
    }
    @Override
    public void set(int address, Value value){map.put(address, value);}

    @Override
    public boolean isDefined(int address){  return map.containsKey(address) && map.get(address)!=null;}

    @Override
    public HashMap<Integer, Value> getContent(){   return map; }

    @Override
    public void setContent(HashMap<Integer, Value> content){ map = content;}

    @Override
    public String toString()
    {
        String reuslt="{";
        int poz = 0;
        for(Integer key: map.keySet())
        {
            if(poz == 0)
                reuslt += "" + key + "->" + map.get(key);
            else
                reuslt += ", " + key + "->" + map.get(key);
            poz++;
        }
        reuslt +="}";
        return reuslt;
    }
    @Override
    public String toStringFile() {
        String print = "Heap: ";
        for(Integer address : map.keySet())
        {
            print += "\n" + address + "->" + map.get(address) + "\n";
        }
        return print;
    }
}
