package pojo.customerService_2_0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserAccountPasswordByPhone {
    private String refreshToken;
    private String password;
    private String newPassword;
}
