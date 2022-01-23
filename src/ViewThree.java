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
import java.awt.geom.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt.geom.Line2D;
import java.awt.Point;
import javafx.scene.text.TextAlignment;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class ViewThree extends StackPane {
	Canvas canvas;

	int canvasWidth = 1000;
	int canvasHeight = 800;
    double aspectRatio = 2.9223;

    GraphicsContext gc;

    Image water;
    Image water2;
    Image clouds;
    Image fishing1;
    Image fishing2;
    Image sun;
    Image hook;
    Image fishCaught;
    Image fishImage;
    Image fish1;
    Image fish2;
    Image fish3;
    Image fish4;
    Image fish5;
    Image fish1Caught;
    Image fish2Caught;
    Image fish3Caught;
    Image fish4Caught;
    Image fish5Caught;

    double fishXRatio = 580.0/2331.7;
    double fishYRatio = 13.5/798.0;

    ArrayList<Image> glowImages = new ArrayList<Image>(8);
    ArrayList<Image> glowRotated = new ArrayList<Image>(8);

    Point fishermanLoc;
    Point fisherRodLoc;

    ArrayList<ArrayList<Image>> fishImages;

    Text t;
    Text s;
    Text caption;

    // background location x
    int waterLoc = 0;
    int water2Loc = 0;
    int cloudsLoc = 0;

    Line fishingLine;

    int updateTick = 0;
    boolean flag = false;
    boolean tutOver = false;

    Label text1;
    Label text2;

		/**
		 * Constructor for third minigame view which sets up the look of the canvas (score, timer, fisherman location, fish images, instructions)
		 */
	public ViewThree() {

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

        text1 = new Label("Hi there, my name is Brad and I'm a fisherman in this Estuary. Help me catch as many fish as I can before time runs out.");
        text2 = new Label("Use the \"UP\" and \"DOWN\" arrow keys to move the fishing rod and \"SPACEBAR\" to catch the fish.");

        fishermanLoc = new Point(378,100);
        fisherRodLoc = new Point(476,82);

        water = createImage("assets/game_3/water.png");
        water2 = createImage("assets/game_3/water2.png");
        clouds = createImage("assets/game_3/clouds.png");
        fishing1 = createImage("assets/game_3/fishing-1.png");
        fishing2 = createImage("assets/game_3/fishing-2.png");

        hook = createImage("assets/game_3/hook.png");

        fish1 = createImage("/assets/fish/mackerel.gif");
        fish2 = createImage("/assets/fish/marlin.gif");
        fish3 = createImage("/assets/fish/snapper.gif");
        fish4 = createImage("/assets/fish/bass.gif");
        fish5 = createImage("/assets/fish/trout.gif");
        fish1Caught = createImage("/assets/fish/mackerel-caught.png");
        fish2Caught = createImage("/assets/fish/marlin-caught.png");
        fish3Caught = createImage("/assets/fish/snapper-caught.png");
        fish4Caught = createImage("/assets/fish/bass-caught.png");
        fish5Caught = createImage("/assets/fish/trout-caught.png");

        glowImages.add(createImage("/assets/fish/glow-1.png"));
        glowImages.add(createImage("/assets/fish/glow-2.png"));
        glowImages.add(createImage("/assets/fish/glow-3.png"));
        glowImages.add(createImage("/assets/fish/glow-4.png"));
        glowImages.add(createImage("/assets/fish/glow-5.png"));
        glowImages.add(createImage("/assets/fish/glow-4.png"));
        glowImages.add(createImage("/assets/fish/glow-3.png"));
        glowImages.add(createImage("/assets/fish/glow-2.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-1.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-2.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-3.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-4.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-5.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-4.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-3.png"));
        glowRotated.add(createImage("/assets/fish/glow-rotated-2.png"));

        sun = new Image("assets/game_3/sun.png", 400, 400, true, false);
	}


	/**
	 * Tutorial view
	 * @param canvasWidth width of page
	 * @param canvasHeight height of page
	 * @param keys ArrayList of keys
	 * @return	void
	 */
    public void tutorial(int canvasWidth, int canvasHeight, ArrayList<String> keys){
        int tearingShift = 1;
        double imgWidth = (canvasHeight*this.aspectRatio);
        double imgHeight = canvasHeight;
        updateTick++;

        setCanvas(this.canvas, canvasWidth, canvasHeight);

        if (updateTick == 1){
            getChildren().add(text1);
        }
        text1.setMaxWidth((canvasWidth/3)*2);
        text1.setMaxHeight(120);
				text1.setWrapText(true);
				text1.setTextAlignment(TextAlignment.CENTER);
        setAlignment(text1,Pos.BOTTOM_CENTER);
        text1.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px; -fx-border-insets: 10px; -fx-background-insets: 10px;");

        text2.setMaxWidth((canvasWidth/3)*2);
        text2.setMaxHeight(120);
				text2.setWrapText(true);
				text2.setTextAlignment(TextAlignment.CENTER);
        setAlignment(text2,Pos.BOTTOM_CENTER);
        text2.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px; -fx-border-insets: 10px; -fx-background-insets: 10px;");


        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // start draw bg
        gc.drawImage(clouds, cloudsLoc, 0, imgWidth, imgHeight);
        gc.drawImage(clouds, cloudsLoc + imgWidth - tearingShift, 0, imgWidth, imgHeight);
        cloudsLoc = cloudsLoc - 4;
        if (imgWidth + cloudsLoc < 0){
            cloudsLoc = -4;
        }

        gc.drawImage(water, waterLoc, 0, imgWidth, imgHeight);
        gc.drawImage(water, waterLoc + imgWidth - tearingShift, 0, imgWidth, imgHeight);

        waterLoc = waterLoc - 16;
        if (imgWidth + waterLoc < 0){
            waterLoc = -16;
        }

        gc.drawImage(sun, canvasWidth - 200, -200);

        gc.drawImage(fishing1, 0, 0, imgWidth, imgHeight);
        gc.drawImage(hook, (fishXRatio * imgWidth) - 12, (fishYRatio * imgHeight), 15, 23);

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

        gc.drawImage(water2, water2Loc, 0, imgWidth, imgHeight);
        gc.drawImage(water2, water2Loc + imgWidth - tearingShift, 0, imgWidth, imgHeight);

        water2Loc = water2Loc - 10;
        if (imgWidth + water2Loc < 0){
            water2Loc = -10;
        }
    }

		/**
		 * method used to repaint on the image and called in controller
		 * @param canvasWidth width of page
		 * @param canvasHeight height of page
		 * @param line 2d line for the fishing poll
		 * @param didCatch boolean that changes if a fish is caught
		 * @param fishes ArrayList of fish in the minigame
		 * @param score int keeps track of score for minigame
		 * @param time int keeps time and counts down with Timer
		 * @param fishCaught Fish object that identifies the kind of fish caught
		 * @return	void
		 */
	public void update(int canvasWidth, int canvasHeight, Line2D line, boolean didCatch, ArrayList<Fish> fishes, int score, int time, Fish fishCaught) {

        int tearingShift = 1;
        double imgWidth = (canvasHeight*this.aspectRatio);
        double imgHeight = canvasHeight;
        updateTick++;


        setCanvas(this.canvas, canvasWidth, canvasHeight);

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // start draw bg
        gc.drawImage(clouds, cloudsLoc, 0, imgWidth, imgHeight);
        gc.drawImage(clouds, cloudsLoc + imgWidth - tearingShift, 0, imgWidth, imgHeight);
        cloudsLoc = cloudsLoc - 4;
        if (imgWidth + cloudsLoc < 0){
            cloudsLoc = -4;
        }

        gc.drawImage(water, waterLoc, 0, imgWidth, imgHeight);
        gc.drawImage(water, waterLoc + imgWidth - tearingShift, 0, imgWidth, imgHeight);

        waterLoc = waterLoc - 16;
        if (imgWidth + waterLoc < 0){
            waterLoc = -16;
        }

        gc.drawImage(sun, canvasWidth - 200, -200);

        //end draw bg

        s.setText("Score: " + score);
        t.setText("Time: " + time);

        if (didCatch == true){
            gc.drawImage(fishing2, 0, 0, imgWidth, imgHeight);
            if (fishCaught.getImage().equals("1")){
                gc.drawImage(fish1Caught, line.getX2()-(fishCaught.getHeight()/2), line.getY2(), fishCaught.getHeight(), fishCaught.getWidth());
            } else if (fishCaught.getImage().equals("2")){
                gc.drawImage(fish2Caught, line.getX2()-(fishCaught.getHeight()/2), line.getY2(), fishCaught.getHeight(), fishCaught.getWidth());
            } else if (fishCaught.getImage().equals("3")){
                gc.drawImage(fish3Caught, line.getX2()-(fishCaught.getHeight()/2), line.getY2(), fishCaught.getHeight(), fishCaught.getWidth());
            } else if (fishCaught.getImage().equals("4")){
                gc.drawImage(fish4Caught, line.getX2()-(fishCaught.getHeight()/2), line.getY2(), fishCaught.getHeight(), fishCaught.getWidth());
            } else if (fishCaught.getImage().equals("5")){
                gc.drawImage(glowRotated.get((int) (updateTick%16)/2), line.getX2()-(fishCaught.getHeight()), line.getY2()-(fishCaught.getWidth()/2), fishCaught.getHeight()*2, fishCaught.getWidth()*2);
                gc.drawImage(fish5Caught, line.getX2()-(fishCaught.getHeight()/2), line.getY2(), fishCaught.getHeight(), fishCaught.getWidth());
            }


        } else {
            gc.drawImage(fishing1, 0, 0, imgWidth, imgHeight);
            gc.drawImage(hook, line.getX2()-12, line.getY2(), 15, 23);
        }

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());


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

        // gc.drawImage(fishImages.get(0).get(0), 500,500,120,100);

        gc.drawImage(water2, water2Loc, 0, imgWidth, imgHeight);
        gc.drawImage(water2, water2Loc + imgWidth - tearingShift, 0, imgWidth, imgHeight);

        water2Loc = water2Loc - 10;
        if (imgWidth + water2Loc < 0){
            water2Loc = -10;
        }

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
	 * called to determine if the tutorial is over
	 * @return boolean tutOver that determines if tutorial is over
	 */
    public boolean getTutStatus(){
        return this.tutOver;
    }

}
