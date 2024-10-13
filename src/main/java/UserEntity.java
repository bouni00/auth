import lombok.Data;

//@Entity
@Data
public class UserEntity {

    //@Id
    private int id;
    private String username;
    private String password;
    private String authority;



}
