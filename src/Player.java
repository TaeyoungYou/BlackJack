import java.util.ArrayList;

public class Player{
    private final ArrayList<String> hand = new ArrayList<>();
    private final String name;
    private int holdMoney = 100;
    private int bet;

    public Player(){
        super();
        this.name = "Eric";
        this.bet = 0;
    }
    public Player(String n){
        super();
        this.name = n;
        this.bet = 0;
    }
    public String getName(){
        return this.name;
    }
    public int getHoldMoney(){
        return this.holdMoney;
    }
    public ArrayList<String> getHand(){
        return this.hand;
    }
    public void setBet(int n){
        this.holdMoney -= n;
        this.bet = n;
    }
    public void setCards(ArrayList<String> cards){
        this.hand.addAll(cards);
    }
    public void hitCard(String card){
        this.hand.add(card);
    }
    public void stateUser(){
        System.out.format("User Name:  %s\n",getName());
        System.out.format("User Money: %d\n",getHoldMoney());
    }
}