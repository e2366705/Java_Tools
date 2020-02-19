package Tools_10;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author YXB
 * @date 2020/2/19 12:19
 */
public class Test001 {

    @Test
    public String Test001(){

        try{
            URL url = new URL("http://localhost:8083/Get_chineseMeaning_Baidu?word=miss");//网址链接
            URLConnection conn = url.openConnection(); //打开链接
            //获取网页的源代码
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String chinese_meaning = "";
            while ((line = br.readLine()) != null) {
                chinese_meaning = line;
            }
            System.err.println(chinese_meaning);
            br.close();
            return chinese_meaning;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Test001.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test001.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
