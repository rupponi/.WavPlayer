import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.control.Slider;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.io.File;

import javax.media.Manager;
import javax.media.Player;

import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;


public class MP3Player implements Runnable {

    private File songFile;
    private Path songPath;
    private URL songURL;
    private Player songPlayer;
    private AudioFileFormat songData;
    private volatile boolean status;//Thread status monitor.

    private long currentTime,pastTime,songTime;
    private float songFrames,frameSpeed;
    private String output;
    private TextArea timer;
    private Slider timeSlider;


    protected MP3Player() {
        try {
            status = true;

            songFile = new File(MusicPlayerFrontPanel.selector.songPath);
            songData = AudioSystem.getAudioFileFormat(songFile);

            songFrames = songData.getFrameLength();
            frameSpeed = songData.getFormat().getFrameRate();

            songPath = Paths.get(MusicPlayerFrontPanel.selector.songPath);
            songURL = songPath.toUri().toURL();
            songPlayer = Manager.createRealizedPlayer(songURL);

            currentTime = 0;
            pastTime = 0;
            songTime= ((int)songFrames / (int)frameSpeed);
            output = new String();



            timer = new TextArea();
            timer.setMaxHeight(40);
            timer.setMaxWidth(85);
            timer.setEditable(false);
            timer.setStyle("-fx-font-family: monospace");
            timer.setFont(Font.font("Arial Black", 12.0));

            timeSlider = new Slider();
            timeSlider.setMinSize(300,50);
            timeSlider.setMin(0);
            timeSlider.setMax((int) songTime);
            timeSlider.setMajorTickUnit(60);
            timeSlider.setBlockIncrement(1);



        } catch (IOException ix) {//Printing out IOExceptions.
            System.out.println(ix);
        } catch (Exception ex) {//Generalized Exception catch for all other Exceptions to print stack trace.
            ex.printStackTrace();
        }
    }

    public Player getSongPlayer() {
        return songPlayer;
    }

    public long getSongTime() {
        return songTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long newCurrentTime) {
        currentTime = newCurrentTime;
    }

    public TextArea getTimer() {
        return timer;
    }

    public Slider getTimeSlider() {
        return timeSlider;
    }

    public void run() {//Run method simply begins stream. Stopping this will restart the song, similar to stop button in music player.
        long startingTime = System.currentTimeMillis()/1000;
        songPlayer.start();

        while((System.currentTimeMillis()/1000) < startingTime + songTime) {
            pastTime = currentTime;

            currentTime = (System.currentTimeMillis()/1000)-startingTime;
            if (currentTime != pastTime) {
                output = String.format("%d:%02d/%d:%02d",currentTime/60,currentTime-(currentTime/60)*60, songTime/60,songTime-(songTime/60)*60);
                timer.setText(output);
                timeSlider.setValue(currentTime);


            }
        }
        status = false;

        System.out.println("Song completed.");
        if (!status) {
            songPlayer.stop();
        }
    }

    public void stop() {
        status = false;
    }

    AudioFileFormat getSongData() {
        return songData;
    }
}
