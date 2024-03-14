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
import pojo.customerService_2_0.UserAuthorizationByPhone;

import static constant.CustomerService_2_0_Constants.*;
import static constant.Message.ERROR_MESSAGE_NOT_EXPECTED;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-8 Получение информации о пользователе")
public class CRS_8_GettingUserInformationTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Получение информации о пользователе в личном кабинете")
    @Description("Данный тест-кейс проверяет возможность получение информации о пользователе при просмотре общей " +
            "информации в основном меню и личном кабинете.")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2107")
    @Test

    public void successfulGettingUserInformation() {
        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-8/checkInfoUser.json";
        Response response = customerService_2_0.checkGettingUserInformation(customerId, token);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение информации о пользователе при не валидном методе запроса.")
    @Description("Данный тест-кейс проверяет возможность получение информации о пользователе при использовании " +
            "не валидных методов: POST, PUT, PATCH")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2114")

    @ParameterizedTest(name = "Method: {0}")
    @ValueSource(strings = {"POST", "PUT", "PATCH", "DELETE"})

    public void unsuccessfulGettingUserInformationWithInvalidMethod(String method) {

        String customerId = CustomerService_2_0_DataBaseRequest.getCustomerIdByMobilePhone(CUSTOMER_USER_PHONE);
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");
        Response response = customerService_2_0.checkGettingUserInformationWithInvalidMethod(method, customerId, token);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, " +
                                "но он был деактивирован и не может быть использован.",
                        response.body().jsonPath().get("message"), ERROR_MESSAGE_NOT_EXPECTED)
        );
    }
}
