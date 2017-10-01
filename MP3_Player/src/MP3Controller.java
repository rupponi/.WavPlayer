import javax.sound.sampled.AudioFileFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class MP3Controller {
    MP3Player mp3Player;
    AudioFileFormat songInfo;
    float songFrames,frameSpeed,songLength;
    Thread playerThread;

    public MP3Controller() {
        mp3Player = new MP3Player();
        playerThread = new Thread(mp3Player);
        songInfo = mp3Player.getSongData();

        songFrames = songInfo.getFrameLength();
        frameSpeed = songInfo.getFormat().getFrameRate();

        songLength = (songFrames/frameSpeed) + 3;//3 second buffer at the end before terminating.

    }

    public void play() {
        long startingTime = System.currentTimeMillis();
        playerThread.run();
        while (startingTime < startingTime + (songLength*1000)) {
            //Do nothing.
        }
        mp3Player.stop();

    }


}
