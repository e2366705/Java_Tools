package Get_Clipboard_to_MySQL_2;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 * @author YXB
 * @date 2020/2/15 17:05
 */
public class Play_Audio {

    public void success_audio() throws MalformedURLException, InterruptedException, JavaLayerException, FileNotFoundException {
        Player player;
        File music;

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src\\main\\Java\\Get_Clipboard_to_MySQL_2\\Audio\\success.MP3"));
        player = new Player(buffer);
        player.play();
    }

    public void failure_audio() throws MalformedURLException, InterruptedException, JavaLayerException, FileNotFoundException {
        Player player;
        File music;

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src\\main\\Java\\Get_Clipboard_to_MySQL_2\\Audio\\failure.MP3"));
        player = new Player(buffer);
        player.play();
    }


    // 正在联网查询
    public void is_searching() throws MalformedURLException, InterruptedException, JavaLayerException, FileNotFoundException {
        Player player;
        File music;

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("src\\main\\Java\\Get_Clipboard_to_MySQL_2\\Audio\\is_searching.mp3"));
        player = new Player(buffer);
        player.play();
    }


}
