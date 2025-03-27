import java.util.*;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

public class Main {

    public static void echo(String[] splittedInput) {
        for (int i = 1; i < splittedInput.length; i++) {
            System.out.print(splittedInput[i]);
            System.out.print(i != (splittedInput.length - 1) ? " " : "");
        }
        System.out.println();
    }

    public static void type(String[] splittedInput, String[] builtinCommands) {
        if(Arrays.asList(builtinCommands).contains(splittedInput[1])) {
            System.out.println(splittedInput[1] + " is a shell builtin");
        }
        else System.out.println(splittedInput[1] + ": not found");
    }
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            // Pattern pattern = Pattern.compile("^echo");
            // Matcher matcher = pattern.matcher(input);
            String[] builtinCommands = {"echo", "type", "exit"};
            String[] splittedInput = input.split(" ");
            String command = splittedInput[0];

            
            if (Arrays.asList(builtinCommands).contains(command)) {
                if (command.equals("exit")) {
                    break;
                }
                switch (command) {
                    case "echo":
                        echo(splittedInput);
                        break;
                    case "type": 
                        type(splittedInput, builtinCommands);
                        break;
                }
                
            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
