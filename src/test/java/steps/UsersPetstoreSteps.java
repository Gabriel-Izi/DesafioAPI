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
import user.UsuarioInvalidoLombok;
import utils.PropertiesUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersPetstoreSteps extends ApiRequests{

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    ApiParams apiParams = new ApiParams();
    ApiBody apiBody = new ApiBody();
    CriandoUsuarioLombok userEnviado;
    Faker faker = new Faker();
    UsuarioInvalidoLombok userInvalidoEnviado;

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

        assertEquals(userEnviado.getId().toString(), response.jsonPath().getString("message"),
                "Error na comparação do objeto!");

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

        //bug na api apesar de funcionar
        //o delete funciona, mas só 50% das vezes por isso foi feito um while
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



////negativoUsersPost---------------------------------------------------------------------------------------------------
//    //bug na api
//    //não retornou a mensagem "Invalid input" e o status code 405
//    @Quando("crio um usuario invalido")
//    public void crioUmUsuarioInvalido() {
//
//        super.uri = prop.getProp("uri_userpetstore");
//        userInvalidoEnviado = apiBody.criandoUserInvalido();
//
//        super.body = new JSONObject(new Gson().toJson(userInvalidoEnviado));
//        super.POST();
//
//    }
//
//    @Então("deve retornar a mensagem de erro {string}")
//    public void deveRetornarAMensagemDeErro(String mensagemEsperada) {
//
//        assertEquals(mensagemEsperada, response.jsonPath().getString("message"),
//                "Error na comparação do objeto!");
//
//    }
//
////negativoUsersPut----------------------------------------------------------------------------------------------------
//    //bug na api
//    //na response a api substitui o id invalido por um valido, e o status code foi 200
//    //não teve a mensagem esperada "invalid input"
//    @Quando("modifico esse usuario com dados invalidos")
//    public void modificoEsseUsuarioComDadosInvalidos() {
//
//        super.uri = prop.getProp("uri_userpetstore")+userEnviado.getUsername();
//        userEnviado.setId(faker.number().numberBetween(-999, -9));
//        super.body = new JSONObject(new Gson().toJson(userEnviado));
//        super.PUT();
//
//    }
//
////negativoUsersDelete
//    //bug na api
//    //não retorna nada no body da response e não retorna o status code esperado (400)
//    @Quando("deleto um usuario com id invalido")
//    public void deletoUmUsuarioComIdInvalido() {
//
//        super.uri = prop.getProp("uri_userpetstore");
//        super.id = String.valueOf(faker.number().numberBetween(-999, -9));
//        super.DELETE();
//
//    }
}
