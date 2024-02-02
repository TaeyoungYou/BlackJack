import java.util.ArrayList;

public class Checker {

    public Checker(){

    }
    public boolean checkBurst(int sum){
        return sum <= 21;
    }
    public int checkCardValueSum(Player cards){
        int sum = 0;
        ArrayList<String> hands = cards.getHand();
        for(String c: hands){
            switch(c){
                case "J":
                case "Q":
                case "K":
                case "A":
                    sum+=1;
                default:
                    sum+=Integer.parseInt(c);
            }
        }
        return sum;
    }
    public boolean checkBlackJack(Player player){
        return checkCardValueSum(player)==21;
    }
}
