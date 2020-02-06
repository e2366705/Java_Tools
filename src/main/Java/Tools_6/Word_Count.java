package Tools_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

/**
 * 实现从文件中读入英文文章，统计单词个数,并按值从大到小输出
 *      全部单词转换成小写 (未完成)
 *      去掉特殊字符      (完成)
 */

public class Word_Count {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("src\\main\\Java\\english_novel.txt"));
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表
        String readLine = null;
        while((readLine = br.readLine()) != null){
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");  //过滤出只含有字母的
            for (String word : wordsArr1) {
                if(word.length() != 0){  //去除长度为0的行
                    lists.add(word);
                }
            }
        }
        br.close();

        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //存储单词计数信息，key值为单词，value为单词数

        //单词的词频统计
        for (String li : lists) {
            if(wordsCount.get(li) != null){
                wordsCount.put(li,wordsCount.get(li) + 1);
            }else{
                wordsCount.put(li,1);
            }
        }
        SortMap(wordsCount);    //按值进行排序
    }


    //按value的大小进行排序
    public static void SortMap(Map<String,Integer> oldmap){
        ArrayList<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(oldmap.entrySet());

        Collections.sort(list,new Comparator<Entry<String,Integer>>(){
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();  //降序
            }
        });

        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
        }
    }
}