package Repository;

import Model.ProgramState.PrgState;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class Repository implements IRepository
{
    private  List<PrgState> prgStates;
    private final String logFilePath;




    public Repository(PrgState initState, String filePath)
    {
        prgStates = new ArrayList<>();
        prgStates.add(initState);
        logFilePath = filePath;
    }
    @Override
    public List<PrgState> getPrgList() {return prgStates;}

    @Override
    public void setPrgList(List<PrgState> newPrgState){ prgStates = newPrgState;}

    @Override
    public PrgState getMainPrg() {
        return prgStates.get(0);
    }


    public void logPrgStateExec(PrgState program)
    {
        try
        {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.write(program.toFile());
            logFile.close();
        }
        catch (IOException exception)
        {
            System.out.println(" path not found\n");
        }
    }

}
