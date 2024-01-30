package api.customerService_2_0;

import api.BaseTest;

import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.customerService_2_0.NotificationStatus;


import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;

import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-11 Изменение PUSH-уведомлений")
public class CRS_11_PushNotificationsTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Проверка возможности изменения получения PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем PUSH уведомлений.")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2261")
    @Test

    public void successfulReceivePUSHNotificationsTrue() {
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusFalse();
        Response response = customerService_2_0.checkPushNotification(customerId, true);
        System.out.println(customerId);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(true, CustomerService_2_0_DataBaseRequest.checkNotificationStatusByCustomerId(customerId),
                        "Статус push_notification данного %s не поменялся" + customerId)
        );
    }

    @DisplayName("Основной сценарий. Проверка возможности отказа от получения PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка возможности отказа от получения пользователем PUSH уведомлений.")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2261")
    @Test

    public void successfulReceivePUSHNotificationsFalse() {
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusTrue();
        Response response = customerService_2_0.checkPushNotification(customerId, false);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertFalse(CustomerService_2_0_DataBaseRequest.checkNotificationStatusByCustomerId(customerId),
                        "Статус push_notification данного %s не поменялся" + customerId)
        );
    }

    @DisplayName("Проверка работы системы если тип параметра notificationStatus не является boolean при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы, если тип параметра notificationStatus в теле запроса не является boolean")
    @Tags({@Tag("API"), @Tag("negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2263")
    @ParameterizedTest(name = "notificationStatus: {0}")
    @ValueSource(strings = {"true", "12321", "gfaghs", ""})

    public void unsuccessfulReceivePUSHNotificationsNotBoolean(String notificationStatus) {
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusFalse();
        Response response = customerService_2_0.checkPushNotificationWithNotBoolean(customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы системы при использовании метода помимо PATCH/OPTIONS при изменении PUSH-уведомлений.")
    @Description("Тест проверяет, возможно ли отправить запрос другим методом помимо PATCH/OPTIONS, которые разрешены на клиенте.")
    @Tags({@Tag("API"), @Tag("negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2264")
    @ParameterizedTest(name = "method: {0}")
    @ValueSource(strings = {"GET", "PUT", "DELETE", "POST"})

    public void unsuccessfulReceivePUSHNotificationsWithInvalidMethod(String method) {
        NotificationStatus notificationStatus = new NotificationStatus(true);
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusFalse();
        Response response = customerService_2_0.checkPushNotificationWithHttpMethod(method, customerId, notificationStatus);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, но он был деактивирован и не может быть использован.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Использование невалидного URL при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы если используется невалидный URL.")
    @Tags({@Tag("API"), @Tag("negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2265")
    @Test

    public void unsuccessfulReceivePUSHNotificationsWithInvalidURL() {
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusFalse();
        Response response = customerService_2_0.checkPushNotificationWithInvalidURL(customerId, false);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Страница не найдена",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @DisplayName("Проверка работы системы если пользователь передал не корректные данные при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы, если тип параметра notificationStatus в теле запроса не валидный.")
    @Tags({@Tag("API"), @Tag("negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2267")
    @Test

    public void unsuccessfulReceivePUSHNotificationsWrongData() {
        NotificationStatus notificationStatus = new NotificationStatus(null);
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusFalse();
        Response response = customerService_2_0.checkPushNotificationWithNull(notificationStatus, customerId);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }
}
