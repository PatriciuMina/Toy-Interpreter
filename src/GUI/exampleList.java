package GUI;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.*;
import Repository.IRepository;
import Repository.Repository;
import View.Command;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class exampleList implements Initializable {

    @FXML
    private ListView<Command> programList;

    @FXML
    private Button button;

    TextMenu menu;

    @FXML
    public void runMain(javafx.event.ActionEvent actionEvent) throws IOException {
        Command program = programList.getSelectionModel().getSelectedItem();
        if (program == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No program was selected");
            alert.show();
            return;
        }
        Stage main = (Stage) programList.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        System.out.println(program.getController());
        loader.setControllerFactory(controller -> {return new mainWindow(program, program.getController());});

        Parent newRoot = loader.load();

        Scene newScene = new Scene(newRoot);
        Stage newStage = new Stage();

        newStage.setTitle("Program " + program.getKey());
        newStage.setScene(newScene);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(main);

        newStage.setX(main.getX()-240);
        newStage.setY(main.getY() + 10);

        newStage.show();

    }

    void typeCheckMethod(MouseEvent event)
    {
        Command program = programList.getSelectionModel().getSelectedItem();

        try
        {
            program.typeCheck();
        }
        catch (Exception excep)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Typecheck error");
            alert.setContentText(excep.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainMenu();
        ObservableList<Command> prgs = FXCollections.observableArrayList();
        menu.getCommands().forEach(command -> prgs.add(command));

        programList.setItems(prgs.sorted(Comparator.comparingInt(c -> Integer.parseInt(c.getKey()))));
        programList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void mainMenu() {

        MyIStack<IStmt> stack1 = new MyStack<>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
        MyIList<Value> out1 = new MyList<>();
        MyIFileTable fileTable1 = new MyFileTable();
        MyIHeap heap1 = new MyHeap();
        MyISemaphoreTable semaphore1 = new MySemaphoreTable();

//        IStmt ex1 = example1();
//        PrgState programState1 = new PrgState(stack1,symTable1,out1,fileTable1,heap1,semaphore1,ex1);
//        IRepository repository1 = new Repository(programState1, "log1.txt");
//        Controller controller1 = new Controller(repository1);

//        MyIStack<IStmt> stack2 = new MyStack<>();
//        MyIDictionary<String, Value> symTable2 = new MyDictionary<>();
//        MyIList<Value> out2 = new MyList<>();
//        MyIFileTable fileTable2 = new MyFileTable();
//        MyIHeap heap2 = new MyHeap();
//
//        IStmt ex2 = example2();
//        PrgState programState2 = new PrgState(stack2,symTable2,out2,fileTable2,heap2,ex2);
//        IRepository repository2 = new Repository(programState2, "log2.txt");
//        Controller controller2 = new Controller(repository2);
//
//        MyIStack<IStmt> stack3 = new MyStack<>();
//        MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
//        MyIList<Value> out3 = new MyList<>();
//        MyIFileTable fileTable3 = new MyFileTable();
//        MyIHeap heap3 = new MyHeap();
//
//        IStmt ex3 = example3();
//        PrgState programState3 = new PrgState(stack3,symTable3,out3,fileTable3,heap3,ex3);
//        IRepository repository3 = new Repository(programState3, "log3.txt");
//        Controller controller3 = new Controller(repository3);
//
//        IStmt ex4=example4();
//        PrgState progState4=new PrgState(new MyStack<>(),new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(),ex4);
//        IRepository repo4=new Repository(progState4,"log4.txt");
//        Controller controller4=new Controller(repo4);
//
//        IStmt ex5 = example5();
//        PrgState programState5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), ex5);
//        IRepository repo5 = new Repository(programState5,"log5.txt");
//        Controller controller5 = new Controller(repo5);
//
//        IStmt ex6 = example6();
//        PrgState programState6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), ex6);
//        IRepository repo6 = new Repository(programState6,"log6.txt");
//        Controller controller6 = new Controller(repo6);
//
//        IStmt ex7 = exampleGarbage();
//        PrgState programState7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), ex7);
//        IRepository repo7 = new Repository(programState7,"log7.txt");
//        Controller controller7 = new Controller(repo7);
//
//        IStmt ex8 = example8();
//        PrgState programState8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), ex8);
//        IRepository repo8 = new Repository(programState8,"log8.txt");
//        Controller controller8 = new Controller(repo8);
//
//        IStmt ex9 = exampleFork();
//        PrgState programState9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), ex9);
//        IRepository repo9 = new Repository(programState9,"log9.txt");
//        Controller controller9 = new Controller(repo9);

        IStmt exSwitch = exampleSwitch();
        PrgState programState10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(),new MySemaphoreTable(), exSwitch);
        IRepository repo10 = new Repository(programState10,"logSwitch.txt");
        Controller controller10 = new Controller(repo10);
//
//        IStmt exSleep = exampleSleep();
//        PrgState programState11 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), exSleep);
//        IRepository repo11 = new Repository(programState11,"logSleep.txt");
//        Controller controller11 = new Controller(repo11);
//
//        IStmt exConditional = exampleConditional();
//        PrgState programState12 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), exConditional);
//        IRepository repo12 = new Repository(programState12,"logConditional.txt");
//        Controller controller12 = new Controller(repo12);
//
//        IStmt exWait = exampleWait();
//        PrgState programState13 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(), exWait);
//        IRepository repo13 = new Repository(programState13,"logWait.txt");
//       Controller controller13 = new Controller(repo13);

        IStmt exSemaphore = exampleSemaphore();
        PrgState programState14 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(),new MySemaphoreTable(), exSemaphore);
        IRepository repo14 = new Repository(programState14,"logSemaphore.txt");
        Controller controller14 = new Controller(repo14);
        menu = new TextMenu();
       // menu.addCommand(new ExitCommand("0","exit"));
//        menu.addCommand(new RunExample("1",ex1.toString(),controller1,ex1));
//        menu.addCommand(new RunExample("2",ex2.toString(),controller2,ex2));
//        menu.addCommand(new RunExample("3",ex3.toString(),controller3,ex3));
//        menu.addCommand(new RunExample("4",ex4.toString(),controller4,ex4));
//        menu.addCommand(new RunExample("5",ex5.toString(),controller5,ex5));
//        menu.addCommand(new RunExample("6",ex6.toString(),controller6,ex6));
//        menu.addCommand(new RunExample("7",ex7.toString(),controller7,ex7));
//        menu.addCommand(new RunExample("8",ex8.toString(),controller8,ex8));
//        menu.addCommand(new RunExample("9",ex9.toString(),controller9,ex9));
        menu.addCommand(new RunExample("10",exSwitch.toString(),controller10,exSwitch));
//        menu.addCommand(new RunExample("11",exSleep.toString(),controller11,exSleep));
//        menu.addCommand(new RunExample("12",exConditional.toString(),controller12,exConditional));
//        menu.addCommand(new RunExample("13",exWait.toString(),controller13,exWait));
        menu.addCommand(new RunExample("14",exSemaphore.toString(),controller14,exSemaphore));

    }
//    public static IStmt example1()
//    {
//        return new CompStmt(new VarDeclStmt("v", new intType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new intValue(2))), new PrintStmt(new
//                        VarExp("v"))));
//    }
//    public static IStmt example2()
//    {
//
//        return  new CompStmt( new VarDeclStmt("a",new intType()),
//                new CompStmt(new VarDeclStmt("b",new intType()),
//                        new CompStmt(new AssignStmt("a", new ArithExp(1,new ValueExp(new intValue(3)),new
//                                ArithExp(4,new ValueExp(new boolValue(true)), new ValueExp(new intValue(1))))),
//                                new CompStmt(new AssignStmt("b",new ArithExp(1,new VarExp("a"), new
//                                        ValueExp(new intValue(1)))), new PrintStmt(new VarExp("b"))))));
//    }
//
//    public static IStmt example3()
//    {
//        return  new CompStmt(new VarDeclStmt("a",new boolType()),
//                new CompStmt(new VarDeclStmt("v", new intType()),
//                        new CompStmt(new AssignStmt("a", new ValueExp(new boolValue(true))),
//                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
//                                        intValue(2))), new AssignStmt("v", new ValueExp(new intValue(3)))), new PrintStmt(new
//                                        VarExp("v"))))));
//    }
//
//
//
//    // Heap allocation example
//    public static IStmt example4(){
//        return new CompStmt(new VarDeclStmt( "v",new refType(new intType())),
//                new CompStmt(new heapAlloc("v",new ValueExp(new intValue(20))),
//                        new CompStmt(new VarDeclStmt( "a",new refType(new refType(new intType()))),
//                                new CompStmt(new heapAlloc("a",new VarExp("v")),
//                                        new CompStmt(new PrintStmt(new VarExp("v")),
//                                                new PrintStmt(new VarExp("a")))))));
//    }
//    // Heap reading example
//    public static IStmt example5()
//    {
//        return new CompStmt(new VarDeclStmt( "v",new refType(new intType())),
//                new CompStmt(new heapAlloc("v",new ValueExp(new intValue(20))),
//                        new CompStmt(new VarDeclStmt( "a",new refType(new refType(new intType()))),
//                                new CompStmt(new heapAlloc("a",new VarExp("v")),
//                                        new CompStmt( new PrintStmt(new readHeapExp(new VarExp("v"))),
//                                                new PrintStmt(new ArithExp(1,new readHeapExp(  new readHeapExp(new VarExp("a"))), new ValueExp(new intValue(5)))))))));
//    }
//    // Heap writing example
//    public static IStmt example6(){
//        return new CompStmt(new VarDeclStmt("v",new refType(new intType())),
//                new CompStmt(new heapAlloc("v", new ValueExp(new intValue(20))),
//                        new CompStmt(new PrintStmt(new readHeapExp(new VarExp("v"))),
//                                new CompStmt(new heapWriteStmt("v", new ValueExp(new intValue(30))),
//                                        new PrintStmt(new ArithExp(1,new readHeapExp(new VarExp("v")),new ValueExp(new intValue(5))))))));
//    }
//
//    // Garbage collector example
//    public static IStmt exampleGarbage()
//    {
//        return new CompStmt(new VarDeclStmt( "v",new refType(new intType())),
//                new CompStmt(new heapAlloc("v",new ValueExp(new intValue(20))),
//                        new CompStmt(new VarDeclStmt( "a",new refType(new refType(new intType()))),
//                                new CompStmt(new heapAlloc("a",new VarExp("v")),
//                                        new CompStmt(new heapAlloc("v",new ValueExp(new intValue(30))),
//                                                new PrintStmt(new readHeapExp(new readHeapExp(new VarExp("a")))))))));
//    }
//
//    // While example
//    public static IStmt example8(){
//        return new CompStmt(new VarDeclStmt("v",new intType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new intValue(4))),
//                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new intValue(0)), ">"),
//                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(2,
//                                        new VarExp("v"), new ValueExp(new intValue(1)))))),
//                                new PrintStmt(new VarExp("v")))));
//    }
//
//
//    public static IStmt exampleFork(){
//        return new CompStmt(new VarDeclStmt("v",new intType()),
//                new CompStmt(new VarDeclStmt("a",new refType(new intType())),
//                        new CompStmt(new AssignStmt("v",new ValueExp(new intValue(10))),
//                                new CompStmt(new heapAlloc("a",new ValueExp(new intValue(22))),
//                                        new CompStmt(new ForkStatement(new CompStmt(new heapWriteStmt("a",new ValueExp(new intValue(30))),
//                                                new CompStmt(new AssignStmt("v",new ValueExp(new intValue(32))),
//                                                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new readHeapExp(new VarExp("a"))))))),
//                                                new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new readHeapExp(new VarExp("a")))))))));
//    }


    private static IStmt exampleSwitch(){
        IStmt switchS = new switchStmt(new ArithExp(3, new VarExp("a"), new ValueExp(new intValue(10))),
                new ArithExp(3, new VarExp("b"), new VarExp("c")), new ValueExp(new intValue(10)),
                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                new CompStmt(new PrintStmt(new ValueExp(new intValue(100))), new PrintStmt(new ValueExp(new intValue(200)))),
                new PrintStmt(new ValueExp(new intValue(300))));

        return new CompStmt(new VarDeclStmt("a", new intType()), new CompStmt(new VarDeclStmt("b", new intType()),
                new CompStmt(new VarDeclStmt("c", new intType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new intValue(1))),
                                new CompStmt(new AssignStmt("b", new ValueExp(new intValue(2))),
                                        new CompStmt(new AssignStmt("c", new ValueExp(new intValue(5))),
                                                new CompStmt(switchS, new PrintStmt(new ValueExp(new intValue(300))))))))));
    }

