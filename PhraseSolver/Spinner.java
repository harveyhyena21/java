package PhraseSolver;

public class Spinner {
    private int money;

    public Spinner() {
        money = 0;
    }

    public Spinner(int m) {
        money = m;
    }

    public int getMoney() {
        return money;
    }

    //spins and returns whether the spin got bankrupt or not
    public boolean spin(Player p) {
        //ranges from 100-600
        money = (int) (Math.random() * 6) * 100 + 100;
        //is bankrupt 10% of the time
        int bankrupt = (int) (Math.random() * 11);
        if (bankrupt == 1) {
            return true;
        } else {
            System.out.println("\n\n\n" + p.getName() + ": you spun $" + money + "! Every time the letter you guess is present in the phrase, you earn $" + money + ".");
            return false;
        }
    }
}
