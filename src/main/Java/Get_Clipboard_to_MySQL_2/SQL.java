package Get_Clipboard_to_MySQL_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author YXB
 * @date 2020/2/15 17:06
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



    public int Insert(String phrase , String chinese_meaning) throws SQLException {
        Connection conn = getConn();
        String sql = "insert into `tb_word`(phrase , chinese_meaning) values(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, phrase);
        ps.setString(2, chinese_meaning);

        int i = 0;
        try {
            i = ps.executeUpdate();
            System.out.printf("添加了  %-12s  条数据" , i);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("数据库已经存在.....");
        }
        return i;
    }


}
