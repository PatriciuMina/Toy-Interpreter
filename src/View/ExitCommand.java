package View;

public class ExitCommand extends Command{

    public ExitCommand(String key, String desc)
    {
        super(key,desc);
    }
    @Override
    public void execute() {

        System.exit(0);
    }
    @Override
    public void typeCheck() {

    }

    @Override
    public void reset() {}
}
