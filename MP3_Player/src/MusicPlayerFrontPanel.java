import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;

import javax.swing.*;


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
        HBox cornerButtons = new HBox();
        HBox timerBox = new HBox();
        VBox container = new VBox();
        BorderPane frontPanel = new BorderPane();
        Label startTime = new Label();
        Label endTime = new Label();



        Button playButton = new Button();
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent playClicked) {
                controller.play();
            }
        });

        Image playImage = new Image(getClass().getResourceAsStream("play.png"));
        ImageView playImageView = new ImageView(playImage);
        playImageView.setFitHeight(50.0);
        playImageView.setFitWidth(50.0);
        playButton.setGraphic(playImageView);

        Circle playCircle = new Circle();
        playCircle.setRadius(50);
        playCircle.maxHeight(40);
        playCircle.maxWidth(40);

        playButton.setMaxHeight(40);
        playButton.setMaxWidth(40);
        playButton.setShape(playCircle);
        playButton.setStyle("-fx-background-color: #0a0a0a");
        playButton.setTextFill(Color.WHITE);



        Button minimizeButton = new Button();
        minimizeButton.setText("-");
        minimizeButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent minimizeWindow) {
               musicStage.setIconified(true);
           }
        });

        minimizeButton.setMinHeight(25);
        minimizeButton.setMinWidth(50);
        minimizeButton.setStyle("-fx-background-color: #4d4d4e");
        minimizeButton.setTextFill(Color.WHITE);



        Button fullScreenButton = new Button();
        fullScreenButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent fullScreen) {
                musicStage.setMaximized(true);
            }
        });

        fullScreenButton.setMinHeight(25);
        fullScreenButton.setMinWidth(50);
        fullScreenButton.setStyle("-fx-background-color: #4d4d4e");




        Button exitButton = new Button();
        exitButton.setText("x");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent exitClicked) {
                musicStage.hide();
                controller.mp3Player.getSongPlayer().stop();
                System.exit(0);
            }
        });

        exitButton.setMinHeight(20);
        exitButton.setMinWidth(50);
        exitButton.setStyle("-fx-background-color: #4d4d4e");
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

        cornerButtons.setAlignment(Pos.TOP_RIGHT);
        cornerButtons.setMaxHeight(30);
        cornerButtons.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.NONE,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        cornerButtons.setStyle("-fx-background-color: #4d4d4e");
        cornerButtons.getChildren().addAll(minimizeButton,fullScreenButton,exitButton);

        songTimer.getChildren().addAll(timerBox,timer);

        controller.mp3Player.getTimer().setText(String.format("0:00/%d:%02d",controller.mp3Player.getSongTime()/60,controller.mp3Player.getSongTime()-(controller.mp3Player.getSongTime()/60)*60));

        buttonInterFace.setAlignment(Pos.BASELINE_CENTER);
        buttonInterFace.setPadding(new Insets(15,20,15,20));
        buttonInterFace.setSpacing(30);
        buttonInterFace.getChildren().add(playButton);


        container.getChildren().addAll(songTimer,buttonInterFace);

        frontPanel.setTop(cornerButtons);
        frontPanel.setCenter(container);
        frontPanel.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        frontPanel.setStyle("-fx-background-color: linear-gradient(#4d4d4e,#0a0a0a)");

        Scene mainScene = new Scene(frontPanel,720,250);


        musicStage.setScene(mainScene);
        musicStage.show();


    }

}