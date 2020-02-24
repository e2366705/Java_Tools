package Tools_11;
import java.io.*;
import java.util.Properties;




import java.io.InputStream;
import java.util.Properties;


public class Read_Configure {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/Java/Tools_11/config.properties"));
        properties.load(bufferedReader);
        // 获取key对应的value值
        String database = properties.getProperty("database");
        String password = properties.getProperty("password");
        System.out.println(database);
        System.out.println(password);
    }
}

