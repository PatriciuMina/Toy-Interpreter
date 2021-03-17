package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Value.Value;
import Model.Value.boolValue;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;

import java.io.FileNotFoundException;
import java.sql.Statement;

public class WhileStmt implements IStmt
{

    private final Expression expr;
    private final IStmt whileStmt;

    public WhileStmt(Expression expr, IStmt whileStmt) {
        this.expr = expr;
        this.whileStmt = whileStmt;
    }

    @Override
    public PrgState execute(PrgState STATE) throws StatementException, ExpressionException, FileException, HeapException {
        MyIStack<IStmt> exeStack = STATE.getExeStack();

        Value cond=null;
        try {
            cond = expr.evaluate(STATE.getSymTable(), STATE.getHeap());
        }
        catch (ExpressionException e)
        {
            e.printStackTrace();
        }

        if (!cond.getType().equals(new boolType()))
            throw new StatementException("Bool type required\n");


        if (((boolValue)cond).getValue())
        {
            exeStack.push(this);
            exeStack.push(whileStmt);
        }
        return null;
    }


    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type condType = expr.typecheck(typeEnv);
        if(!condType.equals(new boolType()))
            throw new TypecheckException("While condition must be of type boolean\n");

        whileStmt.typecheck((MyIDictionary<String, Type>) typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString(){
        return " while("+expr+")"  + " { "+ whileStmt  +" }";
    }
}


