package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneVerificationRequest {
    private String mobilePhone;
    private String verificationCode;
}
