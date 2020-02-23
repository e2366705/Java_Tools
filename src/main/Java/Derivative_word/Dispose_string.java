package Derivative_word;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// 处理字符串
public class Dispose_string {
    public void Get_english_word(String str) throws SQLException {
        SQL sql = new SQL();
        String regex = "[a-zA-Z]+";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);

        while (m.find()) {
            String word = m.group(0);
            System.out.println(word);
            sql.Insert(word , str);

        }
    }
}
