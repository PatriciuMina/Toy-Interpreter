package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.refType;
import Model.Value.refValue;
import Model.Value.Value;

import java.io.FileNotFoundException;

public class heapWriteStmt implements IStmt
{
    private final Expression exp;
    private final String variable;

    public heapWriteStmt(String variable_name, Expression expression)
    {
        variable = variable_name;
        exp = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, FileException, HeapException {
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        MyIHeap heap = state.getHeap();

        if (!symTbl.isDefined(variable))
        {
            throw new HeapException("Variable is not defined in SymTable");
        }

        Value value=symTbl.lookup(variable);

        if (!(value instanceof refValue))
        {
            throw new HeapException("Variable is not a Ref type");
        }

        refValue refVal= (refValue)value;

        if (!heap.isDefined(refVal.getAddress())){
            throw new HeapException("The address from the RefValue associated in SymTable is not a key in Heap");
        }

        Value value2 = null;
        try {
            value2 = exp.evaluate(symTbl,heap);
        } catch (ExpressionException expressionExcep) {
            expressionExcep.printStackTrace();
        }
        if(!value2.getType().equals(refVal.getLocationType()))
            throw new StatementException("Type and locationType of the variable are not equal \n");
        heap.set(refVal.getAddress(),value2);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        if (!typeEnv.isDefined(variable))
            throw new TypecheckException(variable + " is not defined\n");

        Type varType = typeEnv.lookup(variable);
        Type expType = exp.typecheck(typeEnv);

        if (!varType.equals(new refType(expType)))
            throw  new TypecheckException(" Different types!!! \n");
        return typeEnv;
    }

    @Override
    public String toString(){
        return " wH (" + variable + ")= "+exp+") ";
    }

}
