package Model.Expression;
import Exceptions.ExpressionException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements Expression
{
    private final String id;
    public VarExp(String identifier) {id = identifier;}
    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, MyIHeap heap) throws ExpressionException{ return SymTable.lookup(id);}

    @Override
    public String toString() { return id;}

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException {
        return typeEnv.lookup(id);
    }

}
