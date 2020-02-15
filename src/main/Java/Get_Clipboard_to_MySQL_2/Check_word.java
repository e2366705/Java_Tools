package Get_Clipboard_to_MySQL_2;

/**
 * @author YXB
 * @date 2020/2/15 17:16
 */
public class Check_word {
    public boolean check_copy_string(String copy_string){
        if (copy_string == null){
            return false;
        }

        // 2-1 长度是否太长?
        System.out.println(copy_string.length());
        if (copy_string.length() > 60){
            return false;
        }

        // 2-2  【单词是否全为英文】返回true  否则false  (\\s 表示空格, 意思是可以有空格...)
        if (copy_string.matches("[a-zA-Z\\s]+")) {
            return true;
        }else {
            return false;
        }
    }


    //      i miss you  转换成  i%20miss%20you
    public String Check(String word){
        word = word.replaceAll(" ", "%20");
        System.out.println("转换后的字符串:            "+word);
        return word;
    }


    public boolean Has_chinese(String str){
        if (str.getBytes().length == str.length()) {
            System.err.println("全是英文....");
            return false;
        }else {
            System.out.println("是中文....");
            return true;
        }
    }




}
