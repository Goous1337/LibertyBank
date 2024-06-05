package service;

import constant.CardProducts;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.cardService.RegistryDebitBody;
import pojo.cardService.ReissueRequestBody;
import pojo.cardService.UpdateCardLimitsRequest;
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
import static specs.Specs.requestSpec;

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

    public Response updateCardLimits(String cardId, int operationPerDay, int operationPerMonth, int amountPerOperation,
                                     int amountPerDay, int amountPerMonth) {
        return sendSimpleRequest(PATCH, ACTIVE_CARDS + "/" + cardId,
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS),
                UpdateCardLimitsRequest.builder()
                        .operationPerDay(operationPerDay)
                        .operationPerMonth(operationPerMonth)
                        .amountPerOperation(amountPerOperation)
                        .amountPerDay(amountPerDay)
                        .amountPerMonth(amountPerMonth)
                        .build());
    }

    public Response getCardLimits(String cardId) {
        return sendSimpleRequest(GET, ACTIVE_CARDS + "/" + cardId + "/limits",
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS));
    }

    public Response getBenefitsOfCardProducts(CardProducts cardProducts) {
        return sendRequestWithoutParams(GET, CARD_PRODUCT_BENEFITS + cardProducts.getCardName());
    }
    
    public static Response getReissueRequest(ReissueRequestBody body, String cardId) {
        return RestAssured.given(requestSpec)
                .header(HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS)
                .body(body)
                .when()
                .post(ACTIVE_CARDS + "/" + cardId);
    }

    public static Response getReissueRequestNotFound(ReissueRequestBody body) {
        return RestAssured.given(requestSpec)
                .header(HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS)
                .body(body)
                .when()
                .post(ACTIVE_CARDS + "/" + INVALID_CARD_ID);
    }

    public static Response createRegistryDebCard(RegistryDebitBody body) {
        return RestAssured.given(requestSpec)
                .body(body)
                .when()
                .post(ACTIVE_CARDS);

    }

    public Response getCardTariffs(String cardId) {
        return sendSimpleRequest(GET, ACTIVE_CARDS + "/" + cardId + "/tariffs",
                getRP(HEADER, HEADER_CUSTOMER_ID, CUSTOMER_ID_WITH_ACTIVE_CARDS));
    }
}
