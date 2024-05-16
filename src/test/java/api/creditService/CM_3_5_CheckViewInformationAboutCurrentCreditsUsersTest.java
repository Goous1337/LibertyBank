package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import service.CreditService;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.2 Просмотр информации о кредитных продуктах банка")
@DisplayName("US-3.2 Просмотр информации о кредитных продуктах банка")
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
        String jsonSchemaPath = "schemas/creditService/CM_3_5/checkUserInformationIncludeRealCreditProducts.json";
        Response response = CreditService.checkViewInfoCurrentCreditsUsers("1");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя при неправильной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5 по US 3.5 на просмотр подробной информации" +
            " о действующих кредитах пользователя при неправильной конфигурации запроса")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-922")
    @Test
    public void checkUserInformationIncludeRealCreditProductsWithIncorrectRequest() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = CreditService.checkViewInfoCurrentCreditsUsers("/");
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5 по US 3.5 на просмотр подробной информации о действующих" +
            " кредитах пользователя, если в результирующей таблице нет записей по указанным критериям")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-925")
    @Test
    public void checkUserInformationIncludeRealCreditProductsWithoutValues() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = CreditService.checkViewInfoCurrentCreditsUsers("4");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на проверку СМ 3.5 по US 3.5 на просмотр подробной информации" +
            "о действующих кредитах пользователя при неуспешной валидации токена")
    @Tags({@Tag("Negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-924")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditInvalidToken() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = CreditService.checkViewInfoCurrentCreditsUsersInvalidToken("1");
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
