package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import utils.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiUtils extends LogUtils {

    protected Response response;// resposta da request
    protected JSONObject body;//caracteristicas do request
    protected String uri;//url da api
    protected String id;//id do personagem/pet no body da request da API
    protected Map<String, String> headers = new HashMap<>();//caracteristicas do request
    protected Map<String, String> params = new HashMap<>();//caracteristicas do request


    //documenta no report todas as informações da request
    public void log(String verbo){

        super.logInfo(" ****** Dados enviados no request ******");
        super.logInfo(verbo + " " + uri+id);
        super.logInfo("Body : \n" + body);
        super.logInfo("Headers : " + headers);
        super.logInfo("Params : " + params);

        super.logInfo(" ****** Dados Recebidos ******");
        super.logInfo("Status code: " + response.statusCode());
        super.logInfo("Payload recebido: \n" + response.asPrettyString());
        super.logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.SECONDS) + " segundos");
    }

}
