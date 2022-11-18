public class Player {
    private int score = 0;
    private String name;

    //DO NOT CHANGE
    public Player(String name){

        this.name = name;
        System.out.println("-----------------------------");
    }

    //TO DO
    public int/*Change to appropriate data type, not void*/ getScore(){
        return score;
    }
    //TO DO
    public String/*Change to appropriate data type, not void*/ getName(){
        return name;
    }

    public void adjustScore(int x){
        score += x;
    }
}
