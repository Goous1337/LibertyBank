package pojo.customerService_2_0;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PasswordChangeRequest {
    private String newPassword;
}