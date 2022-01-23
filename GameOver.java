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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.geometry.Side;
import java.awt.geom.Line2D;
import java.awt.Point;


public class GameOver extends StackPane {
	Canvas canvas;

    int canvasWidth = 1000;
    int canvasHeight = 800;
    double aspectRatio = 2.9223;

    GraphicsContext gc;

    Image background;

    boolean playAgain = false;
    Button b;
    Button enter;
    Label text;

    TextField initials;
    Text scoreSubmit;

    int totalScore;

	//ViewThree constructor initialize the starting position for the image
	//Called in controller
	public GameOver() {

        canvas = new Canvas(canvasWidth, canvasHeight);
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        Image image = new Image("assets/button-fish.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);

        b = new Button("    Play Again!", imageView);
        setAlignment(b, Pos.BOTTOM_CENTER);
        setMargin(b, new Insets(0,0,75,0));
        getChildren().add(b);

        scoreSubmit = new Text("");
        scoreSubmit.setFont(Font.font("Times New Roman",50));
        scoreSubmit.setFill(Color.WHITE);

        b.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                playAgain = true;
             }
         });

        text = new Label("YOUR SCORE: 0");
        setAlignment(text, Pos.TOP_CENTER);
        setMargin(text, new Insets(75,0,0,0));
        getChildren().add(text);

        int textLength = 4;
        initials = new TextField();
        // here we reject any change which exceeds the length
        UnaryOperator<TextFormatter.Change> rejectChange = c -> {
            // check if the change might effect the validating predicate
            if (c.isContentChange()) {
                // check if change is valid
                if (c.getControlNewText().length() > textLength) {
                    // invalid change
                    // sugar: show a context menu with error message
                    final ContextMenu menu = new ContextMenu();
                    menu.getItems().add(new MenuItem("This field takes\n"+textLength+" characters only."));
                    menu.show(c.getControl(), Side.BOTTOM, 0, 0);
                    // return null to reject the change
                    return null;
                }
            }
            // valid change: accept the change by returning it
            return c;
        };
        initials.setTextFormatter(new TextFormatter(rejectChange));

        initials.setPromptText("Enter your initials");
        setAlignment(initials, Pos.CENTER_LEFT);
        getChildren().add(initials);
        initials.setFont(Font.font("Times New Roman",50));

        enter = new Button("SUBMIT");
        setAlignment(enter, Pos.CENTER_RIGHT);
        //setMargin(enter, new Insets(0,0,75,0));
        getChildren().add(enter);

	    background = createImage("assets/start-screen.png");

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
		 * Updates/calculates final scores and displays them on final screen
		 * @param canvasWidth width of page
		 * @param canvasHeight height of page
		 * @param scoreOne int score from first minigame
		 * @param scoreTwo int score from second minigame
		 * @param scoreThree int score from third minigame
		 * @return void
		 */
	public void update(int canvasWidth, int canvasHeight, int scoreOne, int scoreTwo, int scoreThree) { // Line2D line


        int tearingShift = 1;
        double imgWidth = (canvasHeight*this.aspectRatio);
        double imgHeight = canvasHeight;
        totalScore = scoreOne + scoreTwo + scoreThree;

        setCanvas(this.canvas, canvasWidth, canvasHeight);

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw bg
        gc.drawImage(background, 0, 0, imgWidth, imgHeight);

        enter.setStyle("-fx-border-color: #ffffff; -fx-border-width: 2px; -fx-background-color: #6CC5D7; -fx-font-size: 40; -fx-text-fill: #ffffff;");
        enter.setPrefSize(canvasWidth/2, 100);
        initials.setPrefSize(canvasWidth/2, 100);
        initials.setMaxWidth(canvasWidth/2);
        setMargin(enter, new Insets(0,20,0,(canvasWidth/2)+10));
        setMargin(initials, new Insets(0,(canvasWidth/2)+10,0,20));

        b.setStyle("-fx-border-color: #ffffff; -fx-border-width: 2px; -fx-background-color: #6CC5D7; -fx-font-size: 40; -fx-text-fill: #ffffff;");
        b.setPrefSize(canvasWidth/2, 120);
        b.setMaxWidth(canvasWidth*.8);

        text.setMaxWidth(canvasWidth/3);
        //text.setMaxHeight(120);
        text.setText("Scuba Chad: " + scoreOne + "\n" + "Jimmy the Shark: " + scoreTwo + "\n" + "Fisherman Brad: " + scoreThree + "\n" + "TOTAL SCORE: " + totalScore);
        text.setStyle("-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 25; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px;");


        String ts = String.valueOf(totalScore);

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Name of file to open
                String fileName = "HighScores.csv";
                try {
                    // Assume default encoding.
                    FileWriter fileWriter =
                        new FileWriter(fileName, true);

                    // Always wrap FileWriter in BufferedWriter.
                    BufferedWriter bufferedWriter =
                        new BufferedWriter(fileWriter);

                    // Note that write() does not automatically
                    // append a newline character.
                    bufferedWriter.write(initials.getText());
                    bufferedWriter.write(',');
                    bufferedWriter.write(ts);
                    bufferedWriter.write('\n');
                    // Always close files.
                    bufferedWriter.close();

                    scoreSubmit.setText(initials.getText() + ": " + ts);
                    getChildren().remove(enter);
                    getChildren().remove(initials);
                    getChildren().add(scoreSubmit);
                }
                catch(IOException ex) {
                        System.out.println(
                            "Error writing to file '"
                            + fileName + "'");
                        // Or we could just do this:
                        // ex.printStackTrace();
                    }

                }
            });

	}

	   /**
	    * Sets canvas dimensions
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
		 * Canvas width getter
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
		 * Determines if the game starts over 
		 * @return boolean playAgain to start game over again
		 */
    public boolean getPlay(){
      return this.playAgain;
    }

}
