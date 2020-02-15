package Java_USE_python;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequest {

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + param;
        try {
            // 建立URL
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "keep-alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
            // 建立实际连接
            connection.connect();
            // 获得所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> m : map.entrySet()) {
                List<String> value = m.getValue();
                String key = m.getKey();

//              System.out.println(key + "----->" + value);
            }

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /*
     * unicode转中文
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch+"" );
        }
        return str;
    }

    public static void main(String[] args) {
        // 发送 GET 请求
        try {
            String s = HttpRequest.sendGet("http://ip.taobao.com/service/getIpInfo.php?ip=", "116.24.101.170");
            // s = java.net.URLDecoder.decode(s, "UTF-8");
            // s = new String(s.getBytes("ISO-8859-1"), "UTF-8");

            System.out.println((char)65);

            System.out.println(s);
            System.out.println(unicodeToString(s));
            JSONObject jsonObject = JSON.parseObject(s);
            System.out.println(jsonObject);
            Object object = jsonObject.get("data");

            System.out.println(object);
//          System.out.println(unicodeToString(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






