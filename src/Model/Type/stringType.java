package Model.Type;

import Model.Value.intValue;
import Model.Value.stringValue;
import Model.Value.Value;

import java.nio.file.FileAlreadyExistsException;

public class stringType implements Type
{
    @Override
    public boolean equals(Object object)
    {

        if(object instanceof stringType)
            return true;
        else
            return false;
    }
    @Override
    public String toString() { return "string "; }
    @Override
    public Value defaultValue(){ return new stringValue(""); }

}
