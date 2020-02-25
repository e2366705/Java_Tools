package Json;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class AA_Json_Test {
    public static void main(String[] args) {

        IO io = new IO();
        HashMap<String, String> word_with_chinese = new HashMap<>();
        String json_data = io.Get_json("src/main/Java/Json/Json_file.txt");

        //  derivative_word          word

        try{
            JSONArray jsonArray = new JSONArray(json_data);
            for (int i=0; i < jsonArray.length(); i++)    {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String derivative_word = jsonObject.getString("derivative_word");
                String word = jsonObject.getString("word");

                word_with_chinese.put(word , derivative_word);
            }

            System.err.println(word_with_chinese.size());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
