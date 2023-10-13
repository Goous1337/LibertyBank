package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService.SMS_Notification_Boolean;
import pojo.customerService.SMS_Notification_Integer;
import pojo.customerService.SMS_Notification_String;
import pojo.customerService.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static api.utils.GsonHelper.createBody;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.ApiEndpoints.CHANGE_EMAIL;
import static constant.CustomerServiceConstants.*;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class CustomerService {

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
        return sendSimpleRequest(GET, REGISTRATION, new RequestParam(PARAMETER, PARAMETER_PASSPORT, passport));
    }

    public Response checkRegistrationByPassportInvalidHttpMethod(String invalidHttpMethod, String passport) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), REGISTRATION, new RequestParam(PARAMETER, PARAMETER_PASSPORT, passport));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUser(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new SMS_Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUserInvalidUrl(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, INVALID_SMS_NOTIFICATION, params, new SMS_Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUserInvalidMethod(String invalidHttpMethod,
                                                                                    String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), SMS_NOTIFICATION, params, new SMS_Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsInvalidData(String customerId, String notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new SMS_Notification_String(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsInvalidData1(String customerId, Integer notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new SMS_Notification_Integer(notificationStatus));
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

    public Response checkUnsuccessfulUpdateEmailInvalidHttpMethod(String invalidHttpMethod, String customerId, String email) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), CHANGE_EMAIL, params, new User(customerId, email));
    }

    public Response checkUpdateClientEmailInvalidURL(String invalidURL, String customerId, String email) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, invalidURL, params, new User(customerId, email));
    }
}