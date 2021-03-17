package View;

import Controller.Controller;

public abstract class Command {
    private  String key;
    private  String description;

    public Command(String KEY,String DESC)
    {
        key = KEY;
        description = DESC;
    }

    public abstract void execute();
    public String getKey()
    {
        return key;
    }
    public String getDesc()
    {
        return description;
    }
    public Controller getController() {return null;}
    public abstract void typeCheck();

    public String toString() {return key + ". " + description;}

    public abstract void reset();
}