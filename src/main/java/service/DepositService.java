package service;

import api.core.RequestParam;
import api.core.RequestParamType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.DEPOSIT_SETTINGS;
import static constant.ApiEndpoints.VERIFICATION;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.POST;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;


public class DepositService {
    public Response depositData(Integer depositProductId,Double initialAmount,String periodMonths, Boolean autoRenewal){
        List<RequestParam> params = List.of(new RequestParam(RequestParamType.HEADER,AUTHORIZATION,ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest(POST,DEPOSIT_SETTINGS,params,new
    }
}
