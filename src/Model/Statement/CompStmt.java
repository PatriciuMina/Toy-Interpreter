package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt
{
    private final IStmt first;
    private final IStmt second;

    public CompStmt(IStmt statement1, IStmt statement2)
    {
        first = statement1;
        second = statement2;
    }

    public String toString() { return  first.toString() + "; " + second.toString();}

    @Override
    public PrgState execute(PrgState state) throws StatementException
    {
        MyIStack<IStmt> exeStack = state.getExeStack();
        exeStack.push(second);
        exeStack.push(first);
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return second.typecheck(first.typecheck(typeEnv));
    }

}
