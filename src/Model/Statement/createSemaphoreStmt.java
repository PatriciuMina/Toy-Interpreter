package Model.Statement;

import Exceptions.*;
import Model.ADT.MyIDictionary;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Type.intType;
import Model.Value.Value;
import Model.Value.intValue;
import com.sun.jdi.IntegerValue;
import javafx.util.Pair;

import java.util.ArrayList;

public class createSemaphoreStmt implements IStmt{
    private final String variable;
    private final Expression expression;

    public createSemaphoreStmt(String var, Expression exp) {
        this.variable = var;
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, FileException, HeapException {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        if(!symbolTable.isDefined(variable))
            throw new StatementException("variable is not defined\n");
        if(!symbolTable.lookup(variable).getType().equals(new intType()))
            throw new StatementException("variable must be integer");

        Value number1 = expression.evaluate(state.getSymTable(), state.getHeap());
        if(!number1.getType().equals(new intType()))
            throw new StatementException("expression must be integer\n");

        synchronized (state.getSemaphore()) {
            Integer number = ((intValue) number1).getValue();
            Integer nextFree = state.getSemaphore().getNextEmpty();
            state.getSemaphore().add(nextFree, new Pair<>(number, new ArrayList<>()));

            symbolTable.update(variable, new intValue(nextFree));
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        if(!typeEnv.lookup(variable).equals(new intType()))
            throw new TypecheckException("TYPECHECK EXCEPTION\n");
       Type typ1 = expression.typecheck(typeEnv);
       if(!typ1.equals(new intType()))
           throw new TypecheckException("typecheck exception");
       return typeEnv;
    }
    @Override
    public String toString(){return "createSemaphore(" +variable + "," + expression + ")";}
}
