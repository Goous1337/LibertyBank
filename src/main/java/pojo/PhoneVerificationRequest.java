package pojo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhoneVerificationRequest {
    private String mobilePhone;
    private String verificationCode;
}
