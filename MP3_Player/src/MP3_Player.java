import javax.sound.sampled.*;
import java.io.*;

public class MP3_Player {
    public static void main(String[] args) {
        try {
            AudioFileFormat inputFileFormat = AudioSystem.getAudioFileFormat(new File("ImABoss.mp3"));

        } catch(UnsupportedAudioFileException ax) {
            ax.printStackTrace();
        } catch(IOException ex) {
            System.out.println(ex);
        }
    }
}
