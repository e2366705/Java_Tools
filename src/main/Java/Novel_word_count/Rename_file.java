package Novel_word_count;



import java.io.File;
import java.util.Scanner;

public class Rename_file {

    public static void main(String[] args) {

        String file_path = "";
        String prefix = "";
        String Suffix = "";

        Scanner scan_file_path = new Scanner(System.in);
        // 从键盘接收数据
        // nextLine方式接收字符串
        System.err.println("----------------------------   请输入文件夹路径:  ");
        // 判断是否还有输入
        if (scan_file_path.hasNextLine()) {
            file_path = scan_file_path.nextLine();
//            System.out.println("文件夹路径为:  " + file_path);
        }


        Scanner scan_prefix = new Scanner(System.in);
        System.err.println("----------------------------    请输入你要命名的名字的前缀 :  ");
        // 判断是否还有输入
        if (scan_prefix.hasNextLine()) {
            prefix = scan_prefix.nextLine();
//            System.out.println(prefix);
        }


        Scanner scan_Suffix = new Scanner(System.in);
        System.err.println("----------------------------   请输入文件的后缀 :  ");
        // 判断是否还有输入
        if (scan_Suffix.hasNextLine()) {
            Suffix = scan_Suffix.nextLine();
//            System.out.println(Suffix);
        }


        scan_file_path.close();
        scan_prefix.close();
        scan_Suffix.close();

        //                    Novel_     .txt
        renameFiles(file_path,prefix , Suffix);
        System.out.println("执行完成");
    }

    /**
     * dirPath  文件加路径      比如: C:\Users\SpringBoot\IdeaProjects\Java_Tools\src\main\Java\Tools_6\TXT\
     * prefix   文件名         比如: xxx
     * Suffix   文件后缀       比如: .Java
     */
    public static void renameFiles(String dirPath,String prefix , String Suffix){
        File file=new File(dirPath);
        if(!file.isDirectory()){
            return;
        }
        File files[]=file.listFiles();
        int index=1;

        for(File f:files){
            File newFile=new File(dirPath,prefix+String.valueOf(index++) + Suffix);
            f.renameTo(newFile);
        }
    }
}
