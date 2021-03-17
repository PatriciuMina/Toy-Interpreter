package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.intType;
import Model.Value.Value;
import Model.Value.intValue;

import java.util.List;

public class acquireStmt implements IStmt{
    private final String var;

    public acquireStmt(String variable){var = variable;}


    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, FileException, HeapException {

        MyIDictionary<String, Value> statement = state.getSymTable();
        if(!statement.isDefined(var))
            throw new StatementException("variable is not defined");
        if(!statement.lookup(var).getType().equals(new intType()))
            throw new StatementException("variable has to be integer");

        Integer key = ((intValue)statement.lookup(var)).getValue();
        if(!state.getSemaphore().contains(key))
            throw new StatementException("not in semaphore");

        synchronized (state.getSemaphore())
        {
            Integer number = state.getSemaphore().get(key).getKey();
            List<Integer> lista = state.getSemaphore().get(key).getValue();

            if (number > lista.size())
            {
                if(!lista.contains(state.getId()))
                    lista.add(state.getId());
            } else
                state.getExeStack().push(new acquireStmt(var));
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        if(!typeEnv.lookup(var).equals(new intType()))
            throw new TypecheckException("TYPECHECK EXCEPTION\n");
        return typeEnv;
    }


    @Override
    public String toString(){return "acquire("+var+")";}
}

