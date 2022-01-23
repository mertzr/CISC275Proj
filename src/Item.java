public class Item extends Move{
   String name;
   String image;

   /**
    * Constructor of item with a location, name, and image
    * @param xloc x location parameter
    * @param yloc y location parameter
    * @param name String name of item
    * @param image String of image file
    */
    public Item(int xloc, int yloc, String name, String image){
        super(xloc,yloc);
        this.name = name;
        this.image = image;
    }

    /**
		 * moves item left by a certain value
     * @param val determines how much the item moves when called
		 * @return void
		 */
    public void moveLeft(int val){
        super.updateLocation(-val, 0);
    }

    /**
		 * moves item right by a certain value
     * @param val determines how much the item moves when called
		 * @return void
		 */
    public void moveRight(int val){
        super.updateLocation(val, 0);
    }

    /**
		 * moves item up by a certain value
     * @param val determines how much the item moves when called
		 * @return void
		 */
    public void moveUp(int val){
        super.updateLocation(0, -val);
    }
    /**
		 * moves item down by a certain value
     * @param val determines how much the item moves when called
		 * @return void
		 */
    public void moveDown(int val){
        super.updateLocation(0, val);
    }

    /**
     * Image of item getter
     * @return returns String of image file
     */
    public String getImage(){
        return this.image;
    }

    /**
  	 * Item name getter
  	 * @return string of name of item
  	 */
    public String getName(){
        return name;
    }

}
