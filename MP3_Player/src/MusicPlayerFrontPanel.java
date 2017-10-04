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


public class MusicPlayerFrontPanel extends Application{

    MP3Controller controller = new MP3Controller();

    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage musicStage) {
        musicStage.setTitle(".WavPlayer Music Player");

        HBox container = new HBox();

        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent playClicked) {
                controller.play();
            }
        });

        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent exitClicked) {
                musicStage.hide();
                controller.mp3Player.getSongPlayer().stop();
                System.exit(0);
            }
        });

        //Window Setup
        playButton.setMaxHeight(20);
        playButton.setMaxWidth(50);

        container.setAlignment(Pos.BASELINE_CENTER);
        container.setPadding(new Insets(15,20,15,20));
        container.setSpacing(15);
        container.getChildren().addAll(playButton,controller.mp3Player.timer,exitButton);
        container.setStyle("-fx-background-color: #2f3ae3");

        musicStage.setScene(new Scene(container,480,100));
        musicStage.show();


    }

}
