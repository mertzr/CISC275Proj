public class Score{

  public int score;

  /**
   * Constructor for score depending on the value parameter
   * @param value int that is the value of the player's score
   */
  public Score(int value){
    this.score = value;
  }

  /**
   * Function adds points to player's score
   * @param value int that is the increment by which to increase the player's score after an action
   * @return void
   */
  public void addPoints(int value){
    this.score += value;
  }
  /**
   * Function subtracts points from player's score
   * @param value int that is the increment by which to decrease the player's score after an action
   * @return void
   */
  public void subPoints(int value){
    this.score -= value;
  }
  /**
   * Function sets a player's score
   * @param value int that is the value to set the player's score to
   * @return void
   */
  public void setScore(int value){
    this.score = value;
  }
  /**
   * Getter to get score from game
   * @return int that is the player's score from game
   */
  public int getScore(){
    return this.score;
  }
}
