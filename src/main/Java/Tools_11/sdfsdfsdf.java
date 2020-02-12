package Tools_11;

/**
 * @author YXB
 * @date 2020/2/11 22:08
 */
public class sdfsdfsdf {

    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if (osName.startsWith("Mac OS")) {
            System.err.println("这是苹果电脑 MacOS...");
        } else if (osName.startsWith("Windows")) {
            // windows
            System.err.println("这是 Windows 系统...");
        } else {
            // unix or linux
            System.err.println("unix or Linux...");
        }
    }



}
