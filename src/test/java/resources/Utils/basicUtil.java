package resources.Utils;

import io.restassured.path.json.JsonPath;

public class basicUtil {

    public String jsonPathResp(String response,String key){
        JsonPath jp = new JsonPath(response);
        String value = jp.get(key).toString();
        return value;
    }
}
