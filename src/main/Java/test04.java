
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class test04 {

    public static void main(String[] args) {

        TreeMap<String, Integer> stringIntegerTreeMap = new TreeMap<>();

        stringIntegerTreeMap.put("qwe", 123);

        System.out.println(stringIntegerTreeMap.get("qwe"));

        String aaa = "AadasASDasda";

        System.out.println(First_letter_is_capitalized(aaa));


    }

    // 判断首字母是否大写?
    // 大写 => true
    public static boolean First_letter_is_capitalized(String str) {

        char[] chars = str.toCharArray();

        return Character.isUpperCase(chars[0]);
    }




    public static boolean testAllUpperCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 97 && c <= 122) {
                return false;
            }
        }
        //str.charAt(index)
        return true;
    }


}
