package api;

import com.github.javafaker.Faker;
import user.*;

import java.util.ArrayList;
import java.util.List;

public class ApiBody {

    Faker faker = new Faker();

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

    public List<Tags> setTags(int fakeId, String fakeName) {
        List<Tags> tags = new ArrayList<>();
        var tag = Tags.builder().id(fakeId).name(fakeName).build();
        tags.add(tag);

        return tags;
    }

}
