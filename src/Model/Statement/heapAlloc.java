package Model.Statement;

import Exceptions.ExpressionException;
import Exceptions.HeapException;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.refType;
import Model.Value.Value;
import Model.Value.refValue;

import javax.lang.model.type.ReferenceType;
import javax.swing.plaf.nimbus.State;

public class heapAlloc implements IStmt
{
    private final String variable;
    private final Expression exp;

    public heapAlloc(String var_name, Expression expression)
    {
        variable = var_name;
        exp = expression;
    }
    public PrgState execute(PrgState state) throws ExpressionException,StatementException {
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(!symTbl.isDefined(variable))
            throw new StatementException("Variable not defined in SymTable\n");
        refValue refVal = (refValue) symTbl.lookup(variable);

        Value value = null;
        try {
            value = exp.evaluate(symTbl,state.getHeap());
        } catch (ExpressionException expressionExcep) {
            expressionExcep.printStackTrace();}
        if(!value.getType().equals(refVal.getLocationType()))
            try {
                throw new HeapException("Type is not matching\n");
            } catch (HeapException e) {
                e.printStackTrace();
            }
        int address = heap.add(value);
        symTbl.update(variable,new refValue(address,refVal.getLocationType()));
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            TypecheckException {
        Type typevar = typeEnv.lookup(variable);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new refType(typexp)))
            return typeEnv;
        else
            throw new TypecheckException("Different types!!!");
    }

    @Override
    public String toString(){
        return " new(" + this.variable +"," + this.exp.toString() + ") ";
    }

}
