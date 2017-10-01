import java.nio.file.Path;
import java.nio.file.Paths;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.CannotRealizeException;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3_Player {
    public static void main(String[] args) {
        try {
            File songFile = new File("C://Java Projects/MusicPlayer/ImABoss.wav");
            System.out.println(AudioSystem.getAudioFileFormat(songFile));

            Path songPath = Paths.get("C:","Java Projects/MusicPlayer/ImABoss.wav");
            URL songURL = songPath.toUri().toURL();
            Manager.createRealizedPlayer(songURL).start();
        } catch (MalformedURLException mx) {
            mx.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (NoPlayerException nx) {
            nx.printStackTrace();
        } catch (CannotRealizeException cx) {
            cx.printStackTrace();
        } catch (UnsupportedAudioFileException ux) {
            ux.printStackTrace();
        }
    }
}
