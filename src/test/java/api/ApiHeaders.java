package api;

import java.util.HashMap;
import java.util.Map;

public class ApiHeaders {

    Map <String, String> headers = new HashMap<>();

    //Criação do headers
    public Map<String, String> petstoreHeaders(){
        headers.put("api_key","special-key");
        headers.put("accept","application/json");
        headers.put("Content-Type","application/json");

        return headers;
    }

    public Map<String, String> placeHolderHeaders(){
        headers.put("Content-Type","application/json; charset=UTF-8");

        return headers;
    }

}
