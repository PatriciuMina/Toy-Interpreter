package Model.Expression;
import Exceptions.ExpressionException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Value.boolValue;
import Model.Value.Value;
import com.sun.jdi.BooleanValue;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;

public class LogicExp implements Expression
{
    private final Expression e1;
    private final Expression e2;
    private final int op;

    public LogicExp(int operator, Expression expression1, Expression expression2)
    {
        op = operator;
        e1 = expression1;
        e2 = expression2;
    }
    @Override
    public Value evaluate(MyIDictionary<String, Value>SymTable , MyIHeap heap) throws ExpressionException
    {
        Value v1,v2;
        v1=e1.evaluate(SymTable, heap);
        if (v1.getType().equals(new boolType()))
        {
            v2= e2.evaluate(SymTable, heap);
            if(v2.getType().equals(new boolType()))
            {
                boolValue b1 = (boolValue) v1;
                boolValue b2 = (boolValue) v2;
                boolean n1,n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if(op == 1) return new boolValue(n1 && n2);
                if(op == 2) return new boolValue(n1 || n2);
            }
            else
                throw new ExpressionException("second operand not boolean");
        }
        else
            throw new ExpressionException("first operand not boolean");
        return new boolValue(false);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type type1 = e1.typecheck(typeEnv);
        Type type2 = e2.typecheck(typeEnv);

        if (!type1.equals(new boolType()))
            throw new TypecheckException("First operand is not a boolean \n");
        if (!type2.equals(new boolType()))
            throw new TypecheckException("Second operand is not a boolean \n");

        return new boolType();
    }

    public String toString() { return  e1 + " " + op + " " + e2 ;}
}
