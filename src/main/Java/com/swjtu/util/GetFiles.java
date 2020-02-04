package com.swjtu.util;

import java.io.File;
import java.util.ArrayList;

public class GetFiles {

    public ArrayList<String> Start(String path) {

        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        System.out.println(tempList);

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {

                int length =  tempList[i].toString().length();

                String file_name = tempList[i].toString().substring(31, length);

                System.out.println("文 件： " + file_name);
                files.add(file_name);
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }
}


