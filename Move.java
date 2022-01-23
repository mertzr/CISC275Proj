public class Move{
   public int xloc;
   public int yloc;

   /**
    * Constructor of movement class that updates and sets locations
    * @param xloc x location parameter
    * @param yloc y location parameter
    */
    Move(int xloc, int yloc){
        this.xloc = xloc;
        this.yloc = yloc;
    }

    /**
     * Updates location based on x and y increments
     * @param x x increment
     * @param y y increment
     * @return void
     */
    public void updateLocation(int x, int y) {
        this.xloc += x;
        this.yloc += y;
    }
    /**
     * Sets location based on x and y parameters
     * @param x x location
     * @param y y location
     * @return void
     */
    public void setLocation(int x, int y) {
        this.xloc = x;
        this.yloc = y;
    }
    /**
     * getter for x coordinate of location
     * @return int x location
     */
    public int getX(){
        return this.xloc;
    }

    /**
     * getter for y coordinate of location
     * @return int y location
     */
    public int getY(){
        return this.yloc;
    }
}
