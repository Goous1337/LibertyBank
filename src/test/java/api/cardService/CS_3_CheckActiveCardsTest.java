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

import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

public class CS_3_CheckActiveCardsTest extends BaseTest {
    public static String JSON_SCHEMA_WITH_CARDS = "schemas/cardService/checkActiveCards.json";
    public static String JSON_SCHEMA_WITHOUT_CARDS = "schemas/cardService/checkActiveCardsWithoutCards.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-40")
    @DisplayName("Просмотр активных карт")
    @Description("Тест направлен на проверку возможности просмотра активных карт")
    public void checkActiveCardsWithActiveCards() {
        Response response = cardService.getActiveCardsInfoUserHaveCards();
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_WITH_CARDS))
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-40")
    @DisplayName("Проерка ответа при отсутствии активных карт")
    @Description("Тест направлен на проверку ответа при отсутствии активных карт")
    public void checkActiveCardsWithNoActiveCards() {
        Response response = cardService.getActiveCardsInfoUserHaveNoCards();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_WITHOUT_CARDS))
        );
    }
}
