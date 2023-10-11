package service;

import java.util.List;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.clientService.SMS_Notification_Boolean;
import pojo.clientService.SMS_Notification_Integer;
import pojo.clientService.SMS_Notification_String;
import pojo.userAccountService.GetVerificationCode;
import pojo.userAccountService.PhoneVerificationRequest;
import pojo.userAccountService.Verification;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.BODY;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.PARAMETER;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.SMS_NOTIFICATION;
import static constant.ApiEndpoints.VERIFICATION;
import static constant.ApiEndpoints.VERIFICATION_CODE;
import static constant.UserServiceConstants.PARAMETER_CUSTOMER_ID;
import static constant.UserServiceConstants.PARAMETER_RECEIVER;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
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
        return sendSimpleRequest(PATCH, VERIFICATION_CODE, new RequestParam(PARAMETER, PARAMETER_RECEIVER, phoneNumber));
    }

    public Response checkVerificationCodeWithoutParam() {
        return sendRequestWithoutParams(PATCH, VERIFICATION_CODE);
    }

    public Response checkVerificationCodeInvalidHttpMethod(String invalidHttpMethod, String phoneNumber) {
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), VERIFICATION_CODE, new RequestParam(PARAMETER, PARAMETER_RECEIVER, phoneNumber));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUser(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params, new SMS_Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUserInvalidUrl(String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, "/user/settings/notif/sms", params, new SMS_Notification_Boolean(notificationStatus));
    }

    public Response checkChangingSmsNotificationSettingsAuthorizedUserInvalidMethod(String invalidHttpMethod,
            String customerId, Boolean notificationStatus) {
        List<RequestParam> params = List.of(
                new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
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
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId),
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON), new RequestParam(BODY, " ", "true"));
        return sendSimpleRequest(PATCH, SMS_NOTIFICATION, params);
    }
}