package Tools_10;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author YXB
 * @date 2020/2/19 12:54
 */
public class Get_Translate{

    public String Get_word_translte(String english_word){
        final String url = "https://www.baidu.com/baidu?wd=" + english_word + "&tn=monline_4_dg&ie=utf-8";
        // https://www.baidu.com/baidu?wd=miss&tn=monline_4_dg&ie=utf-8

        try {
            //先获得的是整个页面的html标签页面
            Document doc = Jsoup.connect(url).get();

            //获取阅读数量
            //  class 是 .
            //  id    是 #
            Elements readEl = doc.select(".op_dict_text2");
            String word_chinese_meaning = readEl.text();
            System.err.println("中文意思是：" + word_chinese_meaning);
            return word_chinese_meaning;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "NULL";
    }

}