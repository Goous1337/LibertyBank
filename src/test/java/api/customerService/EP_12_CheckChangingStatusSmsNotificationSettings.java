package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("EP-12 Изменение статуса настройки получения SMS-уведомлений")
public class EP_12_CheckChangingStatusSmsNotificationSettings extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Изменение настроек SMS-уведомлений авторизованному пользователю")
    @Description("Проверка успешного сохранения кода верификации в БД пользователя, авторизованного в приложении.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-924")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, true",
            "033140e9-ea0a-40c3-a738-060283531147, false",
    })

    public void checkChangingSmsNotificationSettingsAuthorizedUser(String customerId, Boolean notificationStatus) {
        Response response = customerService.checkChangingSmsNotificationSettingsAuthorizedUser(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Использование невалидного URL")
    @Description("Тест направлен на проверку возможности отправить запрос при невалидном URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-964")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, true"
    })

    public void checkChangingSmsNotificationSettingsInvalidUrl(String customerId, Boolean notificationStatus) {
        Response response = customerService.checkChangingSmsNotificationSettingsAuthorizedUserInvalidUrl(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Использование невалидного метода")
    @Description("Тест проверяет, возможно ли отправить запрос другим методом помимо PATCH")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-963")
    @ParameterizedTest(name = "invalidHttpMethod: {0}, customerId: {1}, notificationStatus: {2}")
    @CsvSource({
            "PUT,  033140e9-ea0a-40c3-a738-060283531147, true",
            "GET,  033140e9-ea0a-40c3-a738-060283531147, true",
            "HEAD,  033140e9-ea0a-40c3-a738-060283531147, true"
    })

    public void checkChangingSmsNotificationSettingsInvalidMethod(String invalidHttpMethod, String customerId, Boolean notificationStatus) {
        Response response = customerService.checkChangingSmsNotificationSettingsAuthorizedUserInvalidMethod(invalidHttpMethod, customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Валидация значений, передаваемых в теле запроса")
    @Description("Тест направлен на проверку валидации отправляемых в теле запроса значений")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-959")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, 245",
            "033140e9-ea0a-40c3-a738-060283531147, justText",
            "033140e9-ea0a-40c3-a738-060283531147, превысокомногорассматривающий",
            "033140e9-ea0a-40c3-a738-060283531147, %"
    })

    public void checkChangingSmsNotificationSettingsInvalidData(String customerId, String notificationStatus) {
        Response response = customerService.checkChangingSmsNotificationSettingsInvalidData(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Отправка числовых значений в теле запроса")
    @Description("Тест проверят, корректно ли обрабатываются запросы, если вместо логических буквенных значений" +
            " (true, false) передавать численные (0, 1).")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-958")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, 1",
            "033140e9-ea0a-40c3-a738-060283531147, 0"
    })

    public void checkChangingSmsNotificationSettingsInvalidData1(String customerId, Integer notificationStatus) {
        Response response = customerService.checkChangingSmsNotificationSettingsInvalidData1(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка необходимости параметров в теле запроса")
    @Description("Проверка необходимости параметра notificationStatus в теле запроса")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-965")
    @Issues({@Issue("https://jira.astondevs.ru/browse/LIB-1189"), @Issue("https://jira.astondevs.ru/browse/LIB-1188")})
    @ParameterizedTest(name = "customerId: {0}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147"
    })

    public void checkChangingSmsNotificationSettingsAuthorizedUser2(String customerId) {
        Response response = customerService.checkChangingSmsNotificationSettingsAuthorizedUser1(customerId);
        assertAll(
                () -> assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

}