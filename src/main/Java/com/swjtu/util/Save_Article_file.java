package com.swjtu.util;

import java.io.FileOutputStream;
import java.io.IOException;

public class Save_Article_file {

    public void Start(String Article_title, String Article_content) {

        Get_Date getdate = new Get_Date();
        String Date_now = getdate.getDate();


        String path = "src/main/java/com/hehe/Article/";
        String Article_file_name = Article_title + "__" + Date_now + ".txt";

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + Article_file_name);
            byte[] outStr = Article_content.getBytes("UTF-8"); // 读入字节数组，并指定编码方式
            fos.write(outStr); // 使用文件输出流写出到文件
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
