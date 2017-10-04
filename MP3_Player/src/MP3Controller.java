import javafx.scene.control.TextArea;

import javax.sound.sampled.AudioFileFormat;


public class MP3Controller {
    MP3Player mp3Player;
    Thread playerThread;


    public MP3Controller() {
        mp3Player = new MP3Player();
        playerThread = new Thread(mp3Player);
    }

    public void play() {
        playerThread.start();

        mp3Player.stop();
    }





}
