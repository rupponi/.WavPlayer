import javax.sound.sampled.AudioFileFormat;


public class MP3Controller {
    MP3Player mp3Player;
    AudioFileFormat songInfo;
    float songFrames,frameSpeed;
    Thread playerThread;
    long currentTime,pastTime,songTime;
    String output;
    //volatile boolean isPlaying;

    public MP3Controller() {
        //isPlaying = false;
        mp3Player = new MP3Player();
        playerThread = new Thread(mp3Player);
        songInfo = mp3Player.getSongData();

        songFrames = songInfo.getFrameLength();
        frameSpeed = songInfo.getFormat().getFrameRate();

        //songLength = (songFrames/frameSpeed) + 2;//2 second buffer at the end before terminating.
        currentTime = 0;
        pastTime = 0;
        songTime= ((int)songFrames / (int)frameSpeed);
        output = new String();
    }

    public void play() {
        long startingTime = System.currentTimeMillis()/1000;
        playerThread.start();
        while((System.currentTimeMillis()/1000) < startingTime + songTime) {
            //isPlaying = true;
            pastTime = currentTime;
            currentTime = (System.currentTimeMillis()/1000)-startingTime;
            if (currentTime != pastTime) {
                output = String.format("%d:%02d/%d:%02d\n",currentTime/60,currentTime-(currentTime/60)*60, songTime/60,songTime-(songTime/60)*60);
            }
        }
        //isPlaying = false;

        mp3Player.stop();
    }


}
