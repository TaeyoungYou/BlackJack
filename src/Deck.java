import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Deck{
    private final String[] deck = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    public Deck(){

    }
    public ArrayList<String> getCards(){
        return new ArrayList<String>(Arrays.asList(getCard(),getCard()));
    }
    public String getCard(){
        Random ran = new Random();
        return this.deck[ran.nextInt(13)];
    }

}