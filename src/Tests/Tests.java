package Tests;
import Model.Value.*;
import Model.Expression.*;
import Model.ADT.*;
import org.junit.jupiter.api.Test;

import java.util.Dictionary;

public class Tests {

    @Test
    public void testAnd(){
        MyIDictionary<String,Value> dict=new MyDictionary<>();
        MyIHeap heap = new MyHeap();
        Expression exp=new LogicExp(1,new ValueExp(new boolValue(true)),new ValueExp(new boolValue(true)));
        try
        {
            Value val=exp.evaluate(dict,heap);
            boolean answer=((boolValue)val).getValue();
            assert(answer==true);
        }catch (Exception except){}
        System.out.println("passed");
    }

    @Test
    public void testAdd(){
        MyIDictionary<String,Value> dict=new MyDictionary<>();
        MyIHeap heap = new MyHeap();
        Expression exp=new ArithExp(1,new ValueExp(new intValue(5)),new ValueExp(new intValue(2)));
        try
        {
            Value val=exp.evaluate(dict,heap);
            int answer=((intValue)val).getValue();
            assert(answer==7);
        }catch (Exception except){}
        System.out.println("passed");
    }

    @Test
    public void testMultiplication(){
        MyIDictionary<String,Value> dict=new MyDictionary<>();
        MyIHeap heap = new MyHeap();
        Expression exp=new ArithExp(3,new ValueExp(new intValue(2)),new ValueExp(new intValue(3)));
        try
        {
            Value val=exp.evaluate(dict,heap);
            int answer=((intValue)val).getValue();
            assert(answer==6);
        }catch (Exception except){}
        System.out.println(" passed");
    }


}
