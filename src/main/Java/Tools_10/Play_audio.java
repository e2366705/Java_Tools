package Tools_10;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 * @author YXB
 * @date 2020/2/19 12:45
 */
public class Play_audio {
    public void Play_no_Chinese_meaning() throws MalformedURLException, InterruptedException, JavaLayerException, FileNotFoundException {
        Player player;
        File music;

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src/main/Java/Tools_10/Audio/no_Chinese_meaning.MP3"));
        player = new Player(buffer);
        player.play();
    }


    public void Play_success() throws MalformedURLException, InterruptedException, JavaLayerException, FileNotFoundException {
        Player player;
        File music;

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src/main/Java/Tools_10/Audio/success.MP3"));
        player = new Player(buffer);
        player.play();
    }


    public void Play_warning1() throws MalformedURLException, InterruptedException, JavaLayerException, FileNotFoundException {
        Player player;
        File music;

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src/main/Java/Tools_10/Audio/warning1.mp3"));
        player = new Player(buffer);
        player.play();
    }

}
