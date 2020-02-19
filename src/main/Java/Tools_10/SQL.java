package Tools_10;

import java.sql.*;

/*
 * @author YXB
 * @date 2020/2/19 12:51
 */
public class SQL{

    private Settings settings;

    public SQL() {
    }

    public SQL(Settings settings) {
        this.settings = settings;
    }



    private Connection getConn() {

        String database = settings.getDatabase();
        System.err.println("数据库名称为:      " + database);


        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/"+ database +"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
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





    public String SELECT_word() throws SQLException {
        Connection conn = getConn();

        String table_name = settings.getTable();
        System.err.println("表名为:     " + table_name);

        String English_word_column = settings.getEnglish_word_column();
        String Chinese_meaning_column = settings.getChinese_meaning_column();

        Statement stmt = conn.createStatement();
        System.out.println(" 实例化Statement对象...");

        //               SELECT * FROM `word` where `chinese_meaning` = ''   limit 0,1
        String sql = "   SELECT * FROM `"+ table_name +"` where `"+ Chinese_meaning_column +"` = '' limit 0,1   ";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句
        ResultSet rs = ps.executeQuery();

        String word = "";

        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            word = rs.getString(English_word_column);
        }
        System.err.printf(" 当前的单词是   %-30s  \n", word);
        rs.close();
        stmt.close();
        conn.close();

        return word;
    }





    // 更新英文单词的中文意思
    public int Update_chinese_meaning(String english_word , String chinese_meaning) throws SQLException {
        Connection conn = getConn();

        String table_name = settings.getTable();
        System.err.println("表名为:     " + table_name);

        String English_word_column = settings.getEnglish_word_column();
        String Chinese_meaning_column = settings.getChinese_meaning_column();


        Statement stmt = conn.createStatement();
        System.out.println(" 实例化Statement对象...");
        String sql = "UPDATE `"+ table_name +"` SET `"+ Chinese_meaning_column +"`=? WHERE `"+ English_word_column +"`=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句
        ps.setString(1, chinese_meaning);
        ps.setString(2, english_word);

        int status = ps.executeUpdate();

        System.out.printf("更新了  %-15s 条数据:", status);

        stmt.close();
        conn.close();
        return status;
    }







}

