package Tools_6;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 实现从文件中读入英文文章，统计单词个数,并按值从大到小输出
 *      全部单词转换成小写 (未完成)
 *      去掉特殊字符      (完成)
 */

public class Word_Count_YXB {

    @Test
    public  void TEST__2() throws Exception {

        //   找出 首字母为 大写的 单词的 词频

        List_map list_map = new List_map();

        List<String> list = list_map.Get_word_list();

        Map<String, Integer> stringIntegerMap = list_map.Get_word_map(list);

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
}






class List_map{
/*
        把出现的单词放在列表里面
*/
    public List<String> Get_word_list  () throws IOException {

        Check_string check_string = new Check_string();

        BufferedReader br = new BufferedReader(new FileReader("src/main/Java/Tools_6/TXT/Novel_3.txt"));
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表
        String readLine = null;
        while((readLine = br.readLine()) != null){
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");  //过滤出只含有字母的
            for (String word : wordsArr1) {
                if(word.length() != 0){  //去除长度为0的行

                    boolean b =check_string.First_letter_is_capitalized(word);   // 判断首字母是否大写

                    if (b){
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
    public void SortMap(Map<String,Integer> oldmap){
        ArrayList<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(oldmap.entrySet());

        Collections.sort(list,new Comparator<Entry<String,Integer>>(){
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();  //降序
            }
        });

        for(int i = 0; i<list.size(); i++){
            System.out.println( i + "  " + list.get(i).getKey()+ "  " +list.get(i).getValue());
        }
    }
}