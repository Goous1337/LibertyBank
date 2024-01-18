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
import org.junit.jupiter.params.provider.ValueSource;
import pojo.customerService_2_0.CustomerService_2_0_InvalidMobilePhoneValue;
import pojo.customerService_2_0.CustomerService_2_0_Mobile;

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-3 Сохранение кода верификации.")
public class CRS_3_SavingVerificationCodeTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Успешное сохранение кода верификации")
    @Description("Проверка успешного сохранения кода верификации в БД")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2099")
    @Test
    public void checkSuccessSavingVerificationCode() throws InterruptedException {
        int sleepTime;
        String mobilePhone = "79958984928";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobilePhone);
        Response firstResponse = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCodeFirstRequest = CustomerService_2_0_DataBaseRequest
                .getCustomerLastVerificationCodeById(customerId);
        assertAll(
                () -> assertEquals(SC_OK, firstResponse.getStatusCode()),
                () -> assertNotNull(firstResponse.jsonPath().get("blockSeconds")),
                () -> assertNotNull(verificationCodeFirstRequest)
        );
        sleepTime = firstResponse.jsonPath().getInt("blockSeconds");
        Thread.sleep(sleepTime * 1000);
        Response secondResponse = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        String verificationCodeSecondRequest = CustomerService_2_0_DataBaseRequest
                .getCustomerLastVerificationCodeById(customerId);
        assertAll(
                () -> assertEquals(SC_OK, secondResponse.getStatusCode()),
                () -> assertNotNull(secondResponse.jsonPath().get("blockSeconds")),
                () -> assertNotEquals(verificationCodeFirstRequest, verificationCodeSecondRequest)
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Отправка номера телефона, которого нет в БД")
    @Description("""
                  Проверка валидности обработки INSERT-запроса в БД.
                  Зарегистрированный пользователь еще ни разу не отправлял запрос на получение кода верификации.
                  Запись customer_id в БД (customer2_service_db) в таблице user_profile отсутствует в БД.
                  После запроса кода верификации новая запись должна сохраниться в БД.
            """)
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2105")
    @Test
    public void checkSendingPhoneNumberThatNotInDataBase() {
        String mobilePhone = "79808901750";
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobilePhone);
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        CustomerService_2_0_DataBaseRequest.deleteDataById(customerId);
        Response response = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        List<String> customerIdList = CustomerService_2_0_DataBaseRequest.getAllCustomerId();
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("blockSeconds")),
                () -> assertTrue(customerIdList.contains(customerId))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }

    @DisplayName("Проверка валидации номера телефона при регистрации")
    @Description("""
            В данном эндпоинте проверяется попытка регистрации отправка номера мобильного телефона
            с невалидными значениями.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2104")
    @ParameterizedTest
    @ValueSource(strings = {"7999123456", "799912345678", "+7(999)1234567", "privet"})
    public void checkingPhoneNumberValidationDuringRegistration(String mobilePhone) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobilePhone);
        Response response = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка валидации номера телефона при регистрации")
    @Description("""
            В данном эндпоинте проверяется попытка регистрации отправка номера мобильного телефона
             с невалидными значениями.
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2104")
    @ParameterizedTest
    @ValueSource(longs = {1, 7999123456L, 799912345678L, -79808901750L})
    public void checkingPhoneNumberValidationDuringRegistrationWithInvalidTypeOfMobilePhone(long mobilePhone) {
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        CustomerService_2_0_InvalidMobilePhoneValue customerService20InvalidMobilePhoneValue
                = new CustomerService_2_0_InvalidMobilePhoneValue(mobilePhone);
        Response response = customerService_2_0.checkListSavingVerificationCodeWithInvalidMobilePhoneType
                (customerService20InvalidMobilePhoneValue);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Повторный запрос смс-кода верификации, если время блокировки не истекло")
    @Description("""
            Пользователь делает повторный запрос SMS-кода верификации, когда время блокировки еще не истекло
            (не позднее 30 секунд)
            """)
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2099")
    @Test
    public void checkSavingVerificationCodeIfBlockingTimeIsActive() {
        String mobilePhone = "79808901750";
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(mobilePhone);
        CustomerService_2_0_Mobile customerService_2_0_mobile = new CustomerService_2_0_Mobile(mobilePhone);
        customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        Response secondResponse = customerService_2_0.checkListSavingVerificationCode(customerService_2_0_mobile);
        assertAll(
                () -> assertEquals(SC_NOT_ACCEPTABLE, secondResponse.getStatusCode()),
                () -> secondResponse.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        CustomerService_2_0_DataBaseRequest.resetTimerOfVerificationCodeById(customerId);
    }
}