//    private static IStmt exampleSleep(){
//        IStmt decr = new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new intValue(1))));
//        return new CompStmt(new VarDeclStmt("v", new intType()), new CompStmt(new AssignStmt("v", new ValueExp(new intValue(10))),
//                new CompStmt(new ForkStatement(new CompStmt(decr, new CompStmt(decr, new PrintStmt(new VarExp("v"))))),
//                        new CompStmt(new sleepStmt(10), new PrintStmt(new ArithExp(3, new VarExp("v"), new ValueExp(new intValue(10))))))));
//    }
//
//    private static IStmt exampleConditional(){
//        IStmt init = new CompStmt(new VarDeclStmt("a", new refType(new intType())),
//                new CompStmt(new VarDeclStmt("b", new refType(new intType())),
//                        new CompStmt(new VarDeclStmt("v", new intType()),
//                                new CompStmt(new heapAlloc("a", new ValueExp(new intValue(0))),
//                                        new heapAlloc("b", new ValueExp(new intValue(0)))))));
//        IStmt wheap = new CompStmt(new heapWriteStmt("a", new ValueExp(new intValue(1))),
//                new heapWriteStmt("b", new ValueExp(new intValue(2))));
//        IStmt vs = new CompStmt(new conditionalStmt("v", new RelationalExp(
//                new readHeapExp(new VarExp("a")), new readHeapExp(new VarExp("b")),"<"),
//                new ValueExp(new intValue(100)), new ValueExp(new intValue(200))),
//                new CompStmt(new PrintStmt(new VarExp("v")),
//                        new CompStmt(new conditionalStmt("v", new RelationalExp(
//                                new ArithExp(2, new readHeapExp(new VarExp("b")),new ValueExp(new intValue(2))),
//                                new readHeapExp(new VarExp("a")),">"),
//                                new ValueExp(new intValue(100)), new ValueExp(new intValue(200))),
//                                new PrintStmt(new VarExp("v")))));
//
//        return new CompStmt(init, new CompStmt(wheap, vs));
//    }
//
//    private static IStmt exampleWait(){
//        return new CompStmt(new VarDeclStmt("v", new intType()), new CompStmt(new AssignStmt("v", new ValueExp(new intValue(20))),
//                new CompStmt(new waitStmt(10), new PrintStmt(new ArithExp(3, new VarExp("v"), new ValueExp(new intValue(10)))))));
//    }


    private static IStmt exampleSemaphore(){
        return new CompStmt(new VarDeclStmt("v1", new refType(new intType())),
                new CompStmt(new VarDeclStmt("cnt", new intType()),
                        new CompStmt(new heapAlloc("v1", new ValueExp(new intValue(1))),
                                new CompStmt(new createSemaphoreStmt("cnt", new readHeapExp(new VarExp("v1"))),
                                        new CompStmt(new ForkStatement(new CompStmt(new acquireStmt("cnt"),
                                                new CompStmt(new heapWriteStmt("v1", new ArithExp(3, new readHeapExp(new VarExp("v1")), new ValueExp(new intValue(10)))),
                                                        new CompStmt(new PrintStmt(new readHeapExp(new VarExp("v1"))), new releaseStmt("cnt"))))),
                                                new CompStmt(new ForkStatement(new CompStmt(new acquireStmt("cnt"),
                                                        new CompStmt(new heapWriteStmt("v1", new ArithExp(3, new readHeapExp(new VarExp("v1")),new ValueExp(new intValue(10)))),
                                                                new CompStmt(new heapWriteStmt("v1", new ArithExp(3, new readHeapExp(new VarExp("v1")), new ValueExp(new intValue(2)))),
                                                                        new CompStmt(new PrintStmt(new readHeapExp(new VarExp("v1"))), new releaseStmt("cnt")))))),
                                                        new CompStmt(new acquireStmt("cnt"), new CompStmt(new PrintStmt(new ArithExp(2, new readHeapExp(new VarExp("v1")), new ValueExp(new intValue(1)))),
                                                                new releaseStmt("cnt")))))))));
    }


}
