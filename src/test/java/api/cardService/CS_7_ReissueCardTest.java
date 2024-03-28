package api.cardService;

import api.BaseTest;
import dataBase.requests.CardServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import pojo.cardService.ReissueNotFoundResponse;
import pojo.cardService.ReissueResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.cardService.ReissueBadRequest;

import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static constant.CardServiceConstants.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;
import static service.CardService.getReissueRequest;
import static service.CardService.getReissueRequestNotFound;
import static specs.RequestBody.*;
import static specs.Specs.*;

@DisplayName("CS-7 Перевыпуск карты")
public class CS_7_ReissueCardTest extends BaseTest {
    public static String JSON_SCHEMA = "schemas/cardService/successfulReissue.json";
    public static String JSON_SCHEMA_NOT_FOUND = "schemas/cardService/notFoundReissue.json";
    public static String JSON_SCHEMA_BAD_REQUEST = "schemas/cardService/badRequestReissue.json";
    public static String cardId = CardServiceDataBaseRequest.getCardId(STATUS_ACTIVE);

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1044")
    @DisplayName("Перевыпуск карты")
    @Description("Тест направлен на проверку возможности перевыпуска карты с аналогичными параметрами, что и предыдущая")
    public void reissueCardTest() {
        ReissueResponse response = getReissueRequest(reissueRequestBody, cardId)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA))
                .spec(response201)
                .extract().as(ReissueResponse.class);

        assertAll(
                () -> assertEquals(VALID_PRODUCT_TYPE_ID, response.getProductType()),
                () -> assertEquals("a60f746b-7faf-4bcd-bf5c-8d53c4cf0a3a", response.getAccount()),
                () -> assertEquals(CUSTOMER_ID_WITH_ACTIVE_CARDS, response.getCustomer()),
                () -> assertEquals(null, response.getBalance()),
                () -> assertEquals(false, response.getFavourite()),
                () -> assertEquals(STATUS_ACTIVE, response.getCardStatus()),
                () -> assertEquals(true, response.getEmbossed()),
                () -> assertEquals(true, response.getPermitVirtualPayment()),
                () -> assertEquals("03/24", response.getCreatedAt()),
                () -> assertEquals("03/29", response.getExpiredAt()),
                () -> assertEquals(null, response.getClosedAt()));
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1526")
    @DisplayName("Невалидное поле в теле запроса")
    @Description("Тест направлен на проверку возможности перевыпуска карты с невалидным полем в теле запроса")
    public void reissueCardInvalidBodyTest() {
        ReissueBadRequest response = getReissueRequest(reissueRequestBodyInvalidAccountId, cardId)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA_BAD_REQUEST))
                .spec(response400)
                .extract().as(ReissueBadRequest.class);

        assertAll(
                () -> assertEquals("Bad Request", response.getError()));
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1530")
    @DisplayName("Перевыпуск карты с полем, не допускающим значение NULL")
    @Description("Тест направлен на проверку возможности перевыпуска карты  с полем, не допускающим значение NULL")
    public void reissueCardNullAccountIdTest() {
        ReissueBadRequest response = getReissueRequest(reissueRequestBodyNullAccountId, cardId)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA_BAD_REQUEST))
                .spec(response400)
                .extract().as(ReissueBadRequest.class);

        assertAll(
                () -> assertEquals("Bad Request", response.getError()));
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3206")
    @DisplayName("Невалидное значение cardId")
    @Description("Тест направлен на проверку возможности перевыпуска карты с неверным значением в поле cardId ")
    public void reissueCardNotFoundTest() {
        ReissueNotFoundResponse response = getReissueRequestNotFound(reissueRequestBody)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA_NOT_FOUND))
                .spec(response404)
                .extract().as(ReissueNotFoundResponse.class);

        assertAll(
                () -> assertEquals("The card with the passed parameters was not found", response.getMessage()));
    }
}