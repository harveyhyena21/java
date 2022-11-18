package PhraseSolver;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        Player one = new Player();
        Player two = new Player();
        

        System.out.println("Player 1: What's your name?");
        one.setName(sc.nextLine());
        System.out.println("\nPlayer 2: What's your name?");
        two.setName(sc.nextLine());
        boolean replay = true;

        //plays 5 rounds
        while (replay) {
            one.setMoney(0);
            two.setMoney(0);
            for (int i = 1; i <= 5; i++) {
                Board board = new Board();
                board.playRound(one, two, i);
            }
    
            //ending sequence
            System.out.print("\n\n."); Thread.sleep(500);
            System.out.print("."); Thread.sleep(500);
            System.out.print("."); Thread.sleep(500);
            System.out.print("."); Thread.sleep(500);
            System.out.print("."); Thread.sleep(500);
            if (one.getMoney() > two.getMoney()) System.out.println("Congratulations " + one.getName() + "! You won with $" + one.getMoney() + "!");
            else if (one.getMoney() == two.getMoney()) System.out.println("You guys tied with $" + one.getMoney() + "!");
            else System.out.println("Congratulations " + two.getName() + "! You won with $" + two.getMoney() + "!");

            //asks if wants replay
            System.out.println("\nWould you like to play again?\n[Y] Yes\n[N] No");
            boolean isValidInput = false;
            String answer = "";
            while (!isValidInput) {
                answer = sc.nextLine().toUpperCase();
                if (answer.equals("Y")) isValidInput = true;
                else if (answer.equals("N")){
                    isValidInput = true;
                    replay = false;
                }
            }

        }
        sc.close();
        
    }
}

