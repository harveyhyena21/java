import java.util.Scanner;
public class RelayProblem6 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your first word: ");
        String first = sc.nextLine();
        System.out.println("Enter your second word: ");
        String second = sc.nextLine();
        boolean anagram = true;

        for (int i = 0; i < first.length() && anagram == true; i++) {
            anagram = false;
            for (int j = 0; j < second.length(); j++) {
                if (first.substring(i, i+1).equals(second.substring(j, j+1))) anagram = true;
            }
        }

        System.out.println("");
    }
}
