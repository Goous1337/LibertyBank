package service;

import io.restassured.response.Response;
import pojo.userAccountService.GetVerificationCode;
import pojo.userAccountService.PhoneVerificationRequest;
import pojo.userAccountService.Verification;
import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.VERIFICATION_CODE;
import static constant.ApiEndpoints.VERIFICATION;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;



public class VerificationService {

    public String getVerificationCode(String mobilePhone) {
        Response response = sendSimpleRequest(POST, VERIFICATION_CODE, new Verification(mobilePhone));
        return response.jsonPath().get("verificationCode");
    }

    public Response verificationMobilePhoneVerificationCode(String mobilePhone, String verificationCode) {
        return sendSimpleRequest(POST, VERIFICATION,
                new PhoneVerificationRequest(mobilePhone, verificationCode));
    }

    public Response verificationMobilePhone(String mobilePhone) {
        return sendSimpleRequest(POST, VERIFICATION,
                new Verification(mobilePhone));
    }

    public Response verificationVerificationCode(String verificationCode) {
        return sendSimpleRequest(POST, VERIFICATION,
                new GetVerificationCode(verificationCode));
    }

    public Response verificationWithInvalidMethod(String mobilePhone, String verificationCode) {
        return sendSimpleRequest(GET, VERIFICATION,
                new PhoneVerificationRequest(mobilePhone, verificationCode));
    }

    public Response verificationInvalidMobilePhoneAndVerificationCode(String invalidMobilePhone, String invalidVerificationCode) {
        return sendSimpleRequest(POST, VERIFICATION,
                new PhoneVerificationRequest(invalidMobilePhone, invalidVerificationCode));
    }

    public Response verificationService(String phoneNumber) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_RECEIVER, phoneNumber));
        return sendSimpleRequest(PATCH, VERIFICATION_CODE, params);
    }

    public Response checkVerificationCodeWithoutParam() {
        return sendRequestWithoutParams(PATCH, VERIFICATION_CODE);
    }

    public String userVerificationWithValidData(String mobilePhone) {
        Response response = verificationService(mobilePhone);
        String verificationCode = response.jsonPath().get("verificationCode");
        return verificationCode;
    }

    public Response checkVerificationCodeInvalidHttpMethod(String invalidHttpMethod, String phoneNumber) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_RECEIVER, phoneNumber));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), VERIFICATION_CODE, params);

    }

}

