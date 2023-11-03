package service;


import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.List;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.*;
import static constant.InfoServiceConstants.*;
import static io.restassured.http.Method.GET;

public class InfoService {
    public Response unsuccessfulGettingCityListInvalidUrl() {
        return sendRequestWithoutParams(GET, INVALID_CITY_LIST);
    }

    public Response gettingCityList(String HttpMethod) {
        return sendRequestWithoutParams(Method.valueOf(HttpMethod), CITY_LIST);
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

    public Response checkGettingAllBankDivisions(String httpMethod) {
        return sendRequestWithoutParams(Method.valueOf(httpMethod), BANK_DIVISIONS_LIST);
    }

    public Response checkGettingInformationCurrencyExchangeRatesCityId(String cityId) {
        return sendSimpleRequest(GET, BANK_EXCHANGE_RATES, new RequestParam(PARAMETER, PARAMETER_CITYID, cityId));
    }

    public Response checkGettingInformationCurrencyExchangeRateCityIdPageSize(String cityId, String pageNumb, String size) {
        List<RequestParam> params = List.of(new RequestParam(PARAMETER, PARAMETER_CITYID, cityId),
                new RequestParam(PARAMETER, PARAMETER_PAGENUMB, pageNumb), new RequestParam(PARAMETER, PARAMETER_PAGELIMIT, size));
        return sendSimpleRequest(GET, BANK_EXCHANGE_RATES, params);
    }

    public Response checkGettingInformationCurrencyExchangeRatesInvalidUrl() {
        return sendRequestWithoutParams(GET, INVALID_BANK_EXCHANGE_RATES);
    }

    public Response checkGettingInformationCurrencyExchangeRates(String httpMethod) {
        return sendRequestWithoutParams(Method.valueOf(httpMethod), BANK_EXCHANGE_RATES);
    }
}
