package Model.ADT;

import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySemaphoreTable implements MyISemaphoreTable {

    private Map<Integer, Pair<Integer, List<Integer>>> values;
    private Integer nextFree;
    public MySemaphoreTable() {values = Collections.synchronizedMap(new HashMap<>()); nextFree=0;}

    @Override
    public Map<Integer, Pair<Integer, List<Integer>>> getContent(){
        return values;
    }

    @Override
    public void add(Integer key, Pair<Integer, List<Integer>> v) {
        values.put(key, v);
    }
    @Override
    public void update(Integer key, Pair<Integer, List<Integer>> v) {
        values.replace(key, v);
    }
    @Override
    public Pair<Integer, List<Integer>> get(Integer key) {
        return values.get(key);
    }
    @Override
    public boolean contains(Integer key) {
        return values.containsKey(key);
    }

    @Override
    public synchronized Integer getNextEmpty() {return nextFree++;}


    @Override
    public String toString(){
        String display = "{";
        int pos = 0;
        for (Integer value : values.keySet()) {
            if (pos == 0) display += "" + value + "-> ("+values.get(value).getKey()+" : "+values.get(value).getValue()+")";
            else display += "," + value + "-> ("+values.get(value).getKey()+" : "+values.get(value).getValue()+")";
            pos++;
        }
        display+="}";
        return display;
    }

    @Override
    public String toFile(){
        String display = "  SemaphoreTable:";
        for (Integer index : values.keySet()) {
            display += "\n" + index + "-->" + values.get(index).getKey()+" : "+values.get(index).getValue();
        }
        return display;
    }
}