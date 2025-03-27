import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void echo(String input) {
        System.out.println(input.substring(5));
    }

    public static void type(String command, String[] builtinCommands) {
        if(Arrays.asList(builtinCommands).contains(command)) 
            System.out.println(command + " is a shell builtin");
        else System.out.println(command + ": not found");
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] builtinCommands = {"echo", "type", "exit"};
            String[] splittedInput = input.split(" ");
            String command = splittedInput[0];

            
            if (Arrays.asList(builtinCommands).contains(command)) {
                if (input.equals("exit 0")) {
                    break;
                }
                switch (command) {
                    case "echo":
                        echo(input);
                        break;
                    case "type": 
                        type(splittedInput[1], builtinCommands);
                        break;
                }
                
            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
