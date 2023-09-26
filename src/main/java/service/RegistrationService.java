package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.REGISTRATION;
import static constant.UserServiceConstants.PARAMETER_MOBILE_PHONE;
import static constant.UserServiceConstants.PARAMETER_PASSPORT;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;

public class RegistrationService {

    public Response checkRegistrationByPhone(String phoneNumber) {
        return sendSimpleRequest(GET, REGISTRATION, new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
    }
    public Response checkVerificationCode(String phoneNumber) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
        return sendSimpleRequest(PATCH, REGISTRATION, params);
    }

    public Response checkRegistrationByPhoneWithoutParam() {
        return sendRequestWithoutParams(GET, REGISTRATION);
    }

    public Response checkRegistrationByPhoneInvalidURL(String invalidURL, String phoneNumber) {
        return sendSimpleRequest(GET, invalidURL, new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
    }

    public Response checkRegistrationByPhoneInvalidHttpMethod(String invalidHttpMethod, String phoneNumber) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), REGISTRATION, new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
    }

    public Response checkRegistrationByPassport(String passport) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_PASSPORT, passport));
        return sendSimpleRequest(GET, REGISTRATION, params);
    }

    public Response checkRegistrationByPassportInvalidHttpMethod(String invalidHttpMethod, String passport) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_PASSPORT, passport));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), REGISTRATION, params);
    }


}