package Tools_7; /**
 * @author YXB
 * @date 2020/2/5 17:40
 */

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;

/*
 *
 */
public class Statistics_words {



    @Test
    public void Test0(){

        Check_String check_string = new Check_String();
        String str = "“i’m, nana’s, ’ ‘at, ’ ‘as, foo  - ,  twice a, \"sadas\" ce of, ties, 1234567890 \\ ’";

        // 第一种方式:
        String regEx="[\n`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；\\-：”“’。，\\\\ 、？0123456789\"]";
        String newString = str.replaceAll(regEx," ");
        System.out.println(newString);

        // 第二种方式:
        String temp = "";
        temp = str.replaceAll("[^a-zA-Z]", " ");
        System.out.println(temp);

        // 第三种:
    }



    /*
            统计这本小说所有的单词,
            运行结果: 3w 多个单词

            转换成小写       (完成)
            去掉特殊字符      (完成)

     */

    @Test
    public void Test_Main() throws SQLException, InterruptedException {

        IO io = new IO();
        Check_String check_string = new Check_String();
        HashSet<String> hashSet = new HashSet<>();

        String temp = "";

        // 读取字符串
        temp = io.Read();

        temp =  check_string.Str_toLowerCase(temp);         // 转换成小写
        temp =  check_string.Remove_special_word(temp);     // 去掉特殊字符
        hashSet = check_string.Split(temp);                 // 分割

        System.out.println(hashSet.size());

        for(String value: hashSet){
            System.out.println(value);
        }





    }


}


class Check_String{


    // 第一步:  转换成小写
    public String Str_toLowerCase(String str){
        String temp = "";
        temp=  str.toLowerCase();
        return temp;
    }


    // 第二步:  去掉 特殊字符
    public String Remove_special_word(String str){

        String regEx="[\n`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；\\-：”“’。，\\\\ 、？0123456789\"]";
        String newString = str.replaceAll(regEx," ");

        return newString;

    }

    // 第三步:  分割字符串
    public HashSet<String> Split(String str){
        String[] temp;
        String delimeter = " ";  // 指定分割字符
        temp = str.split(delimeter); // 分割字符串

        HashSet<String> hashSet = new HashSet<>();

        // 普通 for 循环
        for(int i =0; i < temp.length ; i++){
            hashSet.add(temp[i]);
        }
        return hashSet;
    }

    public boolean Check(String str){
        boolean Whether_all_english = str.matches("[a-zA-Z]+");
        return Whether_all_english;
    }



}


class IO{


    private String file_name = "english_novel.txt";
    private String path = "src\\main\\Java\\";
    private String file_path = path + file_name;


    public String Read(){
        File file = new File(file_path);
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
        return new String(filecontent);

    }



}


class SQL_YXB{

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












    public HashMap<String, String> SELECT_ALL() throws SQLException {
        Connection conn = getConn();
        Statement stmt = conn.createStatement();
        System.out.println(" 实例化Statement对象...");

        String sql = "  SELECT `word` , `chinese_meaning`  FROM `words_warehouse`  ";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句
        ResultSet rs = ps.executeQuery();

        HashMap<String, String> stringStringHashMap = new HashMap<>();

        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            String word = rs.getString("word");
            String chinese_meaning = rs.getString("chinese_meaning");
            stringStringHashMap.put(word , chinese_meaning);
        }
        rs.close();
        stmt.close();
        conn.close();
        return stringStringHashMap;


    }
}


class SQL_yxb_2{


    private static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/english_word?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
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




    public void Insert(String word , String chinese_meaning , String level) throws SQLException {

        Connection conn = getConn();
        String sql = "   INSERT into `tb_word`(`word` , `chinese_meaning` , `level`) VALUES(? , ? , ?)  ";

        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, word);
        ps.setString(2, chinese_meaning);
        ps.setString(3, level);

        int i = 0;
        try {
            i = ps.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("数据出现重复");
        }


        ps.close();
        conn.close();
    }



    public void Insert_word(String word) throws SQLException {

        Connection conn = getConn();
        String sql = "   INSERT into `tb_word`(`word`) VALUES(?)  ";

        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, word);

        int i = 0;

        try {
            i = ps.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("数据出现重复");
        }
        ps.close();
        conn.close();
    }




}