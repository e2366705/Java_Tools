package Tools_10;

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
 * @date 2020/2/19 12:54
 */
public class Get_Translate{

    public String Get_word_translte_Baidu(String english_word){
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






    // 翻译单词: miss (小姐)
    // 成功, 翻译只有'小姐'一个意思, 不全面           by 2020-02-10 12:06:09
    // 测试单词 miss Java JavaScript
    @Test
    public String Translation_by_Google(String english_word){
        System.err.println("==========   调用了 Google 翻译接口   ==============");
        //  https://github.com/hujingshuang/MTrans
        Querier<AbstractTranslator> querierTrans = new Querier<>();  // 获取查询器
        querierTrans.setParams(LANG.EN, LANG.ZH, english_word);// 设置参数
        querierTrans.attach(new GoogleTranslator());// 向查询器中添加 Google 翻译器
        List<String> result = querierTrans.execute();// 执行查询并接收查询结果

        // 查询到的结果
        String chinese_meaning = result.get(0);
        return chinese_meaning;
    }







}