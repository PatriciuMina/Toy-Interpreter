//package View;
//import Controller.*;
//import Model.ADT.*;
//import Model.Expression.*;
//import Model.ProgramState.*;
//import Model.Statement.*;
//import Model.Type.*;
//import Model.Value.*;
//import Repository.*;
//import Exceptions.*;
//
//import java.time.chrono.IsoChronology;
//import java.util.Scanner;
//
//public class View {
//
//    public void testProgram(IStmt program)
//    {
//        MyIStack<IStmt> stk = new MyStack<>();
//        MyIDictionary<String, Value> symTbl = new MyDictionary<>();
//        MyIList<Value> out = new MyList<>();
//        MyIFileTable fileTable = new MyFileTable();
//        MyIHeap heap = new MyHeap();
////        MyISemaphoreTable = new MySemaphoreTable()
////        PrgState prgState = new PrgState(stk,symTbl,out,fileTable,heap,program);
//
////        IRepository repo = new Repository(prgState, "logFile.txt");
////        Controller contrl = new Controller(repo);
//
//        try
//        {
//            contrl.allSteps();
//            //contrl.printOut();
//        }catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public IStmt RunExampleFile()
//    {
//        return new CompStmt(new VarDeclStmt("varf", new stringType()),
//                new CompStmt(new AssignStmt("varf", new ValueExp(new stringValue("test.in"))),
//                        new CompStmt(new VarDeclStmt("varc", new intType()),
//                                new CompStmt(new openRFile(new VarExp("varf")),
//                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
//                                                new CompStmt(new PrintStmt(new VarExp("varc")),
//                                                        new CompStmt(new readFile(new VarExp("varf"),"varc"),
//                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
//                                                                        new closeRFile(new VarExp("varf"))))))))));
//    }
//
//}