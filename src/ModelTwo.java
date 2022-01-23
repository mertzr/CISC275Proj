import java.util.*;
import java.lang.Math;

import javafx.scene.shape.Rectangle;
import java.awt.geom.Rectangle2D;

class ModelTwo{
    int canvasWidth;
	int canvasHeight;
	int imgWidth;
	int imgHeight;
    Shark shark;
    Rectangle2D sharkHitBox;
    int sharkEat = 0;
    Direction direction;
    int goldenInt;

    Timer timer = new Timer();
    Score score = new Score(0);

    boolean gameStatus = false;
    boolean question = false;
    int updateTick = 0;

    //sizes
    int troutSize=100;
    int mackSize=150;
    int marlinSize=200;
    int snapperSize=150;
    int bassSize=120;

    //aspect ratios
    double troutAsp=2.9848;
    double mackAsp=1.8605;
    double marlinAsp=2.651;
    double snapperAsp=1.6595;
    double bassAsp=2.0051;

    int sharkMoveIncrement=16;
    int fishMoveIncrement=16;

    ArrayList<Fish> fishes = new ArrayList<Fish>();
    ArrayList<Item> items = new ArrayList<Item>();

    /**
		 * The ModelTwo constructor that gets passed canvas width and height and image width and height for edge detection and direction determination.
		 */

	public ModelTwo(int imgWidth, int imgHeight){
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
        shark = new Shark(0,0,"Jimmy",SharkMode.DEFAULT);
	}

  /**
	 * Update function for second model checks the fishes direction to make sure it is correct then updates its position accordingly (with sharkMode determining how fast the fish moves)
	 * @param canvasWidth int width of page
	 * @param canvasHeight int height of page
	 * @param keys ArrayList of key presses for first minigame
	 * @return void
	 */
	void update(int canvasWidth, int canvasHeight,ArrayList<String> keys){
        this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;

        updateTick++;
        if(updateTick == 1){
            timer.start();
            shark.setLocation((int) ((canvasWidth/2) - (imgWidth/2)), (int) ((canvasHeight/2) - (imgHeight/2)));
            goldenInt = randInt(5,timer.getGameTime()-5);
        }

        if (keys.contains("UP") == false && keys.contains("RIGHT") == false && keys.contains("DOWN") == false){
            direction = Direction.EAST;
            shark.moveLeft(sharkMoveIncrement);
        } else {
            if (keys.contains("UP") == true && keys.contains("RIGHT") == true){
                direction = Direction.NORTHEAST;
                shark.moveUp(sharkMoveIncrement);
                shark.moveRight(sharkMoveIncrement);
            } else if (keys.contains("DOWN") == true && keys.contains("RIGHT") == true){
                direction = Direction.SOUTHEAST;
                shark.moveDown(sharkMoveIncrement);
                shark.moveRight(sharkMoveIncrement);
            } else if (keys.contains("UP") == true){
                shark.moveUp(sharkMoveIncrement);
                direction = Direction.NORTH;
            } else if (keys.contains("RIGHT") == true){
                direction = Direction.EAST;
                shark.moveRight(sharkMoveIncrement);
            } else if (keys.contains("DOWN") == true){
                direction = Direction.SOUTH;
                shark.moveDown(sharkMoveIncrement);
            }
        }

        if (shark.getX() < 0){
            shark.setLocation(0,shark.getY());
        } else if (shark.getX() > canvasWidth-imgWidth){
            shark.setLocation(canvasWidth-imgWidth,shark.getY());
        }

        if (shark.getY() < 0){
            shark.setLocation(shark.getX(), 0);
        } else if (shark.getY() > canvasHeight-imgHeight){
            shark.setLocation(shark.getX(), canvasHeight-imgHeight);
        }

        spawn(updateTick, getSharkHit());

        if(timer.getTime() == 0){
            this.gameStatus = true;
        }
	}

