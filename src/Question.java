
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
import javafx.geometry.Insets;
import javafx.scene.control.Label;



import java.awt.geom.Line2D;
import java.awt.Point;



public class Question extends StackPane {
    Canvas canvas;
    int canvasWidth = 1000;
    int canvasHeight = 800;
    double aspectRatio = 2.9223;

    GraphicsContext gc;

    Image background;
    Image multiplier;
    ImageView mult;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Label question;
    Label response;
    Text title;
    Timer timer = new Timer(3);

    ArrayList<String> questions1 = new ArrayList<String>();
    ArrayList<String> questions2 = new ArrayList<String>();
    ArrayList<String> questions3 = new ArrayList<String>();
    ArrayList<ArrayList<String>> choices1 = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> choices2 = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> choices3 = new ArrayList<ArrayList<String>>();
    ArrayList<String> answers1 = new ArrayList<String>();
    ArrayList<String> answers2 = new ArrayList<String>();
    ArrayList<String> answers3 = new ArrayList<String>();

    ArrayList<String> questions;
    ArrayList<ArrayList<String>> choices;
    ArrayList<String> answers;

    ArrayList<Button> buttons = new ArrayList<Button>();

    boolean done = false;
    boolean correct = false;

    int multiplierWidth;
    double multiplierAspect = 4.6485;

    String buttonStyle = "-fx-border-color: #ffffff; -fx-border-width: 2px; -fx-background-color: #6CC5D7; -fx-font-size: 30; -fx-text-fill: #ffffff;";
    String labelStyle = "-fx-border-color: #000000; -fx-border-width: 3px; -fx-background-color: #ffffff; -fx-font-size: 20; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-width: canvasWidth; -fx-height: 120;-fx-padding:10px;-fx-border-insets: 10px; -fx-background-insets: 10px;";


