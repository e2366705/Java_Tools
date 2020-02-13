package Get_Clipboard_to_MySQL;

/*

            功能简介:
            把短句拆分成单个单词, 然后匹配正确的短句以及中文意思,
            然后更新到 MySQL数据库

                MySQL  单词
                word            phrase          chinese_meaning
                --------------------------------------------------
                wide            wide use of      广泛使用


                最后
 */

import org.junit.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Insert_word {

    @Test
    public void Insert_word() throws SQLException {
        SQL_ sql_ = new SQL_();
        String_YXB string_yxb = new String_YXB();
        Set<String> word_set = sql_.SELECT_phrase();
        HashMap<String, String> stringStringHashMap = sql_.SELECT_phrase_and_chinese_meaning();



        // 遍历 Set
        for(String word: word_set){
            for(String phrase: stringStringHashMap.keySet()){

                Set<String> phrase_words_set = string_yxb.Split_string(phrase);
                String chinese_meaning = stringStringHashMap.get(phrase);

                for(String phrase_word: phrase_words_set){
                    if (word.equals(phrase_word)){
                        sql_.Insert(word , phrase , chinese_meaning);
                    }
                }

            }
        }

        // 最后:   delete from `phrase` where `word` is null
        sql_.Delete_word_is_null();
    }
}


// 数据库操作
class SQL_{
    private static Connection getConn() {
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



    //      delete from `phrase` where `word` is null
    public void Delete_word_is_null() throws SQLException {
        Connection conn = getConn();
        String sql = "   delete from `tb_word` where `word` is null   ";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句
        int i = ps.executeUpdate();
        System.out.printf("成功删除  %-15s 条数据:", i);
        ps.close();
        conn.close();
    }



    //   insert into `phrase`(`word` , `phrase` , `chinese_meaning` ) VALUES(1,2,3)
    // insert 增加
    public void Insert(String word , String phrase , String chinese_meaning) throws SQLException {

        Connection conn = getConn();
        String sql = "  insert into `tb_word`(`word` , `phrase` , `chinese_meaning` ) VALUES(? , ? , ? )  ";

        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, word);
        ps.setString(2, phrase);
        ps.setString(3, chinese_meaning);

        try {
            int i = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ooops......数据重复了");
        }

//            System.out.printf("添加了  %-12s  条数据" , i);

        ps.close();
        conn.close();
    }




    //  SELECT `phrase` , `chinese_meaning` from `phrase` WHERE `word` is null
    // 返回: 短句 => 中文意思
    // key value
    public HashMap<String, String> SELECT_phrase_and_chinese_meaning() throws SQLException {
        Connection conn = getConn();
        Statement stmt = conn.createStatement();
        System.out.println(" 实例化Statement对象...");

        String sql = "  SELECT `phrase` , `chinese_meaning` from `tb_word` WHERE `word` is null  ";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();

            HashMap<String, String> stringStringHashMap = new HashMap<>();

            String temp = "";
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String phrase = rs.getString("phrase");
                String chinese_meaning = rs.getString("chinese_meaning");
                stringStringHashMap.put(phrase , chinese_meaning);
            }
            rs.close();
            stmt.close();
            conn.close();
            return stringStringHashMap;

        } catch (SQLException e) {
           // e.printStackTrace();
            System.err.println("没找到符合条件的数据...   =_=!!!    ");
            return null;
        }
    }




    //  SELECT `phrase` from `phrase` WHERE `word` is null
    // 返回:  拆分好的单词
    // 返回:  set
    public Set<String> SELECT_phrase() throws SQLException {
        Connection conn = getConn();
        Statement stmt = conn.createStatement();
        System.out.println(" 实例化Statement对象...");

        String sql = "  SELECT `phrase` from `tb_word` WHERE `word` is null  ";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句
        ResultSet rs = ps.executeQuery();

        String temp = "";
        String_YXB string_yxb = new String_YXB();

        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            String phrase = rs.getString("phrase");
            temp = temp + " " + phrase;
        }

        Set<String> set = string_yxb.Split_string(temp);

        rs.close();
        stmt.close();
        conn.close();
        return set;

    }
}


// 字符串 操作
class String_YXB{

    // 分割字符串  返回类型: Set (不可重复)
    public Set<String> Split_string(String str){
        String[] temp;
        HashSet<String> hashSet = new HashSet<>();
        String delimeter = " ";  // 指定分割字符

        temp = str.split(delimeter); // 分割字符串

        for(int i =0; i < temp.length ; i++){
//            System.out.println(temp[i]);
            hashSet.add(temp[i]);
        }
        return hashSet;

    }

}