public class Bet {
    private int playerBet;
    public Bet(){
        super();
        this.playerBet=0;
    }

    public void setPlayerBet(int n){
        this.playerBet = n;
    }
    public void setReset(Player player){
        this.setPlayerBet(0);
        player.setBet(0);
    }
    public void setLostGame(Player player){
        setReset(player);
    }
    public void setDrawGame(Player player){
        player.setWinMoney(this.playerBet);
        setReset(player);
    }
    public void setWinGame(Player player,boolean bj){
        if(bj){
            player.setWinMoney((this.playerBet*2)+(int)(this.playerBet*0.5));
        }else{
            player.setWinMoney(this.playerBet*2);
        }
        setReset(player);
    }
}
