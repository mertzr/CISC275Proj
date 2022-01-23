public class Shark extends Animal{
    SharkMode mode;

    /**
     * Constructor of Shark with a name, location, and mode of movement
     * @param xloc x location parameter
     * @param yloc y location parameter
     * @param name String name of animal
     * @param mode SharkMode enum depending on shark's eating state
     */
    Shark(int xloc, int yloc, String name, SharkMode mode){
        super(xloc,yloc,name);
        this.mode = mode;
    }

    /**
     * Getter for mode of shark (eating or not)
     * @return SharkMode that is the mode the shark is in based on whether shark is eating or not
     */
    public SharkMode getMode(){
        return this.mode;
    }

    /**
     * Sets mode of shark based on eating or not
     * @param mode SharkMode based on shark eating or not
     * @return void
     */
    public void setMode(SharkMode mode){
        this.mode = mode;
    }
}
