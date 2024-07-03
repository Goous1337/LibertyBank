package api.creditService;

import api.BaseTest;
import dataBase.requests.CreditServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import service.CreditService;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("CM 3.3.1 Отображение электронной формы для оформления заявки на кредит")
public class CM_3_3_1_DisplayingElectronicBackgroundForApplyingCreditTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит")
    @Description("Данный тест-кейс направлен на проверку отображения электронной формы для оформления заявки на кредит")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-850")
    @Test
    public void checkDisplayingElectronicBackgroundForRealCreditProduct() {
        String jsonSchemaPath = "schemas/creditService/CM_3_3_1/checkDisplayingElectronicBackgroundForApplyingCreditValidToken.json";
        Integer productId = CreditServiceDataBaseRequest.getThirdCreditProductId();
        Response response = CreditService.checkGetRequestDisplayingElectronicBackgroundForValid(productId);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на проверку отображения электронной формы для оформления заявки на кредит при неуспешной валидации токена")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-851")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditInvalidToken() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Integer productId = CreditServiceDataBaseRequest.getThirdCreditProductId();
        Response response = creditService.checkCreditInfoNoToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит в случае, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку отображения ошибки 404 в ответе в случае, если в результирующей таблице нет записи по указанным критериям")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-852")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditValidTokenWithoutParameters() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        int countProducts = CreditServiceDataBaseRequest.getCountOfProducts();
        Response response = CreditService.checkGetRequestDisplayingElectronicBackgroundForValid(countProducts + 100);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит в случае ошибки сервера")
    @Description("Данный тест-кейс направлен на проверку отображения ошибки 500 в ответе в случае ошибки сервера")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-854")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditErrorServer() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Integer productId = CreditServiceDataBaseRequest.getThirdCreditProductId();
        Response response = CreditService.checkGetRequestDisplayingElectronicBackground(productId);
        assertAll(
                () -> assertEquals(SC_SERVER_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
