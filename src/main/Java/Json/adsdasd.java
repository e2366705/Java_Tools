package Json;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adsdasd {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {









        String driver = "com.mysql.jdbc.Driver";                            //驱动名称
        String url = "jdbc:mysql://localhost:3306/study_english_database";  //数据库地址
        String table_name = "tb_derivative_word";                           //数据表
        String username = "root";                                           //数据库帐号
        String password = "root";                                           //数据库密码


        try{
            Class.forName(driver);//加载驱动程序
            Connection connection = DriverManager.getConnection(url, username, password);
            if(!connection.isClosed())
                System.out.println("数据库连接成功");
            Statement statement = connection.createStatement();//statement对象，执行数据库操作语句
            String sql = "   select * from   "+ table_name +" ; ";
            ResultSet rs = statement.executeQuery(sql);//执行sql语句并将结果保存在resultset

            //数据保存为JSON格式
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();//创建Arraylist
            while(rs.next()) {
                Map<String,String> map = new HashMap<String,String>();//要每次创建一个新的映射表，不然只会保存最后一组数据。
                map.put("word",rs.getString("word"));
                map.put("derivative_word",rs.getString("derivative_word"));
                list.add(map);//数据保存在list中
            }
            //用GSON包中的方法序列化json字符串
            Gson gson = new Gson();
            String jsonstr = null;
            jsonstr = gson.toJson(list);
            System.out.println(jsonstr);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }













    }
}



