import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;


public class MusicPlayerFrontPanel extends Application{

    MP3Controller controller = new MP3Controller();
    static FileSelector selector = new FileSelector();


    public static void main(String[] args) {
        while (!selector.importFinished) {
            selector.findFile();
        }

        launch(args);
    }




    public void start(Stage musicStage) {

        musicStage.setTitle(".WavPlayer Music Player");
        musicStage.initStyle(StageStyle.TRANSPARENT);

        VBox songTimer = new VBox();
        HBox buttonInterFace = new HBox();
        HBox timerBox = new HBox();
        VBox container = new VBox();
        Label startTime = new Label();
        Label endTime = new Label();

        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent playClicked) {
                controller.play();
            }
        });

        playButton.setMaxHeight(20);
        playButton.setMaxWidth(50);
        playButton.setStyle("-fx-background-color: #090a0c, linear-gradient(#20262b, #191d22), linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b,#191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0))");
        playButton.setTextFill(Color.WHITE);

        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent exitClicked) {
                musicStage.hide();
                controller.mp3Player.getSongPlayer().stop();
                System.exit(0);
            }
        });

        exitButton.setMaxHeight(20);
        exitButton.setMaxWidth(50);
        exitButton.setStyle("-fx-background-color: #090a0c, linear-gradient(#20262b, #191d22), linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b,#191d22), radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0))");
        exitButton.setTextFill(Color.WHITE);

        songTimer.setAlignment(Pos.BASELINE_CENTER);
        songTimer.setPadding(new Insets(15,20,15,20));
        songTimer.setSpacing(15);

        startTime.setText("0:00");
        endTime.setText(String.format("%d:%02d",controller.mp3Player.getSongTime()/60,controller.mp3Player.getSongTime()-(controller.mp3Player.getSongTime()/60)*60));

        TextArea timer = controller.mp3Player.getTimer();
        timer.setStyle("-fx-background-color: linear-gradient(#858589,#5e5e61)");
        timer.setStyle("-fx-font-alignment: center");

        timerBox.setAlignment(Pos.BASELINE_CENTER);
        timerBox.getChildren().addAll(startTime,controller.mp3Player.getTimeSlider(),endTime);

        songTimer.getChildren().addAll(timerBox,timer);

        controller.mp3Player.getTimer().setText(String.format("0:00/%d:%02d",controller.mp3Player.getSongTime()/60,controller.mp3Player.getSongTime()-(controller.mp3Player.getSongTime()/60)*60));

        buttonInterFace.setAlignment(Pos.BASELINE_CENTER);
        buttonInterFace.setPadding(new Insets(15,20,15,20));
        buttonInterFace.setSpacing(30);
        buttonInterFace.getChildren().addAll(playButton,exitButton);


        container.getChildren().addAll(songTimer,buttonInterFace);
        container.setStyle("-fx-background-color: linear-gradient(#4d4d4e,#0a0a0a)");

        Scene mainScene = new Scene(container,720,250);


        musicStage.setScene(mainScene);
        musicStage.show();


    }

}
