package Get_Clipboard_to_MySQL_2;

import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

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





    @org.junit.Test
    public void Test_Translete_360() throws InterruptedException, MalformedURLException, JavaLayerException, FileNotFoundException {
        Translate translate = new Translate();
        String miss = translate.Translate_360("miss%20you");
        System.out.println(miss);



    }
}
