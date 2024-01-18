package service;

import io.restassured.response.Response;
import pojo.customerService_2_0.ChangeUserAccountPasswordByPhone;
import pojo.customerService_2_0.CustomerService_2_0_InvalidMobilePhoneValue;
import pojo.customerService_2_0.CustomerService_2_0_Mobile;
import pojo.customerService_2_0.UserAuthorizationByPhone;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.*;
import static io.restassured.http.Method.PATCH;
import static io.restassured.http.Method.POST;

public class CustomerService_2_0 {
    public Response checkListSavingVerificationCode(CustomerService_2_0_Mobile customerService_2_0_mobile0Mobile){
        return sendSimpleRequest(PATCH,CUSTOMER_SECURITY,customerService_2_0_mobile0Mobile);
    }
    public Response checkListSavingVerificationCodeWithInvalidMobilePhoneType
            (CustomerService_2_0_InvalidMobilePhoneValue customerService_2_0_mobile0Mobile){
        return sendSimpleRequest(PATCH,CUSTOMER_SECURITY,customerService_2_0_mobile0Mobile);
    }
    public Response userAuthorizationByMobilePhone(UserAuthorizationByPhone userAuthorizationByPhone){
        return sendSimpleRequest(POST,CUSTOMER_LOGIN,userAuthorizationByPhone);
    }
    public Response checkListAbilityChangePasswordInPersonalAccount(
            ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone){
        return sendSimpleRequest(PATCH,CUSTOMER_CHANGE_PASSWORD,changeUserAccountPasswordByPhone);
    }
}
