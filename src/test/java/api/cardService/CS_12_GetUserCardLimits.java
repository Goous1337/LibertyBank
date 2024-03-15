package api.cardService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.cardService.CS_2_UpdateCardStatusByCardIdTest.CARD_ID;
import static constant.CardServiceConstants.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

@DisplayName("CS-12 Вывод пользовательских лимитов")
public class CS_12_GetUserCardLimits extends BaseTest {

    public static final String JSON_SCHEMA = "schemas/cardService/checkUserCardLimits.json";
    public static final String JSON_SCHEMA_ERROR_RESPONSE = "schemas/cardService/errorResponse.json";
    public static final String JSON_SCHEMA_ERROR_404 = "schemas/cardService/error404Message.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3128")
    @DisplayName("Просмотр пользовательских лимитов для активной карты")
    @Description("Тест направлен на проверку возможности просмотра пользовательских лимитов для существующего cardId")
    public void checkUserCardLimitsForActiveCard() {
        Response response = cardService.getCardLimits(CARD_ID);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3130")
    @DisplayName("Просмотр пользовательских лимитов для несуществующей карты")
    @Description("Тест направлен на проверку возможности просмотра пользовательских лимитов для несуществующего cardId")
    public void checkUserCardLimitsForInvalidCardId() {
        Response response = cardService.getCardLimits(INVALID_CARD_ID);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_ERROR_RESPONSE))
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3132")
    @DisplayName("Просмотр пользовательских лимитов при неправильной конфигурации запроса")
    @Description("Тест направлен на проверку возможности просмотра пользовательских лимитов при неправильной конфигурации запроса")
    public void checkUserCardLimitsWithInvalidRequestParam() {
        Response response = cardService.getCardLimits("qwe");
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_ERROR_404))
        );
    }
}
