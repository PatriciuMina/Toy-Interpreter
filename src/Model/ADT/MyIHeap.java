package Model.ADT;
import Model.Value.Value;
import Model.Value.refValue;
import Model.Type.refType;
import Model.Type.Type;
import java.util.HashMap;
import java.util.Map;

public interface MyIHeap{
    Value getValue(int address);
    void set(int address, Value value);
    int add(Value value);
    boolean isDefined(int address);

    HashMap<Integer, Value> getContent();
    void setContent(HashMap<Integer, Value> content);
    String toString();
    String toStringFile();

}
