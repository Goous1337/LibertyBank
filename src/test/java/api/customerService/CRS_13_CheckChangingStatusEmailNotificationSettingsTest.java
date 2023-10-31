package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("CRS_13 Изменение статуса настройки получения email-рассылки")
public class CRS_13_CheckChangingStatusEmailNotificationSettingsTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Основной сценарий. Проверка возможности изменения статуса настройки получения email-рассылки.")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем e-mail рассылки.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1009")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, true",
            "033140e9-ea0a-40c3-a738-060283531147, false",
    })

    public void checkChangingEmailNotificationSettings(String customerId, Boolean notificationStatus) {
        Response response = customerService.checkChangingEmailNotificationSettingsAuthorizedUser(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Использование невалидного URL при изменения статуса настройки получения email-рассылки.")
    @Description("В данном тест-кейсе проводится проверка работы системы если используется невалидный URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1120")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, true"
    })

    public void checkChangingEmailNotificationSettingsInvalidUrl(String customerId, Boolean notificationStatus) {
        Response response = customerService.checkChangingEmailNotificationSettingsInvalidUrl(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Использование метода помимо PATCH/OPTIONS при изменения статуса настройки получения email-рассылки.")
    @Description("Тест проверяет, возможно ли отправить запрос другим методом помимо PATCH/OPTIONS")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1119")
    @ParameterizedTest(name = "invalidHttpMethod: {0}, customerId: {1}, notificationStatus: {2}")
    @CsvSource({
            "HEAD,  033140e9-ea0a-40c3-a738-060283531147, true",
            "GET,  033140e9-ea0a-40c3-a738-060283531147, true",
            "PUT,  033140e9-ea0a-40c3-a738-060283531147, true"
    })

    public void checkChangingEmailNotificationSettingsInvalidMethod(String invalidHttpMethod, String customerId,
                                                                    Boolean notificationStatus) {
        Response response = customerService.checkChangingEmailNotificationSettingsInvalidMethod(invalidHttpMethod, customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы системы если тип параметра notificationStatus не является boolean (String) при изменении " +
            "статуса настройки получения email-рассылки.")
    @Description("В данном тест-кейсе проводится проверка работы системы если тип параметра notificationStatus в" +
            " теле запроса не является boolean")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1022")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, api",
            "033140e9-ea0a-40c3-a738-060283531147, 111",
            "033140e9-ea0a-40c3-a738-060283531147, %"
    })

    public void checkChangingEmailNotificationSettingsInvalidData(String customerId, String notificationStatus) {
        Response response = customerService.checkChangingEmailNotificationSettingsInvalidData(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы системы если notificationStatus не является boolean, пустые кавычки или null" +
            "статуса настройки получения email-рассылки.")
    @Description("В данном тест-кейсе проводится проверка работы системы если параметр notificationStatus в" +
            " теле запроса пустые кавычки или null")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1022")
    @Issue("https://jira.astondevs.ru/browse/LIB-1133")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147,  ",
            "033140e9-ea0a-40c3-a738-060283531147, ''"

    })

    public void checkChangingEmailNotificationSettingsInvalidData1(String customerId, String notificationStatus) {
        Response response = customerService.checkChangingEmailNotificationSettingsInvalidData(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_NOT_ACCEPTABLE, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы системы если тип параметра notificationStatus не является boolean (Integer) при изменении \" +\n" +
            "            \"статуса настройки получения email-рассылки.")
    @Description("В данном тест-кейсе проводится проверка работы системы если тип параметра notificationStatus в\" +\n" +
            "            \" теле запроса не является boolean. Отправка числовых значений в теле запроса")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1022")
    @Issue("https://jira.astondevs.ru/browse/LIB-1132")
    @ParameterizedTest(name = "customerId: {0}, notificationStatus: {1}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147, 111",
            "033140e9-ea0a-40c3-a738-060283531147, 1"
    })

    public void checkChangingEmailNotificationSettingsInvalidData(String customerId, Integer notificationStatus) {
        Response response = customerService.checkChangingEmailNotificationSettingsInvalidData(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.statusCode(), "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка необходимости параметров в теле запроса при изменения статуса настройки получения email-рассылки.")
    @Description("[Описание]:\n" +
            "В данном тест-кейсе проводится проверка работы системы если не передать тип параметра notificationStatus в теле запроса")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1329")
    @Issue("https://jira.astondevs.ru/browse/LIB-1335")
    @ParameterizedTest(name = "customerId: {0}")
    @CsvSource({
            "033140e9-ea0a-40c3-a738-060283531147"
    })

    public void checkChangingEmailNotificationSettings(String customerId) {
        Response response = customerService.checkChangingEmailNotificationSettings(customerId);
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }
}