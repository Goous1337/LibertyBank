package api.accountService;

import api.BaseTest;
import dataBase.requests.AccountServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("AS-9 Изменение пользовательского названия счета")
public class AS_9_ChangeUserAccountNameTest extends BaseTest {

    public static final String VALID_ACCOUNT_NAME = "Иван_IvanovЁ@#$%&!?~1234567890";
    public static final String INVALID_LONG_ACCOUNT_NAME = "Иван_Ivanov любит брокколи на завтрак";
    public static final String INVALID_SHORT_ACCOUNT_NAME = " ";
    public static final String INVALID_CHARACTERS_ACCOUNT_NAME = "小林さんは花子さんに花を上げました。";
    public static final String JSON_SCHEMA = "schemas/accountService/successfulChangeAccountName.json";

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @Test
    @Tag("API")
    @Disabled("Выключен, пока бэк не исправит баг с буквой 'Ё/ё'")
    @TmsLink("LIB2-2679")
    @DisplayName("Изменение названия счета")
    @Description("Тест направлен на проверку возможности переименования счета")
    public void changeAccountNameByValidName() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.changeAccountName(accountId, VALID_ACCOUNT_NAME);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertEquals(VALID_ACCOUNT_NAME, AccountServiceDataBaseRequest.getAccountName(accountId), "Имя счета не соответствует ожидаемому")
        );
    }

    @Test
    @Tag("API")
    @Disabled("Выключен, пока бэк не исправит баг с допустимой длиной символов")
    @TmsLink("LIB2-2680")
    @DisplayName("Изменить название счета, более 30 символов")
    @Description("Тест-кейс направлен на проверку невозможности переименования счета при вводе более 30 символов")
    public void changeAccountNameByInvalidLongName() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.changeAccountName(accountId, INVALID_LONG_ACCOUNT_NAME);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertNotEquals(INVALID_LONG_ACCOUNT_NAME, AccountServiceDataBaseRequest.getAccountName(accountId), "Имя счета было изменено")
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-2681")
    @DisplayName("Изменить название счета, менее 1 символа или только пробел")
    @Description("Тест-кейс направлен на проверку невозможности переименования счета при вводе менее 1 символа или только пробела")
    public void changeAccountNameByInvalidShortName() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.changeAccountName(accountId, INVALID_SHORT_ACCOUNT_NAME);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertNotEquals(INVALID_SHORT_ACCOUNT_NAME, AccountServiceDataBaseRequest.getAccountName(accountId), "Имя счета было изменено")
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-2682")
    @DisplayName("Изменить название счета, недопустимые символы")
    @Description("Тест-кейс направлен на проверку невозможности переименования счета при вводе недопустимых символов")
    public void changeAccountNameByInvalidCharactersName() {
        String accountId = AccountServiceDataBaseRequest.getAccountId(STATUS_ACTIVE);
        Response response = accountService.changeAccountName(accountId, INVALID_CHARACTERS_ACCOUNT_NAME);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)),
                () -> assertNotEquals(INVALID_CHARACTERS_ACCOUNT_NAME, AccountServiceDataBaseRequest.getAccountName(accountId), "Имя счета было изменено")
        );
    }
}
