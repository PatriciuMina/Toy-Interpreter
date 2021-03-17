package Model.Value;
import Model.Type.Type;
import Model.Type.refType;
import Model.Type.stringType;

import java.util.Objects;

public class refValue implements Value
{
    private final int address;
    private final Type locationType;


    public refValue(int heapAddress, Type locationtype){
        address = heapAddress;
        locationType = locationtype;
    }


    public Type getLocationType() {return locationType;}

    public int getAddress(){return address;}
    @Override
    public Type getType(){return new refType(locationType);}
    @Override
    public String toString(){return "(" + String.valueOf(address) + ", " + locationType + ")" ;}

    @Override
    public boolean equals(Object object)
    {
        if(object instanceof refValue)
            return (Objects.equals(((refValue) object).getAddress(),address)
                    && locationType.equals(((refValue) object).getLocationType()));
        return false;
    }
}
