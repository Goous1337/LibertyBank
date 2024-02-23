package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.customerService_2_0.NotificationStatus;
import pojo.customerService_2_0.UserAuthorizationByPhone;

import java.util.stream.Stream;

import static constant.CustomerService_2_0_Constants.*;
import static constant.Message.ERROR_MESSAGE_NOT_EXPECTED;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-12 Изменение статуса настройки получения SMS-уведомлений")
public class CRS_12_ChangeStatusSMSNotification extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Изменение статуса настройки получения SMS-уведомлений")
    @Description("Данный тест-кейс позволяет вносить изменения в учетной записи Пользователя в части настроек" +
            " получения SMS-уведомлений notificationStatus. В настройках пользователь может активировать или " +
            "деактивировать чек-бокс на получение SMS-уведомлений. ")
    @Tags({@Tag("API"), @Tag("N-F"), @Tag("Positive"), @Tag("Smoke")})
    @TmsLink("LIB-2758")
    @Test
    public void successChangeStatusSMSNotification() {
        Response authResponse = customerService_2_0.userAuthorizationByMobilePhone(new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        String token = authResponse.jsonPath().get("accessToken");
        boolean status = CustomerService_2_0_DataBaseRequest.getSMSStatusFromMobile(CUSTOMER_USER_PHONE);

        Response response = customerService_2_0.changeStatusSMSNotification(token, new NotificationStatus(!status));
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(
                        !status,
                        CustomerService_2_0_DataBaseRequest.getSMSStatusFromMobile(CUSTOMER_USER_PHONE),
                        "NotificationStatus не изменился")
        );
    }

    @DisplayName("Проверка работы системы при отсутствии данных в BODY")
    @Description("Данном тест-кейсе проводится проверка работы сервиса изменения статуса настройки получения " +
            "SMS-уведомлений, если передано пустое поле")
    @Tags({@Tag("API"), @Tag("N-F"), @Tag("Negative")})
    @TmsLink("LIB-2766")
    @Test
    public void unsuccessfulChangeStatusSMSNotificationWithEmptyBody() {
        Response authResponse = customerService_2_0.userAuthorizationByMobilePhone(new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        String token = authResponse.jsonPath().get("accessToken");
        Response response = customerService_2_0.changeStatusSMSNotification(token, new NotificationStatus(null));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(
                        "Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Проверка работы системы при использовании некорректного URL")
    @Description("Данном тест-кейсе проводится проверка работы сервиса изменения статуса настройки получения " +
            "SMS-уведомлений при указании некорректного URL ")
    @Tags({@Tag("API"), @Tag("N-F"), @Tag("Negative")})
    @TmsLink("LIB-2767")
    @Test
    public void unsuccessfulChangeStatusSMSNotificationWithIncorrectURL() {
        Response authResponse = customerService_2_0.userAuthorizationByMobilePhone(new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        String token = authResponse.jsonPath().get("accessToken");
        Response response = customerService_2_0.changeStatusSMSNotificationWithIncorrectURL(
                token, new NotificationStatus(false));
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Страница не найдена",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Проверка работы системы при использовании метода запроса не PATCH/OPTIONS")
    @Description("Данном тест-кейсе проводится проверка возможности отправки другим методом помимо PATCH/OPTIONS, " +
            "которые являются разрешенными")
    @Tags({@Tag("API"), @Tag("N-F"), @Tag("Negative")})
    @TmsLink("LIB-2768")
    @ParameterizedTest
    @ValueSource(strings = {"GET", "PUT", "DELETE", "POST"})
    public void unsuccessfulChangeStatusSMSNotificationWithInvalidMethods(String method) {
        Response authResponse = customerService_2_0.userAuthorizationByMobilePhone(new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        String token = authResponse.jsonPath().get("accessToken");
        Response response = customerService_2_0.changeStatusSMSNotificationWithHttpMethod(
                token, method, new NotificationStatus(false));
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, но он был деактивирован и не может быть использован.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    static Stream<Object> provideArgumentsForTest() {
        return Stream.of(
                "",
                123,
                "hello"
        );
    }

    @DisplayName("Проверка работы системы при передаче неправильного типа параметра notificationStatus")
    @Description("Данном тест-кейсе проводится проверка работы сервиса при передаче неправильного типа параметра " +
            "notificationStatus")
    @Tags({@Tag("API"), @Tag("N-F"), @Tag("Negative")})
    @TmsLink("LIB-2769")
    @ParameterizedTest
    @MethodSource("provideArgumentsForTest")
    public void unsuccessfulChangeStatusSMSNotificationWithInvalidParameters(Object value) {
        Response authResponse = customerService_2_0.userAuthorizationByMobilePhone(new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        String token = authResponse.jsonPath().get("accessToken");
        Response response = customerService_2_0.changeStatusSMSNotificationWithValue(
                token, value);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }
}
