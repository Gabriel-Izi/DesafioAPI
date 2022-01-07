package steps;

import api.ApiBody;
import api.ApiHeaders;
import api.ApiRequests;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONObject;
import user.PublicacaoLombok;
import utils.PropertiesUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceHolderSteps extends ApiRequests {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    ApiBody apiBody = new ApiBody();
    PublicacaoLombok publicacaoEnviada;
    Faker faker = new Faker();

//Patch------------------------------------------------------------------------------------------------------------
    @Dado("que tenho dados de acesso validos na API JSONPlaceHolder")
    public void que_tenho_dados_de_acesso_validos_na_api_json_place_holder() {

        super.headers = apiHeaders.placeHolderHeaders();

    }

    @Quando("modifico dados em uma publicacao")
    public void modifico_dados_em_uma_publicacao() {

        super.uri = prop.getProp("uri_placeholder");

        publicacaoEnviada = apiBody.criandoPublicacao();
        publicacaoEnviada.setBody(faker.leagueOfLegends().quote());

        super.body = new JSONObject(new Gson().toJson(publicacaoEnviada));
        super.PATCH();

    }

    @Então("deve retornar a publicacao com dados alterados")
    public void deve_retornar_a_publicacao_com_dados_alterados() {

        assertEquals(publicacaoEnviada.getBody(), response.jsonPath().getString("body"));

    }

    @Então("o status code da request JSONPlaceHolder deve ser {int}")
    public void o_status_code_da_request_json_place_holder_deve_ser(Integer statusEsperado) {

        assertEquals(statusEsperado, response.statusCode());

    }

}
