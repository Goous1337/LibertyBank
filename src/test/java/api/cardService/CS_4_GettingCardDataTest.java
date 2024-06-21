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

import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static constant.CardServiceConstants.INVALID_CARD_ID;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

@Tags({@Tag("API"), @Tag("MVP")})
@DisplayName("CS-4 Получение данных по карте")
public class CS_4_GettingCardDataTest extends BaseTest {

    public static final String JSON_SCHEMA = "schemas/cardService/getCardData.json";
    public static final String JSON_SCHEMA_FOR_ERROR = "schemas/cardService/errorResponse.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @TmsLink("LIB2-1039")
    @DisplayName("Получение данных по карте")
    @Description("Тест направлен на проверку возможности просмотра данных по карте пользователем")
    public void gettingCardData() {
        String cardId = CardServiceDataBaseRequest.getCardId(STATUS_ACTIVE);
        Response response = cardService.getCardData(cardId);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }

    @Test
    @TmsLink("LIB2-1039")
    @DisplayName("Получение данных по карте с невалидным id карты")
    @Description("Тест направлен на проверку отсутствия возможности просмотра данных по карте пользователем, если введён невалидный id карты")
    public void gettingCardDataWithInvalidCardId() {
        Response response = cardService.getCardData(INVALID_CARD_ID);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_FOR_ERROR))
        );
    }
}
