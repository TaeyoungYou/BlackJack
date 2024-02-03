import java.util.ArrayList;
import java.util.Collections;

public class Checker {

    public Checker(){

    }
    public boolean checkBurst(int sum){
        return sum > 21;
    }
    public int checkCardValueSum(ArrayList<String> hands){
        int sum = 0;
        int sumInverse = 0;
        ArrayList<String> reHands = new ArrayList<>(hands);
        Collections.reverse(reHands);
        for(String c: hands){
            switch(c){
                case "J":
                case "Q":
                case "K":
                    sum+=10;
                    break;
                case "A":
                    if((sum+11)>21){
                        sum+=1;
                    }else{
                        sum+=11;
                    }
                    break;
                default:
                    sum+=Integer.parseInt(c);
            }
        }
        for(String c: reHands){
            switch(c){
                case "J":
                case "Q":
                case "K":
                    sumInverse+=10;
                    break;
                case "A":
                    if((sum+11)>21){
                        sumInverse+=1;
                    }else{
                        sumInverse+=11;
                    }
                    break;
                default:
                    sumInverse+=Integer.parseInt(c);
            }
        }
        if(sum==sumInverse){
            return sum;
        }else{
            return sumInverse;
        }
    }
    public boolean checkBlackJack(int sum){
        return sum==21;
    }
}
