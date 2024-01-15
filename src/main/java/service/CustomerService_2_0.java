package service;

import io.restassured.response.Response;
import pojo.customerService_2_0.CustomerService_2_0_Mobile;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.CUSTOMER_SECURITY;
import static io.restassured.http.Method.PATCH;

public class CustomerService_2_0 {
    public Response checkListSavingVerificationCode(CustomerService_2_0_Mobile customerService_2_0_mobile0Mobile){
        return sendSimpleRequest(PATCH,CUSTOMER_SECURITY,customerService_2_0_mobile0Mobile);
    }
}
