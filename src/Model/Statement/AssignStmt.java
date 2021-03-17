package Model.Statement;
import Exceptions.ExpressionException;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expression.Expression;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt
{
    private final String id;
    private final Expression exp;

    public AssignStmt(String str, Expression expression)
    {
        id = str;
        exp = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException
    {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();

        if (symTbl.isDefined(id))
        {
            Value val = null;
            try {
                val = exp.evaluate(symTbl,heap);
            } catch (ExpressionException expressionException)
            {
                expressionException.printStackTrace();
            }

            Type typId = (symTbl.lookup(id).getType());
            if(val.getType().equals(typId))
                symTbl.update(id,val);
            else
                throw new StatementException("declared type of variable " +id +"and type of the assigned expression do not match");
        }
        else
            throw new StatementException("The use variable" + id + "was not declared before");
        return null;
    }
    @Override
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            TypecheckException{
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new TypecheckException("Assignment: right hand side and left hand side have different types");
    }

    public String toString() { return id + "=" + exp.toString(); }
}
