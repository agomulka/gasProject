import java.io.IOException;
import java.io.PrintStream;

public class DefaultCommand implements Command {
    private PrintStream outStream;

    public DefaultCommand(PrintStream outStream) {
        this.outStream = outStream;
    }

    @Override
    public void execute() {
        outStream.println("wrong option");
    }

    @Override
    public String description() {
        return "Wrong option";
    }
}
