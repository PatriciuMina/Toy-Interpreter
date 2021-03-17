package Model.Expression;

import Exceptions.ExpressionException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExp implements Expression {
    private final Value e;

    public ValueExp(Value value) {
        e = value;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, MyIHeap heap) throws ExpressionException {
        return e;
    }

    public String toString() {
        return e.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        return e.getType();
    }
}