package Controller;

import Model.ADT.MyIDictionary;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;
import Model.Value.Value;
import Repository.IRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller
{
    private final IRepository repository;
    private ExecutorService executorService;
    private boolean stepsFlag;

    public Controller(IRepository repo) {
        repository = repo;
        stepsFlag=true;
        executorService = Executors.newFixedThreadPool(2);
    }

    public IRepository getRepository() {return repository;}


    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws Exception
    {
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p)->(Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executorService.invokeAll(callList).stream()
                .map(future-> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        //add the new created threads to tthe list of existing threads
        prgList.addAll(newPrgList);
        //after the execution, print the PrgState List into the log file
        prgList.forEach(repository::logPrgStateExec);
        if (stepsFlag)
            prgList.forEach(System.out::println);
        //Save the current programs in the repository
        repository.setPrgList(prgList);

    }
    public void allSteps() throws Exception
    {
        executorService = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());

        while(prgList.size() > 0)
        {

            PrgState program = repository.getMainPrg();
            program.getHeap().setContent((HashMap<Integer, Value>) GarbageCollector.safeGarbage(repository));

            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executorService.shutdownNow();
        //update the repository state
        repository.setPrgList(prgList);
    }
    public void runOneStep() throws Exception {

        List<PrgState> programStates = removeCompletedPrg(repository.getPrgList());
        for (PrgState programState : programStates) {
            repository.logPrgStateExec(programState);
        }
        if(stepsFlag)
            programStates.forEach(System.out::println);

        repository.getMainPrg().getHeap().setContent((HashMap<Integer, Value>) GarbageCollector.safeGarbage(repository));
        oneStepForAllPrg(programStates);

        if(programStates.size() == 0)
            executorService.shutdownNow();
    }

}
