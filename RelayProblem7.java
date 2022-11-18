
import java.util.Scanner;
public class RelayProblem7 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a phrase: ");
        String input = sc.nextLine();
        String output = "";

        int num = input.length()/2;

        for (int i = 0; i < num; i++) {
            if (input.substring(i, i+1).equals(" ")) output += "_";
            else output += "*";
        }

        output += input.substring(num);

        System.out.println("Here is your new phrase: " + output);
    }
}
