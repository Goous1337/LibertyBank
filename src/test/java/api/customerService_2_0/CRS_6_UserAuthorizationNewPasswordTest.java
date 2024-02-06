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
import pojo.customerService_2_0.UserAuthorizationByPhone;

import java.util.List;
import java.util.stream.Collectors;

import static constant.CustomerService_2_0_Constants.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-6 Авторизация Пользователя (создание нового пароля)")
public class CRS_6_UserAuthorizationNewPasswordTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. CRS-6 Авторизация Пользователя.")
    @Description("""
            Данный тест кейс проверяет процесс авторизации клиента по введенному логину
            (телефону или номеру паспорта или вида на жительство) и паролю.
            """)
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2158")
    @Test
    public void checkUserAuthorizationWithMobileAndPassport() {
        String mobilePhone = "79198151165";
        int passportId = CustomerService_2_0_DataBaseRequest.getCustomerPassportIdByMobilePhone(mobilePhone);
        List<String> passportSeriesAndNumber = CustomerService_2_0_DataBaseRequest
                .getCustomerPassportSeriesAndNumberByPassportId(passportId);
        String identityDocNumber = passportSeriesAndNumber.stream().collect(Collectors.joining());
        Response responseByMobile = customerService_2_0.userAuthorizationByMobilePhone(
                new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        Response responseByPassport = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(identityDocNumber, CUSTOMER_USER_PASSWORD, CUSTOMER_IDENTITY_DOC_NUMBER_TYPE));
        assertAll(
                () -> assertEquals(SC_OK, responseByMobile.getStatusCode()),
                () -> assertNotNull(responseByMobile.jsonPath().get("accessToken")),
                () -> assertNotNull(responseByMobile.jsonPath().get("refreshToken")),
                () -> assertEquals(SC_OK, responseByPassport.getStatusCode()),
                () -> assertNotNull(responseByPassport.jsonPath().get("accessToken")),
                () -> assertNotNull(responseByPassport.jsonPath().get("refreshToken"))
        );

    }

    @DisplayName("Авторизация пользователя при отправке невалидных учётных данных пользователя.")
    @Description("""
            Данный тест кейс проверяет процесс авторизации клиента используя невалидные учётные данные.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2159")
    @ParameterizedTest
    @CsvSource({"11111,TSRiNDU0OTM4ODYwMzk5YzZmYzVlOTFlMzQxMzExZDkzM2JlYTk4MDgyYzg0YzMyMjU4NWMxZmVmMTFmZGY0Yg==",
            "@$#$%&*(!@#,12345678"})
    public void checkUserAuthorizationWithInvalidData(String invalidLogin, String invalidPassword) {
        String mobilePhone = "79198151165";
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        int passportId = CustomerService_2_0_DataBaseRequest.getCustomerPassportIdByMobilePhone(mobilePhone);
        List<String> passportSeriesAndNumber = CustomerService_2_0_DataBaseRequest
                .getCustomerPassportSeriesAndNumberByPassportId(passportId);
        String identityDocNumber = passportSeriesAndNumber.stream().collect(Collectors.joining());
        Response responseWithInvalidPhone = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(invalidLogin, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        Response responseMobilePhoneWithInvalidPassword = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(mobilePhone, invalidPassword, CUSTOMER_MOBILE_PHONE_TYPE));
        Response responseWithInvalidDocNumber = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(invalidLogin, CUSTOMER_USER_PASSWORD, CUSTOMER_IDENTITY_DOC_NUMBER_TYPE));
        Response responseDocNumberWithInvalidPassword = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(identityDocNumber, invalidPassword, CUSTOMER_IDENTITY_DOC_NUMBER_TYPE));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, responseWithInvalidPhone.getStatusCode()),
                () -> assertEquals(SC_BAD_REQUEST, responseMobilePhoneWithInvalidPassword.getStatusCode()),
                () -> assertEquals(SC_BAD_REQUEST, responseWithInvalidDocNumber.getStatusCode()),
                () -> assertEquals(SC_BAD_REQUEST, responseDocNumberWithInvalidPassword.getStatusCode()),
                () -> responseWithInvalidPhone.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> responseMobilePhoneWithInvalidPassword.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> responseWithInvalidDocNumber.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> responseDocNumberWithInvalidPassword.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );

    }

    @DisplayName("Авторизация пользователя при отправке невалидного параметра type в теле запроса")
    @Description("""
            Данный тест кейс проверяет процесс авторизации клиента используя невалидный параметр type в теле запроса.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2160")
    @Test
    public void checkUserAuthorizationWithInvalidType() {
        String mobilePhone = "79198151165";
        String invalidType = "PHONE";
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        Response response = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD, invalidType));
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Авторизация пользователя используя невалидный (не существующий) URL")
    @Description("""
            Данный тест кейс проверяет процесс авторизации клиента используя невалидный (не существующий) URL.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2161")
    @Test
    public void checkUserAuthorizationWithInvalidUrl() {
        String mobilePhone = "79198151165";
        String jsonSchema = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        int passportId = CustomerService_2_0_DataBaseRequest.getCustomerPassportIdByMobilePhone(mobilePhone);
        List<String> passportSeriesAndNumber = CustomerService_2_0_DataBaseRequest
                .getCustomerPassportSeriesAndNumberByPassportId(passportId);
        String identityDocNumber = passportSeriesAndNumber.stream().collect(Collectors.joining());
        Response responsePhone = customerService_2_0.checkListUserAuthorizationWithInvalidUrl
                (new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        Response responseDocNumber = customerService_2_0.checkListUserAuthorizationWithInvalidUrl
                (new UserAuthorizationByPhone(identityDocNumber, CUSTOMER_USER_PASSWORD, CUSTOMER_IDENTITY_DOC_NUMBER_TYPE));
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, responsePhone.getStatusCode()),
                () -> responsePhone.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema)),
                () -> assertEquals(SC_NOT_FOUND, responseDocNumber.getStatusCode()),
                () -> responseDocNumber.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        );
    }

    @DisplayName("Авторизация пользователя используя некорректный метод запроса")
    @Description("""
            Данный тест кейс проверяет процесс авторизации клиента используя некорректный метод запроса.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2162")
    @ParameterizedTest
    @CsvSource({"GET", "PUT", "PATCH", "DELETE"})
    public void checkUserAuthorizationWithInvalidMethod(String method) {
        String mobilePhone = "79198151165";
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        int passportId = CustomerService_2_0_DataBaseRequest.getCustomerPassportIdByMobilePhone(mobilePhone);
        List<String> passportSeriesAndNumber = CustomerService_2_0_DataBaseRequest
                .getCustomerPassportSeriesAndNumberByPassportId(passportId);
        String identityDocNumber = passportSeriesAndNumber.stream().collect(Collectors.joining());
        Response responsePhone = customerService_2_0.checkListUserAuthorizationWithInvalidMethod
                (new UserAuthorizationByPhone(mobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE), method);
        Response responseDocNumber = customerService_2_0.checkListUserAuthorizationWithInvalidMethod
                (new UserAuthorizationByPhone(identityDocNumber, CUSTOMER_USER_PASSWORD, CUSTOMER_IDENTITY_DOC_NUMBER_TYPE), method);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, responsePhone.getStatusCode()),
                () -> responsePhone.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, responseDocNumber.getStatusCode()),
                () -> responseDocNumber.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Авторизация пользователя если клиент заблокирован работниками банка.")
    @Description("""
            Данный тест кейс проверяет процесс авторизации используя данные если клиент заблокирован работниками банка.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2163")
    @Test
    public void checkBlockedUserAuthorization() {
        String blockedUserMobilePhone = CustomerService_2_0_DataBaseRequest.getBlockedUserMobilePhone();
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        int passportId = CustomerService_2_0_DataBaseRequest.getBlockedUserPassportId();
        List<String> passportSeriesAndNumber = CustomerService_2_0_DataBaseRequest
                .getCustomerPassportSeriesAndNumberByPassportId(passportId);
        String identityDocNumber = passportSeriesAndNumber.stream().collect(Collectors.joining());
        Response responsePhone = customerService_2_0.userAuthorizationByMobilePhone(new UserAuthorizationByPhone
                (blockedUserMobilePhone, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE));
        Response responseDocNumber = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(identityDocNumber, CUSTOMER_USER_PASSWORD, CUSTOMER_IDENTITY_DOC_NUMBER_TYPE));
        assertAll(
                () -> assertEquals(SC_FORBIDDEN, responsePhone.getStatusCode()),
                () -> responsePhone.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> assertEquals(SC_FORBIDDEN, responseDocNumber.getStatusCode()),
                () -> responseDocNumber.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
