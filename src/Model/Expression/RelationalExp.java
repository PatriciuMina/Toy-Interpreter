package Model.Expression;

import Exceptions.ExpressionException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Type.Type;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Value.*;
import Model.Type.refType;

public class RelationalExp implements Expression{

    private final Expression exp1;
    private final Expression exp2;
    private final String operator;

    public RelationalExp(Expression expression1, Expression expression2, String oper)
    {
        exp1 = expression1;
        exp2 = expression2;
        operator = oper;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, MyIHeap heap) throws ExpressionException {
        Value value1 = exp1.evaluate(SymTable,heap);
        if(!value1.getType().equals(new intType()))
            throw new ExpressionException("expression exp1 must be int expression");
        Value value2 = exp2.evaluate(SymTable,heap);
        if(!value2.getType().equals(new intType()))
            throw new ExpressionException("expression exp2 must be int expression");
        new boolValue(false);
        return switch (operator)
                {
                    case "<" -> new boolValue(((intValue) value1).getValue() < ((intValue) value2).getValue());
                    case "<=" -> new boolValue(((intValue) value1).getValue() <= ((intValue) value2).getValue());
                    case "==" -> new boolValue(((intValue) value1).getValue() == ((intValue) value2).getValue());
                    case "!=" -> new boolValue(((intValue) value1).getValue() != ((intValue) value2).getValue());
                    case ">" -> new boolValue(((intValue) value1).getValue() > ((intValue) value2).getValue());
                    case ">=" -> new boolValue(((intValue) value1).getValue() >= ((intValue) value2).getValue());
                    default -> new boolValue(false);
                };
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws TypecheckException {
        Type type1 = exp1.typecheck(typeEnv);
        Type type2 = exp2.typecheck(typeEnv);

        if (!type1.equals(new intType()))
            throw new TypecheckException("First operand is not an integer \n");
        if (!type2.equals(new intType()))
            throw new TypecheckException("Second operand is not an integer \n");

        return new boolType();
    }


    @Override
    public String toString(){
        return  this.exp1.toString() + this.operator+this.exp2.toString();
    }
}
