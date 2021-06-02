//import java.io.PrintStream;
//
//public class PrintingCommand implements Command{
//    private PrintStream outStream;
//    private GasService service;
//    private GasList gasList;
//
//    public PrintingCommand(PrintStream outStream, GasService service) {
//        this.outStream = outStream;
//        this.service = service;
//    }
//
//    @Override
//    public void execute() {
//        outStream.println("Option 3 chosen");
//        gasList = service.getGasList();
//        gasList.printList();
//    }
//
//    @Override
//    public String description() {
//        return "Print all data";
//    }
//}
