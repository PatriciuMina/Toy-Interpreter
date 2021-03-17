package Model.ADT;


import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface MyISemaphoreTable {
    void add(Integer key, Pair<Integer, List<Integer>> v);
    void update(Integer key, Pair<Integer, List<Integer>> v);
    Pair<Integer, List<Integer>> get(Integer key);
    boolean contains(Integer key);

    Map<Integer, Pair<Integer, List<Integer>>> getContent();

    Integer getNextEmpty();
    String toFile();
}
