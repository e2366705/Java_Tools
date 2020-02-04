package com.swjtu.util;


public class IS_Chinese {

    public String isChinese_(String english_words) {
        if (english_words == null){
            return "null___";
        }
        for (char c : english_words.toCharArray()) {
            if (isChinese(c)) {
                System.out.println("含有中文");
                return "has_chinese";          // 有一个中文字符就返回
            }
        }
        return "all_english";
    }

    // 判断一个字符是否是中文
    public boolean isChinese(char c) {
        return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
    }
}



