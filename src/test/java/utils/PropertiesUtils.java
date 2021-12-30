package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    Properties properties = new Properties();

    public String getProp(String key){// se a variavel no ambiente maven for null, o método lê a variavel do hom.prop
        try{
            if(System.getProperty("env") == null){
                properties.load(getClass().getClassLoader().getResourceAsStream("hom.properties"));
            }else{
                properties.load(getClass().getClassLoader().getResourceAsStream(System.getProperty("env")));
            }
        }catch(IOException e){//se não encontrar o arquivo/variavel, documenta o que aconteceu
            e.printStackTrace();
        }
        return properties.getProperty(key);//essa key = uri do gorest
    }

}
