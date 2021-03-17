package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIFileTable;
import Model.ADT.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.stringType;
import Model.Value.stringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt
{
    private final Expression exp;

    public openRFile(Expression expression){exp = expression;}

    @Override
    public PrgState execute(PrgState state) throws FileException
    {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        Value evaluation = null;
        try {
            evaluation = exp.evaluate(symTable, heap);
        } catch (ExpressionException myException) {
            myException.printStackTrace();
        }
        if(!evaluation.getType().equals(new stringType()) )
            throw new FileException("The input path has to be a string");
        stringValue fileName = (stringValue) evaluation;
        MyIFileTable file = state.getFileTable();
        if(file.isOpen(fileName))
            throw new FileException("file is already open");

        try
        {
            BufferedReader filepath = new BufferedReader(new FileReader(fileName.getValue()));
            file.add(fileName,filepath);
        }
        catch (IOException exception)
        {
            throw new FileException("couldn't open the file");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type varType = exp.typecheck(typeEnv);
        if (!varType.equals(new stringType()))
            throw new TypecheckException("File name must be a string\n");
        return typeEnv;
    }

    public String toString(){ return "openRFile(" + exp + ")";}


}
