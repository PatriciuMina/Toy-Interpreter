package Model.Statement;

import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.Type;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.Value;

public class VarDeclStmt implements IStmt
{
    private final String name;
    private final Type typ;

    public VarDeclStmt(String str, Type Ttype)
    {
        name = str;
        typ = Ttype;
    }

    @Override
    public PrgState execute(PrgState state)throws StatementException
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(name))
            throw new StatementException("DUPLICATE");

        symTbl.add(name, typ.defaultValue());

        return null;
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            TypecheckException {
        typeEnv.add(name,typ);
        return typeEnv;
    }

    public String toString(){return typ + " " + name; }
}
