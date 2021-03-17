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
import Model.Type.intType;
import Model.Type.stringType;
import Model.Value.intValue;
import Model.Value.stringValue;
import Model.Value.Value;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {

    private final Expression exp;
    private final String var_name;

    public readFile(Expression expression, String varr )
    {
        exp = expression;
        var_name = varr;
    }

    @Override
    public PrgState execute(PrgState state) throws  FileException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();

        Value value = null;
        try {
            value = exp.evaluate(symTable, heap);
        } catch (ExpressionException MyException) {
            MyException.printStackTrace();
        }
        if(!value.getType().equals(new stringType()))
            throw new FileException("must be a string");

        MyIFileTable file = state.getFileTable();
        stringValue fileName = (stringValue) value;
        if(!file.isOpen(fileName))
            throw new FileException("file not opened");
        BufferedReader fileDesc = file.getFileDescriptor(fileName);

        int readValue;
        try
        {
            String line = fileDesc.readLine();
            if(line == null)
                readValue =0;
            else
                readValue = Integer.parseInt(line);
        }
        catch (IOException exception)
        {
            throw new FileException("cant read from file");
        }

        symTable.update(var_name,new intValue(readValue));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type nameType = exp.typecheck(typeEnv);
        if (!nameType.equals(new stringType()))
            throw new TypecheckException("File name must be a string\n");

        if (!typeEnv.isDefined(var_name))
            throw new TypecheckException(var_name + " is not defined\n");

        Type varType = typeEnv.lookup(var_name);
        if (!varType.equals(new intType()))
            throw new TypecheckException("From a file you can only read into integer variables\n");

        return typeEnv;
    }

    public String toString()
    {
        return "Readfile(" + exp + "," + var_name + ")";
    }
}
