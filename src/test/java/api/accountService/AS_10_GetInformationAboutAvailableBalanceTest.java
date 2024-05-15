package api.accountService;

import api.BaseTest;
import dataBase.requests.AccountServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static constant.AccountServiceConstants.INVALID_ACCOUNT_ID;
import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@Tags({@Tag("API"), @Tag("MVP")})
@DisplayName("AS-10 Получение справки о доступном остатке на счете")
public class AS_10_GetInformationAboutAvailableBalanceTest extends BaseTest {

    public static final String JSON_SCHEMA = "schemas/accountService/successfulGetInformationAboutAvailableBalance.json";
    public static final String JSON_SCHEMA_FOR_ERROR = "schemas/accountService/errorNotFound.json";

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @Test
    @TmsLink("LIB2-3117")
    @DisplayName("Получение справки о доступном остатке на счете")
    @Description("Тест направлен на проверку возможности получения справки о доступном остатке на счете пользователя")
    public void getInformationAboutAvailableBalance() {
        String accountId = AccountServiceDataBaseRequest.getAccountIdWithMoney(STATUS_ACTIVE);
        Response response = accountService.getInformationAboutAvailableBalance(accountId);
        Assertions.assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }

    @Test
    @TmsLink("LIB2-3118")
    @DisplayName("Получение справки о доступном остатке на счете для несуществующего счета")
    @Description("Тест направлен на проверку невозможности получения справки о доступном остатке на счете пользователя, если счет не существует")
    public void getInformationAboutAvailableBalanceWithInvalidAccountId() {
        Response response = accountService.getInformationAboutAvailableBalance(INVALID_ACCOUNT_ID);
        Assertions.assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_FOR_ERROR))
        );
    }
}
