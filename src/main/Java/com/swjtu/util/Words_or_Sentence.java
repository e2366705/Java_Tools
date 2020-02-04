package com.swjtu.util;

//     判断是个单词 还是个句子
//     数字 > 1 , 就是句子
//     数字 = 1 , 就是单词
public class Words_or_Sentence {

    public int getWords(String text) {
        int count = 0;
        int len = text.length();
        int i, j;

        i = 0;
        while (true) {
            for (; i < len && text.charAt(i) == ' '; i++) ;
            if (i >= len) {
                break;
            }
            count++;
            for (; i < len && text.charAt(i) != ' '; i++) ;
        }
        return count;
    }
}


