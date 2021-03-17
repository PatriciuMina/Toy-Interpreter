package Model.Statement;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIFileTable;
import Model.ADT.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;
import Model.Type.Type;
import Model.Type.stringType;
import Model.Value.stringValue;
import Model.Value.Value;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class closeRFile implements IStmt{

    private final Expression expression;

    public closeRFile(Expression exp)
    {
        expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws  FileException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        Value value = null;
        try {
            value = expression.evaluate(symTable,heap);
        } catch (ExpressionException expressionExcep) {
            expressionExcep.printStackTrace();
        }
        if(!value.getType().equals(new stringType()))
            throw new FileException("path should be string ");
        MyIFileTable file = state.getFileTable();
        stringValue fileName = (stringValue) value;
        if(!file.isOpen(fileName))
            throw new FileException("file not opened");
        BufferedReader filed = file.getFileDescriptor(fileName);

        try
        {
            filed.close();
        }
        catch (IOException exception)
        {
            throw new FileException("cant close the file");
        }
        file.remove(fileName);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type varType = expression.typecheck(typeEnv);
        if(!varType.equals(new stringType()))
            throw new TypecheckException("File name must be a string\n");
        return typeEnv;
    }

    public String toString()
    {
        return "CloseRFile(" + expression + ")";
    }
}
