package api.cardService;

import api.BaseTest;
import dataBase.requests.CardServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.CardServiceConstants.*;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

public class CS_2_UpdateCardStatusByCardIdTest extends BaseTest {

    public static final String CARD_ID = CardServiceDataBaseRequest.getCardId(STATUS_ACTIVE);

    public static final String JSON_SCHEMA = "schemas/cardService/successfulUpdateParameters.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @AfterEach
    public void setCardStatusActive() {
        Response response = cardService.updateCardStatus(CARD_ID, STATUS_ACTIVE);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {STATUS_BLOCKED, STATUS_CLOSED, STATUS_ACTIVE})
    @Tag("API")
    @TmsLink("LIB2-877")
    @DisplayName("Изменение статуса карты на 'Заблокированный', 'Закрытый', 'Активный'")
    @Description("Тест направлен на проверку возможности изменения статуса карты на 'Заблокированный', 'Закрытый', 'Активный'")
    public void updateCardStatusTest(String cardStatus) {
        Response response = cardService.updateCardStatus(CARD_ID, cardStatus);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }
}
