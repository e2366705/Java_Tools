package Tools_10;

import org.junit.Test;
import javazoom.jl.decoder.JavaLayerException;
import org.python.bouncycastle.jcajce.provider.symmetric.Threefish;

import java.io.*;
import java.net.MalformedURLException;
import java.sql.*;
import java.text.SimpleDateFormat;



/*


drop database IF EXISTS `zzza9d2g5k4t1b0k`;

-- 创建 数据库:
CREATE DATABASE IF NOT EXISTS `zzza9d2g5k4t1b0k` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

-- 切换回
use `zzza9d2g5k4t1b0k`;

-- 创建表
CREATE TABLE IF NOT EXISTS `word`(
`word`               VARCHAR(36)   UNIQUE    not null DEFAULT '',
`chinese_meaning`    VARCHAR(250)    not null DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 插入insert数据:
insert into `word`(word,chinese_meaning) values('apple','');
insert into `word`(word,chinese_meaning) values('name','');
insert into `word`(word,chinese_meaning) values('miss','');


 */

/*
 * @author YXB
 * @date 2020/2/19 9:12
 */
public class Get_word_Translate {

    @Test
    public void Get_word_Translate() throws SQLException, InterruptedException, MalformedURLException, FileNotFoundException, JavaLayerException {

        // 设置数据库参数
        Settings settings = new Settings();

        // 设置 数据库
        settings.setDatabase("zzza9d2g5k4t1b0k");

        // 设置 数据表
        settings.setTable("word");

        // 设置 存放英语单词的字段名
        settings.setEnglish_word_column("word");

        // 设置 将要存放中文意思的字段名
        settings.setChinese_meaning_column("chinese_meaning");


       
        SQL sql = new SQL(settings);
        Get_Translate get_translate = new Get_Translate();
        Play_audio play_audio = new Play_audio();
        Random_number random_number = new Random_number();



        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式



        // 记录条数
        int count = 0;


        // 无限循环
        while (true){
            // 获取 没有中文意思的单词
            //      SELECT * FROM `word` where `chinese_meaning` = ''   limit 0,1
            String word = sql.SELECT_word();

            // 说明 没有中文意思的单词, 已经不存在了
            if (word.length() < 1){
                play_audio.Play_warning1();
                System.err.println("没了,  程序即将结束...");
                break;
            }else {
                count++;
                System.err.println(" *** 日志(logs):  现在是第:  "+  count +" 条 , 现在时间是:  " +  df.format(new java.util.Date()));// new Date()为获取当前系统时间


                // 程序随机暂停 , 访问别太频繁
                int random_num = random_number.Get_random_number();
                System.err.println("程序随机暂停了:      " +  random_num  +  "     秒");
                Thread.sleep(random_num);

                // 联网查询英文单词 的 中文意思
                String chinese_meaning = get_translate.Get_word_translte(word);

                // 把这个单词的中文意思 , 更新到这个单词
                int update_number = sql.Update_chinese_meaning(word, chinese_meaning);

                if (update_number == 1){
                    play_audio.Play_success();
                }
            }

        }
    }
}


