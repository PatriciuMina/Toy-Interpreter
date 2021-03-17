package View;

import Controller.Controller;
import Exceptions.StatementException;
import Exceptions.TypecheckException;
import Model.ADT.*;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

public class RunExample extends Command{
    private Controller controller;


    private final IStmt initalProgram;
    public RunExample(String key, String desc, Controller controller1, IStmt initialPrg)
    {
        super(key,desc);
        this.controller = controller1;
        this.initalProgram = initialPrg;
    }
    @Override
    public Controller getController() {
        return controller;
    }
    @Override
    public void execute() {
        try
        {   initalProgram.typecheck(new MyDictionary<>());
            controller.allSteps();
        }catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
    @Override
    public void typeCheck() {
        try
        {
            initalProgram.typecheck(new MyDictionary<>());
        }
        catch(TypecheckException excep)
        {
            System.out.println("prg " + getKey() + " " + getDesc() + '\n' + excep.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void reset() {
        List<PrgState> programs = new ArrayList<PrgState>();
        programs.add(new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable(), new MyHeap(),new MySemaphoreTable(), initalProgram));
        controller.getRepository().setPrgList(programs);
    }
}
