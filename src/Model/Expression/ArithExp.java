package Model.Expression;

import Exceptions.ExpressionException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Type.intType;
import Model.Value.intValue;
import Model.Value.Value;
import org.junit.jupiter.engine.discovery.predicates.IsNestedTestClass;

public class ArithExp implements Expression
{
    private final Expression e1;
    private final Expression e2;
    private final int op;

    public ArithExp(int operator, Expression expression1, Expression expression2)
    {
        e1 = expression1;
        e2 = expression2;
        op = operator;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, MyIHeap heap) throws ExpressionException {
        Value v1, v2;
        v1 = e1.evaluate(SymTable, heap);
        if (v1.getType().equals(new intType())) {
            v2 = e2.evaluate(SymTable, heap);
            if (v2.getType().equals(new intType())) {
                intValue i1 = (intValue) v1;
                intValue i2 = (intValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == 1) return new intValue(n1 + n2);
                if (op == 2) return new intValue(n1 - n2);
                if (op == 3) return new intValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0) throw new ExpressionException("divizion by zero");
                    else
                        return new intValue(n1 / n2);
            } else
                throw new ExpressionException("second operand is not an integer");
        } else
            throw new ExpressionException("first operand is not an integer");

        return new intValue(0);
    }

    @Override
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws TypecheckException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);

        if (typ1.equals(new intType())) {
            if (typ2.equals(new intType())) {
                return new intType();
            } else
                throw new TypecheckException("second operand is not an int");
        }else
            throw new TypecheckException("TypecheckEXP: FIRST OPERAND IS NOT AN INT");
    }

    public String toString()
    {
        if(op == 1) return "("+e1+"+"+e2+")";
        if(op == 2) return "("+e1+" - "+e2+")";
        if(op == 3) return "("+e1+" * "+e2+")";
        if(op == 4) return "("+e1+" / "+e2+")";
        return null;
    }
}
