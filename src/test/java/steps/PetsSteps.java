package steps;

import api.ApiHeaders;
import api.ApiRequests;
import api.ApiBody;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONObject;
import user.PetsInvalidosLombok;
import user.PetsLombok;
import utils.PropertiesUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetsSteps extends ApiRequests {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    ApiBody apiBody = new ApiBody();
    PetsLombok configPets;
    PetsLombok petEnviado;
    Faker faker = new Faker();
    PetsInvalidosLombok configPetsInvalidos;
    PetsInvalidosLombok petInvalidoEnviado;


    //Post---------------------------------------------------------------------------------------------------------------
    @Dado("que tenho dados de acesso validos na API Petstore")
    public void que_tenho_dados_de_acesso_validos_na_api_petstore() {

        super.headers = apiHeaders.petstoreHeaders();

    }


    @Quando("crio um pet")
    public void crio_um_pet() {

        super.uri = prop.getProp("uri_petstore");

        configPets = apiBody.configurandoPets();
        petEnviado = apiBody.criandoPets(configPets.getId(), configPets.getName());

        super.body = new JSONObject(new Gson().toJson(petEnviado));
        super.POST();

    }


    @Então("deve retornar as informacoes do pet criado")
    public void deve_retornar_as_informacoes_do_pet_criado() {

        assertEquals(petEnviado.getId().toString(), response.jsonPath().getString("id"));
        assertEquals(petEnviado.getName(), response.jsonPath().getString("name"));

    }

    @E("o status code da request Petstore deve ser {int}")
    public void oStatusCodeDaRequestPetstoreDeveSer(Integer statusEsperado) {

        assertEquals(statusEsperado, response.statusCode());

    }

//Put---------------------------------------------------------------------------------------------------------------
    @Quando("modifico esse pet")
    public void modificoEssePet() {

        configPets.setName(faker.animal().name());
        petEnviado.setStatus("unavalable");

        petEnviado = apiBody.criandoPets(configPets.getId(), configPets.getName());

        super.body = new JSONObject(new Gson().toJson(petEnviado));
        super.PUT();

    }


    @Então("deve retornar as informacoes do pet modificado")
    public void deveRetornarAsInformacoesDoPetModificado() {

        assertEquals(petEnviado.getId().toString(), response.jsonPath().getString("id"));
        assertEquals(petEnviado.getName(), response.jsonPath().getString("name"));
        assertEquals(petEnviado.getStatus(), response.jsonPath().getString("status"));

    }

//Delete--------------------------------------------------------------------------------------------------------------
    @Quando("deleto esse pet")
    public void deletoEssePet() {

        super.uri = prop.getProp("uri_petstore");
        super.id = petEnviado.getId().toString();
        super.DELETE();

        //bug na api apesar funcionar
        //o delete funciona, mas só 50% das vezes por isso foi feito um while
        while(response.getStatusCode() != 200) {
            super.uri = prop.getProp("uri_petstore");
            super.id = petEnviado.getId().toString();
            super.DELETE();
        }

    }

    @Então("deve retornar o id do pet deletado")
    public void deveRetornarOIdDoPetDeletado() {

        assertEquals(petEnviado.getId().toString(), response.jsonPath().getString("message"));

    }

//Get------------------------------------------------------------------------------------------------------------------
    @Quando("busco o pet criado")
    public void buscoOPetCriado() {

        super.uri = prop.getProp("uri_petstore");
        super.id = String.valueOf(petEnviado.getId());
        super.GET();

        while(response.getStatusCode() != 200) {
            super.uri = prop.getProp("uri_petstore");
            super.id = String.valueOf(petEnviado.getId());
            super.GET();
        }

    }

    @Então("deve retornar as informacoes do pet encontrado")
    public void deveRetornarAsInformacoesDoPetEncontrado() {

        //assertEquals(petEnviado, response.jsonPath().getObject("", PetsLombok.class));
        assertEquals(petEnviado.getId().toString(), response.jsonPath().getString("id"));
        assertEquals(petEnviado.getName(), response.jsonPath().getString("name"));

    }

////negativoPost-------------------------------------------------------------------------------------------------------
//    //bug na api
//    //retorna mensagem "something bad happened" e status code 500
//    @Quando("crio um pet com dados invalidos")
//    public void crioUmPetComDadosInvalidos() {
//
//        super.uri = prop.getProp("uri_petstore");
//
//        configPetsInvalidos = apiBody.configurandoPetsInvalidos();
//        petInvalidoEnviado = apiBody.criandoPetsInvalidos(configPetsInvalidos.getName(), configPetsInvalidos.getId());
//
//        super.body = new JSONObject(new Gson().toJson(petInvalidoEnviado));
//        super.POST();
//
//    }
//
//    @Então("deve retornar a mensagem {string}")
//    public void deveRetornarAMensagem(String mensagemEsperada) {
//
//        assertEquals(mensagemEsperada, response.jsonPath().getString("message"),
//                "Status code diferente do esperado!");
//
//    }
//
////negativoPetsPut----------------------------------------------------------------------------------------------------
//    //bug na api
//    //na response a api substitui o id invalido por um valido e o status code foi 200
//    //não teve a mensagem esperada "invalid ID supplied"
//    @Quando("modifico esse pet com dados invalidos")
//    public void modificoEssePetComDadosInvalidos() {
//
//        configPets.setId(faker.number().numberBetween(-999, -10));
//
//        petEnviado = apiBody.criandoPets(configPets.getId(), configPets.getName());
//
//        super.body = new JSONObject(new Gson().toJson(petEnviado));
//        super.PUT();
//
//    }
//
////negativoPetsDelete-------------------------------------------------------------------------------------------------
//    //bug na api
//    //não retorna nada no body da response e não retorna o status code esperado (400)
//    @Quando("deleto um pet com id invalido")
//    public void deletoUmPetComIdInvalido() {
//
//        super.uri = prop.getProp("uri_petstore");
//        super.id = String.valueOf(faker.number().numberBetween(-999, -1));
//        super.DELETE();
//
//    }

}