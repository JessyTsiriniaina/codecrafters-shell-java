import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            Pattern pattern = Pattern.compile("^echo");
            Matcher matcher = pattern.matcher(input);

            
            if(input.equals("exit 0")) {
                break;
            }
            if(matcher.find()) {
                String[] splittedInput = input.split(" ");
                for(int i = 1; i < splittedInput.length; i++) {
                    System.out.print(splittedInput[i]);
                    System.out.print(i != (splittedInput.length - 1) ? " " : "");
                }
                System.out.println();
            } 
            else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
