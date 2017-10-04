import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.media.Manager;
import javax.media.Player;
import java.net.URL;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;

public class MP3Player implements Runnable {

    private File songFile;
    private Path songPath;
    private URL songURL;
    private Player songPlayer;
    private AudioFileFormat songData;
    private volatile boolean status;//Thread status monitor.

    long currentTime,pastTime,songTime;
    float songFrames,frameSpeed;
    String output;
    TextArea timer;

    protected MP3Player() {
        try {
            status = true;

            songFile = new File("C://Java Projects/MusicPlayer/ImABoss.wav");
            songData = AudioSystem.getAudioFileFormat(songFile);

            songFrames = songData.getFrameLength();
            frameSpeed = songData.getFormat().getFrameRate();

            songPath = Paths.get("C:/","Java Projects/MusicPlayer/ImABoss.wav");
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

        } catch (IOException ix) {//Printing out IOExceptions.
            System.out.println(ix);
        } catch (Exception ex) {//Generalized Exception catch for all other Exceptions to print stack trace.
            ex.printStackTrace();
        }
    }

    public Player getSongPlayer() {
        return songPlayer;
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
