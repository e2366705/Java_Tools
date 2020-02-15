package Get_Clipboard_to_MySQL;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author YXB
 * @date 2020/2/14 15:48
 */
public class Translation {



    // 百度翻译  短句
    @Test
    public String Baidu_translate_phrase(String english_phrase) {

        String chinese_meaning = null;

        //这个就是博客中的java反射的url
        final String url = "https://www.baidu.com/baidu?wd=" + english_phrase + "&tn=monline_4_dg&ie=utf-8";
        // https://www.baidu.com/baidu?wd=a staple ingredient of comedy&tn=monline_4_dg&ie=utf-8

        try {
            //先获得的是整个页面的html标签页面
            Document doc = Jsoup.connect(url).get();

            //获取阅读数量
            //  class 是 .
            //  id    是 #
            Elements readEl = doc.select(".op_sp_fanyi_line_two");
            chinese_meaning = readEl.text();
            System.err.println("中文意思是：" + chinese_meaning);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chinese_meaning;
    }





    public String  English_to_chinese(String english_string){
            Querier<AbstractTranslator> querierTrans = new Querier<>();  // 获取查询器
            querierTrans.setParams(LANG.EN, LANG.ZH, english_string);// 设置参数
//        querierTrans.setParams(LANG.EN, LANG.ZH, "miss");// 设置参数
            querierTrans.attach(new GoogleTranslator());// 向查询器中添加 Google 翻译器
            List<String> result = querierTrans.execute();// 执行查询并接收查询结果
            System.err.println(result.get(0));
            return result.get(0);
        }


}
