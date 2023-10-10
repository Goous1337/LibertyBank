package api.clientService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_12_CheckChangingStatusSmsNotificationSettings extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, true",
            "033140e9-ea0a-40c3-a738-060283531147, false",
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-924")
    @Description("Изменение настроек SMS-уведомлений авторизованному пользователю")
    public void checkChangingSmsNotificationSettingsAuthorizedUser(String customerId, Boolean notificationStatus) {
        Response response = verificationService.checkChangingSmsNotificationSettingsAuthorizedUser(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, true"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-964")
    @Description("Использование невалидного URL")
    public void checkChangingSmsNotificationSettingsInvalidUrl(String customerId, Boolean notificationStatus) {
        Response response = verificationService.checkChangingSmsNotificationSettingsAuthorizedUserInvalidUrl(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "PUT,  033140e9-ea0a-40c3-a738-060283531147, true",
            "GET,  033140e9-ea0a-40c3-a738-060283531147, true",
            "HEAD,  033140e9-ea0a-40c3-a738-060283531147, true"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-963")
    @Description("Использование метода помимо PATCH")
    public void checkChangingSmsNotificationSettingsInvalidMethod(String invalidHttpMethod, String customerId, Boolean notificationStatus) {
        Response response = verificationService.checkChangingSmsNotificationSettingsAuthorizedUserInvalidMethod(invalidHttpMethod, customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, 245",
            "033140e9-ea0a-40c3-a738-060283531147, justText",
            "033140e9-ea0a-40c3-a738-060283531147, превысокомногорассматривающий",
            "033140e9-ea0a-40c3-a738-060283531147, %"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-959")
    @Description("Валидация значений, передаваемых в теле запроса")
    public void checkChangingSmsNotificationSettingsInvalidData(String customerId, String notificationStatus) {
        Response response = verificationService.checkChangingSmsNotificationSettingsInvalidData(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, 1",
            "033140e9-ea0a-40c3-a738-060283531147, 0"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-958")
    @Description("Отправка числовых значений в теле запроса")
    public void checkChangingSmsNotificationSettingsInvalidData1(String customerId, Integer notificationStatus) {
        Response response = verificationService.checkChangingSmsNotificationSettingsInvalidData1(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-965")
    @Description("Проверка необходимости параметров в теле запроса")
    public void checkChangingSmsNotificationSettingsAuthorizedUser2(String customerId) {
        Response response = verificationService.checkChangingSmsNotificationSettingsAuthorizedUser1(customerId);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }
}