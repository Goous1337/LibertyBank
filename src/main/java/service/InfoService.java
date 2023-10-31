package service;


import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService.SMS_Notification_Boolean;
import pojo.customerService.User;

import java.util.List;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.PARAMETER;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static constant.InfoServiceConstants.*;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;

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

    public Response checkGettingAllBankDivisions() {
        return sendRequestWithoutParams(GET, BANK_DIVISIONS_LIST);
    }

    public Response checkGettingBankDivisionsByCity(String cityId) {
        return sendSimpleRequest(GET, BANK_DIVISIONS_LIST, new RequestParam(PARAMETER, PARAMETER_CITYID, cityId));
    }

    public Response checkGettingBankDivisionsLimitedList(String pageNumb, String pageLimit) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_PAGENUMB, pageNumb),
                new RequestParam(PARAMETER, PARAMETER_PAGELIMIT, pageLimit));
        return sendSimpleRequest(GET, BANK_DIVISIONS_LIST, params);
    }

    public Response checkGettingBankDivisionsListInvalidURL(String url) {
        return sendRequestWithoutParams(GET, url);
    }

    public Response checkGettingAllBankDivisionsInvalidMethod(String httpMethod) {
        return sendRequestWithoutParams(Method.valueOf(httpMethod), BANK_DIVISIONS_LIST);

    }
}
