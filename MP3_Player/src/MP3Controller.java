import javax.sound.sampled.AudioFileFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class MP3Controller {
    MP3Player mp3Player;
    AudioFileFormat songInfo;
    float songFrames,frameSpeed,songLength;
    Thread playerThread;
    long currentTime,pastTime;

    public MP3Controller() {
        mp3Player = new MP3Player();
        playerThread = new Thread(mp3Player);
        songInfo = mp3Player.getSongData();

        songFrames = songInfo.getFrameLength();
        frameSpeed = songInfo.getFormat().getFrameRate();

        songLength = (songFrames/frameSpeed) + 3;//3 second buffer at the end before terminating.
        currentTime = 0;
        pastTime = 0;

    }

    public void play() {
        long startingTime = System.currentTimeMillis()/1000;
        playerThread.start();
        while((System.currentTimeMillis()/1000) < startingTime + songLength) {
            pastTime = currentTime;
            currentTime = (System.currentTimeMillis()/1000)-startingTime;
            if (currentTime != pastTime) {
                System.out.printf("%d:%02d\n",currentTime/60,currentTime-(currentTime/60)*60);
            }
        }
        mp3Player.stop();

    }


}
