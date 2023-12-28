package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import service.CreditService;

import static constant.CreditService.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

public class CM_3_5_CheckViewInformationAboutCurrentCreditsUsersTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5" +
            " по US 3.5 на просмотр подробной информации о действующих кредитах пользователя")
    @Tags({@Tag("Positive"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-921")
    @Test
    public void checkUserInformationIncludeRealCreditProducts() {
        Response response = CreditService.checkViewInfoCurrentCreditsUsers("1");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(NAME_INFO_CREDIT, (String) response.jsonPath().get("name")),
                () -> assertEquals(CREDIT_LIMIT, (Integer) response.jsonPath().get("creditLimit")),
                () -> assertEquals(CURRENCY_CODE, (String) response.jsonPath().get("currencyCode")),
                () -> assertEquals(PERIOD_MONTHS, (Integer) response.jsonPath().get("periodMonths")),
                () -> assertEquals(INTEREST_RATE, (Integer) response.jsonPath().get("interestRate")),
                () -> assertEquals(GENERAL_DEPT, (Integer) response.jsonPath().get("generalDebt")),
                () -> assertEquals(CURR_MAIN_CREDIT_PAYMENT, (Integer) response.jsonPath().get("currMainCreditPayment")),
                () -> assertEquals(PERCENT_CREDIT_PAYMENT, (Integer) response.jsonPath().get("percentCreditPayment")),
                () -> assertEquals(PAYMENT_DATE, (String) response.jsonPath().get("paymentDate")),
                () -> assertEquals(OUTSTANDING_PRINCIPAL, (Integer) response.jsonPath().get("outstandingPrincipal")),
                () -> assertEquals(NUMBER, (String) response.jsonPath().get("number")),
                () -> assertEquals(CREDIT_ACCOUNT_NUMBER, (String) response.jsonPath().get("creditAccountNumber"))

        );
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя при неправильной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5 по US 3.5 на просмотр подробной информации" +
            " о действующих кредитах пользователя при неправильной конфигурации запроса")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-922")
    @Test
    public void checkUserInformationIncludeRealCreditProductsWithIncorrectRequest() {
        Response response = CreditService.checkViewInfoCurrentCreditsUsers("/");
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5 по US 3.5 на просмотр подробной информации о действующих" +
            " кредитах пользователя, если в результирующей таблице нет записей по указанным критериям")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-925")
    @Test
    public void checkUserInformationIncludeRealCreditProductsWithoutValues() {
        Response response = CreditService.checkViewInfoCurrentCreditsUsers("4");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5 по US 3.5 на просмотр подробной информации" +
            "о действующих кредитах пользователя при неуспешной валидации токена")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-924")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditInvalidToken() {
        Response response = CreditService.checkViewInfoCurrentCreditsUsersInvalidToken("1");
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }
}
