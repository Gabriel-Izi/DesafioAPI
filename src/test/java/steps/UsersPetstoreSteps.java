package steps;

import api.ApiHeaders;
import api.ApiParams;
import api.ApiRequests;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONObject;
import user.CriandoUsuarioLombok;
import api.ApiBody;
import utils.PropertiesUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersPetstoreSteps extends ApiRequests{

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    ApiParams apiParams = new ApiParams();
    ApiBody apiBody = new ApiBody();
    CriandoUsuarioLombok userEnviado;
    Faker faker = new Faker();

//Post---------------------------------------------------------------------------------------------------------------
    @Dado("que tenho dados de acesso validos de usuario na API Petstore")
    public void queTenhoDadosDeAcessoValidosDeUsuarioNaAPIPetstore() {

        super.uri = prop.getProp("uri_userpetstore")+"login?";
        super.params = apiParams.petstoreParams();
        super.headers = apiHeaders.petstoreHeaders();
        super.GET();

    }


    @Quando("crio um usuario")
    public void crioUmUsuario() {

        super.uri = prop.getProp("uri_userpetstore");
        userEnviado = apiBody.criandoUser();

        super.body = new JSONObject(new Gson().toJson(userEnviado));
        super.POST();

    }


    @Então("deve retornar o id do usuario criado")
    public void deveRetornarOIdDoUsuarioCriado() {

        assertEquals(userEnviado.getId().toString(), response.jsonPath().getString("message"));

    }


    @E("o status code da request userPetstore deve ser {int}")
    public void oStatusCodeDaRequestUserPetstoreDeveSer(int statusEsperado) {

        assertEquals(statusEsperado, response.statusCode());

    }

//Put---------------------------------------------------------------------------------------------------------------
    @Quando("modifico esse usuario")
    public void modificoEsseUsuario() {

        super.uri = prop.getProp("uri_userpetstore")+userEnviado.getUsername();
        userEnviado.setId(faker.number().numberBetween(10000, 20000));
        super.body = new JSONObject(new Gson().toJson(userEnviado));
        super.PUT();

    }


    @Então("deve retornar o id do usuario modificado")
    public void deveRetornarOIdDoUsuarioModificado() {

        assertEquals(userEnviado.getId().toString(), response.jsonPath().getString("message"),
                "Error na comparação do objeto!");

    }

//Delete--------------------------------------------------------------------------------------------------------------
    @Quando("deleto esse usuario")
    public void deletoEsseUsuario() {

        super.uri = prop.getProp("uri_userpetstore");
        super.id = userEnviado.getUsername();
        super.DELETE();

        while(response.getStatusCode() != 200) {
            super.uri = prop.getProp("uri_userpetstore");
            super.id = userEnviado.getUsername();
            super.DELETE();
        }

    }


    @Então("deve retornar o username do usuario deletado")
    public void deveRetornarOUsernameDoUsuarioDeletado() {

        assertEquals(userEnviado.getUsername(), response.jsonPath().getString("message"),
                "Error na comparação do objeto!");

    }

}
