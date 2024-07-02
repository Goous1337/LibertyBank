package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@Tag("API")
@Epic("3 - Кредиты")
@Feature("CM-3.2 Просмотр информации о кредитных продуктах банка")
@DisplayName("CM-3.2 Просмотр информации о кредитных продуктах банка")
public class CM_3_2_CheckInformationCurrentMoreCreditProductsTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Получение информации по действующим кредитным продуктам банка")
    @Description("Данный тест-кейс направлен на проверку CM 3.2 по US 3.2 получение краткой информации" +
            " о кредитных продуктах банка")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-262")
    @Test
    public void checkInformationCurrentCreditProducts() {
        Response response = creditService.checkListCurrentCreditProducts();
        String jsonSchemaPath = "schemas/creditService/CM_3_2/checkInformationCurrentCreditProducts.json";
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> {
                    if (response.getBody().jsonPath().getList("credits").isEmpty()) {
                        // Проверка на пустой список
                        assertFalse(response.getBody().jsonPath().getBoolean("success"), "При пустом списке кредитов не было получено поле success со значением true");
                    } else {
                        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath));
                    }
                }
        );
    }

    @DisplayName("Получение краткой информации о кредитных продуктах банка с невалидным токеном")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 401 при неуспешной валидации токена для " +
            "получения краткой информации о кредитных продуктах банка по CM 3.2 и US 3.2")
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

    @DisplayName("Получение информации при неуспешном соединении с сервером")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 500 при неуспешном соединении с сервером " +
            "запроса для получения статуса кредитной заявки СМ-3.2 и US-3.2")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-615")
    @Test
    public void unsuccessfulInformationBankCreditProductsIncorrectRequestConfiguration() {
        Response response = creditService.checkListCurrentCreditProductsInternalServerError();
        String jsonSchemaPath = "schemas/errorMessage.json";
        assertAll(
                () -> assertEquals(SC_SERVER_ERROR,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
