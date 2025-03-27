import java.util.Scanner;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void echo(String input) {
        System.out.println(input.substring(5));
    }

    public static void type(String command, String[] builtinCommands) {
        if(Arrays.asList(builtinCommands).contains(command)) 
            System.out.println(command + " is a shell builtin");
        else if(getPath(command) != null) {
            System.out.println(command + " is " + getPath(command));
        }
        else System.out.println(command + ": not found");
    }

    public static String getPath(String parameter) {
        for (String path : System.getenv("PATH").split(":")) {
          Path fullPath = Path.of(path, parameter);
          if (Files.isRegularFile(fullPath)) {
            return fullPath.toString();
          }
        }
        return null;
      }

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] builtinCommands = {"echo", "type", "exit"};
            String[] splittedInput = input.split(" ");
            String command = splittedInput[0];

            if (input.equals("exit 0")) {
                System.exit(0);
            }
            
            switch (command) {
                case "echo":
                    echo(input);
                    break;
                case "type": 
                    if(splittedInput.length > 1)
                        type(splittedInput[1], builtinCommands);
                    break;
                default: System.out.println(input + ": command not found");
            }
        }
    }
}
