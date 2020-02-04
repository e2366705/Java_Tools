package Tools_5;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.tts.AbstractTTS;
import com.swjtu.tts.impl.GoogleTTS;

import java.util.List;

/**
 * @author YXB
 * @date 2020/1/27 20:09
 */
public class Download_TTS {

    // 下载这个单词的音频文件, 并且保存在 D 盘的某个文件夹里面
    public void Download(String words){
        //  原地址 :   https://github.com/hujingshuang/MTrans

        //         路径
        Querier<AbstractTTS> querierTTS = new Querier<>(); // 获取查询器

//        querierTTS.setParams(LANG.EN, "To be or not to be, that is a question , apple is good ");   // 设置参数
        querierTTS.setParams(LANG.EN, words);   // 设置参数

        querierTTS.attach(new GoogleTTS());  // 向查询器中添加 Google 翻译器

        List<String> result = querierTTS.execute(); // 执行查询并接收查询结果

        for (String str : result) {
            System.out.println(str);
        }
    }
}