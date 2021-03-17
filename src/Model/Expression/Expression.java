package Model.Expression;
import Exceptions.TypecheckException;
import Exceptions.ExpressionException;
import Model.ADT.*;
import Model.Type.Type;
import Model.Value.*;

public interface Expression
{
    Value evaluate(MyIDictionary<String, Value> SymTable, MyIHeap heap) throws ExpressionException;
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException;
}
