package api.accountService;

import api.BaseTest;
import dataBase.requests.AccountServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static constant.AccountServiceConstants.*;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("AS-4 Сделать счет основным")
public class AS_4_SetMainStatusOnAccountTest extends BaseTest {

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @DisplayName("Сделать активный счет основным")
    @Description("Данный тест-кейс направлен на проверку AS-4 по US-4.4.4 на установку статуса Основной для активного счета")
    @Tag("API")
    @TmsLink("LIB2-1489")
    @Test
    public void setMainStatusOnActiveAccount() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.setMainAccountStatus(accountId, IS_MAIN);
        String jsonSchemaPath = "schemas/accountService/successfulChangeAccountStatus.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Сделать закрытый счет основным")
    @Description("Данный тест-кейс направлен на проверку AS-4 по US-4.4.4 на установку статуса Основной для закрытого счета")
    @Tag("API")
    @TmsLink("LIB2-662")
    @Test
    public void setMainStatusOnClosedAccount() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_CLOSED);
        Response response = accountService.setMainAccountStatus(accountId, IS_MAIN);
        String jsonSchemaPath = "schemas/accountService/errorNotFound.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Сделать заблокированный счет основным")
    @Description("Данный тест-кейс направлен на проверку AS-4 по US-4.4.4 на установку статуса Основной для заблокированного счета")
    @Tag("API")
    @TmsLink("LIB2-661")
    @Test
    public void setMainStatusOnBlockedAccount() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_BLOCKED);
        Response response = accountService.setMainAccountStatus(accountId, IS_MAIN);
        String jsonSchemaPath = "schemas/accountService/errorNotFound.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Сделать счет основным с невалидными параметрами запроса")
    @Description("Данный тест-кейс направлен на проверку AS-4 по US-4.4.4 на установку статуса Основной с невалидным телом запроса")
    @Tag("API")
    @TmsLink("LIB2-1490")
    @ParameterizedTest
    @CsvSource({
            "111111, false",
            " , "
    })
    public void setMainAccountStatusWithInvalidData(String accountId, Boolean isMain) {
        Response response = accountService.setMainAccountStatus(accountId, isMain);
        String jsonSchemaPath = "schemas/accountService/errorNotFound.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}

