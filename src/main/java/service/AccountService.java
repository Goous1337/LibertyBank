package service;

import api.core.RequestParam;
import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static constant.ApiEndpoints.ACCOUNTS_LIST;
import static io.restassured.http.Method.GET;
import static property.BaseProperties.CUSTOMER_ID;

public class AccountService {

    public Response getAccountsList() {
        return sendSimpleRequest(GET, ACCOUNTS_LIST,
                new RequestParam(HEADER, "X-Customer-Id", CUSTOMER_ID));
    }
}
