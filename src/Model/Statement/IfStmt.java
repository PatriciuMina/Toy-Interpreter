package Model.Statement;

import Exceptions.ExpressionException;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Value.boolValue;
import Model.Value.Value;

public class IfStmt implements IStmt
{
    private final Expression exp;
    private final IStmt thenS;
    private final IStmt elseS;

    public IfStmt(Expression e, IStmt t, IStmt el)
    {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException
    {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIHeap heap = state.getHeap();
        Value val = null;
        try
        {
            val = exp.evaluate(state.getSymTable(), heap);
        }
        catch (ExpressionException expressionException)
        {
            expressionException.printStackTrace();
        }
        if(!val.getType().equals(new boolType()))
            throw new StatementException("not boolean!!!");

        if(((boolValue) val).getValue()) stk.push(thenS);
        else   stk.push(elseS);
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type condType = exp.typecheck(typeEnv);
        if (!condType.equals(new boolType()))
            throw new TypecheckException("IF condition must be of boolean type\n");

        thenS.typecheck((MyIDictionary<String, Type>) typeEnv.clone());
        elseS.typecheck((MyIDictionary<String, Type>) typeEnv.clone());

        return typeEnv;
    }


    public String toString(){ return "(IF("+ exp.toString()+") THEN(" +thenS.toString()
            +")ELSE("+elseS.toString()+"))";}
}
