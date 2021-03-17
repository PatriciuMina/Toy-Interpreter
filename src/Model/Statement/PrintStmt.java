package Model.Statement;

import Exceptions.ExpressionException;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIHeap;
import Model.ADT.MyIList;
import Model.ADT.MyIDictionary;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt
{
    private final Expression exp;
    public PrintStmt(Expression expression) { exp = expression;}

    @Override
    public PrgState execute(PrgState state) throws StatementException
    {
        MyIList<Value> output = state.getOut();
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        try
        { output.append(exp.evaluate(symbolTable,heap));}
        catch (ExpressionException expressionException)
        {
            expressionException.printStackTrace();
        }
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }


    public String toString() { return "print("+exp+")"; }
}
