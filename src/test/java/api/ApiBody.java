package api;

import com.github.javafaker.Faker;
import user.*;

import java.util.ArrayList;
import java.util.List;

public class ApiBody {

    Faker faker = new Faker();
//Metodos de Users da API Petstore------------------------------------------------------------------------------------------
    public CriandoUsuarioLombok criandoUser(){
        return CriandoUsuarioLombok.builder()
                .id(faker.number().numberBetween(10000, 20000))
                .username(faker.leagueOfLegends().champion())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().cellPhone())
                .userStatus(1)
                .build();
    }

    public UsuarioInvalidoLombok criandoUserInvalido(){
        return UsuarioInvalidoLombok.builder()
                .id(faker.animal().name())
                .username(faker.leagueOfLegends().champion())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(999)
                .password(faker.internet().password())
                .phone(faker.phoneNumber().cellPhone())
                .userStatus(faker.internet().emailAddress())
                .build();
    }

//Metodos de Pets da API Petstore----------------------------------------------------------------------------------------
    public PetsLombok configurandoPets(){

        return PetsLombok.builder()
                .id(faker.number().numberBetween(10000, 20000))
                .name(faker.animal().name())
                .build();
    }

    public PetsLombok criandoPets(Integer configPetId, String configPetName){

        return  PetsLombok.builder()
                .id(configPetId)
                .category(Category.builder().id(configPetId).name(configPetName).build())
                .name(configPetName)
//                .photoUrls(setPhotoUrls(faker.internet().avatar()))
                .tags(setTags(configPetId, configPetName))
                .status("available")
                .build();
    }


    public PetsInvalidosLombok configurandoPetsInvalidos(){

        return PetsInvalidosLombok.builder()
                .id(faker.animal().name())
                .name(faker.number().numberBetween(10000, 20000))
                .build();
    }

    public PetsInvalidosLombok criandoPetsInvalidos(Integer configPetName, String configPetId){

        return  PetsInvalidosLombok.builder()
                .id(configPetId)
                .category(Category.builder().id(configPetName).name(configPetId).build())
                .name(configPetName)
//                .photoUrls(setPhotoUrls(faker.internet().avatar()))
                .tags(setTags(configPetName, configPetId))
                .status("available")
                .build();
    }


    public List<Tags> setTags(int fakeId, String fakeName) {
        List<Tags> tags = new ArrayList<>();
        var tag = Tags.builder().id(fakeId).name(fakeName).build();
        tags.add(tag);

        return tags;
    }

//Metodos API JSONPlaceHolder-----------------------------------------------------------------------------------------
    public PublicacaoLombok criandoPublicacao(){

        return PublicacaoLombok.builder()
                .userId(1)
                .id(1)
                .title("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                .body("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto")
                .build();

    }

    public PublicacaoInvalidaLombok criandoPublicacaoInvalida(){

        return PublicacaoInvalidaLombok.builder()
                .userId("abc")
                .id("abc")
                .title(123)
                .body(321)
                .build();

    }

}