    /**
     * Question constructor generates different questions and answers based on the minigame number
     * @param modelNum int that is the minigame number
     */
  public Question(int modelNum) {

        canvas = new Canvas(canvasWidth, canvasHeight);
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        ArrayList<String> q1o1 = new ArrayList<String>();
        ArrayList<String> q1o2 = new ArrayList<String>();
        ArrayList<String> q1o3 = new ArrayList<String>();
        ArrayList<String> q1o4 = new ArrayList<String>();
        ArrayList<String> q1o5 = new ArrayList<String>();
        ArrayList<String> q2o1 = new ArrayList<String>();
        ArrayList<String> q2o2 = new ArrayList<String>();
        ArrayList<String> q2o3 = new ArrayList<String>();
        ArrayList<String> q2o4 = new ArrayList<String>();
        ArrayList<String> q2o5 = new ArrayList<String>();
        ArrayList<String> q3o1 = new ArrayList<String>();
        ArrayList<String> q3o2 = new ArrayList<String>();
        ArrayList<String> q3o3 = new ArrayList<String>();
        ArrayList<String> q3o4 = new ArrayList<String>();
        ArrayList<String> q3o5 = new ArrayList<String>();

        // Mini Game 1
        //Questions
        questions1.add("Which item is recyclable?");
        questions1.add("Which item belongs in the trash?");
        questions1.add("Estuaries are important?");
        questions1.add("What color was the recycling bin?");
        questions1.add("Estuaries are...");
        //Choices
        q1o1.add("Fish");
        q1o1.add("Banana Peel");
        q1o1.add("Plastic Water Bottle");
        q1o1.add("An Apple");
        choices1.add(q1o1);

        q1o2.add("Fishing Net");
        q1o2.add("Plastic Bag");
        q1o2.add("Gold Fish");
        q1o2.add("Salmon");
        choices1.add(q1o2);

        q1o3.add("Most Definitely");
        q1o3.add("Not at all");
        choices1.add(q1o3);

        q1o4.add("Green");
        q1o4.add("Blue");
        q1o4.add("Red");
        q1o4.add("Grey");
        choices1.add(q1o4);

        q1o5.add("bad for the Environment ");
        q1o5.add("not important");
        q1o5.add("interconnected with the world oceans");
        q1o5.add("None of the above");
        choices1.add(q1o5);
        //Answers
        answers1.add("Plastic Water Bottle");
        answers1.add("Fishing Net");
        answers1.add("Most Definitely");
        answers1.add("Blue");
        answers1.add("interconnected with the world oceans");

        // Mini Game 2
        //Questions
        questions2.add("Can pollution be harmful to wildlife?");
        questions2.add("Which of the following cant you find in an estuary?");
        questions2.add("How important are fish to sharks?");
        questions2.add("What can happen when people litter?");
        questions2.add("Why is there a lot of trash in the estuary?");
        //Choices
        q2o1.add("Yes, Sadly");
        q2o1.add("Not At All");
        choices2.add(q2o1);

        q2o2.add("Shark");
        q2o2.add("Lion");
        q2o2.add("Crab");
        q2o2.add("Bird");
        choices2.add(q2o2);

        q2o3.add("Not at all");
        q2o3.add("Meh, they'll live");
        q2o3.add("Sharks need fish to survive");
        q2o3.add("Sharks are allergic to fish");
        choices2.add(q2o3);

        q2o4.add("Harm wildlife");
        q2o4.add("Affect ecosystems");
        q2o4.add("Destroy habitats");
        q2o4.add("All of the above");
        choices2.add(q2o4);

        q2o5.add("It falls from the sky");
        q2o5.add("Nature causes");
        q2o5.add("Humans litter");
        q2o5.add("All of the above");
        choices2.add(q2o5);
        //Answers
        answers2.add("Yes, Sadly");
        answers2.add("Lion");
        answers2.add("Sharks need fish to survive");
        answers2.add("All of the above");
        answers2.add("Humans litter");

        // Mini Game 3
        //Questions
        questions3.add("What type of water is in estuaries?");
        questions3.add("What type of fish was not seen in this estuary?");
        questions3.add("How many different fish did you see?");
        questions3.add("How can humans impact estuary life?");
        questions3.add("Estuaries support an abundance of life?");
        //Choices
        //Choices
        q3o1.add("Salt Water");
        q3o1.add("Fresh Water");
        q3o1.add("A mixture of fresh and salt water");
        choices3.add(q3o1);

        q3o2.add("Rainbow Trout");
        q3o2.add("Snapper");
        q3o2.add("Blue Marlin");
        q3o2.add("Blue Whale");
        choices3.add(q3o2);

        q3o3.add("1-5");
        q3o3.add("5-10");
        q3o3.add("10-15");
        q3o3.add("15-20");
        choices3.add(q3o3);

        q3o4.add("Cleaning the water by picking up trash");
        q3o4.add("Hurting wildlife by littering");
        q3o4.add("Fishing");
        q3o4.add("All of the above");
        choices3.add(q3o4);

        q3o5.add("True");
        q3o5.add("False");
        choices3.add(q3o5);
        //Answers
        answers3.add("A mixture of fresh and salt water");
        answers3.add("Blue Whale");
        answers3.add("5-10");
        answers3.add("All of the above");
        answers3.add("True");

        switch (modelNum){
            case 1: {
                questions = questions1;
                choices = choices1;
                answers = answers1;
                break;
            } case 2: {
                questions = questions2;
                choices = choices2;
                answers = answers2;
                break;
            } case 3: {
                questions = questions3;
                choices = choices3;
                answers = answers3;
                break;
            } default: {
                // set to first but set done to true so page move forward
                questions = questions1;
                choices = choices1;
                answers = answers1;
                done = true;
            }
        }

        int rand = randInt(0,4);

        question = new Label(questions.get(rand));
        setAlignment(question, Pos.BOTTOM_CENTER);
        setMargin(question, new Insets(0,0,(canvasHeight/10)*8.5,0));
        response = new Label();
        response.setStyle(labelStyle);

        multiplier = createImage("assets/multiplier.png");
        mult = new ImageView();
        mult.setImage(multiplier);
        mult.setPreserveRatio(true);
        setAlignment(mult, Pos.TOP_CENTER);
        setMargin(mult, new Insets(20,0,0,0));

        getChildren().addAll(mult, question);

        for (String option : choices.get(rand)){
            Button b = new Button(option);
            setAlignment(b, Pos.TOP_CENTER);
            b.setStyle(buttonStyle);

            if (option.equals(answers.get(rand))){
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Correct answers
                        System.out.println("Correct");
                        correct = true;
                        removeChildren();
                        response.setText("Correct your score for this game has been multiplied!");
                        getChildren().add(response);
                        timer.start();
                    }
                });
            } else {
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //incorrect answer
                        System.out.println("Wrong");
                        removeChildren();
                        response.setText("Better luck next time!\n\n" + "The correct answer was: " + answers.get(rand));
                        getChildren().add(response);
                        timer.start();
                    }
                });
            }

            buttons.add(b);
            getChildren().add(b);
        }

        background = createImage("assets/background.png");

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
     * Update function for question page which repaints the canvas and changes view based on question
  	 * @param canvasWidth int width of page
  	 * @param canvasHeight int height of page
  	 * @return void
  	 */
	public void update(int canvasWidth, int canvasHeight) { // Line2D line


        int tearingShift = 1;
        double imgWidth = (canvasHeight*this.aspectRatio);
        double imgHeight = canvasHeight;

        mult.setFitWidth(canvasWidth/3);

        setCanvas(this.canvas, canvasWidth, canvasHeight);

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw bg
        gc.drawImage(background, 0, 0, imgWidth, imgHeight);

        double dis = 3.5;
        for (Button button : buttons) {
            setMargin(button,new Insets((canvasHeight/10)*dis,0,0,0));
            dis = dis + 1.5;
        }

        question.setPrefSize(canvasWidth/2, 100);
        question.setMaxWidth(canvasWidth*.8);
        question.setWrapText(true);
        question.setStyle(labelStyle);

        response.setMaxWidth(canvasWidth/3);
        response.setWrapText(true);
        response.setStyle(labelStyle);
        setMargin(question, new Insets(0,0,(canvasHeight/10)*7,0));

        if(timer.getTime() == 0){
            this.done = true;
        }

	}

  /**
   * Function removes buttons from page
   * @return void
   */
    public void removeChildren() {
        this.getChildren().remove(question);
        for (Button button : this.buttons){
            this.getChildren().remove(button);
        }
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
     * Sets canvas width's and heights
     * @param canvas Canvas object
     * @param canvasWidth int that is width of canvas
     * @param canvasHeight int that is height of canvas
     * @return void
     */
    public void setCanvas(Canvas canvas, int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
    }

    /**
     * Getter for canvas width
     * @return int that is canvasWidth
     */
  public int getCanvasWidth() {
		return canvasWidth;
	}

  /**
   * Getter for canvas height
   * @return int that is canvasHeight
   */
	public int getCanvasHeight() {
		return canvasHeight;
	}

  /**
   * Determines when quiz is over because of done boolean
   * @return boolean that tells if quiz is over or not
   */
	public boolean getDone() {
		return this.done;
	}

  /**
   * Determines whether question was answered correctly or not
   * @return boolean that tells if question was answered correctly
   */
  public boolean getResult() {
		return this.correct;
	}

}
