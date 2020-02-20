package Get_Clipboard_to_MySQL_2;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author YXB
 * @date 2020/2/20 11:48
 */
public class JSON{
    public String Json(String json_data){

        JSONObject jsonobj = new JSONObject(json_data);
        JSONArray jsonArray = (JSONArray) jsonobj.get("translation");
        String chinese_meaning = jsonArray.getString(0);
        System.err.println("中文意思是 :    " + chinese_meaning);
        return chinese_meaning;
    }
}