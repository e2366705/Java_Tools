package Java_USE_python;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import java.io.IOException;
/**
 * @author YXB
 * @date 2020/2/15 16:34
 */
public class java_spider {




        public static String readUrl(String url) {
            PostMethod method = new PostMethod(url);
            String res = null;
            try {
                new HttpClient().executeMethod(method);
                res = new String(method.getResponseBodyAsString().getBytes(), "utf8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(readUrl("https://fanyi.so.com/index/search?eng=1&validate=&ignore_trans=0&query=i miss you"));
        }
}
