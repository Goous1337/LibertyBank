package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.VERIFICATION_CODE;
import static constant.UserServiceConstants.PARAMETER_MOBILE_PHONE;
import static io.restassured.http.Method.PATCH;

public class VerificationService {

    public Response verificationService(String phoneNumber) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
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
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), VERIFICATION_CODE, params);

    }
}