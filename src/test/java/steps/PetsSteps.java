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
}