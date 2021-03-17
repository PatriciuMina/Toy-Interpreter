package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;

import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.boolType;

import java.io.FileNotFoundException;


public class conditionalStmt implements IStmt{
    private final String string;
    private final Expression exp1;
    private final Expression exp2;
    private final Expression exp3;

    public conditionalStmt(String key, Expression expression1, Expression expression2, Expression expression3) {
        this.string = key;
        this.exp1 = expression1;
        this.exp2 = expression2;
        this.exp3 = expression3;
    }

    @Override
    public PrgState execute(PrgState programState) throws ExpressionException, StatementException {
        IStmt ifClasue = new IfStmt(exp1,new AssignStmt(string,exp2),new AssignStmt(string,exp3));
        programState.getExeStack().push(ifClasue);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        if(!exp1.typecheck(typeEnv).equals(new boolType()))
            throw new TypecheckException("cond should be a bool expression\n");
        Type typeChe = typeEnv.lookup(string);
        Type t2 = exp2.typecheck(typeEnv);
        Type t3= exp3.typecheck(typeEnv);

        if (!typeChe.equals(t2)||!typeChe.equals(t3))
            throw new TypecheckException("The variable and the 2 expr should have the same type\n");

        return typeEnv;
    }

    @Override
    public String toString()
    {
        return string + " = " + exp1 + "? " + exp2 + " : " + exp3;
    }
}
