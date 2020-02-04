package Tools_5;

import org.junit.Test;

import java.sql.*;

/**
 * @author YXB
 * @date 2020/1/27 20:10
 */
public class SQL {



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

    // select     找到 MySQL 数据库 `audio` 为 null 的单词, 说明还没有音频文件
    @Test
    public String Find_all_limit() throws SQLException {
        Connection conn = getConn();
        Statement stmt = null;
        System.out.println(" 实例化Statement对象...");
        stmt = conn.createStatement();
        String sql = "SELECT * FROM `words_warehouse` where `audio` is null LIMIT 0, 1 ;";

        ResultSet rs = stmt.executeQuery(sql);

        String word = null;

        // 展开结果集数据库
        while (rs.next()) {
            // 通过字段检索
            word = rs.getString("word");
            String chinese_meaning = rs.getString("chinese_meaning");

            System.out.printf("单词:  %-30s 中文意思:  %s \n", word, chinese_meaning);
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

    // update
    public int update_audio(String word) throws SQLException {

        Connection conn = getConn();
        Statement stmt = null;
        stmt = conn.createStatement();
        String sql;

        sql = "UPDATE `words_warehouse` set `audio` = 1 WHERE `word` = '" + word + "';";
        int status = stmt.executeUpdate(sql);

        System.out.printf("更新了:   %d   条" , status);

        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
        return status;
    }

    int count = 0;

    // insert
    public int Insert(String word) throws SQLException {
        Connection conn = getConn();
        int i = 0;
        String sql = "INSERT into `words_warehouse`(`word`) VALUES('" + word + "');";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            if (i > 0) {
                System.out.println("成功插入一条数据:   " + word);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            conn.close();
        }
        return count;
    }
}
