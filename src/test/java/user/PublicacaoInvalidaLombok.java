package user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublicacaoInvalidaLombok {

    private String userId;
    private String id;
    private Integer title;
    private Integer body;


}
