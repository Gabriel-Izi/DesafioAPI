package utils;

public class LogUtils {

    //Documenta no relatório as informações de sucesso, caso o body tenha conteudo
    public void logInfo(String value){
        if(!value.contains("{}")){
            ScenarioUtils.addText(value);
        }
    }

    //Documenta no relatório as informações de erro, caso o body tenha conteudo
    public void logError(String value){
        if (!value.contains("{}")){
            ScenarioUtils.addText(value);
        }
    }
}
