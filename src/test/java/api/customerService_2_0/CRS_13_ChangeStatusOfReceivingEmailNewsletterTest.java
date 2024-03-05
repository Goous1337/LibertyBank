package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.customerService_2_0.UpdatedEmail;

import java.util.stream.Stream;

import static constant.CustomerService_2_0_Constants.*;
import static constant.Message.ERROR_MESSAGE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-13 Изменение статуса настройки получения email-рассылки.")
public class CRS_13_ChangeStatusOfReceivingEmailNewsletterTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    String mobilePhone = "79198151165";
    String jsonSchemaPath = "schemas/customerService_2_0/CRS-5/errorMessageBody405.json";

    static Stream<Object> notificationStatusesForTest() {
        return Stream.of("false", 123, "");
    }

    @DisplayName("Основной сценарий. Проверка возможности изменения статуса настройки получения email-рассылки.")
    @Description("Проверка возможности изменения статуса настройки получения Email-рассылки.")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("LIB-2759")
    @RepeatedTest(2)
    public void checkChangeStatusOfSettingsReceivingEmailNewsletters() {
        String accessToken = userAuthorization.getAccessToken(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        boolean actualNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        boolean notificationValue = customerService_2_0.changeNotificationStatus(actualNotificationStatus);
        Response response = customerService_2_0.changeStatusOfSettingsReceivingEmailNewsletters
                (accessToken, notificationValue);
        boolean newNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotEquals(actualNotificationStatus, newNotificationStatus)
        );

    }

    @DisplayName("Проверка работы системы если тип параметра notificationStatus не является boolean.")
    @Description("""
            Проверка работы системы при успешной валидации токена, если тип параметра
            notificationStatus в теле запроса не является boolean.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("LIB-2761")
    @ParameterizedTest
    @MethodSource("notificationStatusesForTest")
    public void checkChangeStatusOfSettingsReceivingEmailNewslettersWithInvalidType(Object notificationStatus) {
        String accessToken = userAuthorization.getAccessToken(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        boolean actualNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        Response response = customerService_2_0.changeStatusOfSettingsReceivingEmailNewsletters
                (accessToken, notificationStatus);
        boolean newNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.getStatusCode()),
                () -> assertEquals(actualNotificationStatus, newNotificationStatus),
                () -> assertEquals(ERROR_MASSAGE_415, response.jsonPath().get("message"), ERROR_MESSAGE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка работы системы если метод запроса не PATCH")
    @Description("""
            Тест проверяет, возможно ли отправить запрос другим методом помимо PATCH,
            которые разрешены на клиенте.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("LIB-2762")
    @ParameterizedTest
    @ValueSource(strings = {"GET", "POST", "PUT", "DELETE"})
    public void checkChangeStatusOfSettingsReceivingEmailNewslettersWithInvalidMethod(String method) {
        String accessToken = userAuthorization.getAccessToken(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        boolean actualNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        boolean notificationValue = customerService_2_0.changeNotificationStatus(actualNotificationStatus);
        Response response = customerService_2_0.changeStatusEmailNotificationWithHttpMethod
                (accessToken, notificationValue, method);
        boolean newNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.getStatusCode()),
                () -> assertEquals(actualNotificationStatus, newNotificationStatus),
                () -> assertEquals(ERROR_MASSAGE_405, response.jsonPath().get("message"), ERROR_MESSAGE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка работы системы при неуспешной валидации токена")
    @Description("Проверка работы системы, если указывается невалидный токен.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("LIB-2763")
    @Test
    public void checkChangeStatusOfSettingsReceivingEmailNewslettersWithInvalidToken() {
        boolean actualNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        boolean notificationValue = customerService_2_0.changeNotificationStatus(actualNotificationStatus);
        Response response = customerService_2_0.changeStatusOfSettingsReceivingEmailNewsletters
                (ACCESS_TOKEN_CUSTOMER_SERVICE, notificationValue);
        boolean newNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode()),
                () -> assertEquals(actualNotificationStatus, newNotificationStatus),
                () -> assertEquals(ERROR_MASSAGE_401, response.jsonPath().get("message")),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка работы системы в случае отсутствия email в БД")
    @Description("Проверка работы системы, если у пользователя отсутствует email в базе данных.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("LIB-2765")
    @Test
    public void checkChangeStatusOfSettingsReceivingEmailNewslettersWithInvalidEmail() {
        String accessToken = userAuthorization.getAccessToken(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        boolean actualNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        boolean notificationStatusValue = customerService_2_0.changeNotificationStatus(actualNotificationStatus);
        String actualUserEmail = CustomerService_2_0_DataBaseRequest.getCustomerEmailByPhone(mobilePhone);
        CustomerService_2_0_DataBaseRequest.updateCustomerEmailToNullByPhone(mobilePhone);
        Response response = customerService_2_0.changeStatusOfSettingsReceivingEmailNewsletters
                (accessToken, notificationStatusValue);
        boolean newNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(actualNotificationStatus, newNotificationStatus),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        customerService_2_0.updateEmailForClient(accessToken, new UpdatedEmail(actualUserEmail));
    }

    @DisplayName("Пользователь ввёл несуществующий URL")
    @Description("""
            Проверка работы сервиса изменения статуса настройки получения email-уведомлений при указании некорректного URL.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("LIB-2773")
    @Test
    public void checkChangeStatusOfSettingsReceivingEmailNewslettersWithInvalidUrl() {
        String accessToken = userAuthorization.getAccessToken(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        boolean actualNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        boolean notificationStatusValue = customerService_2_0.changeNotificationStatus(actualNotificationStatus);
        Response response = customerService_2_0.changeStatusEmailNotificationWithInvalidUrl
                (accessToken, notificationStatusValue);
        boolean newNotificationStatus = CustomerService_2_0_DataBaseRequest.getEmailStatusByMobile(mobilePhone);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> assertEquals(actualNotificationStatus, newNotificationStatus),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
