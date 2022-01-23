import java.lang.Math;
import java.util.*;
import java.lang.*;
import javafx.scene.shape.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Point;

class ModelOne{
	int canvasWidth;
	int canvasHeight;

    int updateTick = 0;

    boolean grab = false;
    int holding = -1;

    Score score = new Score(0);
    Timer timer = new Timer();

    boolean gameStart = false;
    boolean gameStatus = false;
    boolean question = false;

    Diver diver = new Diver(0,0,"Chad","assets/game_1/diver.png");

    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Item> trashed = new ArrayList<Item>();
    ArrayList<Item> recycled = new ArrayList<Item>();

    Rectangle2D itemHitBox;
    Rectangle2D diverHitBox;

    Point diverHandLocation;

    DiverMode diverMode = DiverMode.FLOAT;

    int diverSize;
    int binSize = 300;
    int itemSize = 35;
    int diverMoveIncrement=16;
    int itemMoveIncrement=2;

    int goldenInt;

		/**
		 * Constructor for first minigame model sets diver's hand location at point (0,0)
		 */
	public ModelOne(){
		diverHandLocation = new Point(0,0);
	}

	/**
	 * Update function for first model to create canvas dimensions and update tick and get key presses
	 * @param canvasWidth int width of page
	 * @param canvasHeight int height of page
	 * @param time long time for first minigame
	 * @param keys ArrayList of key presses for first minigame
	 * @return void
	 */
	void update(int canvasWidth, int canvasHeight, long time, ArrayList<String> keys){
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        updateTick++;

        diverSize = (int) (canvasWidth/4);
        binSize = (int) canvasWidth/6;

        if(updateTick == 1){
            timer.start();
            diver.setLocation((int) ((canvasWidth/2) - (diverSize/2)), (int) ((canvasHeight/2) - (diverSize/2)));
            goldenInt = randInt(10,timer.getGameTime()-5);
        }

        // do
        // not moving
        if (keys.contains("UP") == false && keys.contains("DOWN") == false && keys.contains("LEFT") == false && keys.contains("RIGHT") == false){
            diverMode = DiverMode.FLOAT;
        } else {
            if (keys.contains("UP") == true){
                diver.moveUp(diverMoveIncrement);
                if (diver.getY() < 0){
                    diver.setLocation(diver.getX(), 0);
                }
                diverMode = DiverMode.FLOAT;
            }
            if (keys.contains("DOWN") == true){
                diver.moveDown(diverMoveIncrement);
                if (diver.getY() > canvasHeight - diverSize){
                    diver.setLocation(diver.getX(), canvasHeight - diverSize);
                }
                diverMode = DiverMode.FLOAT;
            }
            if (keys.contains("LEFT") == true){
                diver.moveLeft(diverMoveIncrement);
                if (diver.getX() < 0){
                    diver.setLocation(0, diver.getY());
                }
                diverMode = DiverMode.SWIMLEFT;
            }
            if (keys.contains("RIGHT") == true){
                diver.moveRight(diverMoveIncrement);
                if (diver.getX() > canvasWidth - diverSize){
                    diver.setLocation(canvasWidth - diverSize, diver.getY());
                }
                diverMode = DiverMode.SWIMRIGHT;
            }
        }

        // get hitBox
        switch(diverMode){
            case CARRYRIGHT:
            case SWIMRIGHT: {
                int handX = (int) (diver.getX() + (620.00/800.00)*diverSize);
                int handY = (int) (diver.getY() + ((426.00/800.00)*diverSize));
                diverHandLocation.setLocation(handX,handY);
                diverHitBox = new Rectangle2D.Double(diver.getX() + (diverSize/2), diver.getY() + (diverSize/10), diverSize/2, diverSize/2);
                break;
            }
            case CARRYLEFT:
            case SWIMLEFT: {
                int handX = (int) (diver.getX() + ((180.00/800.00)*diverSize));
                int handY = (int) (diver.getY() + ((426.00/800.00)*diverSize));
                diverHandLocation.setLocation(handX,handY);
                diverHitBox = new Rectangle2D.Double(diver.getX(), diver.getY() + (diverSize/10), diverSize/2, diverSize/2);
                break;
            }
            default: {
                int handX = (int) (diver.getX() + ((638.00/800.00)*diverSize));
                int handY = (int) (diver.getY() + ((184.00/800.00)*diverSize));
                diverHandLocation.setLocation(handX,handY);
                diverHitBox = new Rectangle2D.Double(diver.getX() + (diverSize/4), diver.getY(), diverSize/2, diverSize/2);
                break;
            }
        }

        if (keys.contains("SPACE") == true){
            grab = true;
        } else {
            grab = false;
            if (holding != -1){
                if(diverHitBox.intersects(0, canvasHeight-binSize, binSize, binSize)){ //trash bin
                    Item temp = items.get(holding);
                    if (temp.getName().equals("trash")){
                        score.addPoints(10);
                    } else {
                        score.subPoints(10);
                    }
                    trashed.add(temp);
                    // add/ subtract points
                    //remove item
                    items.remove(holding);
                }
                if(diverHitBox.intersects(canvasWidth-binSize, canvasHeight-binSize, binSize, binSize)){ //recycle bin
                    Item temp = items.get(holding);
                    if (temp.getName().equals("recycle")){
                        score.addPoints(10);
                    } else {
                        score.subPoints(10);
                    }
                    recycled.add(temp);
                    // add/ subtract points
                    //remove item
                    items.remove(holding);
                }
            }
            holding = -1;
        }


        if(timer.getTime() == 0){
            this.gameStatus = true;
        }

        spawn(updateTick);

    }

