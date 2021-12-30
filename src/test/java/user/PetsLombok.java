package user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PetsLombok {

    private Integer id;
    private Category category;
    private String name;
    private List<PhotoUrls> photoUrls;
    private List<Tags> tags;
    private String status;

}
