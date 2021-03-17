package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt
{
    public NopStmt() {}

    @Override
    public PrgState execute(PrgState state) throws StatementException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        return typeEnv;
    }
}
