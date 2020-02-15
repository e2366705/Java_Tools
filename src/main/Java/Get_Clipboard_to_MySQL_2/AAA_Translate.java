package Get_Clipboard_to_MySQL_2;


import Get_Clipboard_to_MySQL_2.Clipboard;
import Java_USE_python.HttpRequest;
import org.junit.Test;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.sql.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.junit.Test;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Get_Clipboard_to_MySQL_2.Clipboard.getClipboardString;


/**
 * @author YXB
 * @date 2020/2/15 15:59
 */
public class AAA_Translate {
    @Test
    public void Index() throws SQLException, InterruptedException, MalformedURLException, FileNotFoundException, JavaLayerException {
        Translate translate = new Translate();
        JSON json = new JSON();
        Unicodeee unicodeee = new Unicodeee();
        Check_word check_word = new Check_word();
        SQL sql = new SQL();
        Play_Audio play_audio = new Play_Audio();


/*
with an air of superiority
a staple ingredient of comedy
course in art
was his downfall
eloquent speech
country is facing
*/

        // 短句
        String phrase = "";

        Clipboard.setClipboardString("YXB");

        // 从剪贴板中获取文本（粘贴）
        String copy_data  = getClipboardString();
        System.out.println("text: " + copy_data);

        String copy_data_temp = "YXB";          // 临时变量, 用来对比上一次的数据是否重复
        int iii = 0;

        while (true){
            Thread.sleep(999);
            // System.err.println("正在监听剪切板中.....");

            String new_copy = Clipboard.getClipboardString();

            if ( new_copy == null ||  new_copy.equals(copy_data_temp)){
//                System.err.println("你还没有复制新内容...");
            }else {
                System.out.println("你复制新内容了,新的内容是:   " + new_copy);
                copy_data_temp = new_copy;

                // 是否太长?   是否是英文?
                boolean true_or_false = check_word.check_copy_string(new_copy);

                if (true_or_false){
                    phrase = new_copy;

                    String word_temp = "";

                    // 转换字符串
                    word_temp = check_word.Check(phrase);

                    // 从 360 翻译中获取 中文意思
                    //     {"data":{"fanyi":"\u559c\u5267\u7684\u4e3b\u8981\u6210\u5206","speak_url":{"speak_url":"\/audio?from=en&to=zh&voice=2&cate=speakUrl&key=513f35b7b2c335d868151210a52d65a6&query=a staple ingredient of comedy","tSpeak_url":"\/audio?from=en&to=zh&voice=2&cate=tSpeakUrl&key=34f3777897124d22c4aa18cffec2a9ea&query=\u559c\u5267\u7684\u4e3b\u8981\u6210\u5206"},"vendor":"caiyun"},"error":0,"msg":"succ"}
                    String json_data = translate.Translate_360(word_temp);

                    // 获取中文意思
                    String chinese_meaning = json.Json(json_data);

                    // 是否是中文意思
                    boolean has_chinese = check_word.Has_chinese(chinese_meaning);

                    if (has_chinese){
                        int i = sql.Insert(phrase, chinese_meaning);
                        if (i == 1){
                            play_audio.success_audio();
                            iii++;
                            System.err.println("成功了:      "+ iii + "   个");
                        }else {
                            play_audio.failure_audio();
                        }
                    }
                }
            }
        }
    }
}







class JSON{
    public String Json(String json_data){
        JSONObject jsonobj = new JSONObject(json_data);

        JSONObject data = (JSONObject) jsonobj.get("data");
        Object fanyi = data.get("fanyi");

        String fanyi_result = String.valueOf(fanyi);
        System.err.println("翻译 result :             "+fanyi_result);

        return fanyi_result;
    }
}



class Unicodeee{

    public String UnicodeTo_chinese(String str){
        str = "\\u5e26\\u6211\\u8d70\\u3002\\u53bb\\u90a3\\u9065\\u8fdc\\u7684\\u4ee5\\u540e";

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);

        char ch;

        String chinese_meaning = "";

        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        System.out.println("UnicodeTo_chinese:      "+str);
        return str;
    }
}


