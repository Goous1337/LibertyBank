package service;

import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService_2_0.*;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.*;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.*;
import static constant.CustomerService_2_0_Constants.PARAMETER_CUSTOMER_ID;
import static constant.CustomerService_2_0_Constants.PARAMETER_INCORRECT_CUSTOMER_ID;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
import static io.restassured.http.Method.POST;


public class CustomerService_2_0 {
    public Response checkListSavingVerificationCode(CustomerService_2_0_Mobile customerService_2_0_mobile0Mobile) {
        return sendSimpleRequest(PATCH, CUSTOMER_SECURITY, customerService_2_0_mobile0Mobile);
    }

    public Response checkListSavingVerificationCodeWithInvalidMobilePhoneType
            (CustomerService_2_0_InvalidMobilePhoneValue customerService_2_0_mobile0Mobile) {
        return sendSimpleRequest(PATCH, CUSTOMER_SECURITY, customerService_2_0_mobile0Mobile);
    }

    public Response checkGetNotificationInPersonalAccount(String customerId) {
        return sendSimpleRequest(GET, CUSTOMER_2_0_NOTIFICATION, getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }

    public Response checkGetPersonalInfoClientsWithIncorrectCustomerId() {
        return sendSimpleRequest(GET, CUSTOMER_2_0_NOTIFICATION,
                getRP(PARAMETER, PARAMETER_CUSTOMER_ID, PARAMETER_INCORRECT_CUSTOMER_ID));
    }

    public Response checkGetPersonalInfoClientsWithIncorrectRequest(String incorrectRequest, String customerId) {
        return sendSimpleRequest(Method.valueOf(incorrectRequest), CUSTOMER_2_0_NOTIFICATION,
                getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }

    public Response checkGetPersonalInfoClientsWithIncorrectURI() {
        return sendSimpleRequest(GET, INCORRECT_CUSTOMER_2_0_NOTIFICATION,
                getRP(PARAMETER, PARAMETER_CUSTOMER_ID, PARAMETER_INCORRECT_CUSTOMER_ID));
    }

    public Response userAuthorizationByMobilePhone(UserAuthorizationByPhone userAuthorizationByPhone) {
        return sendSimpleRequest(POST, CUSTOMER_LOGIN, userAuthorizationByPhone);
    }

    public Response checkListAbilityChangePasswordInPersonalAccount(
            ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone) {
        return sendSimpleRequest(PATCH, CUSTOMER_CHANGE_PASSWORD, changeUserAccountPasswordByPhone);
    }

    public Response checkListSavingVerificationCodeWithInvalidMethods
            (ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone, String httpMethod) {
        return sendSimpleRequest(Method.valueOf(httpMethod), CUSTOMER_CHANGE_PASSWORD, changeUserAccountPasswordByPhone);
    }

    public Response checkListUserVerificationWithValidData(UserVerificationWithCode userVerificationWithCode) {
        return sendSimpleRequest(POST, CUSTOMER_VERIFICATION_BY_CODE, userVerificationWithCode);
    }

    public Response checkListUserVerificationWithInvalidMethod
            (UserVerificationWithCode userVerificationWithCode, String method) {
        return sendSimpleRequest(Method.valueOf(method), CUSTOMER_VERIFICATION_BY_CODE, userVerificationWithCode);
    }

    public Response checkListUserVerificationWithInvalidTypeMobilePhone
            (UserVerificationWithInvalidTypeMobilePhone userVerificationWithInvalidTypeMobilePhone) {
        return sendSimpleRequest(POST, CUSTOMER_VERIFICATION_BY_CODE, userVerificationWithInvalidTypeMobilePhone);
    }

    public Response checkListUserVerificationWithInvalidTypeVerificationCode
            (UserVerificationWithInvalidTypeVerificationCode userVerificationWithInvalidTypeVerificationCode) {
        return sendSimpleRequest(POST, CUSTOMER_VERIFICATION_BY_CODE, userVerificationWithInvalidTypeVerificationCode);
    }
}
