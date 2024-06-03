package api.cardService;

import api.BaseTest;
import dataBase.requests.CardServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pojo.cardService.ReissueBadRequest;
import pojo.cardService.ReissueNotFoundResponse;

import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.hc.core5.http.HttpStatus.SC_CREATED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;
import static service.CardService.getReissueRequest;
import static service.CardService.getReissueRequestNotFound;
import static specs.RequestBody.*;
import static specs.Specs.response400;
import static specs.Specs.response404;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("CS-7 Перевыпуск карты")
public class CS_7_ReissueCardTest extends BaseTest {
    public static String JSON_SCHEMA_WITH_CARDS_1 = "schemas/cardService/successCreateCard.json";
    public static String JSON_SCHEMA_NOT_FOUND = "schemas/cardService/notFoundReissue.json";
    public static String JSON_SCHEMA_BAD_REQUEST = "schemas/cardService/badRequestReissue.json";
    public static String cardId = CardServiceDataBaseRequest.getCardId(STATUS_ACTIVE);

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @TmsLink("LIB2-1044")
    @DisplayName("Перевыпуск карты")
    @Description("Тест направлен на проверку возможности перевыпуска карты с аналогичными параметрами, что и предыдущая")
    public void reissueCardTest() {
        Response response = getReissueRequest(REISSUE_REQUEST_BODY, cardId);

        assertAll(
                () -> assertEquals(SC_CREATED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_WITH_CARDS_1)));
    }

    @Test
    @TmsLink("LIB2-1526")
    @DisplayName("Невалидное поле в теле запроса")
    @Description("Тест направлен на проверку возможности перевыпуска карты с невалидным полем в теле запроса")
    public void reissueCardInvalidBodyTest() {
        ReissueBadRequest response = getReissueRequest(REISSUE_REQUEST_BODY_INVALID_ACCOUNT_ID, cardId)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA_BAD_REQUEST))
                .spec(response400)
                .extract().as(ReissueBadRequest.class);

        assertEquals("JSON parse error: Cannot deserialize value of type `java.util.UUID` from String \"InvalidID\": UUID has to be represented by standard 36-char representation", response.getMessage());
    }

    @Test
    @TmsLink("LIB2-1530")
    @DisplayName("Перевыпуск карты с полем, не допускающим значение NULL")
    @Description("Тест направлен на проверку возможности перевыпуска карты  с полем, не допускающим значение NULL")
    public void reissueCardNullAccountIdTest() {
        ReissueBadRequest response = getReissueRequest(REISSUE_REQUEST_BODY_NULL_ACCOUNT_ID, cardId)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA_BAD_REQUEST))
                .spec(response400)
                .extract().as(ReissueBadRequest.class);

        assertEquals("JSON parse error: Cannot deserialize value of type `java.util.UUID` from String \"NULL\": UUID has to be represented by standard 36-char representation", response.getMessage());
    }

    @Test
    @TmsLink("LIB2-3206")
    @DisplayName("Невалидное значение cardId")
    @Description("Тест направлен на проверку возможности перевыпуска карты с неверным значением в поле cardId ")
    public void reissueCardNotFoundTest() {
        ReissueNotFoundResponse response = getReissueRequestNotFound(REISSUE_REQUEST_BODY)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(JSON_SCHEMA_NOT_FOUND))
                .spec(response404)
                .extract().as(ReissueNotFoundResponse.class);

        assertEquals("The card with the passed parameters was not found", response.getMessage());
    }
}