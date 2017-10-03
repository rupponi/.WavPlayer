import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.swing.*;


public class Main extends Application{

    MP3Controller controller = new MP3Controller();
    static TextArea timer = new TextArea("");

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage musicStage) {
        musicStage.setTitle(".WavPlayer Music Player");
        timer.setMaxHeight(20);
        timer.setMaxWidth(200);

        //Play Button Creation
        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent playAction) {
                controller.play();
           }
        });

        //Stop Button Creation
        Button stopButton = new Button();
        stopButton.setText("Exit");
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent exitAction) {
                System.exit(0);
            }
        });


        HBox midSection = new HBox();
        midSection.setPadding(new Insets(15,12,15,12));
        midSection.setSpacing(20);
        midSection.setStyle("-fx-background-color: #336699;");
        midSection.setAlignment(Pos.BASELINE_CENTER);
        midSection.setMaxHeight(50);

        BorderPane playerPane = new BorderPane();

        midSection.getChildren().addAll(playButton,timer,stopButton);
        playerPane.setTop(midSection);

        musicStage.setScene(new Scene(playerPane,1280,720));
        musicStage.show();
    }

}
