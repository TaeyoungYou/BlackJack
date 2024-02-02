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
        Checker check = new Checker();
        int sum;
        dealer.setLevel(1);


        player.stateUser();
        System.out.print("Betting: ");
        player.setBet(scan.nextInt());
        System.out.println("Setting Cards...");
        player.setCards(deck.getCards());
        System.out.format("%s player cards -> %s %s\n",player.getName(), player.getHand().get(0),player.getHand().get(1));
        if(check.checkBlackJack(player)){
            // won
        }

        dealer.setCard(deck.getCard());
        System.out.format("%s dealer cards -> %s ?\n",dealer.getName(),player.getHand().get(0));
        System.out.print("1) HIT..?     2) STOP..?");
        while(scan.nextInt()==1) {
            System.out.println("Choose HIT...");
            System.out.println("Give one more card");
            player.hitCard(deck.getCard());
            sum=check.checkCardValueSum(player);
            System.out.format("%s player cards -> ", player.getName());
            for (int i = 0; i < player.getHand().size(); i++) {
                System.out.format("%s ", player.getHand().get(i));
                if(check.checkBurst(sum)){
                    // burst
                }
            }
            System.out.print("1) HIT..?     2) STOP..?");
        }
        dealer.setCard(deck.getCard());     // 2nd card supplied
        System.out.format("%s dealer cards -> %s %s\n",dealer.getName(),dealer.getHand().get(0),dealer.getHand().get(1));
        if(dealer.getLevel()==1){

        }
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
        // information of how to play game
    }
}
