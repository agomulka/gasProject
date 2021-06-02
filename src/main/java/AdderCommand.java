//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintStream;
//
//public class AdderCommand implements Command {
//    private PrintStream outStream;
//    private BufferedReader bufferedReader;
//    private GasService service;
//
//    public AdderCommand(PrintStream outStream, BufferedReader bufferedReader, GasService service) {
//        this.outStream = outStream;
//        this.bufferedReader = bufferedReader;
//        this.service = service;
//    }
//
//
//    @Override
//    public void execute() {
//        //dodanie nowego gazu
//        System.out.println("kwik add");
//        outStream.println("Option 1 chosen");
//        outStream.println("Provide gas value, data or TODAY for todays date:");
//        String strings;
//        try {
//            if ((strings = bufferedReader.readLine()) != null) {
//                try {
//                    service.addGasData(strings);
//                    outStream.println("Added new gas data");
//
//                } catch (RuntimeException e) {
//                    outStream.println(e.getMessage());
//                }
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String description() {
//        return "Add new data";
//    }
//}
