package utils;


import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioUtils {

    private static Map <Long, Scenario> repository = new HashMap<>();

    //Verifica id do cenário, caso for null, adiciona o cenário
    public static void add(Scenario scenario){
        if(get() == null)
            repository.put(getId(), scenario);
    }

    //Remove o cenário caso exista
    public static void remove(){
        if(get() != null)
            repository.remove(getId());
    }

    //Captura id do cenário
    public static Scenario get(){
        return repository.get(getId());
    }

    //Busca o id pela thread para auxiliar o metódo get()
    public static Long getId() {
        return Thread.currentThread().getId();
    }

    //Adiciona informações nos cenários
    public static void addText(String texto){
        get().log(texto);
    }

}
