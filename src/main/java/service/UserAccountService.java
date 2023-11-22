package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.userAccountService.*;

import java.util.List;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.*;


public class UserAccountService {

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

    public Response verificationWithInvalidMethod(String mobilePhone, String verificationCode) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(GET, VERIFICATION, params,
                new PhoneVerificationRequest(mobilePhone, verificationCode));
    }

    public Response checkVerificationCodeSuccessfulSaved(String phoneNumber) {
        return sendSimpleRequest(PATCH, VERIFICATION_CODE, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new Verification(phoneNumber));
    }

    public Response checkVerificationCodeInvalidHttpMethod(String httpMethod, String phoneNumber) {
        return sendSimpleRequest(Method.valueOf(httpMethod), VERIFICATION_CODE,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON), new Verification(phoneNumber));
    }

    public Response changePasswordForUserUpdatedDatabase(String sessionToken, String newPassword) {
        return sendSimpleRequest(PATCH, CHANGE_PASSWORD, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new PasswordChangeRequest(sessionToken, newPassword));
    }

    public Response changePasswordForUserUpdatedDatabaseInvalidURL(String sessionToken, String newPassword) {
        return sendSimpleRequest(PATCH, INVALID_CHANGE_PASSWORD, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new PasswordChangeRequest(sessionToken, newPassword));
    }

    public Response checkPasswordChangesInvalidMethod(String invalidHttpMethod, String sessionToken, String customerId) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), CHANGE_PASSWORD, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new PasswordChangeRequest(sessionToken, customerId));
    }

    public Response changePasswordForUserUpdatedDatabaseWithoutToken(String newPassword) {
        return sendSimpleRequest(PATCH, CHANGE_PASSWORD, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new OnlyPasswordChangeRequest(newPassword));
    }

    public Response changePasswordForUserUpdatedDatabaseWithoutNewPassword(String sessionToken) {
        return sendSimpleRequest(PATCH, CHANGE_PASSWORD, new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new OnlySessionTokenChangeRequest(sessionToken));
    }

    public Response changePasswordForUserUpdatedDatabaseWithoutBody() {
        return sendRequestWithoutParams(PATCH, CHANGE_PASSWORD);
    }
}