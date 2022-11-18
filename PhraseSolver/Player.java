package PhraseSolver;

public class Player {
    private String name;
    private int money;
    
    //constructors
    public Player() {
        name = "Player";
        money = 0;
    }

    public Player(String n, int m) {
        name = n;
        money = m;
    }

    //accessors
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    //mutators
    public void setName(String n) {
        name = n;
    }

    public void setMoney(int m) {
        money = m;
    }
}
