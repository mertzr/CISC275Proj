import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Remove when views are removed from same file
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Controller extends Application {
    double canvasHeight;
    double canvasWidth;
    ViewStart viewStart;
    ViewOne viewOne;
    ViewTwo viewTwo;
    ViewThree viewThree;
    Question question1;
    Question question2;
    Question question3;
    GameOver gameOver;
    ModelOne modelOne;
    ModelTwo modelTwo;
    ModelThree modelThree;

    int scoreOne = 0;
    int scoreTwo = 0;
    int scoreThree = 0;

    double minWidth=736.0;
    double minHeight=545.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final StackPane display = new StackPane();
        restart();

        display.getChildren().add(viewStart); //Load view

        VBox root = new VBox(display);
        Scene scene = new Scene(root);

        KeyController keyController = new KeyController(scene);

        primaryStage.setTitle("Estuary Game");
        primaryStage.setFullScreen(true);
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.setScene(scene);

        new AnimationTimer() {

            @Override
            public void handle(long currentNanoTime){
                canvasHeight = primaryStage.getHeight();
                canvasWidth = primaryStage.getWidth();

                // buttons 1, 2, and 3 toggle between the 3 minigames
                if (keyController.getKeys().contains("1")){
                    if(!display.getChildren().get(0).equals(viewOne)) { //If sceneOne is not loaded, load it.
                        display.getChildren().set(0, viewOne);
                    }
                } else if (keyController.getKeys().contains("2")){
                    if(!display.getChildren().get(0).equals(viewTwo)) { //If sceneTwo is not loaded, load it.
                        display.getChildren().set(0, viewTwo);
                    }
                } else if (keyController.getKeys().contains("3")){
                    if(!display.getChildren().get(0).equals(viewThree)) { //If sceneThree is not loaded, load it.
                        display.getChildren().set(0, viewThree);
                    }
                }

                if (display.getChildren().get(0).equals(viewStart)) {
                    //update model
                    if (viewStart.getStart() == false) {
                        viewStart.update((int) canvasWidth, (int) canvasHeight);
                    } else {
                        display.getChildren().set(0, viewOne);
                    }
                } else if (display.getChildren().get(0).equals(viewOne)) {
                    if (modelOne.gameOver() == false) {
                        if (viewOne.getTutStatus() == false){
                            viewOne.tutorial((int) canvasWidth, (int) canvasHeight, keyController.getKeys());
                        } else {
                            modelOne.update((int) canvasWidth, (int) canvasHeight, currentNanoTime, keyController.getKeys());
                            viewOne.update((int) canvasWidth, (int) canvasHeight, modelOne.getDiver(), modelOne.getDiverMode(), modelOne.getItems(), modelOne.getTime(), modelOne.getScore());
                        }
                    } else {
                        scoreOne = modelOne.getScore();
                        if (modelOne.getQuestion() == false){
                            display.getChildren().set(0, viewTwo);
                        } else {
                            display.getChildren().set(0, question1);
                        }
                    }
                } else if (display.getChildren().get(0).equals(question1)) {
                    if (question1.getDone() == false) {
                        question1.update((int) canvasWidth, (int) canvasHeight);
                    } else {
                        if (question1.getResult() == true) {
                            scoreOne = scoreOne * 2;
                        }
                        display.getChildren().set(0, viewTwo);
                    }
                } else if (display.getChildren().get(0).equals(viewTwo)) {
                    if (modelTwo.gameOver() == false) {
                        if (viewTwo.getTutStatus() == false){
                            viewTwo.tutorial((int) canvasWidth, (int) canvasHeight, keyController.getKeys());
                        } else {
                            modelTwo.update((int) canvasWidth, (int) canvasHeight, keyController.getKeys());
                            viewTwo.update((int) canvasWidth, (int) canvasHeight, modelTwo.getDirection(), modelTwo.getTime(), modelTwo.getScore(), modelTwo.getShark(), modelTwo.getFish(), modelTwo.getItems());
                        }
                    } else {
                        scoreTwo = modelTwo.getScore();
                        if (modelTwo.getQuestion() == false){
                            display.getChildren().set(0, viewThree);
                        } else {
                            display.getChildren().set(0, question2);
                        }
                    }
                } else if (display.getChildren().get(0).equals(question2)) {
                    if (question2.getDone() == false) {
                        question2.update((int) canvasWidth, (int) canvasHeight);
                    } else {
                        if (question2.getResult() == true) {
                            scoreTwo = scoreTwo * 2;
                        }
                        display.getChildren().set(0, viewThree);
                    }
                } else if (display.getChildren().get(0).equals(viewThree)) {
                    if (modelThree.gameOver() == false) {
                        if (viewThree.getTutStatus() == false){
                            viewThree.tutorial((int) canvasWidth, (int) canvasHeight, keyController.getKeys());
                        } else {
                            modelThree.update((int) canvasWidth, (int) canvasHeight, currentNanoTime, keyController.getKeys());
                            viewThree.update((int) canvasWidth, (int) canvasHeight, modelThree.getLine(), modelThree.getCatch(), modelThree.getFish(), modelThree.getScore(), modelThree.getTime(), modelThree.getFishCaught());
                        }
                    } else {
                        scoreThree = modelThree.getScore();
                        if (modelThree.getQuestion() == false){
                            display.getChildren().set(0, gameOver);
                        } else {
                            display.getChildren().set(0, question3);
                        }
                    }
                } else if (display.getChildren().get(0).equals(question3)) {
                    if (question3.getDone() == false) {
                        question3.update((int) canvasWidth, (int) canvasHeight);
                    } else {
                        if (question3.getResult() == true) {
                            scoreThree = scoreThree * 2;
                        }
                        display.getChildren().set(0, gameOver);
                    }
                } else if (display.getChildren().get(0).equals(gameOver)) {
                    if (gameOver.getPlay() == false) {
                        gameOver.update((int) canvasWidth, (int) canvasHeight, scoreOne, scoreTwo, scoreThree);
                    } else {
                        restart();
                        display.getChildren().set(0, viewStart);
                    }
                }

                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        primaryStage.show();
    }

    public void restart() {
        this.viewStart = new ViewStart();
        this.viewOne = new ViewOne();
        this.viewTwo = new ViewTwo();
        this.viewThree = new ViewThree();
        this.question1 = new Question(1);
        this.question2 = new Question(2);
        this.question3 = new Question(3);
        this.gameOver = new GameOver();
        this.modelOne = new ModelOne();
        this.modelTwo = new ModelTwo(viewTwo.getImageWidth(), viewTwo.getImageHeight());
        this.modelThree = new ModelThree();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
