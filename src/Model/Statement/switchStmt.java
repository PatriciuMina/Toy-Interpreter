package Model.Statement;

import Exceptions.*;

import Model.ADT.MyIDictionary;
import Model.Expression.Expression;
import Model.Expression.RelationalExp;
import Model.ProgramState.PrgState;
import Model.Type.Type;

import java.io.FileNotFoundException;

public class switchStmt implements IStmt{
    private final Expression exp;
    private final Expression exp1;
    private final Expression exp2;
    private final IStmt stmt1;
    private final IStmt stmt2;
    private final IStmt stmt3;

    public switchStmt(Expression expression, Expression expression1, Expression expression2, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp = expression;
        this.exp1 = expression1;
        this.exp2 = expression2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }


    @Override
    public PrgState execute(PrgState programState) throws ExpressionException,StatementException {


        IStmt elseSwitch=new IfStmt(new RelationalExp(exp,exp2,"=="),stmt2,stmt3);
        IStmt mainSwitch=new IfStmt(new RelationalExp(exp,exp1,"=="),stmt1,elseSwitch);

        programState.getExeStack().push(mainSwitch);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type type1 = exp.typecheck(typeEnv);
        Type type2 = exp1.typecheck(typeEnv);
        Type type3 = exp2.typecheck(typeEnv);
        if(type1.equals(type2) && type1.equals(type3))
        {
            stmt3.typecheck(typeEnv.copy());
            stmt2.typecheck(typeEnv.copy());
            stmt1.typecheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new TypecheckException("Expressions must have the same type!");
    }

    @Override
    public String toString() {return "switch("+exp+") (case "+exp1+") "+stmt1+")(case "+exp2+") "+stmt2+")(default  "+stmt3+")";}

}
