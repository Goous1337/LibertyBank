package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
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
import pojo.customerService_2_0.CustomerService_2_0_Mobile;
import pojo.customerService_2_0.GetSessionToken;
import pojo.customerService_2_0.RecoveryPassword;

import static constant.CustomerService_2_0_Constants.NEW_CUSTOMER_PASSWORD_RECOVERY;
import static constant.CustomerService_2_0_Constants.NEW_CUSTOMER_PASSWORD_RECOVERY_UPDATE;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-16 Восстановление пароля.")
public class CRS_16_PasswordRecoveryTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Восстановление пароля пользователя на странице авторизации.")
    @Description("Данный тест-кейс проверяет возможность восстановления пароля Пользователя на странице авторизации в случае если пользователь забыл пароль.")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2082")
    @Test

    public void checkPasswordRecoveryOnAuthorizationPage() {
        String mobile = CustomerService_2_0_DataBaseRequest.getMobilePhoneByCustomerId("d54eb158-7499-4bda-bafb-d4bd965a1985");
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobile);
        String password = CustomerService_2_0_DataBaseRequest.getPasswordByCustomerId(customerId);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobile);
        Response response = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCodeRequest = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getSessionTokenToken = customerService_2_0.getSessionToken(new GetSessionToken(mobile, verificationCodeRequest));
        String sessionToken = getSessionTokenToken.jsonPath().get("sessionToken");
        RecoveryPassword getNewPassword = new RecoveryPassword(NEW_CUSTOMER_PASSWORD_RECOVERY);
        Response response1 = customerService_2_0.checkRecoveryPasswordOnAuthorizationPage(getNewPassword, sessionToken);
        String newPassword = CustomerService_2_0_DataBaseRequest.getPasswordByCustomerId(customerId);
        assertAll(
                () -> assertEquals(SC_OK, response1.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotEquals(password, newPassword)
        );
        RecoveryPassword updateNewPassword = new RecoveryPassword(NEW_CUSTOMER_PASSWORD_RECOVERY_UPDATE);
        customerService_2_0.checkRecoveryPasswordOnAuthorizationPage(updateNewPassword, sessionToken);
    }

    @DisplayName("Восстановление пароля пользователя на странице авторизации используя метод помимо PATCH.")
    @Description("Данный тест-кейс проверяет возможность восстановления пароля Пользователя на странице авторизации в случае если пользователь забыл пароль. Используя при этом невалидный метод")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2091")
    @ParameterizedTest
    @CsvSource({"POST", "PUT", "GET", "DELETE"})

    public void checkPasswordRecoveryOnAuthorizationPageUseInvalidMethod(String httpMethod) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String mobile = CustomerService_2_0_DataBaseRequest.getMobilePhoneByCustomerId("d54eb158-7499-4bda-bafb-d4bd965a1985");
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobile);
        String password = CustomerService_2_0_DataBaseRequest.getPasswordByCustomerId(customerId);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobile);
        Response response = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCodeRequest = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getSessionTokenToken = customerService_2_0.getSessionToken(new GetSessionToken(mobile, verificationCodeRequest));
        String sessionToken = getSessionTokenToken.jsonPath().get("sessionToken");
        RecoveryPassword getNewPassword = new RecoveryPassword(NEW_CUSTOMER_PASSWORD_RECOVERY);
        Response response1 = customerService_2_0.checkRecoveryPasswordOnAuthorizationPageWithInvalidMethod(getNewPassword, sessionToken, httpMethod);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response1.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response1.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка обязательности полей при запросе восстановление пароля пользователя на странице авторизации.")
    @Description("Данный тест-кейс проверяет возможность восстановления пароля Пользователя на странице авторизации в случае если пользователь забыл пароль.")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2093")
    @ParameterizedTest
    @CsvSource({" '' ", "null",}) //тест-кейс требует доработки

    public void checkFieldsRequiredWhenPasswordRecoveryOnAuthorizationPage(String newPassword) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String mobile = CustomerService_2_0_DataBaseRequest.getMobilePhoneByCustomerId("d54eb158-7499-4bda-bafb-d4bd965a1985");
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobile);
        String password = CustomerService_2_0_DataBaseRequest.getPasswordByCustomerId(customerId);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobile);
        Response response = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCodeRequest = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getSessionTokenToken = customerService_2_0.getSessionToken(new GetSessionToken(mobile, verificationCodeRequest));
        String sessionToken = getSessionTokenToken.jsonPath().get("sessionToken");
        RecoveryPassword getNewPassword = new RecoveryPassword(newPassword);
        Response response1 = customerService_2_0.checkRecoveryPasswordOnAuthorizationPage(getNewPassword, sessionToken);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response1.getStatusCode(),RESPONSE_CODE_NOT_EXPECTED),
                () -> response1.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}