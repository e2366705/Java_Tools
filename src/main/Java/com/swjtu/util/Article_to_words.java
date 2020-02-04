package com.swjtu.util;

public class Article_to_words {


    //
    // 并且统计一共有多少个单词 (汉字除外)
    public  void Start(String article_data) {
        String[] arr = article_data.split("[^a-zA-Z0-9]+");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("There are " + arr.length + " words in the line!");
    }
}
