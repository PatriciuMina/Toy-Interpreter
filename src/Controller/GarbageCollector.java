package Controller;

import Exceptions.ExpressionException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expression.readHeapExp;
import Model.Expression.ValueExp;
import Model.ProgramState.PrgState;
import Model.Value.Value;
import Model.Value.refValue;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollector {

    public static List<Integer> getAddrFromSymTable(List<PrgState> symbolTableValues){
        List<Integer> allAddr = new ArrayList<>();

        symbolTableValues.stream()
                .map(programState -> getAllAddr(programState.getSymTable(),programState.getHeap()))
                .forEach(allAddr::addAll);

        return allAddr;
    }

    public static List<Integer> getAllAddr(MyIDictionary<String,Value> symbolTable, MyIHeap heap)
    {
        return symbolTable.getContent().values().stream()
                .filter(value -> value instanceof refValue)
                .flatMap(value ->
                {
                    Stream.Builder<Integer> builder = Stream.builder();
                    while(value instanceof refValue)
                    {
                        int address = ((refValue) value).getAddress();
                        if(address == 0)
                            break;
                        builder.accept(address);
                        value = heap.getValue(address);
                    }
                    return builder.build();
                })
                .collect(Collectors.toList());
    }

    public static Map<Integer,Value> safeGarbage(IRepository repository)
    {
        List<Integer> addresses = getAddrFromSymTable(repository.getPrgList());
        MyIHeap heap = repository.getMainPrg().getHeap();

        return heap.getContent().entrySet().stream()
                .filter(key-> addresses.contains(key.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
}
