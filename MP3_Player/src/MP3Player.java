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
        status = true;

        currentTime = 0;
        pastTime = 0;
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
        timeSlider.setMajorTickUnit(60);
        timeSlider.setBlockIncrement(1);


    }


    public Player getSongPlayer() {
        return songPlayer;
    }
    public void setSongPlayer(Player newPlayer) {
        songPlayer = newPlayer;
    }


    public Path getSongPath() {
        return songPath;
    }
    public void setSongPath(Path newSongPath) {
        songPath = newSongPath;
    }


    public URL getSongURL() {
        return songURL;
    }
    public void setSongURL(URL newSongURL) {
        songURL = newSongURL;
    }


    public File getSongFile() {
        return songFile;
    }
    public void setSongFile(File newSong) {
        songFile = newSong;
    }


    public AudioFileFormat getSongData() {
        return songData;
    }
    public void setSongData(AudioFileFormat newSongData) {
        songData = newSongData;
    }

    public float getSongFrames() {
        return songFrames;
    }
    public void setSongFrames(float newSongFrames) {
        songFrames = newSongFrames;
    }


    public float getFrameSpeed() {
        return frameSpeed;
    }
    public void setFrameSpeed(float newFrameSpeed) {
        frameSpeed = newFrameSpeed;
    }


    public long getSongTime() {
        return songTime;
    }
    public void setSongTime(long newSongTime) {
        songTime = newSongTime;
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
    public void setTimer(TextArea newTimer) {
        timer = newTimer;
    }


    public Slider getTimeSlider() {
        return timeSlider;
    }
    public void setTimeSlider(Slider newTimeSlider) {
        timeSlider = newTimeSlider;
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
}
