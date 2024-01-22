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
import pojo.customerService_2_0.CustomerService_2_0_Mobile;
import pojo.customerService_2_0.UserVerificationWithCode;

import static constant.CustomerService_2_0_Constants.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
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
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(CUSTOMER_USER_PHONE);
        customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        UserVerificationWithCode userVerificationWithCode = new UserVerificationWithCode
                (CUSTOMER_USER_PHONE, verificationCode);
        Response response = customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithCode);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
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
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_MOBILE_PHONE);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile
                (CUSTOMER_USER_MOBILE_PHONE);
        customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCode = CustomerService_2_0_DataBaseRequest.getCustomerLastVerificationCodeById(customerId);
        UserVerificationWithCode userVerificationWithCode = new UserVerificationWithCode
                (UN_EXIST_CUSTOMER_USER_PHONE, verificationCode);
        Response response = customerService_2_0.checkListUserVerificationWithValidData(userVerificationWithCode);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode())
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }
}
