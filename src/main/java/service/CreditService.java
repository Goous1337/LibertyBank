package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.*;
import static property.BaseProperties.*;

public class CreditService {
    public static Response checkGetRequestDisplayingElectronicBackground(String productId) {
        List<RequestParam> param = List.of(new RequestParam(PARAMETER, PARAMETER_PRODUCT_ID, productId),
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest(Method.GET, CREDIT_BACKGROUND, param);
    }

    public static Response checkGetRequestDisplayingElectronicBackgroundInvalidToken() {
        return sendSimpleRequest(Method.GET, CREDIT_BACKGROUND,
                new RequestParam(HEADER, AUTHORIZATION, INVALID_TOKEN_CREDIT_SERVICE));
    }
}

