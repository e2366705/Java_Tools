package Get_Clipboard_to_MySQL_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YXB
 * @date 2020/2/20 11:49
 */
public class Unicodeee{

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

