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

import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("AS-3 Просмотр списка счетов")
public class AS_3_ShowAllAccountsByCustomerIdTest extends BaseTest {

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @DisplayName("Просмотр списка счетов, у клиента есть активные счета")
    @Description("Данный тест-кейс направлен на проверку AS-3 по US 4.3 Просмотр списка счетов")
    @Tag("API")
    @TmsLink("LIB2-879")
    @Test
    public void getAllTypesAccountsListTest() {
        Response response = accountService.getAccountsList();
        String jsonSchemaPath = "schemas/accountService/successfulGetAllTypesAccountsList.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Просмотр списка счетов, у клиента нет счетов")
    @Description("Данный тест-кейс направлен на проверку AS-3 по US 4.3 Просмотр списка счетов")
    @Tag("API")
    @TmsLink("LIB2-1006")
    @Test
    public void getAccountsByCustomerIdWithNoAccountsTest() {
        Response response = accountService.getAccountsListByCustomerIdWithNoAccounts();
        String jsonSchemaPath = "schemas/accountService/errorNoAccountsFoundByCustomerId.json";
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
