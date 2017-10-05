import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


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
        container.getChildren().addAll(playButton,controller.mp3Player.getTimer(),exitButton);
        container.setStyle("-fx-background-color: #2f3ae3");
        controller.mp3Player.getTimer().setText(String.format("0:00/%d:%02d",controller.mp3Player.getSongTime()/60,controller.mp3Player.getSongTime()-(controller.mp3Player.getSongTime()/60)*60));

        musicStage.setScene(new Scene(container,480,100));
        musicStage.show();


    }

}
