import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Main{
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        boolean stop = false;
        int mainChooseNum;

        while(!stop){
            mainChooseNum = mainScreen();
            switch(mainChooseNum){
                case 1:
                    gamePlay();
                    break;
                case 2:
                    infoShow();
                    break;
                case 3:
                    stop=true;
                    break;
                default:
                    System.out.println("Choose wrong number");
                    continue;
            }
        }
    }
    public static int mainScreen(){
        System.out.println("            ------------            ");
        System.out.println("             BLANK JACK             ");
        System.out.println("            ------------            ");
        System.out.println("                                    ");
        System.out.println("              1) Play               ");
        System.out.println("              2) Info               ");
        System.out.println("              3) Quit               ");
        System.out.println("                                    ");
        System.out.print("               ->");
        return scan.nextInt();
    }
    public static void gamePlay(){
        Dealer dealer = new Dealer("Taeyoung");
        Deck deck = new Deck();
        String name = makeUser();
        Player player = new Player(name);


        stateUser(player);
        System.out.print("Betting: ");
        player.setBet(scan.nextInt());
        System.out.println("Setting Cards...");
        player.setCards(deck.getCards());
        System.out.format("%s player cards -> %s %s\n",player.getName(), player.getHand().get(0),player.getHand().get(1));
        dealer.setCard(deck.getCard());
        System.out.format("%s dealer cards -> %s ?\n",dealer.getName(),player.getHand().get(0));
        System.out.print("1) HIT..?     2) STOP..?");
        while(scan.nextInt()==1) {
            System.out.println("Choose HIT...");
            System.out.println("Give one more card");
            player.hitCard(deck.getCard());
            System.out.format("%s player cards -> ", player.getName());
            for (int i = 0; i < player.getHand().size(); i++) {
                System.out.format("%s ", player.getHand().get(i));
            }
        }

    }
    public static boolean checkBurst(Player cards){
        int sum = 0;
        ArrayList hands = cards.getHand();
        for(int i=0;i<hands.size();i++){
            if(hands.get(i).equals("A")){
                sum+=1;
            }else if(hands.get(i))
        }
    }

    public static void stateUser(Player p){
        System.out.format("User Name:  %s\n",p.getName());
        System.out.format("User Money: %d\n",p.getHoldMoney());
    }
    public static String makeUser(){
        System.out.println("            -------------           ");
        System.out.println("              USER NAME             ");
        System.out.println("            -------------           ");
        System.out.println("                                    ");
        System.out.print("              ->");
        return scan.next();
    }
    public static void infoShow(){

    }
}

// dealer
class Dealer{
    private ArrayList hand = new ArrayList<String>();
    private String name;

    public Dealer(){
        super();
        this.name = "John";
    }
    public Dealer(String n){
        super();
        this.name = n;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList getHand(){
        return this.hand;
    }
    public void setCard(String card){
        this.hand.add(card);
    }
    public void hitCard(String card){
        this.hand.add(card);
    }
}
// player
class Player{
    private ArrayList hand = new ArrayList<String>();
    private String name;
    private int holdMoney = 100;
    private int bet = 0;

    public Player(){
        super();
        this.name = "Eric";
    }
    public Player(String n){
        super();
        this.name = n;
    }
    public String getName(){
        return this.name;
    }
    public int getHoldMoney(){
        return this.holdMoney;
    }
    public ArrayList getHand(){
        return this.hand;
    }
    public void setBet(int n){
        this.holdMoney -= n;
        this.bet = n;
    }
    public void setCards(ArrayList<String> cards){
        for(String c: cards){
            this.hand.add(c);
        }
    }
    public void hitCard(String card){
        this.hand.add(card);
    }
}
// card role
class Deck{
    private String[] deck = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    public Deck(){

    }
    public ArrayList getCards(){
        Random ran = new Random();
        return new ArrayList<String>(Arrays.asList(deck[ran.nextInt(13)],deck[ran.nextInt(13)]));
    }
    public String getCard(){
        Random ran = new Random();
        return this.deck[ran.nextInt(13)];
    }

}
// betting role
class Bet{

}