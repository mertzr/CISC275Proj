public class HS{

    private String name;
    private int totalScore;
    /**
  	 * Constructor for HS with name and total score
  	 * @param n string player name
     * @param ts int total score
  	 */
    public HS(String n, int ts){
        this.name = n;
        this.totalScore = ts;
    }
  
    /**
  	 * Player name getter
  	 * @return string of name of player
  	 */
    public String getName(){
        return name;
    }

    /**
     * Sets name of player
     * @param name string name of player
     * @return void
     */
    public void setName(String name){
        this.name = name;
    }

    /**
  	 * total score getter
  	 * @return int player's total score
  	 */
    public int getTotalScore(){
        return totalScore;
    }

    /**
  	 * puts score and name in a usable string
  	 * @return string of name and player's total score
  	 */
    public String toString(){
        return this.name + "," + this.totalScore;
    }

}
