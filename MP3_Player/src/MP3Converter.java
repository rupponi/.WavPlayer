import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import net.sourceforge.jffmpeg.codecs.audio.mpeg.mp3.MP3;
import net.sourceforge.jffmpeg.demux.mpg.MpegAC3AudioTrack;

import javax.media.Format;
import javax.media.Manager;
import javax.media.Player;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;

public class MP3Converter {

    Player testPlayer;
    URL testURL;
    File convertedWav;

    public void convertToWav(File input) {

        File output = new File("output");
        AudioAttributes outputProperties = new AudioAttributes();
        outputProperties.setCodec("pcm_s16le");
        outputProperties.setBitRate(16);
        outputProperties.setChannels(2);
        outputProperties.setSamplingRate(44100);


        EncodingAttributes encodingProperties = new EncodingAttributes();
        encodingProperties.setFormat("wav");
        encodingProperties.setAudioAttributes(outputProperties);

        Encoder encoder = new Encoder();

        try {
            encoder.encode(input,output,encodingProperties);
            convertedWav = output;
            testURL = output.toURI().toURL();
            testPlayer = Manager.createRealizedPlayer(testURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}