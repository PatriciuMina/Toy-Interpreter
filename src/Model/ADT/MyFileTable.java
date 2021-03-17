package Model.ADT;

import Model.Value.stringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class MyFileTable implements MyIFileTable
{

    private final HashMap<stringValue,BufferedReader> filePaths;
    public MyFileTable()
    {
        filePaths = new HashMap<>();
    }

    @Override
    public void add(stringValue file, BufferedReader fileDescriptor) {
        filePaths.put(file,fileDescriptor);
    }

    @Override
    public void remove(stringValue file) {
        filePaths.remove(file);
    }

    @Override
    public boolean isOpen(stringValue file) {
        return filePaths.containsKey(file);
    }

    @Override
    public BufferedReader getFileDescriptor(stringValue file) {
        return filePaths.get(file);
    }
    @Override
    public Map<stringValue, BufferedReader> getElements() {
        return filePaths;
    }
    public String toString()
    {
        String results = "{";
        int position = 0;
        for(stringValue element : filePaths.keySet())
        {
            if (position == 0) results +="" + element;
            else results += "," + element;
            position++;
        }
        results +="}";
        return results;
    }

    @Override
    public String toStringFile() {
        String print = "FileTable: ";
        for(stringValue file: filePaths.keySet())
        {
            print += "\n" + file;
        }
        return print;
    }
}
