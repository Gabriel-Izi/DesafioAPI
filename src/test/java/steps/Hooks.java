package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScenarioUtils;

public class Hooks /*extends ApiUtils*/ {

    @Before//Adiciona o cenário para o teste
    public void before(Scenario scenario){
        ScenarioUtils.add(scenario);
        //Caso as coisas abaixo interfiram em testes de api diferentes
        //super.body = null;
        //super.headers = null;
        //super.params = null;
    }

    @After//Remove o cenário adicionado
    public void after(){
        ScenarioUtils.remove();
    }
}