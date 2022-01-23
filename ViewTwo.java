import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.transform.Rotate;
import javafx.animation.Timeline;
import java.lang.Math;
import java.util.*;
import java.lang.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt.geom.Line2D;
import java.awt.Point;
import javafx.scene.text.TextAlignment;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ViewTwo extends StackPane {
	Canvas canvas;

	// value of the height and width of screen
	int canvasWidth = 1000;
	int canvasHeight = 800;
    double aspectRatio = 2.9223;

	// value of the size of the image
	static final int imgWidthOrig = 495;
	static final int imgHeightOrig = 224;

	int imgWidth = 300;
	int imgHeight = 136;

    GraphicsContext gc;

    Image floor;
    Image water;
    Image water2;

	Image sharkImage;
    Image sharkEatImage;
    Image fish1;
    Image fish2;
    Image fish3;
    Image fish4;
    Image fish5;
    Image item1;
    Image item2;
    Image item3;
    Image item4;
    Image item5;
    Image item6;

	// Used to determine the direction to warp the bass image into
	Direction currentDirection, lastDirection;
    SharkMode sharkMode;

	//variables to determine the location of image
	int x = 0;
	int y = 0;

	Direction direction = Direction.EAST;

    // variables for button presses
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    // background location x
    int bgLoc = 0;
    int water2Loc = 0;

    // Score
    Text t;
    Text s;
    Label text1;
    Label text2;

    int updateTick = 0;
    ArrayList<Image> glowImages = new ArrayList<Image>(8);

    int itemSize = 50; //itemSize

    boolean flag = false;
    boolean tutOver = false;

		/**
		 * Constructor for second minigame view which sets up the look of the canvas (score, timer, shark location, fish images, instructions)
		 */
	public ViewTwo() {


        canvas = new Canvas(canvasWidth, canvasHeight);
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        s = new Text();
        s.setText("Score: 0");
        s.setFont(Font.font ("Verdana", 40));
        s.setFill(Color.WHITE);
        setAlignment(s, Pos.TOP_RIGHT);
        getChildren().add(s);

        t = new Text();
        t.setText("Time: 45");
        t.setFont(Font.font ("Verdana", 40));
        t.setFill(Color.WHITE);
        setAlignment(t, Pos.TOP_LEFT);
        getChildren().add(t);

        sharkMode = SharkMode.DEFAULT;

        // import images
        floor = createImage("/assets/game_2/floor.png");
        water = createImage("/assets/game_2/water.png");
        water2 = createImage("/assets/game_2/water2.png");
        sharkImage = createImage("/assets/game_2/shark-default.gif");
        sharkEatImage = createImage("/assets/game_2/shark-eat.gif");

        item1 = createImage("/assets/game_2/items/recycle-1.png");
        item2 = createImage("/assets/game_2/items/recycle-2.png");
        item3 = createImage("/assets/game_2/items/recycle-3.png");
        item4 = createImage("/assets/game_2/items/recycle-4.png");
        item5 = createImage("/assets/game_2/items/trash-1.png");
        item6 = createImage("/assets/game_2/items/trash-2.png");

        fish1 = createImage("/assets/fish/mackerel.gif");
        fish2 = createImage("/assets/fish/marlin.gif");
        fish3 = createImage("/assets/fish/snapper.gif");
        fish4 = createImage("/assets/fish/bass.gif");
        fish5 = createImage("/assets/fish/trout.gif");

        glowImages.add(createImage("/assets/fish/glow-1.png"));
        glowImages.add(createImage("/assets/fish/glow-2.png"));
        glowImages.add(createImage("/assets/fish/glow-3.png"));
        glowImages.add(createImage("/assets/fish/glow-4.png"));
        glowImages.add(createImage("/assets/fish/glow-5.png"));
        glowImages.add(createImage("/assets/fish/glow-4.png"));
        glowImages.add(createImage("/assets/fish/glow-3.png"));
        glowImages.add(createImage("/assets/fish/glow-2.png"));


        text1 = new Label("Hi there, I'm Jimmy and I'm a shark in this Estuary. Help me eat as many fish as possible while avoiding trash before time runs out. Press the spacebar to continue.");
        text2 = new Label("Use the arrow keys to move me toward my dinner. Press the spacebar when you're ready to play!");
    }

		/**
		 * Tutorial view and deals with moving on from tutorial to gameplay by spacebar
		 * @param canvasWidth width of page
		 * @param canvasHeight height of page
		 * @param keys ArrayList of keys
		 * @return void
		 */
    public void tutorial(int canvasWidth, int canvasHeight, ArrayList<String> keys){
        int tearingShift = 2;
        double bgImgWidth = (canvasHeight*this.aspectRatio);
        double bgImgHeight = canvasHeight;
        updateTick++;
        if (updateTick == 1){
            getChildren().add(text1);
        }

        setCanvas(this.canvas, canvasWidth, canvasHeight);


        // text1.setMinWidth(canvasWidth/2);
        // text1.setMinHeight(120);
        text1.setMaxWidth((canvasWidth/3)*2);
        text1.setMaxHeight(160);
				text1.setWrapText(true);
				text1.setTextAlignment(TextAlignment.CENTER);
        setAlignment(text1,Pos.BOTTOM_CENTER);
        text1.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px; -fx-border-insets: 10px; -fx-background-insets: 10px;");

        // text2.setMinWidth(canvasWidth/2);
        // text2.setMinHeight(120);
        text2.setMaxWidth((canvasWidth/3)*2);
        text2.setMaxHeight(120);
				text2.setWrapText(true);
				text2.setTextAlignment(TextAlignment.CENTER);
        setAlignment(text2,Pos.BOTTOM_CENTER);
        text2.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px; -fx-border-insets: 10px; -fx-background-insets: 10px;");

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw background and sharkMove such like current
        gc.drawImage(water, bgLoc, 0, bgImgWidth, bgImgHeight);
        gc.drawImage(water, bgLoc + bgImgWidth - tearingShift, 0, bgImgWidth, bgImgHeight);
        bgLoc = bgLoc - 12;
        if (bgImgWidth + bgLoc < 0){
            bgLoc = -12;
        }

        gc.drawImage(floor, water2Loc, 0, bgImgWidth, bgImgHeight);
        gc.drawImage(floor, water2Loc + bgImgWidth - tearingShift, 0, bgImgWidth, bgImgHeight);

        gc.drawImage(water2, water2Loc, 0, bgImgWidth, bgImgHeight);
        gc.drawImage(water2, water2Loc + bgImgWidth - tearingShift, 0, bgImgWidth, bgImgHeight);
        water2Loc = water2Loc - 6;
        if (bgImgWidth + water2Loc < 0){
            water2Loc = -6;
        }

        transformAndDraw(gc, sharkImage, (canvasWidth/2) - (imgWidth/2), (canvasHeight/2) - (imgWidth/2), direction);

        if (keys.contains("SPACE") == true){
            flag = true;
        } else {
            if (flag == true){
                flag = false;
                if (getChildren().indexOf(text1) != -1){
                    getChildren().remove(text1);
                    getChildren().add(text2);
                } else if (getChildren().indexOf(text2) != -1) {
                    getChildren().remove(text2);
                    tutOver = true;
                }

            }
        }
    }


		/**
		 * method used to repaint on the image and called in controller
		 * @param canvasWidth width of page
		 * @param canvasHeight height of page
		 * @param Direction direction enum for shark movement
		 * @param score int keeps track of score for minigame
		 * @param shark Shark object that is main "character of game"
		 * @param time int keeps time and counts down with Timer
		 * @param fishes ArrayList of fish in the minigame
		 * @param items ArrayList of garbage items in the minigame
		 * @return	void
		 */
	public void update(int canvasWidth, int canvasHeight, Direction direction, int time, int score, Shark shark, ArrayList<Fish> fishes, ArrayList<Item> items) {

        int tearingShift = 2;
        double bgImgWidth = (canvasHeight*this.aspectRatio);
        double bgImgHeight = canvasHeight;
        updateTick++;

        setCanvas(this.canvas, canvasWidth, canvasHeight);

		currentDirection = direction;

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        s.setText("Score: " + score);
        t.setText("Time: " + time);

        // draw background and sharkMove such like current
        gc.drawImage(water, bgLoc, 0, bgImgWidth, bgImgHeight);
        gc.drawImage(water, bgLoc + bgImgWidth - tearingShift, 0, bgImgWidth, bgImgHeight);
        bgLoc = bgLoc - 12;
        if (bgImgWidth + bgLoc < 0){
            bgLoc = -12;
        }

        gc.drawImage(floor, water2Loc, 0, bgImgWidth, bgImgHeight);
        gc.drawImage(floor, water2Loc + bgImgWidth - tearingShift, 0, bgImgWidth, bgImgHeight);

        gc.drawImage(water2, water2Loc, 0, bgImgWidth, bgImgHeight);
        gc.drawImage(water2, water2Loc + bgImgWidth - tearingShift, 0, bgImgWidth, bgImgHeight);
        water2Loc = water2Loc - 6;
        if (bgImgWidth + water2Loc < 0){
            water2Loc = -6;
        }

        for (Fish fish : fishes){
            if (fish.getImage().equals("1")){
                gc.drawImage(fish1, fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
            } else if (fish.getImage().equals("2")){
                gc.drawImage(fish2, fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
            } else if (fish.getImage().equals("3")){
                gc.drawImage(fish3, fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
            } else if (fish.getImage().equals("4")){
                gc.drawImage(fish4, fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
            } else if (fish.getImage().equals("5")){
                addGlow(fish, updateTick);
                gc.drawImage(fish5, fish.getX(), fish.getY(), fish.getWidth(), fish.getHeight());
            }
        }

        for (Item item : items){
            if (item.getImage().equals("1")){
                gc.drawImage(item1, item.getX(), item.getY(), itemSize, itemSize);
            } else if (item.getImage().equals("2")){
                gc.drawImage(item2, item.getX(), item.getY(), itemSize, itemSize);
            } else if (item.getImage().equals("3")){
                gc.drawImage(item3, item.getX(), item.getY(), itemSize, itemSize);
            } else if (item.getImage().equals("4")){
                gc.drawImage(item4, item.getX(), item.getY(), itemSize, itemSize);
            } else if (item.getImage().equals("5")){
                gc.drawImage(item5, item.getX(), item.getY(), itemSize, itemSize);
            } else {
                gc.drawImage(item6, item.getX(), item.getY(), itemSize, itemSize);
            }
        }

        if (shark.getMode() == SharkMode.EAT){
			transformAndDraw(gc, sharkEatImage, shark.getX(), shark.getY(), direction);
		} else {
            transformAndDraw(gc, sharkImage, shark.getX(), shark.getY(), direction);
        }

	}

	/**
	 * Adds golden fish
	 * @param golden golden Fish object
	 * @param updateTick tick used throughout the class
	 * @return void
	 */
    public void addGlow(Fish golden, int updateTick){
        int idx = updateTick%8;
        gc.drawImage(glowImages.get(idx), golden.getX()-(golden.getWidth()/2), golden.getY()-(golden.getHeight()/2), golden.getWidth()*2, golden.getHeight()*2);
    }

		/**
		 * Read image from file and return
		 * @param  image_file String for the location of the image
		 * @return the Image referenced by the image string
		 */
    private Image createImage(String image_file) {
        Image img = new Image(image_file);
        return img;
    }

		/**
		 * sets canvas size
		 * @param  canvas Canvas object
		 * @param canvasWidth int width of canvas
		 * @param canvasHeight int height of canvas
		 * @return void
		 */
    public void setCanvas(Canvas canvas, int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
    }

		/**
		 * canvas width getter
		 * @return canvas width
		 */
	public int getCanvasWidth() {
		return canvasWidth;
	}

	/**
	 * canvas height getter
	 * @return canvas height
	 */
	public int getCanvasHeight() {
		return canvasHeight;
	}

	/**
	 * Image width getter
	 * @return image width
	 */
	public int getImageWidth() {
		return imgWidth;
	}

	/**
	 * Image height getter
	 * @return image height
	 */
	public int getImageHeight() {
		return imgHeight;
	}

	/**
	 * Getter for the direction of the shark's movement
	 * @return Direction enum based on shark's movement
	 */
	public Direction getTheDirection(){
		return direction;
	}

	/**
	 * Getter for mode of the shark
	 * @return SharkMode enum based on state of shark's movement
	 */
	public SharkMode getTheSharkMode(){
		return sharkMode;
	}

	/**
	 * called to determine if the tutorial is over
	 * @return boolean tutOver that determines if tutorial is over
	 */
    public boolean getTutStatus(){
        return this.tutOver;
    }

    // If the bass is facing to the WEST, we must flip it, then rotate accordingly
    // for NORTH/SOUTH
    // Then draw to gc

		/**
		 * Transforms shark image based on movement from player
		 * @param gc GraphicsContext
		 * @param image Image of shark depending on
		 * @param x x location of shark
		 * @param y y location of shark
		 * @param d Direction of shark
		 * @return void
		 */
    private void transformAndDraw(GraphicsContext gc, Image image, double x, double y, Direction d) {
        // clockwise rotation angle
        // Why clockwise? I don't know. It should probably be counter-clockwise
        double theta = 0.0;
        // Switch statemement is good to use for enums
        switch (d) {
            case NORTH: {
                theta = -60.0;
                break;
            } case NORTHEAST: {
                theta = -30.0;
                break;
            } case EAST: {
                theta = 0.0;
                break;
            } case SOUTHEAST: {
                theta = 30.0;
                break;
            } case SOUTH: {
                theta = 60.0;
                break;
            }
        }

        // Rotate the CANVAS and NOT the Image, because rotating the image
        // will crop part of the bass's tail for certain angles

        gc.save(); // saves the current state on stack, including the current transform

        // set canvas rotation about the point (x+imageWidth/2, y+imageWidth/2) by angle theta

        // TODO: switch second argument to imgHeight so that it rotates around center but then fix the hit boxes
        Rotate r = new Rotate(theta, x+imgWidth/2, y+imgHeight/2);
        // Transform gc by the rotation
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        // draw the subimage from the original png to animate the bass's motion
        // The arguments for drawImage are:
        //
        // drawImage(sourceImage, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight)
        //
        // This means that a rectangle of size (sWidth, sHeight) will be CROPPED from sourceImage
        // at location (sX, sY), RESIZED to (dWidth, dHeight), and drawn to the
        // parent of the GraphicsContext at location (dX, dY).
        gc.drawImage(image, 0, 0, imgWidthOrig, imgHeightOrig,
                            x, y, imgWidth, imgHeight);

        gc.restore(); // back to original state (before rotation)
    }

}
