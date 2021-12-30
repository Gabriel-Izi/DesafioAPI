package user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tags {

    private Integer id;
    private String name;

}
