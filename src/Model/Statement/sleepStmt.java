package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import java.io.FileNotFoundException;

public class sleepStmt implements IStmt{

    private final int nrSleep;

    public sleepStmt(int nrSleep) {
        this.nrSleep = nrSleep;
    }


    @Override
    public PrgState execute(PrgState programState) throws StatementException, ExpressionException{
        if (nrSleep!=0)
            programState.getExeStack().push(new sleepStmt(nrSleep-1));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "sleep("+ nrSleep +") ";
    }
}
