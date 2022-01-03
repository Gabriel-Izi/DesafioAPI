package user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PetsInvalidosLombok {

    private String id;
    private Category category;
    private Integer name;
    private List<PhotoUrls> photoUrls;
    private List<Tags> tags;
    private String status;

}
