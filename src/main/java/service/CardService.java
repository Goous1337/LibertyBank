package service;

import io.restassured.response.Response;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.*;
import static constant.AccountServiceConstants.*;
import static constant.ApiEndpoints.*;
import static constant.CardServiceConstants.*;
import static io.restassured.http.Method.GET;

public class CardService {

    public Response getCardsInfo() {
        return sendSimpleRequest(GET, CARDS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID));
    }

    public Response getActiveCardsInfoUserHaveCards() {
        return sendSimpleRequest(GET, ACTIVE_CARDS,
                getRP(QUERY_PARAMETER, PARAMETER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS));
    }

    public Response getCardData(String cardId) {
        return sendSimpleRequest(GET, ACTIVE_CARDS + "/" + cardId,
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS));
    }

    public Response getInformationCardProduct(String productTypeId) {
        return sendRequestWithoutParams(GET, CARDS_LIST + "/" + productTypeId);
    }
}
