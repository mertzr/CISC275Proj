public class Animal extends Move{
    public String image;
    public String name;


    /**
     * Constructor of animal with a name and location
     * @param xloc x location parameter
     * @param yloc y location parameter
     * @param name String name of animal
     */
    Animal(int xloc, int yloc, String name){
        super(xloc,yloc);
        this.name = name;
    }

    /**
     * Constructor of animal with a name, location, and image 
     * @param xloc x location parameter
     * @param yloc y location parameter
     * @param name String name of animal
     * @param image String of image file
     */
    Animal(int xloc, int yloc, String name, String image){
        super(xloc,yloc);
        this.name = name;
        this.image = image;
    }
    /**
		 * moves Animal left by a certain value
     * @param val determines how much the animal moves when called
		 * @return void
		 */
    public void moveLeft(int val){
        super.updateLocation(-val, 0);
    }
    /**
		 * moves Animal right by a certain value
     * @param val determines how much the animal moves when called
		 * @return void
		 */
    public void moveRight(int val){
        super.updateLocation(val, 0);
    }

    /**
		 * moves Animal up by a certain value
     * @param val determines how much the animal moves when called
		 * @return void
		 */
    public void moveUp(int val){
        super.updateLocation(0, -val);
    }

    /**
		 * moves Animal down by a certain value
     * @param val determines how much the animal moves when called
		 * @return void
		 */
    public void moveDown(int val){
        super.updateLocation(0, val);
    }

    /**
  	 * Animal name getter
  	 * @return string of name of Animal
  	 */
    public String getName(){
        return this.name;
    }

    /**
     * Image of Animal getter
     * @return returns String of image file
     */
    public String getImage(){
        return this.image;
    }
}
