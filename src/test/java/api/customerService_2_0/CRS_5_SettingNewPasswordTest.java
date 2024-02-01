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

import static org.apache.hc.core5.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String mobileNumber = "79581210902";
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
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotEquals(oldHashPassword, newPassword)
        );
    }

    @DisplayName("Валидация метода запроса установления пароля")
    @Description("Данный тест-кейс проверяет возможность отправки запроса методом отличным от PATCH.")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2129")
    @ParameterizedTest(name = "method: {0}, mobilePhone: {1}")
    @CsvSource({"POST, 79225123582",
            "PUT, 79228134511",
            "GET, 79221768263",
            "DELETE, 79727824061"})
    public void checkSettingNewPasswordUserWithInvalidMethod(String method, String mobilePhone) {

        String newPassword = "YjMzYjM2Mzg3Y2U1YWExZGRkZmMyOGRkZGNiZDQxNGVkYjQ4NTRiNWZhZWM5ZDAzOGRmZWQzMmIyZTEyYWZjYQ==";
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-5/errorMessageBody405.json";
        customerService_2_0.checkListSavingVerificationCode(new CustomerService_2_0_Mobile(mobilePhone));
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        Response getTokenResponse = customerService_2_0.checkListUserVerificationWithValidData
                (new UserVerificationWithCode(mobilePhone, verificationCode));
        CustomerService_2_0_DataBaseRequest.updatePasswordInUserProfileTableIsNull(customerId);
        String token = getTokenResponse.body().jsonPath().get("sessionToken");
        Response response = customerService_2_0.changePasswordForUserUpdatedDatabaseWithInvalidMethod(method, token, newPassword);


        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }


}