    /**
    * Method controls spawn of all fish (including golden), the interaction between shark and fish when there is contact
    * @param updateTick int tick used for spawn of fish
    * @param sharkHitBox Rectangle2D that is the hit box for the shark
    * @return void
    */
    public void spawn(int updateTick, Rectangle2D sharkHitBox){

        if(shark.getMode() == SharkMode.EAT && sharkEat < 2){
            sharkEat++;
        } else {
            sharkEat = 0;
            shark.setMode(SharkMode.DEFAULT);
        }


        // height fish spawns in at
        int y = randInt(150,canvasHeight-350);

        // golden fish
        if (timer.getTime() == goldenInt){
            goldenInt = 0;
        }


        // spawn on occurence
        if(updateTick%20 == 0){
            int rand1 = randInt(1,2);
            if (goldenInt == 0) {
                goldenInt = -1;
                Fish golden = new Fish(canvasWidth,y,"Golden Rainbow Trout","5", troutSize, troutAsp);
                fishes.add(golden);
            } else if(rand1 == 1){ // fish
                int rand2 = randInt(1,4);
                Fish fish;
                if (rand2 == 1){
                    fish = new Fish(canvasWidth,y,"Atlantic Mackerel","1", mackSize, mackAsp);
                } else if (rand2 == 2){
                    fish = new Fish(canvasWidth,y,"Blue Marlin","2", marlinSize, marlinAsp);
                } else if (rand2 == 3) {
                    fish = new Fish(canvasWidth,y,"Cubera Snapper","3", snapperSize, snapperAsp);
                } else {
                    fish = new Fish(canvasWidth,y,"Large Mouth Bass","4", bassSize, bassAsp);
                }
                fishes.add(fish);
            } else{ // items
                int rand2 = randInt(1,6);
                Item item;
                if (rand2 == 1){
                    item = new Item(canvasWidth,y,"Item","1");
                } else if (rand2 == 2){
                    item = new Item(canvasWidth,y,"Item","2");
                } else if (rand2 == 3){
                    item = new Item(canvasWidth,y,"Item","3");
                } else if (rand2 == 4){
                    item = new Item(canvasWidth,y,"Item","4");
                } else if (rand2 == 5){
                    item = new Item(canvasWidth,y,"Item","5");
                } else {
                    item = new Item(canvasWidth,y,"Item","6");
                }
                items.add(item);
            }
        }

        Iterator<Fish> i = fishes.iterator();
        Iterator<Item> j = items.iterator();

        //show all items/fish and iterate movement
        while (i.hasNext()) {
            Fish fish = i.next(); // must be called before you can call i.remove()

            fish.moveLeft(fishMoveIncrement);
            if (sharkHitBox.intersects(fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight())) {
                // collision
                this.score.addPoints(10);
                shark.setMode(SharkMode.EAT);
                if (fish.getImage().equals("5")){
                    this.question = true;
                }
                i.remove();
            }

            if(fish.getX() < 0 - fish.getWidth()){
                i.remove();
            }


        }

        while (j.hasNext()) {
            Item item = j.next(); // must be called before you can call i.remove()

            item.moveLeft(10);

            if (sharkHitBox.intersects(item.getX(), item.getY(), 50, 50)) {
                // collision
                this.score.subPoints(10);
                shark.setMode(SharkMode.EAT);
                j.remove();
            }

            if(item.getX() < 0 - 50){
                j.remove();
            }
        }
    }

