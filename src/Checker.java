import java.util.ArrayList;

public class Checker {

    public Checker(){

    }
    public boolean checkBurst(int sum){
        return sum > 21;
    }
    public int checkCardValueSum(ArrayList<String> hands){
        int sum = 0;
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
        return sum;
    }
    public boolean checkBlackJack(int sum){
        return sum==21;
    }
}
