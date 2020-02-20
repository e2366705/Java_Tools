package Get_Clipboard_to_MySQL_2;

import javazoom.jl.decoder.JavaLayerException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author YXB
 * @date 2020/2/15 17:07
 */
public class Translate {


    public String  Translate_360(String english) throws InterruptedException, MalformedURLException, FileNotFoundException, JavaLayerException {

//        Play_Audio play_audio = new Play_Audio();
//        play_audio.is_searching();

        String json_data = "";
        try{
            URL url = new URL("http://fanyi.youdao.com/openapi.do?keyfrom=xinlei&key=759115437&type=data&doctype=json&version=1.1&q=" + english);
            URLConnection conn = url.openConnection(); //打开链接
            //获取网页的源代码
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                json_data = line;
            }
            System.out.println("----temp----->            "+json_data);//输出内容
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AAA_Translate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AAA_Translate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json_data;
    }


}
