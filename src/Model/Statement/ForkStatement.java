package Model.Statement;

import Exceptions.FileException;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyStack;
import Model.ProgramState.PrgState;
import Model.Type.Type;

import java.io.File;

public class ForkStatement implements IStmt{
    private final IStmt statement;
    public ForkStatement(IStmt newStatement) { statement = newStatement;}

    @Override
    public PrgState execute (PrgState state) {
        return new PrgState(new MyStack<>(),
                state.getSymTable().copy(),
                state.getOut(),
                state.getFileTable(),
                state.getHeap(),
                state.getSemaphore(),
                statement);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            TypecheckException {
        statement.typecheck((MyIDictionary<String,Type>) typeEnv.clone());
        return typeEnv;
    }
    public String toString() {return "Fork(" + statement + ")";}
}
