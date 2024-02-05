package api.accountService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("AS-6 Проверка наличия счетов по customerId")
public class AS_6_CheckIfUserHasAccountsTest extends BaseTest {

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @DisplayName("Проверка наличия счетов, невалидный customerId")
    @Description("Данный тест-кейс направлен на проверку AS-6 по US 4.3 Просмотр списка счетов")
    @Tag("API")
    @TmsLink("LIB2-1018")
    @Test
    public void getAccountsByInvalidCustomerIdTest() {
        Response response = accountService.getAccountsListByInvalidCustomerId();
        String jsonSchemaPath = "schemas/accountService/badRequestInvalidCustomerIdMessage.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка наличия счетов, валидный customerId, нет счетов")
    @Description("Данный тест-кейс направлен на проверку AS-6 по US 4.3 Просмотр списка счетов")
    @Tag("API")
    @TmsLink("LIB2-1017")
    @Test
    public void getAccountsByValidCustomerIdWithNoAccountsTest() {
        Response response = accountService.getAccountsListByValidCustomerIdWithNoAccounts();
        String jsonSchemaPath = "schemas/accountService/errorNoAccountsFoundByCustomerId.json";
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка наличия счетов, валидный customerId, есть счета")
    @Description("Данный тест-кейс направлен на проверку AS-6 по US 4.3 Просмотр списка счетов")
    @Tag("API")
    @TmsLink("LIB2-1016")
    @Test
    public void getAccountsByValidCustomerIdWithAccountsTest() {
        Response response = accountService.getAccountsListByValidCustomerIdWithAccounts();
        String jsonSchemaPath = "schemas/accountService/successfulGetAllTypesAccountsList.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
