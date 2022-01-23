public class Timer{
    long startTime;
    int currentTime;
    int gameTime;

    /**
     * First timer constructor that sets gametime and current time to 45 automatically
     */
    public Timer(){
        this.gameTime = 45;
        this.currentTime = 45;
    }

    /**
     * Second constructor for timer that can set timer to a specific value
     * @param time int that is the specific value the timer is being set to
     */
    public Timer(int time){
        this.currentTime = time;
        this.gameTime = time;
    }

    /**
     * Function Starts running the timer
     * @return void
     */
    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Getter for remaining game time
     * @return int that is gameTimer remaining
     */
    public int getGameTime(){
        return this.gameTime;
    }

    /**
     * Getter for current time remaining
     * @return int that is the current time from gameTime minus System timer
     */
    public int getTime(){
        this.currentTime = this.gameTime - ((int)(System.currentTimeMillis() - startTime) / 1000);
        if(currentTime < 0){
            this.currentTime = 0;
        }
        return this.currentTime;
    }
}
