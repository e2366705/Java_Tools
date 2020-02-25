package Fifty_thousand_word;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
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

        insert 数据库暂时没有实现

 */
public class A_Index {

    @Test
    public void Testsql() throws SQLException {
        System.out.println("A_Index.Testsql");
    }


    public static void main(String[] args)  throws IOException, InterruptedException, SQLException {
        Check_string check_string = new Check_string();
        Get_file_name get_file_name = new Get_file_name();
        List_map list_map = new List_map();

        List<String> list_1 = new ArrayList<>();
        List<String> temp_list = new ArrayList<>();



        //   C:\Users\SpringBoot\IdeaProjects\Java_Tools\src\main\Java\Novel_word_count\Novel\
        String Folder_path = "";
        Scanner scan_prefix = new Scanner(System.in);
        System.err.println("----------------------------    请输入存放 txt 文本文件的文件夹路径 :  ");
        // 判断是否还有输入
        if (scan_prefix.hasNextLine()) {
            Folder_path = scan_prefix.nextLine();
        }

        // 读取这个文件夹里面的 txt文件
        ArrayList<String> files_list = get_file_name.getFiles(Folder_path);


        for(String file_path : files_list){
            Thread.sleep(2500);
            list_1 = list_map.Get_word_list(file_path);
            temp_list.addAll(list_1);
        }

        Map<String, Integer> stringIntegerMap = list_map.Get_word_map(temp_list);
        list_map.SortMap(stringIntegerMap);
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
}


// 某一文件夹里面的所有 txt 文件
class Get_file_name{
    public ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文 件： " + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }
}


class List_map{
    // 把出现的单词放在列表里面
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

        for(int i = 0; i < 66666; i++){
            Thread.sleep(10);
            //         1201       danger      出现次数 : 34
            System.out.printf("%-8s   %-8s    出现次数 : %-8s \n", i , list.get(i).getKey() , list.get(i).getValue());
            sql.Insert(list.get(i).getKey());
        }
    }
}

