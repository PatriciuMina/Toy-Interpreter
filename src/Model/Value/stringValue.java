package Model.Value;
import Model.Type.stringType;
import Model.Type.Type;

import java.util.Objects;

public class stringValue implements Value
{
    private final String value;

    public stringValue(String str){value = str;}
    public String getValue(){return value;}

    @Override
    public Type getType(){return new stringType();}
    @Override
    public String toString(){return "'" + value + "'";}
    @Override
    public boolean equals(Object object)
    {
        if(object instanceof stringValue)
            return ((stringValue)object).getValue().equals(value);
        return false;
    }




}
