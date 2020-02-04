package com.swjtu.util;


import java.io.*;

/*
        获取文章的内容
 */
public class Get_Article_content {

    public StringBuffer Get_content(String file_name) {
        String path = "src/main/java/com/hehe/Article/";
        String file_path = path + file_name;

        /**
         推荐这种方式:
         原因: 换行符正常导入 , 文本文件有换行符, 文本文件保持原样
         */

        int count = 0;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file_path);
        } catch (FileNotFoundException e) {
            System.out.println("can not find it");
            System.exit(1);
        }

        long num = 0;
        StringBuffer Article_content =  new StringBuffer();
        try {
            while ((count = in.read()) != -1) {
                Article_content.append((char) count);
//                System.out.print((char) count);
//                System.out.println(count);    输出asci码
                num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Article_content);
        System.out.println("\n字符个数：" + num);

        return Article_content;
    }
}



