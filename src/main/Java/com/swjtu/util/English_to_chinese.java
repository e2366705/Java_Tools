package com.swjtu.util;


import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.BaiduTranslator;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class  English_to_chinese {

//                                        中文 => 英文
//    public void Chinese_to_english(){
//        //  https://github.com/hujingshuang/MTrans
//        Querier<AbstractTranslator> querierTrans = new Querier<>();  // 获取查询器
//
//        querierTrans.setParams(LANG.ZH, LANG.EN, "如果这都不算爱，我有什么好悲哀！");// 设置参数
//
//        querierTrans.attach(new GoogleTranslator());// 向查询器中添加 Google 翻译器
//
//        List<String> result = querierTrans.execute();// 执行查询并接收查询结果
//
//        for (String str : result) {
//            System.out.println(str);
//        }
//    }


    public String English_Sentence_to_chinese(String english_Sentence){

        //  https://github.com/hujingshuang/MTrans
        Querier<AbstractTranslator> querierTrans = new Querier<>();  // 获取查询器

        querierTrans.setParams(LANG.EN, LANG.ZH, english_Sentence);// 设置参数

        querierTrans.attach(new BaiduTranslator());// 向查询器中添加 Google 翻译器

        List<String> result = querierTrans.execute();// 执行查询并接收查询结果

//        for (String str : result) {
//            System.out.println(str);
//        }
        return result.get(0);
    }





    private final static String PreUrl = "http://www.baidu.com/s?wd=";                        //百度搜索URL
    private final static String TransResultStartFlag = "<span class=\"op_dict_text2\">";      //翻译开始标签
    private final static String TransResultEndFlag = "</span>";                               //翻译结束标签

    //传入要搜索的单词
    public String getTranslateResult(String english_words) throws Exception {

        System.out.println("正在进行联网查询.......");

        URL url = new URL(PreUrl + english_words);            //生成完整的URL

        System.out.println("完整的 URL 是:   " + url);

        // 打开URL
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        // 得到输入流，即获得了网页的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        int flag = 1;
        String line;
        String preLine = "";
        String content = "";          //翻译结果

        //获取翻译结果的算法
        while ((line = reader.readLine()) != null) {
            if (preLine.contains(TransResultStartFlag) && !line.contains(TransResultEndFlag)) {
                content += line.replaceAll("　| ", "") + "\n";   //去掉源代码上面的半角以及全角字符
                flag = 0;
            }
            if (line.contains(TransResultEndFlag)) {
                flag = 1;
            }
            if (flag == 1) {
                preLine = line;
            }
        }
        return content;
    }

}