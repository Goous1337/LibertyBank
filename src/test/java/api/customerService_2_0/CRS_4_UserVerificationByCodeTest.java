package api.customerService_2_0;

import api.BaseTest;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.customerService_2_0.CustomerService_2_0_Mobile;
import pojo.customerService_2_0.UserVerificationWithCode;
import pojo.customerService_2_0.UserVerificationWithInvalidTypeMobilePhone;
import pojo.customerService_2_0.UserVerificationWithInvalidTypeVerificationCode;

import java.sql.SQLException;

import static constant.CustomerService_2_0_Constants.*;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-4 Верификация пользователя по коду")
public class CRS_4_UserVerificationByCodeTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Верификация пользователя с валидными данными")
    @Description("Данный тест-кейс проверяет поведение системы при верификации пользователя с валидными данными.")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2115")
    @Test
    public void checkUserVerificationWithValidData() {
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(CUSTOMER_USER_PHONE));
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response response = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(CUSTOMER_USER_PHONE, verificationCode));
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotNull(response.jsonPath().get("sessionToken"))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Верификация пользователя номера телефона которого нет в БД")
    @Description("""
            Данный тест-кейс проверяет поведение системы при попытке верификации пользователя, номера которого нет в БД
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2126")
    @Test
    public void checkUserVerificationWithUnExistMobilePhone() {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_MOBILE_PHONE);
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(CUSTOMER_USER_MOBILE_PHONE));
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response response = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(UN_EXIST_CUSTOMER_USER_PHONE, verificationCode));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Верификация пользователя, код верификации с истекшим сроком действия")
    @Description("""
            Данный тест-кейс проверяет верификацию пользователя с истекшим кодом верификации.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2117")
    @Test
    public void checkUserVerificationWithExpiredVerificationCode() throws SQLException {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_MOBILE_PHONE);
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(CUSTOMER_MOBILE_PHONE));
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        CustomerService_2_0_DataBaseRequest.updateLastCodeExpiration(customerId);
        Response response = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(CUSTOMER_MOBILE_PHONE, verificationCode));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Верификация пользователя невалидным методом")
    @Description("""
            Данный тест-кейс проверяет, что при указании метода PATCH вместо POST,
             система выдает сообщение с 405 ошибкой
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2127")
    @Test
    public void checkUserVerificationWithInvalidMethod() {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String mobilePhone = "79198235298";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobilePhone));
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response response = customerService_2_0.checkListUserVerificationWithInvalidMethod
                (new UserVerificationWithCode(mobilePhone, verificationCode), "PATCH");
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Проверка верификации пользователя при введении валидного кода, после блокировки")
    @Description("""
            Данный тест-кейс проверяет верификацию пользователя при введении валидного кода, после того
            как пользователь ввел 3 раза невалидный код и заблокировался.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2119")
    @Test
    public void checkUserVerificationAfterBlocking() {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String mobilePhone = "79228098773";
        String invalidVerificationCode = "111111";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobilePhone));
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        UserVerificationWithCode userVerificationWithInvalidCode = new UserVerificationWithCode
                (mobilePhone, invalidVerificationCode);
        customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithInvalidCode);
        customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithInvalidCode);
        customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithInvalidCode);
        Integer wrongAttemptsValue = CustomerService_2_0_DataBaseRequest.getWrongAttemptsByCustomerId(customerId);
        Integer smsSentCounterValue = CustomerService_2_0_DataBaseRequest.getSmsSendCounterByCustomerId(customerId);
        Response response = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobilePhone, verificationCode));
        assertAll(
                () -> assertEquals(SC_NOT_ACCEPTABLE, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(0, wrongAttemptsValue),
                () -> assertEquals(0, smsSentCounterValue),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Проверка валидации входных параметров при верификации пользователя")
    @Description("""
            Данный тест-кейс проверяет валидацию поля "mobilePhone" и "verificationCode" при верификации пользователя
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2116")
    @ParameterizedTest
    @CsvSource({
            " ,906060",
            "7901987277,906060",
            "790198727723,906060",
            "790198727abc,906060",
            "790198727!@#,906060",
            " 79019872724,906060",
            "79019872724 ,906060",
            "79999457736, ",
            "79999457736,30034",
            "79999457736,3003434",
            "79999457736,234Snq",
            "79999457736,$$$424",
            "79999457736, 00343",
            "79999457736,30034 ",
            "79999457736,300343"})
    public void checkValidationOfInputParametersDuringUserVerification(String mobilePhone, String verificationCode) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        Response response = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobilePhone, verificationCode));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка валидации входных параметров при верификации пользователя")
    @Description("""
            Данный тест-кейс проверяет валидацию поля "mobilePhone" и "verificationCode" при верификации пользователя
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2116")
    @Test
    public void checkValidationOfInputParametersDuringUserVerificationWithWrongType() {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String mobilePhone = "79101464757";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobilePhone));
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response firstResponse = customerService_2_0.checkListUserVerificationWithInvalidTypeMobilePhone
                (new UserVerificationWithInvalidTypeMobilePhone(INVALID_TYPE_USER_MOBILE_PHONE, verificationCode));
        Response secondResponse = customerService_2_0.checkListUserVerificationWithInvalidTypeVerificationCode
                (new UserVerificationWithInvalidTypeVerificationCode(mobilePhone, INVALID_TYPE_CUSTOMER_VERIFICATION_CODE));
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, firstResponse.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> firstResponse.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, secondResponse.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> secondResponse.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB-2599")
    @DisplayName("Проверка верификации пользователя при введении валидного кода, после 2 попыток ввода невалидного кода")
    @Description("""
            Данный тест-кейс проверяет верификацию пользователя при введении валидного кода,
             после 2 попыток введения невалидного кода.
            """)
    @Tags({@Tag("API"), @Tag("Positive")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2120")
    @Issue("https://jira.astondevs.ru/browse/LIB-2599")
    @Test
    public void checkUserVerificationAfterAttemptsToEnterInvalidCode() throws InterruptedException {
        String mobilePhone = "79221009133";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobilePhone);
        Response getTimeBlockSeconds = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        int blockSecondsValue = getTimeBlockSeconds.jsonPath().get("blockSeconds");
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        UserVerificationWithCode userVerificationWithWrongCode = new UserVerificationWithCode
                (mobilePhone, INVALID_VERIFICATION_CODE);
        CustomerService_2_0_DataBaseRequest.updateWrongAttemptsById(customerId);
        customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithWrongCode);
        Integer wrongAttemptsValue = CustomerService_2_0_DataBaseRequest.getWrongAttemptsByCustomerId(customerId);
        Response firstResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobilePhone, verificationCode));
        assertAll(
                () -> assertEquals(SC_OK, firstResponse.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(1, wrongAttemptsValue),
                () -> assertNotNull(firstResponse.jsonPath().get("sessionToken"))
        );
        CustomerService_2_0_DataBaseRequest.updateWrongAttemptsById(customerId);
        Thread.sleep(blockSecondsValue * 1000L);
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
        customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String secondVerificationCode = CustomerService_2_0_DataBaseRequest
                .getCustomerLastVerificationCodeById(customerId);
        customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithWrongCode);
        customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithWrongCode);
        Integer secondWrongAttemptsValue = CustomerService_2_0_DataBaseRequest.getWrongAttemptsByCustomerId(customerId);
        Response secondResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobilePhone, secondVerificationCode));
        assertAll(
                () -> assertEquals(SC_OK, secondResponse.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(2, secondWrongAttemptsValue),
                () -> assertNotNull(secondResponse.jsonPath().get("sessionToken"))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
        CustomerService_2_0_DataBaseRequest.updateWrongAttemptsById(customerId);
    }
}
