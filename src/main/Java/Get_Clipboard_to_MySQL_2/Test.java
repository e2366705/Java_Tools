package Get_Clipboard_to_MySQL_2;

/**
 * @author YXB
 * @date 2020/2/15 17:13
 */
public class Test {

    @org.junit.Test
    public void Test1(){
        String str = "my friends gardens and we've become closer '' * & ^ % # @ ! ` ~  士 123123123123  ";
        if (str.getBytes().length == str.length()) {
            System.err.println("english....");
        }else {
            System.err.println("those string has chinese....");
        }
    }
}
