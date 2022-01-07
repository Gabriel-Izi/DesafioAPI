package user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioInvalidoLombok {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer email;
    private String password;
    private String phone;
    private String userStatus;

}
