package Tools_10;

/**
 * @author YXB
 * @date 2020/2/19 15:58
 */
public class Random_number {
    public int Get_random_number(){
        int min_number = 5000;
        int max_number = 9999;
        int Random_number = min_number + (int) (Math.random() * (max_number - min_number + 1000));
        return Random_number;
    }
}
