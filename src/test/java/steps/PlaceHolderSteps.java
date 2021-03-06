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
import user.PublicacaoInvalidaLombok;
import user.PublicacaoLombok;
import utils.PropertiesUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceHolderSteps extends ApiRequests {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    ApiBody apiBody = new ApiBody();
    PublicacaoLombok publicacaoEnviada;
    PublicacaoInvalidaLombok publicacaoInvalidaEnviada;
    Faker faker = new Faker();

//Patch------------------------------------------------------------------------------------------------------------
    @Dado("que tenho dados de acesso validos na API JSONPlaceHolder")
    public void que_tenho_dados_de_acesso_validos_na_api_json_place_holder() {

        super.headers = apiHeaders.placeHolderHeaders();

    }

    @Quando("modifico um dado de uma publicacao")
    public void modifico_um_dado_de_uma_publicacao() {

        super.uri = prop.getProp("uri_placeholder")+"1";
//        super.id = "1";

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

//Post---------------------------------------------------------------------------------------------------------------
    @Quando("crio uma publicacao")
    public void crioUmaPublicacao() {

        super.uri = prop.getProp("uri_placeholder");

        publicacaoEnviada = apiBody.criandoPublicacao();
        publicacaoEnviada.setId(101);

        super.body = new JSONObject(new Gson().toJson(publicacaoEnviada));
        super.POST();

    }

    @Então("deve retornar a publicacao com dados criados")
    public void deveRetornarAPublicacaoComDadosCriados() {

        assertEquals(publicacaoEnviada, response.jsonPath().getObject("", PublicacaoLombok.class));

    }

//Put-----------------------------------------------------------------------------------------------------------------
    @Quando("modifico uma publicacao")
    public void modificoUmaPublicacao() {

        super.uri = prop.getProp("uri_placeholder")+"1";
//        super.id = "1";

        publicacaoEnviada = apiBody.criandoPublicacao();
        publicacaoEnviada.setBody(faker.leagueOfLegends().quote());
        publicacaoEnviada.setUserId(123);
        publicacaoEnviada.setTitle(faker.leagueOfLegends().summonerSpell());

        super.body = new JSONObject(new Gson().toJson(publicacaoEnviada));
        super.PUT();

    }

    @Então("deve retornar a publicacao com dados modificados")
    public void deveRetornarAPublicacaoComDadosModificados() {

        deveRetornarAPublicacaoComDadosCriados();

    }

//Get-------------------------------------------------------------------------------------------------------------------
    @Quando("busco uma publicacao pelo id")
    public void buscoUmaPublicacaoPeloId() {

        super.uri = prop.getProp("uri_placeholder");
        super.id = "1";

        publicacaoEnviada = apiBody.criandoPublicacao();

        super.body = new JSONObject(new Gson().toJson(publicacaoEnviada));
        super.GET();
    }

    @Então("deve retornar a publicacao do id buscado")
    public void deveRetornarAPublicacaoDoIdBuscado() {

        deveRetornarAPublicacaoComDadosCriados();

    }

//Delete-----------------------------------------------------------------------------------------------------------
    @Quando("deleto uma publicacao pelo id")
    public void deletoUmaPublicacaoPeloId() {

        super.uri = prop.getProp("uri_placeholder");
        super.id = "1";

        super.DELETE();

    }

    @Então("deve retornar uma resposta vazia")
    public void deveRetornarUmaRespostaVazia() {

        assertEquals("[:]", response.jsonPath().getString(""));

    }

////negativoPatchPlaceHolder---------------------------------------------------------------------------------------------
//    //erro na API
//    //está aceitando string no lugar de int e vice-versa
//    //não retorna nenhuma mensagem de erro e devolve o status code 200
//    @Quando("modifico um dado de uma publicacao com um dado invalido")
//    public void modificoUmDadoDeUmaPublicacaoComUmDadoInvalido() {
//
//        super.uri = prop.getProp("uri_placeholder")+"1";
//
//        publicacaoInvalidaEnviada = apiBody.criandoPublicacaoInvalida();
//
//        super.body = new JSONObject(new Gson().toJson(publicacaoInvalidaEnviada));
//        super.PATCH();
//
//    }
//
////negativoPostPlaceHolder-----------------------------------------------------------------------------------------------
//    //erro na API
//    //está aceitando string no lugar de int e vice-versa
//    //não retorna nenhuma mensagem de erro e devolve o status code 200
//    @Quando("crio uma publicacao invalida")
//    public void crioUmaPublicacaoInvalida() {
//
//        super.uri = prop.getProp("uri_placeholder");
//
//        publicacaoInvalidaEnviada = apiBody.criandoPublicacaoInvalida();
//
//        super.body = new JSONObject(new Gson().toJson(publicacaoInvalidaEnviada));
//        super.POST();
//
//    }
//
////negativoPutPlaceHolder-----------------------------------------------------------------------------------------------
//    //erro na API
//    //está aceitando string no lugar de int e vice-versa
//    //não retorna nenhuma mensagem de erro e devolve o status code 200
//    @Quando("modifico uma publicacao com dados invalidos")
//    public void modificoUmaPublicacaoComDadosInvalidos() {
//
//        super.uri = prop.getProp("uri_placeholder")+"1";
//
//        publicacaoInvalidaEnviada = apiBody.criandoPublicacaoInvalida();
//
//        super.body = new JSONObject(new Gson().toJson(publicacaoInvalidaEnviada));
//        super.PUT();
//
//    }
//
////negativoGetPlaceHolder-----------------------------------------------------------------------------------------------
//    @Quando("busco uma publicacao por um id invalido")
//    public void buscoUmaPublicacaoPorUmIdInvalido() {
//
//        super.uri = prop.getProp("uri_placeholder");
//        super.id = "abc";
//
//        super.GET();
//
//    }
//
//    @Então("deve retornar uma publicacao vazia")
//    public void deveRetornarUmaPublicacaoVazia() {
//
//        assertEquals("[:]", response.jsonPath().getString(""));
//
//    }
//
////negativoDeletePlaceHolder--------------------------------------------------------------------------------------------
//    //erro na API
//    //está aceitando string no lugar de int e vice-versa
//    //não retorna nenhuma mensagem de erro e devolve o status code 200
//    @Quando("tento deletar uma publicacao com id inexistente")
//    public void tentoDeletarUmaPublicacaoComIdInexistente() {
//
//        super.uri = prop.getProp("uri_placeholder");
//        super.id = "abc";
//
//        super.DELETE();
//
//    }
}
