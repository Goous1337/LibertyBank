package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import constant.CustomerServiceConstants;
import io.restassured.response.Response;
import pojo.customerService.UserQuestion;
import pojo.customerService_2_0.*;

import java.util.List;
import java.util.Map;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static api.utils.GsonHelper.createBody;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static api.core.RequestParam.getRP;
import static constant.CustomerServiceConstants.PARAMETER_NOTIFICATION_STATUS;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.PARAMETER;

import static constant.CustomerService_2_0_Constants.BEARER_TOKEN;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
import static io.restassured.http.Method.POST;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class CustomerService_2_0 {
    public Response checkListSavingVerificationCode(CustomerService_2_0_Mobile customerService_2_0_mobile0Mobile) {
        return sendSimpleRequest(PATCH, CUSTOMER_SECURITY, customerService_2_0_mobile0Mobile);
    }

    public Response checkListSavingVerificationCodeWithInvalidMobilePhoneType
            (CustomerService_2_0_InvalidMobilePhoneValue customerService_2_0_mobile0Mobile) {
        return sendSimpleRequest(PATCH, CUSTOMER_SECURITY, customerService_2_0_mobile0Mobile);
    }

    public Response  checkUpdateQuestionAnswer(String question, String answer,String customerId){
        List<RequestParam> params = List.of(getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                getRP(PARAMETER,PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, QUESTION_ANSWER_2_0, params, new UserQuestion(question, answer));
    }

    public Response updateQuestionAnswerInvalidHttpMethod(String question, String answer, String invalidHttpMethod) {
        List<RequestParam> params = List.of(getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), QUESTION_ANSWER_2_0, params, new UserQuestion(question, answer));
    }

    public Response checkUpdateQuestionAnswerInvalidUrl(String question, String answer,String customerId){
        List<RequestParam> params = List.of(getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                getRP(PARAMETER,PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, QUESTION_ANSWER_INVALID_URL_2_0, params, new UserQuestion(question, answer));
    }

    public Response checkGetNotificationInPersonalAccount(String token) {
        return sendSimpleRequest(GET, CUSTOMER_2_0_NOTIFICATION,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
    }


    public Response checkGetPersonalInfoClientsWithIncorrectRequest(String incorrectRequest, String token) {
        return sendSimpleRequest(Method.valueOf(incorrectRequest), CUSTOMER_2_0_NOTIFICATION,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
    }

    public Response checkGetPersonalInfoClientsWithIncorrectURI(String token) {
        return sendSimpleRequest(GET, INCORRECT_CUSTOMER_2_0_NOTIFICATION,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
    }

    public Response userAuthorizationByMobilePhone(UserAuthorizationByPhone userAuthorizationByPhone) {
        return sendSimpleRequest(POST, CUSTOMER_LOGIN, userAuthorizationByPhone);
    }
    public Response checkListUserAuthorizationWithInvalidUrl(UserAuthorizationByPhone userAuthorizationByPhone){
        return sendSimpleRequest(POST,INVALID_CUSTOMER_LOGIN,userAuthorizationByPhone);
    }
    public Response checkListUserAuthorizationWithInvalidMethod(UserAuthorizationByPhone userAuthorizationByPhone,String method){
        return sendSimpleRequest(Method.valueOf(method),CUSTOMER_LOGIN,userAuthorizationByPhone);
    }

    public Response checkListAbilityChangePasswordInPersonalAccount(
            ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone, String refreshToken) {
        return sendSimpleRequest(PATCH, CUSTOMER_CHANGE_PASSWORD,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + refreshToken), changeUserAccountPasswordByPhone);
    }

    public Response checkListSavingVerificationCodeWithInvalidMethods
            (ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone, String httpMethod, String refreshToken) {
        return sendSimpleRequest(Method.valueOf(httpMethod), CUSTOMER_CHANGE_PASSWORD,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + refreshToken), changeUserAccountPasswordByPhone);
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

    public Response checkGettingUserInformation(String customerId, String token) {
        List<RequestParam> params = List.of(getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
        return sendSimpleRequest(GET, RETRIEVING_USER_INFO, params);
    }


    public Response checkGettingUserInformationWithInvalidMethod(String invalidMethod, String customerId, String token) {
        List<RequestParam> params = List.of(getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
        return sendSimpleRequest(Method.valueOf(invalidMethod), RETRIEVING_USER_INFO, params);
    }

    public Response checkPushNotification(String customerId, Boolean notificationStatus, String token) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS,notificationStatus));
        List<RequestParam> params = List.of(getRP(PARAMETER, CustomerServiceConstants.PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON),getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token), getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithNotBoolean(String customerId, String notificationStatus, String token) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS,notificationStatus));
        List<RequestParam> params = List.of(getRP(PARAMETER, CustomerServiceConstants.PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token),
                getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithHttpMethod(String method, String customerId, String token) {
        List<RequestParam> params = List.of(getRP(PARAMETER, CustomerServiceConstants.PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token),
                getRP(QUERY_PARAMETER, CustomerServiceConstants.PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(Method.valueOf(method), PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithInvalidURL(String customerId, Boolean notificationStatus) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS, notificationStatus));
        List<RequestParam> params = List.of(getRP(PARAMETER, CustomerServiceConstants.PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, INVALID_PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithNull(NotificationStatus notificationStatus, String customerId , String token) {
        List<RequestParam> params = List.of(getRP(PARAMETER, CustomerServiceConstants.PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION_2_0, params, notificationStatus);
    }


    public Response getSessionToken(GetSessionToken getSessionToken) {
        return sendSimpleRequest(POST, CUSTOMER_2_0_SECURITY_VERIFICATION, getSessionToken);
    }

    public Response checkRecoveryPasswordOnAuthorizationPage(
            RecoveryPassword recoveryPassword, String sessionToken) {
        return sendSimpleRequest(PATCH, CUSTOMER_2_0_RECOVERY, getRP(HEADER, REGISTRATION, sessionToken), recoveryPassword);
    }

    public Response checkRecoveryPasswordOnAuthorizationPageWithInvalidMethod(
            RecoveryPassword recoveryPassword, String sessionToken, String httpMethod) {
        return sendSimpleRequest(Method.valueOf(httpMethod), CUSTOMER_2_0_RECOVERY, getRP(HEADER, REGISTRATION, sessionToken), recoveryPassword);
    }

    public Response updateEmailForClient(String token, UpdatedEmail email) {
        return sendSimpleRequest(PATCH, CUSTOMER_2_0_EMAIL,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token), email);
    }

    public Response updateEmailForClientWithoutEmail(String token) {
        return sendSimpleRequest(PATCH, CUSTOMER_2_0_EMAIL,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token));
    }

    public Response updateEmailForClientWithInvalidURL(String token, UpdatedEmail email, String url) {
        return sendSimpleRequest(PATCH, url,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token), email);
    }

    public Response updateEmailForClientWithHttpMethod(String token, String method, UpdatedEmail email) {
        return sendSimpleRequest(Method.valueOf(method), CUSTOMER_2_0_EMAIL, getRP(HEADER, AUTHORIZATION,
                BEARER_TOKEN + token), email);
    }


    public Response changePasswordForUserUpdatedDatabase(String sessionToken, String newPassword) {
        return sendSimpleRequest(PATCH, CHANGE_PASSWORD_2_0, getRP(HEADER, REGISTRATION_HEADER, sessionToken),
                new PasswordChangeRequest(newPassword));
    }

    public Response changePasswordForUserUpdatedDatabaseWithInvalidMethod(String method, String sessionToken, String newPassword) {
        return sendSimpleRequest(Method.valueOf(method), CHANGE_PASSWORD_2_0, getRP(HEADER, REGISTRATION_HEADER, sessionToken),
                new PasswordChangeRequest(newPassword));
    }

    public Response changePasswordForUserUpdatedDatabaseInvalidEndpoint(String sessionToken, String newPassword) {
        return sendSimpleRequest(PATCH, INVALID_CHANGE_PASSWORD_2_0, getRP(HEADER, REGISTRATION_HEADER, sessionToken),
                new PasswordChangeRequest(newPassword));
    }

    public Response changeStatusSMSNotification(String token, NotificationStatus status) {
        return sendSimpleRequest(PATCH, CUSTOMER_2_0_NOTIFICATION_SMS, getRP(HEADER, AUTHORIZATION,
                BEARER_TOKEN + token), status);
    }

    public Response changeStatusSMSNotificationWithIncorrectURL(String token, NotificationStatus status) {
        return sendSimpleRequest(PATCH, INCORRECT_CUSTOMER_2_0_NOTIFICATION_SMS, getRP(HEADER, AUTHORIZATION,
                BEARER_TOKEN + token), status);
    }

    public Response changeStatusSMSNotificationWithHttpMethod(String token, String method, NotificationStatus status) {
        return sendSimpleRequest(Method.valueOf(method), CUSTOMER_2_0_NOTIFICATION_SMS, getRP(HEADER, AUTHORIZATION,
                BEARER_TOKEN + token), status);
    }

    public Response changeStatusSMSNotificationWithValue(String token, Object value) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS, value));
        List<RequestParam> params = List.of(getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + token),
                getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, CUSTOMER_2_0_NOTIFICATION_SMS, params);
    }
}
