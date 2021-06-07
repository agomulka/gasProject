import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private Map<String,Command> commandsMap;
    private Command defaultCommand;

    public Invoker(Command defaultCommand, Command... commandArray) {
        this.commandsMap = new HashMap<>();
        commandsMap.put("1", commandArray[0]);
        commandsMap.put("2", commandArray[1]);
        commandsMap.put("2", commandArray[2]);
        this.defaultCommand = defaultCommand;
    }

    public void invoke(String line) throws IOException {
        Command commandToExecute = commandsMap.getOrDefault(line, defaultCommand);
        commandToExecute.execute();
    }

    public String createMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("What do you want to do?" + System.lineSeparator());

        for (String key : commandsMap.keySet()) {
            Command value = commandsMap.get(key);
            stringBuilder.append(key + ". " + value.description() + System.lineSeparator());
        }

        int lineSeparatorLength = System.lineSeparator().length();
        stringBuilder.delete(stringBuilder.length() - lineSeparatorLength, stringBuilder.length());
        return stringBuilder.toString();
    }

}
