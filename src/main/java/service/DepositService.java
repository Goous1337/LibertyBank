package service;

import api.core.RequestParam;
import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;

import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;

public class DepositService {
    public Response checkListCurrentDepositProducts() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductsInvalidEndpoint() {
        return sendSimpleRequest(GET, INVALID_DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductEmptyToken() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
    }

    public Response checkListCurrentDepositProductsUsers() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_USER,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductUserEmptyToken() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_USER,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
    }
}
