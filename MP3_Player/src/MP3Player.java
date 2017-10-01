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

    protected MP3Player() {
        try {
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

    Player getSongPlayer() {
        return songPlayer;
    }

    void play() {
        System.out.println(songData);
    }

    public void run() {
        songPlayer.start();
    }
}
