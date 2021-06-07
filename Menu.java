import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Menu {

    private PrintStream outStream;
    private BufferedReader bufferedReader;
    private Invoker invoker;
    private GasService service;

    public Menu(PrintStream outStream, BufferedReader bufferedReader, GasService service) {
        this.outStream = outStream;
        this.bufferedReader = bufferedReader;
        Command addingCommand = new AdderCommand(outStream, bufferedReader, service);
        Command generatingCommand = new GeneratingFileCommand();
        Command printingCommand = new PrintingCommand(outStream, service);
        Command defaultCommand = new DefaultCommand(outStream);
        this.invoker = new Invoker(defaultCommand, addingCommand, generatingCommand, printingCommand);
    }


    public void runMenu() {
        try {
            String menu = invoker.createMenu();
            String line;

            while ((line = getLine(menu)) != null) {
                invoker.invoke(line);
            }
            outStream.println("exiting");
        } catch (IOException e) {
        }

    }

    private String getLine(String menu) throws IOException {
        outStream.println(menu);
        return bufferedReader.readLine();
    }


}
