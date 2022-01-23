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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import java.awt.geom.Line2D;
import java.awt.Point;
import javafx.collections.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;


public class ViewStart extends StackPane {
	Canvas canvas;

    int canvasWidth = 1000;
	int canvasHeight = 800;
    double aspectRatio = 2.9223;

    GraphicsContext gc;

    Image background;

	boolean startGame;
    Button b;
    Button exit;

    //ArrayList<HS> scoreboard = new ArrayList<HS>();

    TableView<HS> table = new TableView<>();

		/**
		 * Constructor for start screen view which sets up the look of the canvas and the different images and button handlings
		 */
	public ViewStart() {
        startGame = false;

        canvas = new Canvas(canvasWidth, canvasHeight);
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        Image image = new Image("assets/button-fish.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);

        b = new Button("    Start Game", imageView);
        getChildren().add(b);

        b.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                startGame = true;
             }
         });

		background = createImage("assets/start-screen.png");

       //System.out.print(scoreboard + " ");
        //Name Column
        TableColumn<HS, String> nameCol = new TableColumn<>("Player's Initials");
        nameCol.setMinWidth(50);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        //score Column
        TableColumn<HS, Integer> scoreCol = new TableColumn<>("Current Scores");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
        scoreCol.setMinWidth(50);

        table.setItems(getScoreBoard());
        table.getColumns().addAll(nameCol, scoreCol);
        getChildren().add(table);
        setAlignment(table, Pos.TOP_CENTER);

        exit = new Button("exit");
        exit.setPrefSize(canvasWidth/2, 25);
        setAlignment(exit, Pos.TOP_RIGHT);
        getChildren().add(exit);

        exit.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               getChildren().remove(table);
               getChildren().remove(exit);
             }
         });
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
		 * Update method used to repaint on the image and called in controller
		 * @param canvasWidth width of page
		 * @param canvasHeight height of page
		 * @return	void
		 */
	public void update(int canvasWidth, int canvasHeight) { // Line2D line


        int tearingShift = 1;
        double imgWidth = (canvasHeight*this.aspectRatio);
        double imgHeight = canvasHeight;

        b.setStyle("-fx-border-color: #ffffff; -fx-border-width: 2px; -fx-background-color: #6CC5D7; -fx-font-size: 40; -fx-text-fill: #ffffff;");
        b.setPrefSize(canvasWidth/2, 120);

        setCanvas(this.canvas, canvasWidth, canvasHeight);

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw bg
        gc.drawImage(background, 0, 0, imgWidth, imgHeight);

        // setMargin(table, new Insets(0,0,0,(canvasWidth/4)));

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
	 * called to determine if the game should start or not
	 * @return boolean startGame that tells if button start has been pressed
	 */
	public boolean getStart() {
		return this.startGame;
	}

	/**
	 * Shows List of highest scores thus far
	 * @return ObservableList of high scores so far 
	 */
    public ObservableList<HS> getScoreBoard(){
        ObservableList<HS> scoreboard = FXCollections.observableArrayList();
        String fileName = "HighScores.csv";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                HS temp = new HS(data[0], Integer.parseInt(data[1]));
                scoreboard.add(temp);
            }
            csvReader.close();
        } catch(FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.print(scoreboard + " ");
        return scoreboard;
    }
}
