package service;

import io.restassured.response.Response;
import pojo.cardService.UpdateCardStatusRequest;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.QUERY_PARAMETER;
import static constant.AccountServiceConstants.HEADER_CUSTOMER_ID;
import static constant.AccountServiceConstants.VALID_CUSTOMER_ID;
import static constant.ApiEndpoints.*;
import static constant.CardServiceConstants.*;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;

public class CardService {

    public Response getCardsInfo() {
        return sendSimpleRequest(GET, CARDS_LIST,
                getRP(HEADER, HEADER_CUSTOMER_ID, VALID_CUSTOMER_ID));
    }

    public Response getActiveCardsInfoUserHaveCards() {
        return sendSimpleRequest(GET, ACTIVE_CARDS,
                getRP(QUERY_PARAMETER, PARAMETER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS));
    }

    public Response getActiveCardsInfoUserHaveNoCards() {
        return sendSimpleRequest(GET, ACTIVE_CARDS,
                getRP(QUERY_PARAMETER, PARAMETER_CUSTOMER_ID, CUSTOMER_ID_WITHOUT_ACTIVE_CARDS));
    }

    public Response getCardInfo(String cardName) {
        return sendRequestWithoutParams(GET, CARD_BY_NAME + "/" + cardName);
    }

    public Response getCardData(String cardId) {
        return sendSimpleRequest(GET, ACTIVE_CARDS + "/" + cardId,
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS));
    }

    public Response getInformationCardProduct(String productTypeId) {
        return sendRequestWithoutParams(GET, CARDS_LIST + "/" + productTypeId);
    }

    public Response updateCardStatus(String cardId, String cardStatus) {
        return sendSimpleRequest(PATCH, ACTIVE_CARDS + "/" + cardId,
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS),
                UpdateCardStatusRequest.builder().status(cardStatus).build());
    }
}
