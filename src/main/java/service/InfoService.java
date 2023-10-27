package service;


import io.restassured.http.Method;
import io.restassured.response.Response;

import static api.core.ApiClient.sendRequestWithoutParams;
import static constant.ApiEndpoints.*;
import static io.restassured.http.Method.GET;

public class InfoService {
    public Response successfulGettingCityList() {
        return sendRequestWithoutParams(GET, CITY_LIST);
    }

    public Response unsuccessfulGettingCityListInvalidUrl() {
        return sendRequestWithoutParams(GET, INVALID_CITY_LIST);
    }
    public Response unsuccessfulGettingCityListInvalidHttpMethod(String invalidHttpMethod) {
        return sendRequestWithoutParams(Method.valueOf(invalidHttpMethod), CITY_LIST);
    }
}
