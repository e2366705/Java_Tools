package Tools_3;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.BaiduTranslator;
import com.swjtu.trans.impl.GoogleTranslator;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static Tools_3.Clipboard_.getClipboardString;



/*
    功能简介:
    -------------------------------------------------
    获取系统剪切板复制的单词词组 (扇贝记单词)
    并获取相关的翻译
    然后把词组和中文意思都保存在MySQL数据库里
*/
class Aaa{
    public void Aaaaaa(){
    }
}
public class Get_Clipboard_to_MySQL {
    @Test
    public void Main___() throws InterruptedException, IOException, JavaLayerException, SQLException {
        Audio_ audio_ = new Audio_();
        Check_txt check_txt = new Check_txt();
        TO_MySQL to_mySQL = new TO_MySQL();
        Translation translation = new Translation();


        Clipboard_.setClipboardString("YXB");

        // 从剪贴板中获取文本（粘贴）
        String text = getClipboardString();
        System.out.println("text: " + text);

        String temp = "YXB";          // 临时变量, 用来对比上一次的数据是否重复
        int iii = 0;

        while (true){
            Thread.sleep(888);
            // System.err.println("正在监听剪切板中.....");

            String text2 = getClipboardString();

            if ( text2 == null ||  text2.equals(temp)){
//                System.err.println("你还没有复制新内容...");
            }else {
                System.out.println("你复制新内容了,新的内容是:   " + text2);

                boolean true_or_false = check_txt.check(text2);

                if (true_or_false){
                    String chinese_meaning = translation.English_to_chinese(text2);
                    System.err.println("中文意思是:   " + chinese_meaning);

                    if (chinese_meaning.length() > 0){
                        boolean item = to_mySQL.Insert(text2 , chinese_meaning);
                        if (item){
                            audio_.PlayMusic();      // 成功了, 就播放音频文件
                            temp = text2;
                            iii++;
                            System.out.printf("这是第     %d   次  \n\n\n" , iii);
                        }
                    }else {
                        audio_.Play_warning_sound();
                    }
                }
            }
        }
    }
}




// 1 获取剪切板
class Clipboard_{

    /**
     * 把文本设置到剪贴板（复制）
     */
    public static void setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    /**
     * 从剪贴板中获取文本（粘贴）
     */
    public static String getClipboardString() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);

        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
             // System.err.println(" error : 你复制的不是文本 ");
            }
        }
        return null;
    }
}


// 2 检查文本
class Check_txt{
    public boolean check(String str){

        if (str == null){
            return false;
        }

        // 2-1 长度是否太长?
        System.out.println(str.length());
        if (str.length() > 60){
            return false;
        }

        // 2-2  【单词是否全为英文】返回true  否则false  (\\s 表示空格, 意思是可以有空格...)
        if (str.matches("[a-zA-Z\\s]+")) {
            return true;
        }else {
            return false;
        }
    }

}


// 写入成功 , 发出提示音              D:\Download________\Browser_Download\5c892a30468f811812.mp3
class Audio_{
    public void PlayMusic() throws JavaLayerException, FileNotFoundException {
        Player player;
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("C:\\D\\YXB___Download___\\AAA.mp3"));
        player = new Player(buffer);
        player.play();
    }


    public void Play_warning_sound() throws JavaLayerException, FileNotFoundException {
        Player player;
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("C:\\D\\YXB___Download___\\warning.MP3"));
        player = new Player(buffer);
        player.play();
    }
}

class TO_MySQL {
    private Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/study_english_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /*
    数据库名称:   study_english_database
    表名:        phrase

    字段1:       phrase
    字段2:       chinese_meaning

    -- 切换回
    use `study_english_database`;

    -- 创建表
    CREATE TABLE IF NOT EXISTS `phrase`(
    `phrase` VARCHAR(250) primary key,
    `chinese_meaning` VARCHAR(256)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

     */
    public boolean Insert(String phrase, String chinese_meaning) throws SQLException {

        Connection conn = getConn();
        String sql = "insert into `tb_word`(phrase , chinese_meaning) values(? , ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, phrase);
        ps.setString(2, chinese_meaning);

        int iii = 0;

        try {
            iii = ps.executeUpdate();
            if(iii == 1){
                System.out.printf("insert 成功!!! 添加了  %-12s  条数据", iii);
                ps.close();
                conn.close();
                return true;
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            System.err.println("这个词组数据库已经有了....");
        }
        return false;
    }
}


class  Translation{

    public String  English_to_chinese(String english_string){
        Querier<AbstractTranslator> querierTrans = new Querier<>();  // 获取查询器
        querierTrans.setParams(LANG.EN, LANG.ZH, english_string);// 设置参数
//        querierTrans.setParams(LANG.EN, LANG.ZH, "miss");// 设置参数
        querierTrans.attach(new GoogleTranslator());// 向查询器中添加 Google 翻译器
        List<String> result = querierTrans.execute();// 执行查询并接收查询结果
        System.err.println(result.get(0));

        return result.get(0);
    }
}