		/**
		 * Method controls random spawn of trash, hit boxes for picking up trash, movement of trash
		 * @param updateTick int tick used for random spawn of trash
		 * @return void
		 */
    public void spawn(int updateTick){

        // golden fish
        if (timer.getTime() == goldenInt){
            goldenInt = 0;
        }

        if(updateTick%50 == 0){
            if (items.size() < 5){
                int rand1 = randInt(1,2);
                int x = randInt(binSize,canvasWidth-binSize);
                int y = randInt(binSize,canvasHeight-binSize);
                if (goldenInt == 0) {
                    goldenInt = -1;
                    Item gold = new Item(x,-itemSize,"gold","0");
                    items.add(gold);
                } else if(rand1 == 1){ //trash
                    int rand2 = randInt(1,3);
                    Item item;
                    if (rand2 == 1){
                        item = new Item(x,y,"trash","1");
                    } else if (rand2 == 3) {
                        item = new Item(x,y,"trash","2");
                    } else {
                        item = new Item(x,y,"trash","3");
                    }
                    items.add(item);
                } else{ //recycle
                    int rand2 = randInt(1,2);
                    Item item;
                    if (rand2 == 1){
                        item = new Item(x,y,"recycle","1");
                    } else {
                        item = new Item(x,y,"recycle","2");
                    }
                    items.add(item);
                }
            }
        }


        Iterator<Item> i = items.iterator();
        int index = 0;
        while (i.hasNext()) {
            Item item = i.next(); // must be called before you can call i.remove()

            // moves items
            if (item.getName().equals("gold")){
                item.moveDown(6);
            } else {
                switch(updateTick%40){
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                    case 8:
                        item.moveDown(itemMoveIncrement);
                        break;
                    case 10:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                        item.moveRight(itemMoveIncrement);
                        break;
                    case 20:
                    case 22:
                    case 24:
                    case 26:
                    case 28:
                        item.moveUp(itemMoveIncrement);
                        break;
                    case 30:
                    case 32:
                    case 34:
                    case 36:
                    case 38:
                        item.moveLeft(itemMoveIncrement);
                        break;
                }
            }

            if (diverHitBox.intersects(item.getX(), item.getY(), itemSize, itemSize)) {
                // collision
                // this.score.addPoints(10);
                if(grab == true && holding == -1){
                    if (item.getName().equals("gold")){
                        //gold item was grabbed
                        question = true;
                        i.remove();
                    } else {
                        holding = index;
                    }

                }
            }

            index++;

        }

        if (holding != -1) {
            Item itemHeld = items.get(holding);
            itemHeld.setLocation((int) diverHandLocation.getX()-(itemSize/2),(int) diverHandLocation.getY()-(itemSize/2));
            // System.out.println(itemHeld.getX() + "," + itemHeld.getY());
            items.set(holding, itemHeld);

            if (diverMode == DiverMode.SWIMLEFT){
                diverMode = DiverMode.CARRYLEFT;
            } else if (diverMode == DiverMode.SWIMRIGHT){
                diverMode = DiverMode.CARRYRIGHT;
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
  	 * Score getter
  	 * @return int score of first minigame
  	 */
    public int getScore() {
        return score.getScore();
    }

		/**
  	 * Time getter
  	 * @return int time to be displayed on timer
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
  	 * Diver getter
  	 * @return Diver object
  	 */
    public Diver getDiver(){
        return this.diver;
    }

		/**
  	 * Getter for list of Items
  	 * @return ArrayList of potential garbage items
  	 */
    public ArrayList<Item> getItems() {
        return this.items;
    }

		/**
  	 * Getter for the movement mode of the Diver (the direction he's swimming in)
  	 * @return enum mode of the Diver's movement
  	 */
    public DiverMode getDiverMode() {
        return this.diverMode;
    }

		/**
  	 * Getter for the items that are in the trash can
  	 * @return ArrayList of items in the trash can
  	 */
    public ArrayList<Item> getTrash(){
        return this.trashed;
    }

		/**
  	 * Getter for the items that are in the recycle can
  	 * @return ArrayList of items in the recycle can
  	 */
    public ArrayList<Item> getRecycle(){
        return this.recycled;
    }

		/**
  	 * Getter to return boolean variable question. If true, a quiz question is displayed after minigame. If false, go to next game.
  	 * @return boolean question
  	 */
    public boolean getQuestion() {
        return this.question;
    }
}
