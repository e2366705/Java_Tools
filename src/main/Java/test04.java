
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
    public void Test000() throws InterruptedException {

        long start_Time =System.currentTimeMillis();   //获取开始时间
        Thread.sleep(1231);
        long end_Time =System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(end_Time-start_Time)+"  ms");


        long startTime=System.nanoTime();   //获取开始时间
        Thread.sleep(1231);
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"  ns");



    }

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


