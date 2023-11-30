package service;

import api.core.RequestParam;
import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.ApiEndpoints.CREDIT_PRODUCTS;
import static constant.ApiEndpoints.INVALID_CREDIT_PRODUCTS;
import static io.restassured.http.Method.GET;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;

public class CreditService {

    public Response checkListCurrentCreditProducts() {
        return sendSimpleRequest(GET, CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentCreditProductsWithInvalidToken(String invalidToken) {
        return sendSimpleRequest(GET, CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, invalidToken));
    }

    public Response checkListCurrentCreditProductsIncorrectRequestConfiguration() {
        return sendSimpleRequest(GET, INVALID_CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }
}
