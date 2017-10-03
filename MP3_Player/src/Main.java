import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application{

    MP3Controller controller = new MP3Controller();

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage musicStage) {
        musicStage.setTitle(".WavPlayer Music Player");

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
                controller.mp3Player.stop();
            }
        });

        TextArea timer = new TextArea(controller.output);

        //BorderPane to hold content
        BorderPane playerPane = new BorderPane();
        playerPane.setTop(playButton);
        playerPane.setCenter(timer);
        playerPane.setBottom(stopButton);

        musicStage.setScene(new Scene(playerPane,1280,720));
        musicStage.show();
    }

}
