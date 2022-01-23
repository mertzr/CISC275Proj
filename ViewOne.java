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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import java.awt.geom.Rectangle2D;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

public class ViewOne extends StackPane {
	Canvas canvas;

	int canvasWidth = 1000;
	int canvasHeight = 800;
    double aspectRatio = 2.9223;

    GraphicsContext gc;

    Text t;
    Text s;

    Image background;

    Image swimRight;
    Image carryRight;
    Image carryLeft;
    Image swimLeft;
    Image diverImage;

    Image trashBin;
    Image recycleBin;

    Image trash1;
    Image trash2;
    Image trash3;
    Image recycle1;
    Image recycle2;
    Image gold;

    Label text1;
    Label text2;

    int diverSize = 400;
    int binSize = 300;
    int itemSize = 50; //itemSize

    int updateTick = 0;
    boolean flag = false;
    boolean tutOver = false;

		/**
		 * Constructor for first minigame view which sets up the look of the canvas (score, timer, instructions, diver images, trash images)
		 */
	public ViewOne() {

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

        background = createImage("assets/background.png");

        swimRight = createImage("assets/game_1/swim-right.gif");
        carryRight = createImage("assets/game_1/carry-right.gif");
        carryLeft = createImage("assets/game_1/carry-left.gif");
        swimLeft = createImage("assets/game_1/swim-left.gif");
        diverImage = createImage("assets/game_1/diver.png");

        trashBin = createImage("assets/game_1/trash-bin.png");
        recycleBin = createImage("assets/game_1/recycle-bin.png");

        gold = createImage("assets/game_1/items/gold.png");
        trash1 = createImage("assets/game_1/items/trash-1.png");
        trash2 = createImage("assets/game_1/items/trash-2.png");
        trash3 = createImage("assets/game_1/items/trash-3.png");
        recycle1 = createImage("assets/game_1/items/recycle-1.png");
        recycle2 = createImage("assets/game_1/items/recycle-2.png");

        text1 = new Label("Hey I'm Chad and I enjoy scuba diving in this estuary. Lately, I've noticed a lot of trash in the water. Help me pick up as much trash as possible before the timer hits zero. Press the spacebar to continue.");
        text2 = new Label("Use the arrow keys to move me toward the trash. Press and hold the spacebar to grab the trash and then move it to the correct recycle or trash bin. Press the spacebar when you're ready to play! ");

	}

	/**
	 * Tutorial view that sets up canvas during tutorial and moves along as user presses spacebar
	 * @param canvasWidth width of page
	 * @param canvasHeight height of page
	 * @param keys ArrayList of keys for key presses
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

        diverSize = (int) canvasWidth/4;
        binSize = (int) canvasWidth/6;

        text1.setMaxWidth((canvasWidth/3)*2);
        text1.setMaxHeight(160);
        text1.setWrapText(true);
        text1.setTextAlignment(TextAlignment.CENTER);
        setAlignment(text1,Pos.BOTTOM_CENTER);
        text1.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px; -fx-border-insets: 10px; -fx-background-insets: 10px;");

        text2.setMaxWidth((canvasWidth/3)*2);
        text2.setMaxHeight(160);
        text2.setWrapText(true);
        text2.setTextAlignment(TextAlignment.CENTER);
        setAlignment(text2,Pos.BOTTOM_CENTER);
        text2.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px; -fx-border-insets: 10px; -fx-background-insets: 10px;");

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw bg
        gc.drawImage(background, 0, 0, imgWidth, imgHeight);
        gc.drawImage(trashBin, 0 , canvasHeight-binSize, binSize, binSize);
        gc.drawImage(recycleBin, canvasWidth-binSize , canvasHeight-binSize, binSize, binSize);

        gc.drawImage(diverImage, (canvasWidth/2) - (diverSize/2), (canvasHeight/2) - (diverSize/2), diverSize, diverSize);

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
		 * Update method used to repaint on the image and called in controller, deals with placing garbage in correct bin
		 * @param canvasWidth width of page
		 * @param canvasHeight height of page
		 * @param diver Diver object
		 * @param diverMode mode of diver's movement
		 * @param items ArrayList of garbage items in the minigame
		 * @param score int keeps track of score for minigame
		 * @param time int keeps time and counts down with Timer
		 * @return	void
		 */
	public void update(int canvasWidth, int canvasHeight, Diver diver, DiverMode diverMode, ArrayList<Item> items, int time, int score) {

        int tearingShift = 1;
        double imgWidth = (canvasHeight*this.aspectRatio);
        double imgHeight = canvasHeight;

        setCanvas(this.canvas, canvasWidth, canvasHeight);

        diverSize = (int) canvasWidth/4;
        binSize = (int) canvasWidth/6;


        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw bg
        gc.drawImage(background, 0, 0, imgWidth, imgHeight);
        gc.drawImage(trashBin, 0 , canvasHeight-binSize, binSize, binSize);
        gc.drawImage(recycleBin, canvasWidth-binSize , canvasHeight-binSize, binSize, binSize);

        t.setText("Time: " + time);
        s.setText("Score: " + score);

        for (Item item : items){
            if (item.getName().equals("gold")){
                gc.drawImage(gold, item.getX(), item.getY(), itemSize, itemSize);
            } else if (item.getName().equals("trash")){
                if (item.getImage().equals("1")){
                    gc.drawImage(trash1, item.getX(), item.getY(), itemSize, itemSize);
                } else if (item.getImage().equals("2")){
                    gc.drawImage(trash2, item.getX(), item.getY(), itemSize, itemSize);
                } else {
                    gc.drawImage(trash3, item.getX(), item.getY(), itemSize, itemSize);
                }
            } else {
                if (item.getImage().equals("1")){
                    gc.drawImage(recycle1, item.getX(), item.getY(), itemSize, itemSize);
                } else {
                    gc.drawImage(recycle2, item.getX(), item.getY(), itemSize, itemSize);
                }
            }
        }

        switch(diverMode){
            case FLOAT: {
                gc.drawImage(diverImage, diver.getX(), diver.getY(), diverSize, diverSize);
                break;
            }
            case SWIMRIGHT: {
                gc.drawImage(swimRight, diver.getX(), diver.getY(), diverSize, diverSize);
                break;
            }
            case SWIMLEFT: {
                gc.drawImage(swimLeft, diver.getX(), diver.getY(), diverSize, diverSize);
                break;
            }
            case CARRYRIGHT: {
                gc.drawImage(carryRight, diver.getX(), diver.getY(), diverSize, diverSize);
                break;
            }
            case CARRYLEFT: {
                gc.drawImage(carryLeft, diver.getX(), diver.getY(), diverSize, diverSize);
                break;
            }
            default: {
                gc.drawImage(diverImage, diver.getX(), diver.getY(), diverSize, diverSize);
                break;
            }
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
		 * sets canvas size
		 * @param canvas Canvas object
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
		 * called to determine if the tutorial is over
		 * @return boolean tutOver that determines if tutorial is over
		 */
    public boolean getTutStatus(){
        return this.tutOver;
    }

}
