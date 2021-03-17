package Model.Expression;

import Exceptions.ExpressionException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Type.refType;
import Model.Value.Value;
import Model.Value.refValue;

public class readHeapExp implements Expression
{
    private final Expression exp;

    public readHeapExp(Expression expression)
    {
        exp = expression;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable, MyIHeap heap) throws ExpressionException {
        Value value=exp.evaluate(symbolTable,heap);
        if (!(value instanceof refValue))
            throw new ExpressionException("Not a RefValue\n");
        int address=((refValue)value).getAddress();

        if (!heap.isDefined(address))
            throw new ExpressionException("Address is not a key in Heap table");
        return heap.getValue(address);
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException {
        Type typ = exp.typecheck(typeEnv);
        if (typ instanceof refType) {
            refType reft = (refType) typ;
            return reft.getInner();
        } else
            throw new TypecheckException("the rH argument is not a Ref Type");
    }



    public String toString()
    {
        return "rH(" + exp + ")";
    }
}
