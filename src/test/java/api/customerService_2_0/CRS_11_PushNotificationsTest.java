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
import pojo.customerService_2_0.UserAuthorizationByPhone;

import static constant.CustomerService_2_0_Constants.CUSTOMER_MOBILE_PHONE_TYPE;
import static constant.CustomerService_2_0_Constants.CUSTOMER_USER_PASSWORD_CRS_11;
import static constant.Message.ERROR_MESSAGE_NOT_EXPECTED;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
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
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive"), @Tag("CRS")})
    @TmsLink("LIB-2261")
    @Test

    public void successfulReceivePUSHNotificationsTrue() {
        String mobilePhone = "79527765108";
        String customerId = "d44cfcb5-5263-4cf2-a7a3-03ea73359108";
        Response responseByMobile = customerService_2_0.userAuthorizationByMobilePhone(
                new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD_CRS_11, CUSTOMER_MOBILE_PHONE_TYPE));
        String accessToken = responseByMobile.jsonPath().get("accessToken");
        Response response = customerService_2_0.checkPushNotification(customerId, true, accessToken);
        System.out.println(customerId);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(true, CustomerService_2_0_DataBaseRequest.checkNotificationStatusByCustomerId(customerId),
                        "Статус push_notification данного %s не поменялся" + customerId)
        );
    }

    @DisplayName("Основной сценарий. Проверка возможности отказа от получения PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка возможности отказа от получения пользователем PUSH уведомлений.")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive"), @Tag("CRS")})
    @TmsLink("LIB-2261")
    @Test

    public void successfulReceivePUSHNotificationsFalse() {
        String mobilePhone = "79527765108";
        String customerId = "d44cfcb5-5263-4cf2-a7a3-03ea73359108";
        Response responseByMobile = customerService_2_0.userAuthorizationByMobilePhone(
                new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD_CRS_11, CUSTOMER_MOBILE_PHONE_TYPE));
        String accessToken = responseByMobile.jsonPath().get("accessToken");

        Response response = customerService_2_0.checkPushNotification(customerId, false, accessToken);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertFalse(CustomerService_2_0_DataBaseRequest.checkNotificationStatusByCustomerId(customerId),
                        "Статус push_notification данного %s не поменялся" + customerId)
        );
    }

    @DisplayName("Проверка работы системы если тип параметра notificationStatus не является boolean при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы, если тип параметра notificationStatus в теле запроса не является boolean")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("LIB-2263")
    @ParameterizedTest(name = "notificationStatus: {0}")
    @ValueSource(strings = {"true", "12321", "gfaghs", ""})

    public void unsuccessfulReceivePUSHNotificationsNotBoolean(String notificationStatus) {
        String mobilePhone = "79527765108";
        String customerId = "d44cfcb5-5263-4cf2-a7a3-03ea73359108";
        Response responseByMobile = customerService_2_0.userAuthorizationByMobilePhone(
                new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD_CRS_11, CUSTOMER_MOBILE_PHONE_TYPE));
        String accessToken = responseByMobile.jsonPath().get("accessToken");

        Response response = customerService_2_0.checkPushNotificationWithNotBoolean(customerId, notificationStatus, accessToken);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Проверка работы системы при использовании метода помимо PATCH/OPTIONS при изменении PUSH-уведомлений.")
    @Description("Тест проверяет, возможно ли отправить запрос другим методом помимо PATCH/OPTIONS, которые разрешены на клиенте.")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("LIB-2264")
    @ParameterizedTest(name = "method: {0}")
    @ValueSource(strings = {"GET", "PUT", "DELETE", "POST"})

    public void unsuccessfulReceivePUSHNotificationsWithInvalidMethod(String method) {
        String mobilePhone = "79527765108";
        String customerId = "d44cfcb5-5263-4cf2-a7a3-03ea73359108";
        Response responseByMobile = customerService_2_0.userAuthorizationByMobilePhone(
                new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD_CRS_11, CUSTOMER_MOBILE_PHONE_TYPE));
        String accessToken = responseByMobile.jsonPath().get("accessToken");
        Response response = customerService_2_0.checkPushNotificationWithHttpMethod(method, customerId, accessToken);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, но он был деактивирован и не может быть использован.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Использование невалидного URL при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы если используется невалидный URL.")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("LIB-2265")
    @Test

    public void unsuccessfulReceivePUSHNotificationsWithInvalidURL() {
        String customerId = CustomerService_2_0_DataBaseRequest.receivingCustomerIdWithNotificationStatusFalse();
        Response response = customerService_2_0.checkPushNotificationWithInvalidURL(customerId, false);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Страница не найдена",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Проверка работы системы если пользователь передал не корректные данные при изменении PUSH-уведомлений.")
    @Description("В данном тест-кейсе проводится проверка работы системы, если тип параметра notificationStatus в теле запроса не валидный.")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("LIB-2267")
    @Test

    public void unsuccessfulReceivePUSHNotificationsWrongData() {
        String mobilePhone = "79527765108";
        String customerId = "d44cfcb5-5263-4cf2-a7a3-03ea73359108";
        Response responseByMobile = customerService_2_0.userAuthorizationByMobilePhone(
                new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD_CRS_11, CUSTOMER_MOBILE_PHONE_TYPE));
        String accessToken = responseByMobile.jsonPath().get("accessToken");
        NotificationStatus notificationStatus = new NotificationStatus(null);
        Response response = customerService_2_0.checkPushNotificationWithNull(notificationStatus, customerId, accessToken);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }
}
