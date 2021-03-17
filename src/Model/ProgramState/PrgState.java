package Model.ProgramState;

import Model.ADT.*;
import Model.Statement.IStmt;
import Model.Value.Value;

import java.sql.Statement;


public class PrgState
{
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> SymTable;
    private MyIList<Value> out;
    private MyIFileTable fileTable;
    private MyIHeap heap;
    int id;

    private MyISemaphoreTable semaphoreTable;
    static int programId=0;
    public static synchronized int next(){
        programId++;
        return programId;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot,MyIFileTable file,MyIHeap heaap,MyISemaphoreTable semaphoreTable, IStmt prg)
    {
        id = next();
        exeStack = stk;
        SymTable = symtbl;
        out = ot;
        // exeStack.push(prg); !!!!!!!!!
        fileTable = file;
        heap=heaap;
        this.semaphoreTable= semaphoreTable;

        exeStack.push(prg);
    }
    public MyIStack<IStmt> getExeStack()
    {
        return exeStack;
    }
    public MyIDictionary<String,Value>getSymTable()
    {
        return SymTable;
    }
    public MyIList<Value> getOut()
    {
        return out;
    }
    public MyIFileTable getFileTable(){return fileTable;}
    public MyIHeap getHeap(){return heap;}
    public MyISemaphoreTable getSemaphore(){return semaphoreTable;}

    public void setExeStack(MyIStack<IStmt> exstk)
    {
        exeStack = exstk;
    }
    public void setSymTable(MyIDictionary<String,Value> sytbl)
    {
        SymTable = sytbl;
    }
    public void setOut(MyIList<Value> outt)
    {
        out=outt;
    }
    public void setFileTable(MyIFileTable file){fileTable = file;}
    public void setHeap(MyIHeap newHeap){heap=newHeap;}
    public void setSemaphoreTable(MyISemaphoreTable newSemaphore) { semaphoreTable= newSemaphore;}


    @Override
    public String toString()
    {
        String display = "ProgramState ID: " + id + "\n";
        display +="EXECUTION STACK = " + exeStack + "\n";
        display +="SYMTABLE = "+ SymTable + "\n";
        display +="Out = " + out + "\n";
        display +="FileTable = " + fileTable + "\n";
        display +="Heap = " + heap + "\n";
        display +="Semaphore = " + semaphoreTable + "\n";
        return display;
    }

    public String toFile()
    {
        String display = "\nProgramState id:" + id + "\n" + exeStack.toStringFile() + "\n" + SymTable.toStringFile() + "\n" + out.toStringFile() + "\n" + fileTable.toStringFile() + "\n" + heap.toStringFile() + "\n" + semaphoreTable.toFile()+ "\n";
        return display;
    }

    public int getId(){return id;}


    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws Exception
    {
        if(exeStack.isEmpty())
            throw new Exception("prgstate stack is empty\n");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}