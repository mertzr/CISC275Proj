public class Fish extends Animal{
    double width;
    double aspect;

    /**
    * fish constructor that takes in location, name, and fish image
    * @param xloc x coordinate of fish location
    * @param yloc y coordinate of fish location
    * @param name String of Fish name
    * @param image String of image file
    */
    Fish(int xloc, int yloc, String name, String image){
        super(xloc,yloc,name,image);
    }

    /**
    * fish constructor that takes in location, name, fish image, size, and aspect
    * @param xloc x coordinate of fish location
    * @param yloc y coordinate of fish location
    * @param name String of Fish name
    * @param image String of image file
    * @param size int size of fish
    * @param aspect double aspect ratio of fish
    */
    Fish(int xloc, int yloc, String name, String image, int size, double aspect){
        super(xloc,yloc,name,image);
        this.width = (double) size;
        this.aspect = aspect;
    }

    /**
    * Fish Width getter
    * @return int width of fish
    */
    public int getWidth(){
        return (int) this.width;
    }

    /**
    * Fish height getter
    * @return int height of fish
    */
    public int getHeight(){
        return (int) (this.width/this.aspect);
    }
}
