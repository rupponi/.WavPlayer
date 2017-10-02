import java.nio.file.Path;
import java.nio.file.Paths;
import javax.media.Manager;
import javax.media.Player;
import java.net.URL;
import java.net.MalformedURLException;
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

    protected MP3Player() {
        try {
            status = true;

            songFile = new File("C://Java Projects/MusicPlayer/ImABoss.wav");
            songData = AudioSystem.getAudioFileFormat(songFile);

            songPath = Paths.get("C:/","Java Projects/MusicPlayer/ImABoss.wav");
            songURL = songPath.toUri().toURL();
            songPlayer = Manager.createRealizedPlayer(songURL);

        } catch (IOException ix) {//Printing out IOExceptions.
            System.out.println(ix);
        } catch (Exception ex) {//Generalized Exception catch for all other Exceptions to print stack trace.
            ex.printStackTrace();
        }
    }

    public void run() {//Run method simply begins stream. Stopping this will restart the song, similar to stop button in music player.
        songPlayer.start();
        while (status) {
            //Do nothing.
        }
        System.out.println("Song completed.");
    }

    public void stop() {
        status = false;
    }


    Player getSongPlayer() {
        return songPlayer;
    }

    AudioFileFormat getSongData() {
        return songData;
    }
}
