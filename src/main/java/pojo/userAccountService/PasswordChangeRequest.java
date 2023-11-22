package pojo.userAccountService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PasswordChangeRequest {
    private String sessionToken;
    private String newPassword;
}

