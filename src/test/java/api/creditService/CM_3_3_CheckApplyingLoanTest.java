package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static constant.CreditServiceConstants.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("СМ 3.3 Оформление заявки на кредит")
public class CM_3_3_CheckApplyingLoanTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Оформление заявки на кредит")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление" +
            " заявки на кредит авторизованным пользователем.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-606")
    @Test
    public void checkApplyingLoan() {
        Response response = creditService.checkListApplyingLoan
                (1, 250, 20, "RUB", "2023-11-28"
                        , 60000, 30000, "8698345212");

        assertAll(
                () -> assertEquals(SC_OK,
                        response.getStatusCode(),
                        "Код ответа не соответствует ожидаемому"),

                () -> assertEquals(PRODUCT_ID, (Integer) response.jsonPath().get("productId")),
                () -> assertEquals(AMOUNT, (Integer) response.jsonPath().get("amount")),
                () -> assertEquals(PERIOD_MONTH, (Integer) response.jsonPath().get("periodMonths"))
        );
    }

    @DisplayName("Оформление заявки на кредит с невалидным токеном")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки" +
            " на кредит авторизованным пользователем в случае неуспешной валидации токена.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-611")
    @Test
    public void checkApplyingLoanInvalidToken() {
        String jsonSchemaPath = "schemas/creditService/CM_3_3/errorMessage.json";
        Response response = creditService.checkListApplyingLoanInvalidToken
                (3, 2500000, 20, "RUB", "2023-11-28",
                        60000, 30000, "8698345212");
        Assertions.assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Оформление заявки на кредит в случае, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки на кредит" +
            " авторизованным пользователем в случае, если в результирующей таблице нет записей по указанным критериям.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-614")
    @Test
    public void checkApplyingLoanServerNoRecordsMatchingCriteria() {
        String jsonSchemaPath = "schemas/creditService/CM_3_3/errorMessage.json";
        Response response = creditService.checkListApplyingLoan
                (0, 2500000, 20, "RUB", "2023-11-28",
                        60000, 30000, "8698345212");
        Assertions.assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Оформление заявки на кредит в случае некорректной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку CM 3.3 по US 3.3 на оформление заявки" +
            " на кредит авторизованным пользователем в случае некорректной конфигурации запроса.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-612")
    @Test
    public void checkApplyingLoanInvalidConfig() { //TODO сделать параметризацию по каждому полю по очереди
        String jsonSchemaPath = "schemas/creditService/CM_3_3/errorMessage.json";
        Response response = creditService.checkListApplyingLoan
                (null, 2500000, 20, "RUB", "2023-11-28",
                        60000, 30000, "8698345212");
        Assertions.assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}

