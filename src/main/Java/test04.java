
import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.util.*;

/*
相似单词:
    investigates
    investigating

    defect
    defective

    simple
    sample

    exclude
    include
*/


public class test04 {

    @Test
    public void main___() {
        String word_A = "defect";
        String word_B = "defective";

        List<String> word_A_list = Arrays.asList(word_A.split(""));
        List<String> word_B_list = Arrays.asList(word_B.split(""));

//        System.out.println(word_B_list);


//        for (int i = 0; i < strarray.length; i++){
//            System.out.println(strarray[i]);
//        }


        // 交集
        word_A_list.retainAll(word_B_list);
        System.out.println(word_A_list.toString());

        String same_word = StringUtils.join(word_A_list, "");
        System.out.println(same_word);

        System.out.println(word_A.contains(same_word));
        System.out.println(word_B.contains(same_word));


    }


    @Test
    public void Test002() {

        String str = "我叫王力宏";
        boolean status = str.contains("力");
        if (status) {
            System.out.println("包含");
        } else {
            System.out.println("不包含");
        }


    }
}


class Similar_word {
    //  First  Second  Third   Fourth   Fifth       Similar
    /*
        第一种相似的情况( First_Similar ):
            类似于: defect 和 defective
            可以发现 , defective 是完全包含 defect 的 , 中文意思也是非常相似

            但是也有意外, 比如 be 和 belong , 虽然 belong 完全包含 be, 但是这根本就不是一码事...不能视为相似单词
      */
    public boolean First_Similar(String word_A, String word_B) {
        return true;
    }



}