    /**
  	 * Controls movement of hitbox and size of hitbox as shark moves
  	 * @return Rectangle2D object that is the shark's hitbox
  	 */
    Rectangle2D getSharkHit(){
        double hitHeight = (imgHeight*.7);
        double hitWidth = (imgWidth*.25);
        double hitX = shark.getX() + (imgWidth*.75);
        double hitY = shark.getY() + (imgHeight*.3);

        switch (direction) {
            case NORTH: {
                hitX = shark.getX() + (imgWidth*.75) - 65;
                hitY = shark.getY() + (imgHeight*.3) - 110;
                break;
            } case NORTHEAST: {
                hitX = shark.getX() + (imgWidth*.75) - 20;
                hitY = shark.getY() + (imgHeight*.3) - 65;
                break;
            } case EAST: {
                hitX = shark.getX() + (imgWidth*.75);
                hitY = shark.getY() + (imgHeight*.3);
                break;
            } case SOUTHEAST: {
                hitX = shark.getX() + (imgWidth*.75) - 5;
                hitY = shark.getY() + (imgHeight*.3) + 30;
                break;
            } case SOUTH: {
                hitX = shark.getX() + (imgWidth*.75) - 60;
                hitY = shark.getY() + (imgHeight*.3) + 70;
                break;
            }
        }

        sharkHitBox = new Rectangle2D.Double(hitX, hitY, hitWidth, hitHeight);
        return sharkHitBox;

        //TODO: Fix rotation around center and hitboxes
        // Rectangle sharkHitBox = new Rectangle(xLoc + (imgWidth/2), yLoc, imgWidth, imgHeight);

        // int theta = 0;
        // int currWidth = 0;
        // double newWidth;
        // double newHeight;
        // switch (direction) {
        //     case NORTH: {
        //         theta = -60;
        //         break;
        //     } case NORTHEAST: {
        //         theta = -30;
        //         break;
        //     } case EAST: {
        //         theta = 0;
        //         break;
        //     } case SOUTHEAST: {
        //         theta = 30;
        //         break;
        //     } case SOUTH: {
        //         theta = 60;
        //         break;
        //     }
        // }
        // if (theta != 0){
        //     newWidth = imgWidth*Math.cos(Math.toRadians(Math.abs(theta))) + imgHeight*Math.cos(Math.toRadians(90-Math.abs(theta)));
        //     newHeight = imgWidth*Math.sin(Math.toRadians(Math.abs(theta))) + imgHeight*Math.sin(Math.toRadians(90-Math.abs(theta)));
        // } else {
        //     newWidth = imgWidth;
        //     newHeight = imgHeight;
        // }

        // double pivotX = xLoc + (newWidth/2);
        // double pivotY = yLoc + (newHeight/2);

        // System.out.println(newWidth + "," + newHeight);

        // sharkHitBox.getTransforms().add(new Rotate(theta,pivotX,pivotY));
    }

    /**
		 * Generates a random number
		 * @param min minimum value for randomly generated number
		 * @param max maximum value for randomly generated number
		 * @return int that is the randomly generated number
		 */
    public static int randInt(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    /**
     * Getter for the direction of the shark's movement
     * @return Direction enum based on shark's movement
     */
	public Direction getDirection(){
		return direction;
	}

  /**
   * Getter for Shark object
   * @return Shark that is main "character" of the minigame
   */
  public Shark getShark(){
		return shark;
	}

  /**
   * Getter for arraylist of spawned fish
   * @return ArrayList of fish that have been spawned
   */
  public ArrayList<Fish> getFish(){
		return fishes;
	}

  /**
   * Getter for arraylist of spawned garbage
   * @return ArrayList of garbage that has been spawned
   */
  public ArrayList<Item> getItems(){
		return items;
	}

  /**
   * Getter for time
   * @return int time to be left in timer
   */
    public int getTime(){
        return timer.getTime();
    }

    /**
  	 * Getter for score
  	 * @return int of second minigame score
  	 */
    public int getScore() {
        return score.getScore();
    }

    /**
  	 * Determines when game is over because of gameStatus boolean
  	 * @return boolean if game is over or not
  	 */
    public boolean gameOver() {
        return this.gameStatus;
    }

    /**
  	 * Getter to return boolean variable question. If true, a quiz question is displayed after minigame. If false, go to next game.
  	 * @return boolean question
  	 */
    public boolean getQuestion() {
        return this.question;
    }
}
