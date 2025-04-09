import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public static String getPath(String command) {
        for (String path : System.getenv("PATH").split(":")) {
          Path fullPath = Path.of(path, command);
          if (Files.isRegularFile(fullPath)) {
            return fullPath.toString();
          }
        }
        return null;
      }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Runtime runtime = Runtime.getRuntime();

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] builtinCommands = {"echo", "type", "exit"};
            String[] splittedInput = input.split(" ");
            String command = splittedInput[0];

            if (input.equals("exit 0")) {
                scanner.close();
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
                default: 
                    if(getPath(command) != null) {
                        Process process = runtime.exec(splittedInput);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                    else {
                        System.out.println(input + ": command not found");
                    }
            }
        }
    }
}
