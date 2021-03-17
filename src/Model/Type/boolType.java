package Model.Type;

import Model.Value.boolValue;
import Model.Value.Value;


public class boolType implements Type
{
    @Override
    public boolean equals(Object object)
    {
        return object instanceof boolType;
    }
    @Override
    public String toString() { return "bool";}
    @Override
    public Value defaultValue(){return new boolValue(false);}

}
