package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.customerService_2_0.CustomerService_2_0_Mobile;
import pojo.customerService_2_0.UserVerificationWithCode;

import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static constant.Message.THERE_NOT_ERROR_MESSAGE;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-5 Установление нового пароля")
public class CRS_5_SettingNewPasswordTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Установка нового пароля")
    @Description("Данный тест-кейс проверяет успешную установку нового пароля пользователю.")
    @Tags({@Tag("API"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2128")
    @Test
    public void checkSettingNewPasswordUser() {
        String mobileNumber = "79527765101";
        String newPassword = "YjMzYjM2Mzg3Y2U1YWExZGRkZmMyOGRkZGNiZDQxNGVkYjQ4NTRiNWZhZWM5ZDAzOGRmZWQzMmIyZTEyYWZjYQ==";
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobileNumber));
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobileNumber);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        String oldHashPassword = CustomerService_2_0_DataBaseRequest.getPasswordByCustomerId(customerId);
        Response getTokenResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobileNumber, verificationCode));
        CustomerService_2_0_DataBaseRequest.updatePasswordInUserProfileTableIsNull(customerId);
        String token = getTokenResponse.body().jsonPath().get("sessionToken");
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabase(token, newPassword);

        assertAll(
                () -> assertEquals(HttpStatus.SC_OK, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotEquals(oldHashPassword, newPassword)
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Валидация метода запроса установления пароля")
    @Description("Данный тест-кейс проверяет возможность отправки запроса методом отличным от PATCH.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2129")
    @ParameterizedTest(name = "method: {0}, mobilePhone: {1}")
    @CsvSource({"POST, 79527765102",
            "PUT, 79527765102",
            "GET, 79527765102",
            "DELETE, 79527765102"})
    public void checkSettingNewPasswordUserWithInvalidMethod(String method, String mobilePhone) {

        String newPassword = "YjMzYjM2Mzg3Y2U1YWExZGRkZmMyOGRkZGNiZDQxNGVkYjQ4NTRiNWZhZWM5ZDAzOGRmZWQzMmIyZTEyYWZjYQ==";
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-5/errorMessageBody405.json";
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobilePhone));
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getTokenResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobilePhone, verificationCode));
        String token = getTokenResponse.body().jsonPath().get("sessionToken");
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabaseWithInvalidMethod(method, token, newPassword);


        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Проверка обязательности значения token в теле запроса при установлении нового пароля")
    @Description("Данный тест-кейс проверяет обязательность token в теле запроса")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2130")
    @ParameterizedTest(name = "token: {0}, newPassword: {1}")
    @CsvSource({"asd , YjMzYjM2Mzg3Y2U1YWExZGRkZmMyOGRkZGNiZDQxNGVkYjQ4NTRiNWZhZWM5ZDAzOGRmZWQzMmIyZTEyYWZjYQ==",
            " asd , null"})

    public void checkSettingNewPasswordUserWithoutToken(String token, String newPassword) {

        Response response = customerService_2_0.changePasswordForUserUpdatedDatabase(token, newPassword);
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotNull(response.getBody(), THERE_NOT_ERROR_MESSAGE)
        );
    }

    @DisplayName("Проверка обязательности значения password в теле запроса при установлении нового пароля")
    @Description("Данный тест-кейс проверяет обязательность password в теле запроса")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2130")
    @Test

    public void checkSettingNewPasswordUserWithoutPassword() {
        String mobileNumber = "79527765102";
        String newPassword = "";
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobileNumber));
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobileNumber);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getTokenResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobileNumber, verificationCode));
        CustomerService_2_0_DataBaseRequest.updatePasswordInUserProfileTableIsNull(customerId);
        String token = getTokenResponse.body().jsonPath().get("sessionToken");
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabase(token, newPassword);

        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotNull(response.getBody(), THERE_NOT_ERROR_MESSAGE)
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Проверка обязательности значения password в теле запроса при установлении нового пароля")
    @Description("Данный тест-кейс проверяет обязательность password в теле запроса")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2131")
    @Test

    public void checkSettingNewPasswordUserAlreadyHasPassword() {
        String mobileNumber = "79527765103";
        String newPassword = "YjMzYjM2Mzg3Y2U1YWExZGRkZmMyOGRkZGNiZDQxNGVkYjQ4NTRiNWZhZWM5ZDAzOGRmZWQzMmIyZTEyYWZjYQ==";
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobileNumber));
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobileNumber);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getTokenResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobileNumber, verificationCode));
        String token = getTokenResponse.body().jsonPath().get("sessionToken");
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabase(token, newPassword);

        assertAll(
                () -> assertEquals(SC_CONFLICT, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotNull(response.getBody(), THERE_NOT_ERROR_MESSAGE)
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Проверка обязательности значения password в теле запроса при установлении нового пароля")
    @Description("Данный тест-кейс проверяет обязательность password в теле запроса")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2132")
    @Test

    public void checkSettingNewPasswordWithoutInvalidEndpoint() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4OTgzNjBmNy1hZTkxLTQzMGQtYWQ5My1kYzI0YmNkYzZhYWUiLCJpYXQiO";
        String newPassword = "YjMzYjM2Mzg3Y2U1YWExZGRkZmMyOGRkZGNiZDQxNGVkYjQ4NTRiNWZhZWM5ZDAzOGRmZWQzMmIyZTEyYWZjYQ==";
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabaseInvalidEndpoint(token, newPassword);

        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), THERE_NOT_ERROR_MESSAGE)
        );
    }

    @DisplayName("Валидация входных параметров при установлении нового пароля")
    @Description("Данный тест-кейс проверяет валидацию входных параметров: newPassword.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2133")
    @ParameterizedTest(name = "newPassword: {0}, mobilePhone: {1}")
    @CsvSource({"cXdlcnR5MjU2, 79527765102",
            "99202a04b653cb6667aa98d435aa52f8df7ed8b91d1cc15ee66945d13ffc1c9b, 79527765102",
            "9a97e78fd64751eb33b8880ce568b248991cb3b481904ca647e2964157ef26cb, 79527765102"})

    public void checkSettingNewPasswordUserWithUnencryptedPassword(String newPassword, String mobileNumber) {

        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobileNumber));
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobileNumber);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getTokenResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobileNumber, verificationCode));
        String token = getTokenResponse.body().jsonPath().get("sessionToken");
        CustomerService_2_0_DataBaseRequest.updatePasswordInUserProfileTableIsNull(customerId);
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabase(token, newPassword);

        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), THERE_NOT_ERROR_MESSAGE)
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }
}
