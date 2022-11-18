package Unit4Activities;
import java.util.Scanner;

public class WhileLoopsAssignment {
    //Create a program in VS Code that implements an 
    //algorithm to determine the sum and average of 
    //a set of user-provided numbers.
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //asks and receives number of numbers
        System.out.print("How many numbers are you inputting? ");
        int numberOfNumbers = scan.nextInt();
        int temp = numberOfNumbers;

        //initializes sum and average variables
        int sum = 0;
        double avg = 0;
        
        //loops while until the user has inputted the number of numbers
        while (temp != 0) {
            //adds each number user inputs to the sum
            sum += scan.nextInt();
            temp--;
        }

        avg = (double) sum/numberOfNumbers;
        
        System.out.println("Sum: " + sum + "\nAverage: " + avg);
    }
}