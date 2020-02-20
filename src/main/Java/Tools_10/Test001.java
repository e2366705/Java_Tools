package Tools_10;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;
import javazoom.jl.decoder.JavaLayerException;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author YXB
 * @date 2020/2/19 12:19
 */
public class Test001 {

    @Test
    public void Test001(){

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
        } catch (MalformedURLException ex) {
            Logger.getLogger(Test001.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test001.class.getName()).log(Level.SEVERE, null, ex);
        }

    }




    @Test
    public void Play_audio_TEST() throws InterruptedException, MalformedURLException, FileNotFoundException, JavaLayerException {
        Play_audio play_audio = new Play_audio();
        play_audio.Play_no_Chinese_meaning();
    }







    // 翻译单词: miss (小姐)
    // 成功, 翻译只有'小姐'一个意思, 不全面           by 2020-02-10 12:06:09
    // 测试单词 miss Java JavaScript
    @Test
    public void Translation_by_Google() throws InterruptedException {
        //  https://github.com/hujingshuang/MTrans
        Querier<AbstractTranslator> querierTrans = new Querier<>();  // 获取查询器

        querierTrans.setParams(LANG.EN, LANG.ZH, "Java");// 设置参数

        querierTrans.attach(new GoogleTranslator());// 向查询器中添加 Google 翻译器

        List<String> result = querierTrans.execute();// 执行查询并接收查询结果

        System.err.println(result.get(0));

        Thread.sleep(666);

        for (String str : result) {
            System.out.println(str);
        }
    }










    // 测试单词 miss Java JavaScript
    @Test
    public void Test_Baidu_Translate(){

        Get_Translate get_translate = new Get_Translate();
        String java = get_translate.Get_word_translte_Baidu("java");


        if (java.length() < 1){
            System.err.println("查询失败......");
        }else {
            System.out.println("------------>>>         "+java);
        }


    }
}
