package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService.*;

import java.util.List;
import java.util.Map;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static api.utils.GsonHelper.createBody;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.*;
import static constant.InfoServiceConstants.PARAMETER_CITYID;
import static constant.InfoServiceConstants.PARAMETER_CUSTOMERID;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class CustomerService {

    public Response checkRegistrationByPhone(String phoneNumber) {
        return sendSimpleRequest(GET, REGISTRATION, new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
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

    public Response checkRegistrationByPassport(String passportSeries, String passportNumber) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_PASSPORT_SERIES, passportSeries),
                new RequestParam(PARAMETER, PARAMETER_PASSPORT_NUMBER, passportNumber));
        return sendSimpleRequest(GET, REGISTRATION, params);
    }

    public Response checkRegistrationByPassportInvalidHttpMethod(String invalidHttpMethod, String passportSeries, String passport) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), REGISTRATION, new RequestParam(PARAMETER, PARAMETER_PASSPORT, passport));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUser(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUserInvalidUrl(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, INVALID_SMS_NOTIFICATION, params, new Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUserInvalidMethod(String invalidHttpMethod,
                                                                                    String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), SMS_NOTIFICATION, params, new Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsInvalidData(String customerId, String notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new Notification_String(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsInvalidData1(String customerId, Integer notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new Notification_Integer(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUser1(String customerId) {
        String body = createBody(Map.of(EMPTY, true));
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON), new RequestParam(BODY, EMPTY, body));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params);
    }

    public Response checkUpdateClientEmail(String customerId, String email) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, CHANGE_EMAIL, params, new User(customerId, email));
    }

    public Response checkUnsuccessfulUpdateEmailInvalidHttpMethod(String httpMethod, String customerId, String email) {
        return sendSimpleRequest(Method.valueOf(httpMethod), CHANGE_EMAIL,
                new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId), new User(customerId, email));
    }

    public Response checkUpdateClientEmailInvalidURL(String url, String customerId, String email) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, url, params, new User(customerId, email));
    }

    public Response checkSuccessfulUpdateQuestionAnswer(String customerId, String securityQuestion, String securityAnswer) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new RequestParam(PATH, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, QUESTION_ANSWER, params, new UserQuestion(securityQuestion, securityAnswer));
    }

    public Response checkUpdateQuestionAnswerInvalidHttpMethod(String invalidHttpMethod,
                                                               String customerId,
                                                               String securityQuestion, String securityAnswer) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new RequestParam(PATH, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), QUESTION_ANSWER,
                params, new UserQuestion(securityQuestion, securityAnswer));
    }

    public Response checkUpdateQuestionAnswerInvalidUrl(String customerId, String securityQuestion, String securityAnswer) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new RequestParam(PATH, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, INVALID_QUESTION_ANSWER, params, new UserQuestion(securityQuestion, securityAnswer));
    }

    public Response checkChangingEmailNotificationSettingsAuthorizedUser(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, EMAIL_NOTIFICATION, params, new Notification_Boolean(notificationStatus));
    }

    public Response checkChangingEmailNotificationSettingsInvalidMethod(String invalidHttpMethod,
                                                                                    String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), EMAIL_NOTIFICATION, params, new Notification_Boolean(notificationStatus));
    }

    public Response checkChangingEmailNotificationSettingsInvalidUrl(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, INVALID_EMAIL_NOTIFICATION, params, new Notification_Boolean(notificationStatus));
    }

    public Response checkChangingEmailNotificationSettingsInvalidData(String customerId, String notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, EMAIL_NOTIFICATION, params, new Notification_String(notificationStatus));
    }

    public Response checkChangingEmailNotificationSettingsInvalidData(String customerId, Integer notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, EMAIL_NOTIFICATION, params, new Notification_Integer(notificationStatus));
    }

    public Response checkChangingEmailNotificationSettings(String customerId) {
        String body = createBody(Map.of(EMPTY, true));
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON), new RequestParam(BODY, EMPTY, body));
        return sendSimpleRequest(PATCH, EMAIL_NOTIFICATION, params);
    }
    public Response checkPushNotification(String customerId, String notificationStatus) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS,notificationStatus));
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId)
                , new RequestParam(BODY, EMPTY, body),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION, params);
    }

    public Response checkPushNotificationWithHttpMethod(String customerId, String httpMethod) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(Method.valueOf(httpMethod), PUSH_NOTIFICATION, params);
    }
    public Response checkPushNotificationInvalidUrl (String customerId) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, INVALID_NOTIFICATION, params);
    }
    public Response checkSendingNotificationSettings(String customerId) {
        return sendSimpleRequest(GET, NOTIFICATION_SETTINGS, new RequestParam(PARAMETER, PARAMETER_CUSTOMERID, customerId));
    }

    public Response checkSendingNotificationSettingsInvalidMethod(String invalidHttpMethod, String customerId) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), NOTIFICATION_SETTINGS, new RequestParam(PARAMETER,
                PARAMETER_CUSTOMER_ID, customerId));
    }
    public Response checkGettingUserInformation(String customerId) {
        return sendSimpleRequest(GET, RETRIEVING_USER_DATA, new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }

    public Response checkGettingUserInformationInvalidMethod(String invalidHttpMethod, String customerId) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), RETRIEVING_USER_DATA, new RequestParam(PARAMETER,
                PARAMETER_CUSTOMER_ID, customerId));
    }
}