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
import pojo.customerService_2_0.ChangeUserAccountPasswordByPhone;
import pojo.customerService_2_0.UserAuthorizationByPhone;

import static constant.CustomerService_2_0_Constants.*;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-14 Изменение пароля в личном кабинете.")
public class CRS_14_ChangingPasswordInPersonalAccountTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Проверка возможности изменения пароля в личном кабинете.")
    @Description("Данный тест-кейс проверяет возможность изменения пароля в личном кабинете пользователя.")
    @Tags({@Tag("API"), @Tag("Smoke"), @Tag("CRS")})
    @TmsLink("LIB-2085")
    @Test
    public void checkSuccessSavingVerificationCode() {
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        String password = CustomerService_2_0_DataBaseRequest.getCustomerPasswordById(customerId);
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");
        ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone = new ChangeUserAccountPasswordByPhone
                (CUSTOMER_USER_PASSWORD, NEW_CUSTOMER_USER_PASSWORD);
        Response response = customerService_2_0
                .checkListAbilityChangePasswordInPersonalAccount(changeUserAccountPasswordByPhone, refreshToken);
        String newPassword = CustomerService_2_0_DataBaseRequest.getCustomerPasswordById(customerId);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotEquals(password, newPassword)
        );
        ChangeUserAccountPasswordByPhone resetPassword = new ChangeUserAccountPasswordByPhone
                (NEW_CUSTOMER_USER_PASSWORD, CUSTOMER_USER_PASSWORD);
        customerService_2_0.checkListAbilityChangePasswordInPersonalAccount(resetPassword, refreshToken);
    }

    @DisplayName("Проверка обязательности полей при запросе изменения пароля в личном кабинете.")
    @Description("""
            Данный тест-кейс проверяет возможность изменения пароля в личном кабинете пользователя
            при не валидных обязательных параметрах в BODY запроса.
            """)
    @Tags({@Tag("API"), @Tag("Negative"), @Tag("CRS")})
    @TmsLink("LIB-2100")
    @ParameterizedTest
    @CsvSource({"., ", "$,#"})
    public void checkSavingVerificationCodeWithInvalidValues(String password, String newPassword) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");
        ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone = new ChangeUserAccountPasswordByPhone
                (password, newPassword);
        Response response = customerService_2_0.checkListAbilityChangePasswordInPersonalAccount
                (changeUserAccountPasswordByPhone, refreshToken);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName(" Проверка системы валидации параметров в BODY при запросе восстановления пароля.")
    @Description("""
            Данный тест-кейс проверяет возможность изменения пароля в личном кабинете пользователя при
            не валидных обязательных параметрах в BODY запроса.
            """)
    @Tags({@Tag("API"), @Tag("Negative"), @Tag("CRS")})
    @TmsLink("LIB-2089")
    @Test
    public void checkSavingVerificationCodeWithNullParameters() {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");
        ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone = new ChangeUserAccountPasswordByPhone();
        Response response = customerService_2_0
                .checkListAbilityChangePasswordInPersonalAccount(changeUserAccountPasswordByPhone, refreshToken);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка работы системы при запросе изменения пароля в личном кабинете используя методы помимо PATCH/OPTIONS.")
    @Description("""
            Данный тест-кейс проверяет возможность изменения пароля в личном кабинете пользователя при 
            не валидных обязательных параметрах в BODY запроса.
            """)
    @Tags({@Tag("API"), @Tag("Negative"), @Tag("CRS")})
    @TmsLink("LIB-2095")
    @ParameterizedTest
    @CsvSource({"GET", "POST", "PUT", "DELETE"})
    public void checkSavingVerificationCodeWithInvalidMethods(String httpMethod) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");
        ChangeUserAccountPasswordByPhone changeUserAccountPasswordByPhone = new ChangeUserAccountPasswordByPhone
                (CUSTOMER_USER_PASSWORD, NEW_CUSTOMER_USER_PASSWORD);
        Response response = customerService_2_0
                .checkListSavingVerificationCodeWithInvalidMethods
                        (changeUserAccountPasswordByPhone, httpMethod, refreshToken);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
