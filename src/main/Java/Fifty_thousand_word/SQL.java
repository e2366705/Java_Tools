package Fifty_thousand_word;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL{

/*
-- 切换回
use `zzza9d2g5k4t1b0k`;

-- 创建表
CREATE TABLE IF NOT EXISTS `tb_dictionary`(
`english_word`   	VARCHAR(40)  UNIQUE   	NOT NULL DEFAULT '',
`chinese_meaning`   VARCHAR(250)	NOT NULL DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


zzza9d2g5k4t1b0k
tb_dictionary
english_word
chinese_meaning

*/

    @Test
    public void Test_insert() throws SQLException {
        SQL sql = new SQL();
        sql.Insert("z");
    }


    private static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/zzza9d2g5k4t1b0k?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
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

    public void Insert( String word) throws SQLException {
        Connection conn = getConn();
        String sql = "insert into `tb_dictionary`(english_word) values(?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, word);

        int i = 0;
        try {
            i = ps.executeUpdate();
            if (i == 1){
                System.out.printf("添加了  %-12s  条数据" , i);
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ps.close();
            conn.close();
        }
    }

}