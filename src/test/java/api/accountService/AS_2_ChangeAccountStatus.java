package api.accountService;

import api.BaseTest;
import dataBase.requests.AccountServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("AS-2 Изменить статус счета")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AS_2_ChangeAccountStatus extends BaseTest {

    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_CLOSED = "CLOSED";
    public static final String STATUS_BLOCKED = "BLOCKED";
    public static final String JSON_SCHEMA = "schemas/accountService/successfulChangeAccountStatus.json";

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @Test
    @Order(1)
    @Tag("API")
    @TmsLink("LIB2-2673")
    @DisplayName("Изменение статуса счета на 'BLOCKED'")
    @Description("Тест направлен на проверку возможности изменения статуса счета на 'BLOCKED'")
    public void changeAccountStatusToBlocked() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.changeAccountStatus(accountId, STATUS_BLOCKED);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertEquals(STATUS_BLOCKED, AccountServiceDataBaseRequest.getAccountStatus(accountId), "Статус счета не соответствует ожидаемому")
        );
    }

    @Test
    @Order(2)
    @Tag("API")
    @TmsLink("LIB2-2674")
    @DisplayName("Изменение статуса счета на 'ACTIVE'")
    @Description("Тест направлен на проверку возможности изменения статуса счета на 'ACTIVE'")
    public void changeAccountStatusToActive() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_BLOCKED);
        Response response = accountService.changeAccountStatus(accountId, STATUS_ACTIVE);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertEquals(STATUS_ACTIVE, AccountServiceDataBaseRequest.getAccountStatus(accountId), "Статус счета не соответствует ожидаемому")
        );
    }

    @Test
    @Order(3)
    @Tag("API")
    @TmsLink("LIB2-2672")
    @DisplayName("Изменение статуса счета на 'CLOSED'")
    @Description("Тест направлен на проверку возможности изменения статуса счета на 'CLOSED'")
    public void changeAccountStatusToClosed() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.changeAccountStatus(accountId, STATUS_CLOSED);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertEquals(STATUS_CLOSED, AccountServiceDataBaseRequest.getAccountStatus(accountId), "Статус счета не соответствует ожидаемому")
        );
    }
}
