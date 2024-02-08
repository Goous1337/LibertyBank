package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.customerService_2_0.UserAuthorizationByPhone;

import static constant.CustomerService_2_0_Constants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-10 Отправка настроек уведомлений.")
public class CRS_10_SendingNotificationInPersonalAccountTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("[CRS-10] [STATUS CODE 200] (GET) Отправка настроек уведомлений.")
    @Description("Данный тест-кейс проверяет отправку настроек уведомлений после запроса информации о настройках уведомлений  от пользователя в личном кабинете.")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2145")
    @Test

    public void checkSendingNotificationInPersonalAccount() {
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-10/CRS_10_checkSendingNotificationInPersonalAccountTest.json";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");
        Response response = customerService_2_0.checkGetNotificationInPersonalAccount(token);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("[CRS-10] [STATUS CODE 405] (GET) Отправка настроек уведомлений.")
    @Description("Данный тест-кейс проверяет, что выбор некорректного метода запроса возвращается STATUS CODE 405 METHOD NOT ALLOWED .")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2148")
    @ParameterizedTest(name = "Method: {0}")
    @CsvSource({"POST", "PATCH", "PUT"})

    public void checkSendingNotificationInPersonalAccountInvalidRequest(String method) {
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-10/CRS_10_checkSendingNotificationInPersonalAccountInvalidRequest.json";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");
        Response response = customerService_2_0.checkGetPersonalInfoClientsWithIncorrectRequest(method, token);
        assertAll(
                () -> assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("[CRS-10] [STATUS CODE 404] (GET) Отправка настроек уведомлений.")
    @Description("Данный тест-кейс проверяет, что в случае если не удалось обнаружить данные, возвращается STATUS CODE 404 NOT FOUND.")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2149")
    @Test

    public void checkSendingNotificationInPersonalAccountWithIncorrectURI() {
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");
        Response response1 = customerService_2_0.checkGetPersonalInfoClientsWithIncorrectURI(token);
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response1.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }
}
