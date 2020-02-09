package Novel_word_count;

import org.junit.Test;
import java.io.*;
import java.sql.*;

import java.lang.reflect.Array;
import java.util.*;

/*
    为了防止太多没意义的单词混淆视线,:
        比如一些人名  (Esther Belkin. 埃丝特·贝尔金)
        一些地名:     California 加利福尼亚

        该程序会将一切带有大写字母的单词 , 直接滤过(人名和地名都有大写字母的存在, 所以直接跳过)

        只留下单纯全是小写字母的单词, 然后再去分析词频,

        不然一本小说里面, 光是人名就出现了好几百次, 这样子统计出来的词频没有意义...

        ps:
            src\main\Java\Novel_word_count\Novel
            这个文件夹里面存放的是英文文本数据, 用来作为数据样本,

 */
public class A_Index {

    @Test
    public void Testsql() throws SQLException {
        SQL sql = new SQL();
        sql.Insert("num" , "word" , "times");
    }


    @Test
    public void Main__() throws IOException, InterruptedException, SQLException {
        Check_string check_string = new Check_string();
        IO io = new IO();
        List_map list_map = new List_map();
        String temp = "";
        temp = io.Read_file();

//        temp = check_string.Remove_non_english_letters(temp);

        List<String> list_1 = new ArrayList<>();
        List<String> temp_list = new ArrayList<>();

        String file_path = "";

//        for (int i = 6; i < 9; i++) {
//            file_path = "src\\main\\Java\\Novel_word_count\\Novel\\Novel_"+ i +".txt";
//            list_1 = list_map.Get_word_list(file_path);
//            list_3.addAll(list_1);
//        }


        for (int i = 1; i < 7; i++) {
            Thread.sleep(2121);
            file_path = "src\\main\\Java\\Novel_word_count\\Novel\\Novel ("+ i +").txt";
            list_1 = list_map.Get_word_list(file_path);
            temp_list.addAll(list_1);
        }

        Map<String, Integer> stringIntegerMap = list_map.Get_word_map(temp_list);

        list_map.SortMap(stringIntegerMap);


//        System.out.println(temp);
    }
}


class Check_string{

    // 判断首字母是否大写?
    // 大写 => true
    public boolean First_letter_is_capitalized(String str) {
        char[] chars = str.toCharArray();
        return Character.isUpperCase(chars[0]);
    }


    // 去掉非英文字母 , 并且用 ' ' 来代替
    public String Remove_non_english_letters(String str){
        String temp = "";
        temp = str.replaceAll("[^a-zA-Z]", " ");
        return temp;
    }

    // 是否包含大写字母?
    // 算了, 这个功能好像没必要...
    public boolean Contain_Capital_word(String str){
        String word = "asda1231^&%^&*er,..<><>[]]()*/-+sdfsdfAAA";
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (Character.isUpperCase(c)){
                System.out.println("包含");
            }
        }
        return true;
    }
}


// 读写文件
class IO{
    private String file_name = "Novel_7.txt";
    private String path = "src\\main\\Java\\Novel_word_count\\Novel\\";
    private String file_path = path + file_name;

    /**
     *   读 文本文件
     *   推荐 ^_^
     */
    @Test
    public String Read_file(){
        /**
         来源 :  http://lvhongqiang.com/blog5.html
         我们都习惯于一次把文本的原始内容直接读取到内存中再做处理（暂时不考虑内存大小），这样做效率也会提高。
         很多人用readline()之类的方法，可能需要反复访问文件，
         而且每次readline()都会调用编码转换，降低了速度，
         所以，在已知编码的情况下，
         按字节流方式先将文件都读入内存，
         再一次性编码转换是最快的方式

         这种方法的好处是读取的内容可以彻底保持文件的原貌，
         而且速度应该是最快的，因为只需要调用一次文件访问，字符编码转换也只需要一次
         */

        File file = new File(file_path);

        Long filelength = file.length();     //获取文件长度

        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(filecontent);
    }
}




class List_map{
    /*
            把出现的单词放在列表里面
    */
    public List<String> Get_word_list  (String file_path) throws IOException {

        Check_string check_string = new Check_string();

        BufferedReader br = new BufferedReader(new FileReader(file_path));
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表
        String readLine = null;
        while((readLine = br.readLine()) != null){
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");  //过滤出只含有字母的
            for (String word : wordsArr1) {
                if(word.length() != 0){  //去除长度为0的行

                    boolean b =check_string.First_letter_is_capitalized(word);   // 判断首字母是否大写

                    if (b == false){
                        lists.add(word);
                    }
                }
            }
        }
        br.close();
        return lists;
    }



    /*
     *      单词词频的统计
     */
    public Map<String, Integer> Get_word_map  (List<String> list) throws IOException {
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //存储单词计数信息，key值为单词，value为单词数

        //单词的词频统计
        for (String li : list) {
            if(wordsCount.get(li) != null){
                wordsCount.put(li,wordsCount.get(li) + 1);
            }else{
                wordsCount.put(li,1);
            }
        }
        return wordsCount;
    }



    //按value的大小进行排序
    public void SortMap(Map<String,Integer> oldmap) throws InterruptedException, SQLException {
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();  //降序
            }
        });

        SQL sql = new SQL();

        for(int i = 0; i < 10002; i++){
            Thread.sleep(120);

            // 将数据 insert 到数据库里面去
            // sql.Insert("No."+i , list.get(i).getKey()   ,  String.valueOf(list.get(i).getValue()) );
            System.out.println( i + "  " + list.get(i).getKey()  + "  " + list.get(i).getValue());
        }
    }
}


class SQL{

    private static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/english_word_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
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

    public void Insert(String num , String word , String times) throws SQLException {
        Connection conn = getConn();
        String sql = "insert into `tb_word`(num , word , times) values(?, ? , ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.println(ps.toString());      // 输出 SQL 语句

        ps.setString(1, num);
        ps.setString(2, word);
        ps.setString(3, times);

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
        }
    }

}