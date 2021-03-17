package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.Expression.*;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.intValue;
import Model.Expression.ValueExp;

import java.io.FileNotFoundException;

public class waitStmt implements IStmt{

    private final int nrWait;

    public waitStmt(int numberForWait) {
        this.nrWait = numberForWait;
    }

    @Override
    public PrgState execute(PrgState programState) throws StatementException, ExpressionException {
        if (nrWait!=0)
            programState.getExeStack().push(new CompStmt(new PrintStmt(new ValueExp(new intValue(nrWait))),new waitStmt(nrWait-1)));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        return typeEnv;
    }

    public int getNrWait() {
        return nrWait;
    }

    @Override
    public String toString()
    {
        return "WAIT (" + nrWait + ") ";
    }
}
