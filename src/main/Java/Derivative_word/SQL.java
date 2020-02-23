package Derivative_word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void Insert(String word , String  Derivative_word) throws SQLException {
        Connection conn = getConn();
        String sql = "insert into `tb_derivative_word`(word, derivative_word) values(?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, word);
        ps.setString(2, Derivative_word);

        int i = 0;
        try {
            i = ps.executeUpdate();
            if (i == 1){
                System.out.printf("添加了  %-12s  条数据     " , i);
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("插入数据重复...");
        }
    }
}
