public class GeneratingFileCommand implements Command {
    @Override
    public void execute() {
        //generating output file
        System.out.println("generating...");
    }

    @Override
    public String description() {
        return "Generate file";
    }
}
