package api.cardService;

import api.BaseTest;
import dataBase.requests.CardServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.cardService.cardTariffs.CardTariffs;

import static api.utils.JsonParser.parseJson;
import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static constant.CardServiceConstants.INVALID_CARD_ID;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

public class CS_8_GetCardTariffs extends BaseTest {
    private static final String JSON = "/jsons/cardJsons/cardTariffsGoldEur.json";
    private static final String JSON_SCHEMA_FOR_ERROR = "schemas/cardService/getCardTariffsWithInvalidCardId.json";
    private static final String CARD_ID = CardServiceDataBaseRequest.getCardId(STATUS_ACTIVE);

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3169")
    @DisplayName("Просмотр тарифов по карте пользователя")
    @Description("Тест направлен на проверку возможности просмотра тарифов по карте пользователя")
    public void getCardTariffsTest() {
        CardTariffs actualTariffs = cardService.getCardTariffs(CARD_ID).as(CardTariffs.class);

        CardTariffs expectedTariffs = parseJson(CardTariffs.class, JSON);
        Assertions.assertEquals(actualTariffs, expectedTariffs, "Данные не соответствуют ожидаемым");
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3169")
    @DisplayName("Просмотр тарифов по карте пользователя с невалидным id карты")
    @Description("Тест направлен на проверку возможности просмотра тарифов по карте пользователя с невалидным id карты")
    public void getCardTariffsWithInvalidCardId() {
        Response response = cardService.getCardTariffs(INVALID_CARD_ID);

        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_FOR_ERROR))
        );
    }
}
