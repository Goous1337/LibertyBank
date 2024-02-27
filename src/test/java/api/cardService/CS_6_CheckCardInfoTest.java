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
import pojo.cardService.CardData;

import static constant.ApiEndpoints.CARD_BY_NAME;
import static constant.CardServiceConstants.CARD_NAME_CLASSIC;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

public class CS_6_CheckCardInfoTest extends BaseTest {
    public static String JSON_SCHEMA_VALID_NAME = "schemas/cardService/checkCardInfo.json";
    public static String JSON_SCHEMA_INVALID_NAME = "schemas/cardService/checkCardInfoInvalidName.json";
    public static String cardName = CARD_NAME_CLASSIC;

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-2773")
    @DisplayName("Просмотр подробной информации по карточному продукту")
    @Description("Тест направлен на проверку возможности просмотра подробной информации по определенной карте")
    public void checkCardInfoWithValidCardName() {
        Response response = cardService.getCardInfo(cardName);
        CardData[] cards = given().when().get(CARD_BY_NAME + "/" + cardName).as(CardData[].class);
        for (CardData card : cards) {
            assertEquals(cardName, card.getTypeName());
        }
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_VALID_NAME))
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-2773")
    @DisplayName("Проверка ответа при неверном имени карты")
    @Description("Тест направлен на проверку ответа при неверном имени карты")
    public void checkCardInfoWithInvalidCardName() {
        Response response = cardService.getCardInfo("qwe");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_INVALID_NAME))
        );
    }
}
