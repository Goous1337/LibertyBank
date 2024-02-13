package service;

import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.*;
import static constant.AccountServiceConstants.*;
import static constant.ApiEndpoints.*;
import static io.restassured.http.Method.GET;

public class CardService {
    public Response getCardsInfo() {
        return sendSimpleRequest(GET, CARDS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID));
    }
}
