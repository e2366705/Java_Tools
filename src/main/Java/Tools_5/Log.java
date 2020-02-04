package Tools_5;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YXB
 * @date 2020/1/27 20:13
 */
public class Log {

        // 打印日志
        // 时间日期
        // 单词
        public void start_log(String words){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.printf("日志:   现在是:  %10s  出现的单词是:  %5s \n" , df.format(new Date()) , words);
        }
    }
