import org.junit.Test;

import java.util.ArrayList;

/**
 * @author YXB
 * @date 2020/2/19 16:48
 */
public class word {

    @Test
    public void Test0001(){

        //  Similar 相似的单词   派生词   Derivative words  衍生词
        ArrayList<String> Similar_word_list = new ArrayList<>();

        String aaaaaaa = "4. real adj. 真实的 really adv. 真正地; 事实上 reality n. 事实; 真实   realize v. 实现; 意识到   5. person n. 人 personal adj. 私人的；个人的 personally adv. 就个人而言; 亲自";


        /*
n. 名词 ,noun的缩写v. 动词 , verb的缩写pron. 代词 , pronoun的缩写adj. 形容词, adjective的缩写adv. 副词, adverb的缩写num.数词 , numeral的缩写art. 冠词, article的缩写prep. 介词 ,前置词,preposition的缩写conj. 连词 , conjunction的缩写interj. 感叹词 , interjection的缩写u = 不可数名词,uncountable noun的缩写c = 可数名词,countable noun的缩写pl = 复数,plural的缩写

         */
        aaaaaaa = aaaaaaa.replaceAll("[0-9]", "===");
        aaaaaaa = aaaaaaa.replaceAll("\\.", "");              // 去掉 .
        aaaaaaa = aaaaaaa.replaceAll("\\bn\\b", "");          // 去掉 n
        aaaaaaa = aaaaaaa.replaceAll("\\bv\\b", "");          // 去掉 v
        aaaaaaa = aaaaaaa.replaceAll("\\badj\\b", "");        // 去掉 adj
        aaaaaaa = aaaaaaa.replaceAll("\\badv\\b", "");        // 去掉 adv
        System.out.println(aaaaaaa);


        String[] temp_array;
        String delimeter = "===";  // 指定分割字符
        temp_array = aaaaaaa.split(delimeter); // 分割字符串
        // 普通 for 循环
        for(int i =0; i < temp_array.length ; i++){
            Similar_word_list.add(temp_array[i]);
//            System.out.println( i + "   --->   "+temp_array[i]);
        }

        for (String value : Similar_word_list){
            System.out.println(value);
        }



    }
}
