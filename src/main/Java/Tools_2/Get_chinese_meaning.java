package Tools_2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Get_chinese_meaning {

    private final String database_name = "";
    private final String table_name = "";


    private static Connection getConn() {
        String database_name = "study_english_database";            // 数据库
        String driver = "com.mysql.cj.jdbc.Driver";                 // MySQL驱动
        String username = "root";
        String password = "root";

        String url = "jdbc:mysql://localhost:3306/"+ database_name +"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

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


    /**
     *          no.0
     *          主方法  =_=
     */
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

        for (int i = 0; i < 9999; i++) {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println( "现在是:  " +  df.format(new Date()));// new Date()为获取当前系统时间

            int min_number = 5000;
            int max_number = 9999;
            int Random_number = min_number + (int) (Math.random() * (max_number - min_number + 1000));

            System.out.println("===========  app sleeping : ================"  +  Random_number  );
            Thread.sleep(Random_number);

            // 没有中英文意思的英文单词
            String english_words =  Get_english_words_if_chinese_is_null();

            // 这个单词的中文意思
            String chinese_mean = get_chinese_mean_from_baidu(english_words);

            // 更新数据库
            int state = update_chinese_mean(english_words , chinese_mean);

            System.out.println("===============" + state +  "=====================");
        }
    }

    /**
     *      no.1
     *      获取中文意思为  null 的英文单词
     */
    public static String Get_english_words_if_chinese_is_null() throws SQLException {

        System.err.println("获取中文意思为 null 的英文单词");

        Connection conn = getConn();
        Statement stmt = null;
        System.out.println(" 实例化Statement对象...");
        stmt = conn.createStatement();
        String sql = "SELECT * FROM `words_warehouse` where `chinese_meaning` is null LIMIT 0,1;";

        ResultSet rs = stmt.executeQuery(sql);

        String word = null;
        String chinese_meaning = null;

        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            word = rs.getString("word");
            chinese_meaning = rs.getString("chinese_meaning");

            System.err.printf("单词:  %-30s 中文意思:  %s \n", word, chinese_meaning);
        }

        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }

        return word;
    }




    /**
     *  no.2
     * 从百度上面获取英文单词的中文意思
     * 传入: 英文单词
     */
    public static String get_chinese_mean_from_baidu(String English_words) throws IOException {

        final String url = "https://www.baidu.com/baidu?wd=" + English_words + "&tn=monline_4_dg&ie=utf-8";
        //                  https://www.baidu.com/baidu?wd=system&tn=monline_4_dg&ie=utf-8


        //先获得的是整个页面的html标签页面
        Document doc = Jsoup.connect(url).get();

        //获取阅读数量
        //  class 是 .
        //  id    是 #
        Elements elements = doc.select(".op_dict_text2");
        String chinese_mean = elements.text();
        System.out.println("中文意思：" + chinese_mean);

        return chinese_mean;
    }




    /**
     *  no.3
     * 更新 数据库中的单词
     */
    public static int update_chinese_mean(String word, String chinese_mean) throws SQLException {

        Connection conn = getConn();
        Statement stmt = null;
        System.out.println(" 实例化Statement对象...");
        stmt = conn.createStatement();
        String sql;

        sql = "UPDATE `words_warehouse` SET `chinese_meaning`='" + chinese_mean + "' WHERE `word`='" + word + "';";
        int status = stmt.executeUpdate(sql);

        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
        return status;
    }


    /**
     *      生成随机数, 随机暂停
     */
    public static int GET_ramdom_num() {
        int min_number = 5000;
        int max_number = 9999;
        int Random_number = min_number + (int) (Math.random() * (max_number - min_number + 1000));
        return Random_number;
    }
}
