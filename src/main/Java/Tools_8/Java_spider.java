package Tools_8;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/*
 * 封装的CET请求
 */
public class Java_spider {
    private static String url = "http://www.chsi.com.cn/cet/query?zkzh=NUM&xm=NAME";
    private static Map<String, String> header = new HashMap<String, String>();

    public  static Cet46 getCet46Score(String examNumber, String examName) {
        String result = "";
        Cet46 cet46 = new Cet46();
        url = url.replace("NAME", examName);
        url = url.replace("NUM", examNumber);
        header.put("Accept", "image/png, image/svg+xml, image/jxr, image/*; q=0.8, */*; q=0.5");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-Hans-CN, zh-Hans; q=0.5");
        header.put("Connection", "Keep-Alive");
        header.put("Cookie", "AQAAAIhMp3BbugwAKRFC2uesy+2iO0Lw");
        header.put("Host", "www.chsi.com.cn");
        header.put("Referer", url);
        header.put("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393");

        try {
            Document html = Jsoup.connect(url).headers(header).get();
            result = html.text().substring(html.text().indexOf("结果 姓 名"), html.text().indexOf("口试成绩")).trim()
                    .replace("：", "");
            String[] array = result.split(" ");
            cet46.setName(array[2]);
            cet46.setCertificateNumber(array[9]);
            cet46.setSchool(array[4]);
            cet46.setResult(array[11]);
            cet46.setType(array[6]);
            cet46.setListenScore(array[13]);
            cet46.setWrittingAndTranslatingScore(array[17]);
            cet46.setReadingScore(array[15]);
        } catch (Exception e) {
            System.err.println("[查询出错,请认真查看是否填写正确!]");
            System.exit(1);
        }
        return cet46;
    }



    public static void main(String[] args) {
        Cet46 cet46 = Java_spider.getCet46Score("考号", "姓名");
        System.out.println(cet46);
    }






}


class Cet46 {
    private String name;
    private String school;
    private String type;
    private String listenScore;
    private String readingScore;
    private String writtingAndTranslatingScore;
    private String certificateNumber;
    private String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getListenScore() {
        return listenScore;
    }

    public void setListenScore(String listenScore) {
        this.listenScore = listenScore;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(String readingScore) {
        this.readingScore = readingScore;
    }

    public String getWrittingAndTranslatingScore() {
        return writtingAndTranslatingScore;
    }

    public void setWrittingAndTranslatingScore(String writtingAndTranslatingScore) {
        this.writtingAndTranslatingScore = writtingAndTranslatingScore;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String toString() {
        return "{name='" + this.name + '\'' + ", school='" + this.school + '\'' + ", type='" + this.type + '\''
                + ", listenScore='" + this.listenScore + '\'' + ", readingScore='" + this.readingScore + '\''
                + ", writtingAndTranslatingScore='" + this.writtingAndTranslatingScore + '\'' + ", certificateNumber='"
                + this.certificateNumber + '\'' + ", result='" + this.result + '\'' + '}';
    }

}
