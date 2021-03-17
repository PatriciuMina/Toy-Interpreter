package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public interface IStmt
{
    PrgState execute(PrgState state) throws StatementException, ExpressionException, FileException, HeapException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException;

}
