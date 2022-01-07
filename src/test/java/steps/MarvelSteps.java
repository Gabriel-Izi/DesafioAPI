package steps;

import api.ApiParams;
import api.ApiRequests;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import utils.PropertiesUtils;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarvelSteps extends ApiRequests {

    PropertiesUtils prop = new PropertiesUtils();
    ApiParams apiParams = new ApiParams();
    Faker faker = new Faker();


    @Dado("que tenho dados de acesso validos na API Marvel")
    public void queTenhoDadosDeAcessoValidosNaAPIMarvel() throws NoSuchAlgorithmException {

        //Seta os parametros de acesso
        super.params = apiParams.marvelParams();

    }


    @Quando("busco um personagem existente pelo {string}")
    public void buscoUmPersonagemExistentePelo(String idDoPersonagem) {

        //Seta a url com o id do personagem, e em seguida faz o get
        super.uri = prop.getProp("uri_marvel");
        super.id = idDoPersonagem;
        super.GET();

    }


    @Então("o {string} deve ser retornado")
    public void oDeveSerRetornado(String nomeDoPersonagem) {

        //Faz um assert com o nome dos personagens
        assertEquals(nomeDoPersonagem, response.jsonPath().getString("data.results.name")
                .replace("[","").replace("]",""));

    }


    @E("esse personagem deve participar do evento {string}")
    public void essePersonagemDeveParticiparDoEvento(String evento) {

        //Faz um assert com o evento que os personagens participam
        String listaDeEventos = response.jsonPath().getString("data.results.events.items.name");
        assertTrue(listaDeEventos.contains(evento));

    }


    @Então("o status code da request Marvel deve ser {int}")
    public void o_status_code_da_request_marvel_deve_ser(Integer statusEsperado) {

        assertEquals(statusEsperado, response.statusCode(), "Status code diferente do esperado!");

    }

//negativoMarvel-------------------------------------------------------------------------------------------------------
    @Quando("busco um personagem com id invalido")
    public void buscoUmPersonagemComIdInvalido() {

        super.uri = prop.getProp("uri_marvel");
        super.id = String.valueOf(faker.number().numberBetween(-999,-9));
        super.GET();

    }

    @Então("deve retornar uma mensagem de erro {string}")
    public void deveRetornarUmaMensagemDeErro(String mensagemEsperada) {

        assertEquals(mensagemEsperada, response.jsonPath().getString("status"));

    }

}
