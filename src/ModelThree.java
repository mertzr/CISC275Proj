import java.awt.geom.Line2D;
import java.awt.Point;
import java.util.*;
import java.lang.Math;

import javafx.scene.shape.Rectangle;
import java.awt.geom.Rectangle2D;



class ModelThree{
	int canvasWidth;
	int canvasHeight;
    Point point1;
    Point point2;
    boolean didCatch = false;
    boolean grab = false;
    boolean space = false;
    Line2D line;
    int updateTick = 0;
    double bgAspect = 2331.7/798.0;
    double catchXRatio = 595.9/2331.7;
    double catchYRatio = 82.7/798.0;
    double fishXRatio = 580.0/2331.7;
    double fishYRatio = 13.5/798.0;

    int fishMoveIncrement=12;

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

Rectangle2D fishHitBox;
Rectangle2D hookHitBox;
    int fishBoxHeight=113;
    int fishBoxWidth=60;
    int hookBoxHeight=15;
    int hookBoxWidth=23;
int goldenInt;


    Score score = new Score(0);
    Timer timer = new Timer();

    int spawnHeight = 380;
    int waterHeight = 200;
    Fish fishCaught;

    boolean gameStart = false;

    boolean gameStatus = false;
    boolean question = false;

    ArrayList<Fish> fishes = new ArrayList<Fish>();


		/**
		 * Model three constructor creates a line and two points between which that line is drawn
		 */
	public ModelThree(){

        line = new Line2D.Double(0, 0, 0, 0);
        point1 = new Point(0,0); // location of tip of fishing rod before catch
        point2 = new Point(0,0); // location of end of fishing line
	}

	/**
	 * Update function for third model checks the fishes direction to make sure it is correct then updates its position accordingly (with sharkMode determining how fast the fish moves)
	 * @param canvasWidth int width of page
	 * @param canvasHeight int height of page
	 * @param time long time for first minigame
	 * @param keys ArrayList of key presses for first minigame
	 * @return void
	 */
	void update(int canvasWidth, int canvasHeight, long time, ArrayList<String> keys){
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        double imgWidth = (int) canvasHeight * bgAspect;
        double imgHeight = canvasHeight;

        updateTick++;
        if(updateTick == 1){
            timer.start();
            point1.setLocation(fishXRatio * imgWidth, fishYRatio * imgHeight);
            point2.setLocation((fishXRatio * imgWidth), (fishYRatio * imgHeight) + 10);
            goldenInt = randInt(5,timer.getGameTime()-5);
        }


        if (keys.contains("UP") == true){
            point2.setLocation(point2.getX(), point2.getY() - 20);
        } else if (keys.contains("DOWN") == true){
            point2.setLocation(point2.getX(), point2.getY() + 20);
        }

        if(point1.getY() > point2.getY()){
            point2.setLocation(point1);
        }
        else if (point2.getY() > canvasHeight - 23){ // 23 is height of hook
            point2.setLocation(point2.getX(), canvasHeight - 23);
        }

        if (keys.contains("SPACE") == true){
            space = true;
        } else {
            if(space == true){
                space = false;
                grab = true;
            } else {
                grab = false;
            }
        }

        if (didCatch == true){
            point1.setLocation((catchXRatio * imgWidth), (catchYRatio * imgHeight));
            if (point2.getY() < waterHeight) { //fish was raised out of water
                //actual catch
                score.addPoints(10);
                didCatch = false;

            }
        } else {
            //be in did not catch position
            point1.setLocation(fishXRatio * imgWidth, fishYRatio * imgHeight);
            point2.setLocation(point1.getX(), point2.getY());

        }

            //         // fishing rod with tension
            // if (didCatch != true){
            //     point1.translate(12,50);
            //     point2.translate(12,0);
            // }
            // didCatch = true;


            //             // fishing rod without tension
            // if (didCatch == true){
            //     point1.translate(-12,-50);
            //     point2.translate(-12,0);
            // }
            // didCatch = false;

        line.setLine(point1, point2);

        spawn(updateTick, point2);

        // hookHitBox = new Rectangle2D.Double(point2.getX()-15, point2.getY(), 15, 23);

        if(timer.getTime() == 0){
            this.gameStatus = true;
        }
	}

	/**
	 * Method controls spawn of all fish (including golden), the interaction between hook and a fish when one is caught,
	 * @param updateTick int tick used for spawn of fish
	 * @param hookPoint point object for the hook on the fishing rod
	 * @return void
	 */
    public void spawn(int updateTick, Point hookPoint){

        // golden fish
        if (timer.getTime() == goldenInt){
            goldenInt = 0;
        }

        if(updateTick%50 == 0){
            int y = randInt(spawnHeight,canvasHeight-100);
            if (goldenInt == 0) {
                goldenInt = -1;
                Fish golden = new Fish(canvasWidth,y,"Golden Rainbow Trout","5", troutSize, troutAsp);
                fishes.add(golden);
            } else { // fish
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
            }
        }

        Iterator<Fish> i = fishes.iterator();

        //show all items/fish and iterate movement
        while (i.hasNext()) {
            Fish fish = i.next(); // must be called before you can call i.remove()

            fish.moveLeft(fishMoveIncrement);
            fishHitBox = new Rectangle2D.Double(fish.getX(), fish.getY(), fishBoxHeight, fishBoxWidth);

            if (fishHitBox.intersects(hookPoint.getX()-15, hookPoint.getY(), hookBoxHeight, hookBoxWidth)) {
                // collision
                // this.score.addPoints(10);
                if(this.grab == true && didCatch == false){
                    this.fishCaught = fish;
                    if (fish.getImage().equals("5")){
                        this.question = true;
                    }
                    didCatch = true;
                    i.remove();
                }
            }

            if(fish.getX() < 0 - 110){
                i.remove();
            }


        }
    }

		/**
		 * Generates a random number
		 * @param min minimum value for randomly generated number
		 * @param max maximum value for randomly generated number
		 * @return int returns randomly generated number
		 */
    public static int randInt(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

		/**
  	 * Getter for fishing line
  	 * @return Line2D object for fishing line
  	 */
    public Line2D getLine(){
        return this.line;
    }

		/**
  	 * Boolean catch getter
  	 * @return boolean catch which tells if a fish is now on the hook
  	 */
    public boolean getCatch(){
        return this.didCatch;
    }

		/**
  	 * Getter for list of fish
  	 * @return ArrayList of fish that could be spawned
  	 */
    public ArrayList<Fish> getFish(){
        return this.fishes;
    }

		/**
  	 * Getter for score
  	 * @return int of third minigame score
  	 */
    public int getScore() {
        return score.getScore();
    }

		/**
  	 * Getter for Fish that was just caught
  	 * @return Fish object of the type of fish that was just caught
  	 */
    public Fish getFishCaught(){
        return this.fishCaught;
    }

		/**
  	 * Getter for time
  	 * @return int time to be left in timer
  	 */
    public int getTime() {
        return timer.getTime();
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
