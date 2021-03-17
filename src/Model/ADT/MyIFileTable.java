package Model.ADT;

import Model.Value.stringValue;

import java.io.BufferedReader;
import java.util.Map;

public interface MyIFileTable
{
    void add(stringValue file, BufferedReader fileDescriptor);
    void remove(stringValue file);
    boolean isOpen(stringValue file);
    Map<stringValue,BufferedReader> getElements();
    BufferedReader getFileDescriptor(stringValue file);
    String toString();
    String toStringFile();
}
