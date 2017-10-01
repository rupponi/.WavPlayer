public class MP3Controller {
    MP3Player mp3Player;

    public MP3Controller() {
        mp3Player = new MP3Player();
        Thread playerThread = new Thread(mp3Player);
    }

    public void play() {
        //Will be implemented using Threading on Runnable MP3Player.
    }


}
