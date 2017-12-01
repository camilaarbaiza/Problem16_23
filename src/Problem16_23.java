/*
Camila Arbaiza

Take home test

Problem 16.23

Professor: Tanes Kanchanawanchai

 */



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Problem16_23 extends Application {

    protected Timeline animation;
    protected TextField speed = new TextField();
    protected TextField prefix = new TextField();
    protected TextField numOfImages = new TextField();
    protected TextField URL = new TextField();
    protected StackPane paneForImage = new StackPane();
    protected int n = 1;


    @Override
    public void start(Stage primaryStage) throws Exception {


        final int COLUMN_NUM = 27;
        speed.setPrefColumnCount(COLUMN_NUM);
        prefix.setPrefColumnCount(COLUMN_NUM);
        numOfImages.setPrefColumnCount(COLUMN_NUM);
        URL.setPrefColumnCount(COLUMN_NUM);

        // Create a button
        Button startButton = new Button("Start Animation");

        // Creating a grid pane for labels and text fields
        GridPane paneForInfo = new GridPane();
        paneForInfo.setAlignment(Pos.CENTER);
        paneForInfo.add(new Label("Enter information for animation"), 0, 0);
        paneForInfo.add(new Label("Animation speed in milliseconds"), 0, 1);
        paneForInfo.add(speed, 1, 1);
        paneForInfo.add(new Label("Image file prefix"), 0, 2);
        paneForInfo.add(prefix, 1, 2);
        paneForInfo.add(new Label("Number of images"), 0, 3);
        paneForInfo.add(numOfImages, 1, 3);
        paneForInfo.add(new Label("Audio file URL"), 0, 4);
        paneForInfo.add(URL, 1, 4);


        // Creating a border pane
        BorderPane pane = new BorderPane();
        pane.setBottom(paneForInfo);
        pane.setCenter(paneForImage);
        pane.setTop(startButton);
        pane.setAlignment(startButton, Pos.TOP_RIGHT);

        // Creating animation
        animation = new Timeline(
                new KeyFrame(Duration.millis(1000), e -> nextImage()));
        animation.setCycleCount(Timeline.INDEFINITE);

        // Creating and registering the handler
        startButton.setOnAction(e -> {
            if (URL.getText().length() > 0) {
                MediaPlayer mediaPlayer = new MediaPlayer(
                        new Media(URL.getText()));
                mediaPlayer.play();
            }
            if (speed.getText().length() > 0)
                animation.setRate(Integer.parseInt(speed.getText()));
            animation.play();
        });


        Scene scene = new Scene(pane, 600, 680);
        primaryStage.setTitle("Problem16_23"); // Set stage title
        primaryStage.setScene(scene);          // Place the scene in the stage
        primaryStage.show();                   // Display the stage
    }


    private void getImage() {

        paneForImage.getChildren().clear();

        paneForImage.getChildren().add(new ImageView(new Image("https://i0.wp.com/yourspot.net/wp-content/uploads/2016/06/java.png?fit=250%2C250" + prefix.getText() + n + ".gif")));
    }

    private void nextImage() {
        n = n < Integer.parseInt(numOfImages.getText()) ? n += 1 : 1;
        getImage();

    }
}
