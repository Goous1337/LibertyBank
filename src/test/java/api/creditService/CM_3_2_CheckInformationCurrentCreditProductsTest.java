package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("Получение информации о кредитных продуктах банка")
public class CM_3_2_CheckInformationCurrentCreditProductsTest extends BaseTest {

    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Получение информации по действующим кредитным продуктам банка")
    @Description("Данный тест-кейс направлен на проверку CM 3.2 по US 3.2 получение краткой информации" +
            " о кредитных продуктах банка")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-262")
    @Test

    public void checkInformationCurrentCreditProducts() {
        Response response = creditService.checkListCurrentCreditProducts();
        String jsonSchemaPath = "schemas/creditService/CM_3_2/checkInformationCurrentCreditProducts.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))

        );
    }

    @Disabled("BUG https://jira.astondevs.ru/browse/LIB3-1133")
    @DisplayName("Получение краткой информации о кредитных продуктах банка с невалидным токеном")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 401 при неуспешной валидации токена для " +
            "получения краткой информации о кредитных продуктах банка по CM 3.2 и US 3.2")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-267")
    @Test

    public void checkInformationCurrentCreditProductsWithInvalidToken() {
        String invalidToken = "asdasaksdkasd";
        Response response = creditService.checkListCurrentCreditProductsWithInvalidToken(invalidToken);
        String jsonSchemaPath = "schemas/errorMessage.json";
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение краткой информации о кредитных продуктах банка при неправильной конфигурации запроса")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 400 при неправильной конфигурации " +
            "запроса для получения статуса кредитной заявки СМ-3.2 и US-3.2")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-272")
    @Test

    public void unsuccessfulInformationBankCreditProductsIncorrectRequestConfiguration() {
        Response response = creditService.checkListCurrentCreditProductsIncorrectRequestConfiguration();
        String jsonSchemaPath = "schemas/errorMessage.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}