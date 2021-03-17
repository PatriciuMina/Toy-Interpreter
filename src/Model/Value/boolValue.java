package Model.Value;

import Model.Type.Type;
import Model.Type.boolType;
import com.sun.jdi.BooleanValue;

import java.util.Objects;

public class boolValue implements Value
{
    private final boolean value;

    public boolValue(boolean str) { value = str; }

    public boolean getValue() { return value; }

    @Override
    public Type getType() { return new boolType(); }
    @Override
    public String toString() {return String.valueOf(value);}

    @Override
    public boolean equals(Object object)
    {
        if(object instanceof boolValue)
            return Objects.equals(((boolValue) object).getValue(), value);
        return false;
    }
}
