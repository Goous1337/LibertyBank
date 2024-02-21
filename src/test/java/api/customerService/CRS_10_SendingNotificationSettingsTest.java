package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("CRS-10 Отправка настроек уведомлений")
public class CRS_10_SendingNotificationSettingsTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Отправка настроек уведомлений")
    @Description("Тестирование отправки настроек уведомлений после запроса информации о настройках уведомлений  от " +
            "пользователя")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1197")
    @Test

    public void successfulSendingNotificationSettings() {
        String jsonSchemaPath = "schemas/customerService/CRS_10/successfulSendingNotificationSettings.json";
        String customerId = "813f5509-7696-44be-a321-3a094c48a6e7";

        Response response = customerService.checkSendingNotificationSettings(customerId);

        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отправка настроек уведомлений (Пользователь не авторизован)")
    @Description("Тестирование отправки настроек уведомлений после запроса информации о настройках уведомлений  от " +
            "неавторизованного пользователя. " + "Данный тест-кейс пока не может быть выполнен, так как механизм " +
            "авторизации пользователя пока не реализован.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1199")
    @Test

    public void unsuccessfulSendingNotificationSettingsUserNotAuth() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        String customerId = "813f5509-7696-44be-a321-3a094c48a6e7";

        Response response = customerService.checkSendingNotificationSettings(customerId);

        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отправка настроек уведомлений (не удалось обнаружить данные)")
    @Description("Тестирование отправки настроек уведомлений после запроса информации о настройках уведомлений, если " +
            "пользователя/данных нет в базе.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1200")
    @Test

    public void unsuccessfulSendingNotificationSettingsUserNotInBase() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        String customerId = "813f5509-7696-44be-a321-3a094c48a7e6";

        Response response = customerService.checkSendingNotificationSettings(customerId);

        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Отправка настроек уведомлений (некорректный метод запроса)")
    @Description("Тестирование отправки настроек уведомлений после запроса информации о настройках уведомлений  от " +
            "пользователя используя не корректный метод запроса (POST, PATCH, PUT).")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1262")
    @ParameterizedTest(name = "invalidHttpMethod: {0}, customerId: {1}")
    @CsvSource({
            "PUT, 813f5509-7696-44be-a321-3a094c48a6e7",
            "POST, 813f5509-7696-44be-a321-3a094c48a6e7",
            "PATCH, 813f5509-7696-44be-a321-3a094c48a6e7"
    })

    public void unsuccessfulSendingNotificationSettingsInvalidMethod(String invalidHttpMethod, String customerId) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkSendingNotificationSettingsInvalidMethod(invalidHttpMethod, customerId);

        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
