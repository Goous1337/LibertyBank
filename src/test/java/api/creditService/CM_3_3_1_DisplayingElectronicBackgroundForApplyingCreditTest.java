package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import service.CreditService;

import static constant.CreditService.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("CM 3.3.1 Отображение электронной формы для оформления заявки на кредит")
public class CM_3_3_1_DisplayingElectronicBackgroundForApplyingCreditTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }


    @DisplayName("Отображение электронной формы для оформления заявки на кредит")
    @Description("Данный тест-кейс направлен на проверку отображения электронной формы для оформления заявки на кредит")
    @Tags({@Tag("Positive"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-850")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditValidToken() {
        Response response = CreditService.checkGetRequestDisplayingElectronicBackground("3");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(ID,(String) response.jsonPath().get("id")),
                () -> assertEquals(NAME_CREDIT,(String) response.jsonPath().get("name")),
                () -> assertEquals(MIN_SUM,(Integer) response.jsonPath().get("minSum")),
                () -> assertEquals(MAX_SUM,(Integer) response.jsonPath().get("maxSum")),
                () -> assertEquals(CURRENCY_CODE,(String) response.jsonPath().get("currencyCode")),
                () -> assertEquals(MIN_PERIOD_MONTHS,(Integer) response.jsonPath().get("minPeriodMonths")),
                () -> assertEquals(MAX_PERIOD_MONTHS,(Integer) response.jsonPath().get("maxPeriodMonths"))
        );
    }

    @Disabled("https://jira.astondevs.ru/browse/LIB3-1132")
    @DisplayName("Отображение электронной формы для оформления заявки на кредит при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на проверку отображения электронной формы для оформления заявки на кредит при неуспешной валидации токена")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-851")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditInvalidToken() {
        Response response = CreditService.checkGetRequestDisplayingElectronicBackgroundInvalidToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит в случае, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку отображения ошибки в ответе сервера в случае, если в результирующей таблице нет записи по указанным критериям")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-852")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditValidTokenWithoutParameters() {
        Response response = CreditService.checkGetRequestDisplayingElectronicBackground("4");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );

    }
}





