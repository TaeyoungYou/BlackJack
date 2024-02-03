import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Main{
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, IOException{
        boolean stop = false;
        int mainChooseNum;


        while(!stop){
            clearScreen();
            mainChooseNum = mainScreen();
            clearScreen();
            switch(mainChooseNum){
                case 1:
                    gamePlay();
                    break;
                case 2:
                    infoShow();
                    pause();
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
    public static void gamePlay() throws InterruptedException, IOException{
        Dealer dealer = new Dealer("Taeyoung");
        Deck deck = new Deck();
        Bet bet = new Bet();
        Checker check = new Checker();

        int sumPlayer=0, sumDealer=0;

        boolean Player_BLACKJACK = false;
        boolean Dealer_BLACKJACK = false;
        boolean Player_BURST = false;
        boolean Dealer_BURST = false;
        boolean play = false;

        dealer.setLevel(1);

        String name = makeUser();
        Player player = new Player(name);

        do{
            clearScreen();
            player.stateUser();
            System.out.print("Betting: ");
            player.setBet(scan.nextInt());
            bet.setPlayerBet(player.getBet());
            clearScreen();

            System.out.println("Setting Cards...");
            loadingScreen();
            dealer.setCard(deck.getCard());
            player.setCards(deck.getCards());
            sumPlayer = check.checkCardValueSum(player.getHand());
            clearScreen();

            printState(player,dealer);
            if(check.checkBlackJack(sumPlayer)){
                Player_BLACKJACK = true;
                System.out.format("You got a BLACKJACK!!\n");
                pause();
            }else{
                System.out.print("1) HIT..?     2) STOP..?\n\t->");
                while(!Player_BURST&&scan.nextInt()==1) {
                    clearScreen();
                    printState(player,dealer);
                    System.out.println("Choose HIT...");
                    System.out.println("Give one more card");
                    player.hitCard(deck.getCard());
                    sumPlayer=check.checkCardValueSum(player.getHand());
                    pauseClearScreen();
                    printState(player,dealer);
                    if(check.checkBurst(sumPlayer)){
                        Player_BURST = true;
                        System.out.println("You are bursted..");
                        pause();
                        break;
                    }
                    System.out.print("1) HIT..?     2) STOP..?\n\t->");
                }
            }
            clearScreen();

            System.out.println("Setting last card to Dealer");
            Thread.sleep(1000);
            clearScreen();
            dealer.setCard(deck.getCard());     // 2nd card supplied
            sumDealer = check.checkCardValueSum(dealer.getHand());
            printState(player,dealer);
            if(check.checkBlackJack(sumDealer)){
                Dealer_BLACKJACK=true;
                System.out.format("Dealer got a BLACKJACK\n");
            }else{
                while(!Player_BURST&&sumDealer<sumPlayer){
                    System.out.println("Dealer Choose HIT...");
                    System.out.println("Get one more card");
                    pauseClearScreen();
                    dealer.setCard(deck.getCard());
                    sumDealer=check.checkCardValueSum(dealer.getHand());
                    printState(player,dealer);
                    if(check.checkBurst(sumDealer)){
                        Dealer_BURST = true;
                        System.out.println("Dealer is bursted..!");
                        break;
                    }
                }
            }
            System.out.println("Let's see the result..!");
            pauseClearScreen();
            printState(player,dealer);
            if((Dealer_BLACKJACK&&!Player_BLACKJACK)||!Dealer_BURST&&(Player_BURST||sumDealer>sumPlayer)){
                System.out.format("Player %s - LOSE\n",player.getName());
                bet.setLostGame(player);
            }else if((Dealer_BLACKJACK&&Player_BLACKJACK)||(Dealer_BURST&&Player_BURST)||(sumPlayer==sumDealer)){
                System.out.format("Player %s & Dealer %s - DRAW\n",player.getName(),dealer.getName());
                bet.setDrawGame(player);
            }else{
                if(Player_BLACKJACK){
                    System.out.format("Player %s - Win with BLACKJACK...!\n",player.getName());
                    bet.setWinGame(player,Player_BLACKJACK);
                }
                System.out.format("Player %s - Win\n",player.getName());
                bet.setWinGame(player,Player_BLACKJACK);
            }
            pauseClearScreen();
            player.stateUser();
            System.out.print("Play Continue..? (yes: 1 | no: 0)\n\t->");
            if(scan.nextInt()==1){
                play=true;
                player.resetDeck();
                dealer.resetDeck();
                sumPlayer=0;
                sumDealer=0;
                Player_BLACKJACK=false;
                Player_BURST=false;
                Dealer_BLACKJACK=false;
                Dealer_BURST=false;
            }
            else{ play=false;}
        }while(play);
    }
    public static void printState(Player player, Dealer dealer){
        System.out.format("Player: %s\tBet: %d\n",player.getName(),player.getBet());
        System.out.format("Player %s Deck\n",player.getName());
        for(int i=0;i<player.getHand().size();i++){
            System.out.format("%s ",player.getHand().get(i));
        } System.out.println();
        System.out.format("Dealer %s Deck\n",dealer.getName());
        if(dealer.getHand().size()==1){
            System.out.format("%s ?",dealer.getHand().get(0));
        }else{
            for(int i=0;i<dealer.getHand().size();i++){
                System.out.format("%s ",dealer.getHand().get(i));
            }
        }System.out.println("\n");
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
        System.out.println("HOW TO PLAY\n");
        System.out.println("Blackjack is an incredibly popular, exciting and easy card game to play.");
        System.out.println("The object is to have a hand with a total value higher than the dealer’s without going over 21.");
        System.out.println("Kings, Queens, Jacks and Tens are worth a value of 10. An Ace has the value of 1 or 11.");
        System.out.println("The remaining cards are counted at face value\n");
    }
    public static void loadingScreen() throws InterruptedException {
        System.out.print("\tSetting\n");
        for(int i=0;i<12;i++){
            System.out.print("==");
            Thread.sleep(250);
        }
    }
    public static void pauseClearScreen() throws IOException, InterruptedException{
        pause();
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    }
    public static void clearScreen() throws IOException, InterruptedException{
        Thread.sleep(1500);
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    }
    public static void pause() {
        try{
            System.out.print("Enter...");
            scan.nextLine();
            System.in.read();
        }catch(IOException e){

        }
    }
}
