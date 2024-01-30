package pojo.customerService_2_0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVerificationWithCode {
    private String mobilePhone;
    private String verificationCode;
}
