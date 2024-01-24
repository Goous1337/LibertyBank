package pojo.customerService_2_0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorizationByPhone {
    private String login;
    private String password;
    private String type;
}
