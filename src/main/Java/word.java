import oracle.jrockit.jfr.jdkevents.ThrowableTracer;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

/**
 * @author YXB
 * @date 2020/2/19 16:48
 */
public class word {

    @Test
    public void A__index() throws InterruptedException {

        Read_txt read_txt = new Read_txt();

        //  Similar 相似的单词   派生词   Derivative words  衍生词
        ArrayList<String> Similar_word_list = new ArrayList<>();
        String file_data= read_txt.Read_();




/*
n. 名词 ,noun的缩写v. 动词 , verb的缩写pron. 代词 , pronoun的缩写adj. 形容词, adjective的缩写adv. 副词, adverb的缩写num.数词 , numeral的缩写art. 冠词, article的缩写prep. 介词 ,前置词,preposition的缩写conj. 连词 , conjunction的缩写interj. 感叹词 , interjection的缩写u = 不可数名词,uncountable noun的缩写c = 可数名词,countable noun的缩写pl = 复数,plural的缩写
\badj\b
\badv\b
\bady\b
\bart\b
\baux\b
\babbr\b
\bn\b
\bv\b
\bu\b
\bc\b
\bconj\b
\bvt\b
\bvi\b
\bpron\b
\bprep\b
\bpl\b
\bphr\b
\bnum\b
\binterj\b
*/
        file_data = file_data.replaceAll("[0-9]", "===");         // 去掉数字 , 把数字 转换成 ===
        file_data = file_data.replaceAll("\\.", "");              // 去掉 .
        file_data = file_data.replaceAll("\\bUnit\\b", "");         // 去掉Unit
        file_data = file_data.replaceAll("\\bn\\b", "");          // 去掉 n
        file_data = file_data.replaceAll("\\bv\\b", "");          // 去掉 v
        file_data = file_data.replaceAll("\\badj\\b", "");        // 去掉 adj
        file_data = file_data.replaceAll("\\badv\\b", "");        // 去掉 adv
        file_data = file_data.replaceAll("\\badj\\b", "");        // 去掉 adv
        file_data = file_data.replaceAll("\\badv\\b", "");        // 以此类推
        file_data = file_data.replaceAll("\\bady\\b", "");
        file_data = file_data.replaceAll("\\baux\\b", "");
        file_data = file_data.replaceAll("\\bart\\b", "");
        file_data = file_data.replaceAll("\\babbr\\b", "");
        file_data = file_data.replaceAll("\\bn\\b", "");
        file_data = file_data.replaceAll("\\bv\\b", "");
        file_data = file_data.replaceAll("\\bu\\b ", "");
        file_data = file_data.replaceAll("\\bc\\b", "");
        file_data = file_data.replaceAll("\\bconj\\b", "");
        file_data = file_data.replaceAll("\\bvt\\b", "");
        file_data = file_data.replaceAll("\\bvi\\b", "");
        file_data = file_data.replaceAll("\\bpron\\b", "");
        file_data = file_data.replaceAll("\\bprep\\b", "");
        file_data = file_data.replaceAll("\\bpl\\b ", "");
        file_data = file_data.replaceAll("\\bphr\\b", "");
        file_data = file_data.replaceAll("\\bnum\\b", "");
        file_data = file_data.replaceAll("\\binterj\\b", "");
        System.out.println(file_data);


        String[] temp_array;
        String delimeter = "===";  // 指定分割字符
        temp_array = file_data.split(delimeter); // 分割字符串
        // 普通 for 循环
        for (int i = 0; i < temp_array.length; i++) {
            Similar_word_list.add(temp_array[i]);
//            System.out.println( i + "   --->   "+temp_array[i]);
        }

        int count = 0;

        for (String value : Similar_word_list) {
            count++;
            Thread.sleep(88);
            Thread.sleep(88);
            System.out.println(value);
            System.out.println("======================================================");
            Thread.sleep(88);
            Thread.sleep(88);
            System.err.println("当前 count 是   :     " +  count);
            Thread.sleep(88);
            Thread.sleep(88);

        }


    }
}


class Read_txt {
    public String Read_() {
        /**
         来源 :  http://lvhongqiang.com/blog5.html
         我们都习惯于一次把文本的原始内容直接读取到内存中再做处理（暂时不考虑内存大小），这样做效率也会提高。
         很多人用readline()之类的方法，可能需要反复访问文件，
         而且每次readline()都会调用编码转换，降低了速度，
         所以，在已知编码的情况下，
         按字节流方式先将文件都读入内存，
         再一次性编码转换是最快的方式

         这种方法的好处是读取的内容可以彻底保持文件的原貌，
         而且速度应该是最快的，因为只需要调用一次文件访问，字符编码转换也只需要一次
         */

        File file = new File("src\\main\\Java\\Derivative_word.txt");

        Long filelength = file.length();     //获取文件长度

        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(filecontent));
        return new String(filecontent);
    }
}