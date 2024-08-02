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
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@Tag("API")
@Epic("3 - Кредиты")
@Feature("CM-3.1 Просмотр действующих кредитных продуктов пользователя в личном кабинете")
@DisplayName("CM-3.1 Просмотр действующих кредитных продуктов пользователя в личном кабинете")
public class CM_3_1_CheckCreditTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Получение информации по действующему кредиту пользователя")
    @Description("Данный тест-кейс направлен на проверку CM 3.1 по US 3.1 на получение краткой информации по действующим кредитам авторизованного пользователя (если у пользователя есть кредит)")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-84")
    @Test
    public void successfulGetUserCreditInfo() {
        Response response = creditService.checkCreditInfo();
        String jsonSchemaPath = "schemas/creditService/CM_3_1/successfulGetUserCreditInfo.json";
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> {
                    if (response.getBody().jsonPath().getList("credits").isEmpty()) {
                        // Проверка на пустой список
                        assertFalse(response.getBody().jsonPath().getBoolean("success"), "При пустом списке кредитов не было получено поле success со значением true");
                    } else {
                        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath));
                    }
                }
        );
    }

    @DisplayName("Получение информации по действующему кредиту пользователя")
    @Description("Данный тест-кейс направлен на проверку CM 3.1 по US 3.1 на получение краткой информации по действующим кредитам авторизованного пользователя (если у пользователя нет кредитов)")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-84")
    @Test
    public void notSuccessfulGetUserCreditInfo() {
        Response response = creditService.checkCreditInfo();
        String jsonSchemaPath = "schemas/creditService/CM_3_1/notSuccessfulGetUserCreditInfo.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение информации по действующему кредиту при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 401 при неуспешной валидации токена для получения краткой информации о кредитах авторизованного пользователя по CM 3.1 и US 3.1")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-86")
    @Test
    public void unsuccessfulGetUserCreditInfoNoToken() {
        Response response = creditService.checkCreditInfoNoToken();
        String jsonSchemaPath = "schemas/errorMessage.json";
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение информации при неправильной конфигурации запроса")
    @Description("Данный тест-кейс направлен на получение 400 BAD REQUEST  при неправильной конфигурации запроса")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-2591")
    @Test
    public void unsuccessfulGetUserCreditInfoBadRequest() {
        Response response = creditService.checkListCurrentCreditProductsIncorrectRequest();
        String jsonSchemaPath = "schemas/errorMessage.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение информации при неуспешном соединении с сервером")
    @Description("Данный тест-кейс направлен на получение 500 INTERNAL SERVER ERROR  в случае ошибки соединения с сервером")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-88")
    @Test
    public void unsuccessfulGetUserCreditInfoServerError() {
        Response response = creditService.checkListCurrentCreditProductsInternalServerError();
        String jsonSchemaPath = "schemas/errorMessage.json";
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
