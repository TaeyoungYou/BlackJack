import java.util.ArrayList;

public class Dealer{
    private final ArrayList<String> hand = new ArrayList<>();
    private final String name;
    private int level; // 1 or 2

    public Dealer(){
        super();
        this.name = "John";
        this.level = 0;
    }
    public Dealer(String n){
        super();
        this.name = n;
        this.level = 0;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<String> getHand(){
        return this.hand;
    }
    public void setCard(String card){
        this.hand.add(card);
    }
    public void setLevel(int l){
        this.level = l;
    }
    public int getLevel(){
        return this.level;
    }
}