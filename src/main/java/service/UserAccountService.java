package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService.SMS_Notification_String;
import pojo.userAccountService.GetVerificationCode;
import pojo.userAccountService.PhoneVerificationRequest;
import pojo.userAccountService.Verification;

import java.util.List;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static constant.CustomerServiceConstants.PARAMETER_RECEIVER;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.*;


public class UserAccountService {

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
        return sendSimpleRequest(PATCH, VERIFICATION_CODE, new Verification(phoneNumber));
    }

    public Response check111(String phoneNumber) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(PATCH, VERIFICATION_CODE, params, new Verification(phoneNumber));


//        public Response checkVerificationCodeWithoutParam () {
//            return sendRequestWithoutParams(PATCH, VERIFICATION_CODE);
//        }

//        public Response checkVerificationCodeInvalidHttpMethod (String invalidHttpMethod, String phoneNumber){
//            return sendSimpleRequest(Method.valueOf(invalidHttpMethod), VERIFICATION_CODE, new RequestParam(PARAMETER, PARAMETER_RECEIVER, phoneNumber));
//        }
    }}