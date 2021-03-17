package Model.Type;
import Model.Value.intValue;
import Model.Value.Value;
public class intType implements Type
{
    @Override
    public boolean equals(Object object)
    {
        return object instanceof intType;
    }
    @Override
    public String toString() { return "int"; }
    @Override
    public Value defaultValue(){ return new intValue(0); }
}
