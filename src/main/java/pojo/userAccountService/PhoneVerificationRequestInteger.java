package pojo.userAccountService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PhoneVerificationRequestInteger {

    private String mobilePhone;
    private Integer verificationCode;
}
