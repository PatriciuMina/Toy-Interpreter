package Model.Type;
import Model.Value.Value;
import Model.Value.refValue;

public class refType implements Type
{
    Type inner;

    public refType(Type inner) {this.inner=inner;}
    public Type getInner() {return inner;}
    public boolean equals(Object object){
        if (object instanceof refType)
            return inner.equals(((refType) object).getInner());
        else
            return false;
    }

    public Value defaultValue() { return new refValue(0,inner);}

    public String toString() { return "Ref(" +inner.toString()+")";}

}
