package api.customerService;

import api.BaseTest;
import constant.LibertyServiceName;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static dataBase.DataBaseConnector.getDBConnection;
import static dataProviders.DataUtils.getCustomerIdWithCustomerStatus;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE;

public class CRS_11_PushNotificationsTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }
    @Disabled("пока не работает'")
    @DisplayName("Основной сценарий. Проверка возможности изменения получения PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем PUSH уведомлений.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1190")
    @Test
    public void successfulPushNotificationTest() {
        String customerId = getCustomerIdWithCustomerStatus(true);
        Response response = customerService.checkPushNotification(customerId,"true");
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> {
                    String sql = "SELECT push_notification FROM public.customer WHERE customer_id = ?";
                    assertFalse(getDBConnection(LibertyServiceName.CUSTOMER_SERVICE)
                            .queryForList(sql, Boolean.class,customerId).get(0));
                }
        );
    }

    @DisplayName("Проверка работы системы если тип параметра notificationStatus не является boolean при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы если тип параметра notificationStatus в теле запроса не является boolean")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1191")
    @ParameterizedTest(name = "notificationStatus: {0}")
    @CsvSource({
            "api",
            "111",
            "''",
            "null",
    })
    public void unsuccessfulPushNotificationWrongTypeTest(String notificationStatus) {
        String customerId = getCustomerIdWithCustomerStatus(true);
        Response response = customerService.checkPushNotification(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы системы при использовании метода помимо PATCH/OPTIONS при изменении PUSH-уведомлений.")
    @Description("Тест проверяет, возможно ли отправить запрос другим методом помимо PATCH/OPTIONS, которые разрешены на клиенте.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1193")
    @ParameterizedTest(name = "Http Method: {0}")
    @CsvSource({
            "GET",
            "POST",
            "PUT",
            "DELETE",
    })
    public void unsuccessfulPushNotificationWrongHttpMethod(String httpMethod) {
        String customerId = getCustomerIdWithCustomerStatus(true);
        Response response = customerService.checkPushNotificationWIthHttpMethod(customerId,httpMethod);
        assertAll(
                () -> assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Использование невалидного URL при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы если используется невалидный URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1194")
    @Test
    public void unsuccessfulPushNotificationInvalidUrl() {
        String customerId = "e4934e7d-d27f-4af5-8d76-e3a7c90ebb89";
        Response response = customerService.checkPushNotificationInvalidUrl(customerId);
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Not Found",
                        response.jsonPath().get("error"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }



}
