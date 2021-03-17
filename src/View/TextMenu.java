package View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextMenu {
    private Map<String,Command> commands;
    public TextMenu()
    {
        commands = new HashMap<>();
    }
    public void addCommand(Command c)
    {
        commands.put(c.getKey(), c);
    }
    public List<Command> getCommands() {
        return commands.values().stream().collect(Collectors.toList());
    }
    private void printMenu()
    {
        for(Command com : commands.values())
        {
            String line = String.format("%4s: %s",com.getKey(),com.getDesc());
            System.out.println(line);
        }
    }

    public void show()
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            printMenu();
            System.out.println("Input the option : ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if(com == null)
            {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}
