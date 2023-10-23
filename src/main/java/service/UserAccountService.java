package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.userAccountService.GetVerificationCode;
import pojo.userAccountService.PhoneVerificationRequest;
import pojo.userAccountService.PhoneVerificationRequestInteger;
import pojo.userAccountService.Verification;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.VERIFICATION;
import static constant.ApiEndpoints.VERIFICATION_CODE;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.*;


public class UserAccountService {

    public String getVerificationCode(String mobilePhone) {
        Response response = sendSimpleRequest(POST, VERIFICATION_CODE, new Verification(mobilePhone));
        return response.jsonPath().get("verificationCode");
    }

    public Response verificationMobilePhoneVerificationCode(String mobilePhone, String verificationCode) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(POST, VERIFICATION, params,
                new PhoneVerificationRequest(mobilePhone, verificationCode));
    }

    public Response verificationMobilePhoneVerificationCodeInteger(String mobilePhone, Integer verificationCode) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(POST, VERIFICATION, params,
                new PhoneVerificationRequestInteger(mobilePhone, verificationCode));
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
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(GET, VERIFICATION, params,
                new PhoneVerificationRequest(mobilePhone, verificationCode));
    }

    public Response verificationInvalidMobilePhoneAndVerificationCode(String invalidMobilePhone, String invalidVerificationCode) {
        return sendSimpleRequest(POST, VERIFICATION,
                new PhoneVerificationRequest(invalidMobilePhone, invalidVerificationCode));
    }

    public Response checkVerificationCodeSuccessfulSaved(String phoneNumber) {
        return sendSimpleRequest(PATCH, VERIFICATION_CODE, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new Verification(phoneNumber));
    }

    public Response checkVerificationCodeInvalidHttpMethod(String httpMethod, String phoneNumber) {
        return sendSimpleRequest(Method.valueOf(httpMethod), VERIFICATION_CODE,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON), new Verification(phoneNumber));
    }

}