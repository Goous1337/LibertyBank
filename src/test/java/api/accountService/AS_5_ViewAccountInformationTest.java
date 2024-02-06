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

import static constant.AccountServiceConstants.INVALID_ACCOUNT_ID;
import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("AS-5 Просмотр информации о счете")
public class AS_5_ViewAccountInformationTest extends BaseTest {

    public static final String JSON_SCHEMA = "schemas/accountService/successfulGetAccountInfoData.json";
    public static final String JSON_SCHEMA_FOR_ERROR = "schemas/accountService/errorResponse.json";

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1012")
    @DisplayName("Просмотр информации о счете, пользователь имеет открытые счета")
    @Description("Тест направлен на проверку возможности просмотра подробной информации о счете")
    public void viewAccountInfoData() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.getAccountInfoData(accountId);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1013")
    @DisplayName("Просмотр информации о счете, пользователь не имеет открытых счетов")
    @Description("Тест направлен на проверку невозможности просмотра подробной информации о счете, если пользователь не имеет открытых счетов")
    public void viewAccountInfoDataIfUserNotHaveAccounts() {
        Response response = accountService.getAccountInfoData(INVALID_ACCOUNT_ID);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_FOR_ERROR))
        );
    }
}
