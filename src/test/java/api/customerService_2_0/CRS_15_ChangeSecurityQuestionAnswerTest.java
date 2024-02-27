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
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-15 Изменение контрольного вопроса / ответа")
public class CRS_15_ChangeSecurityQuestionAnswerTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Изменение контрольного вопроса/ответа")
    @Description("Данный тест-кейс проверяет возможность изменения контрольного вопроса/ответа.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2083")
    @Test

    public void successfulUpdateQuestionAnswer() {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");

        String customerId = CustomerService_2_0_DataBaseRequest.getAllCustomerId().get(0);
        String jsonSchemaPath = "schemas/customerService_2_0/CRS-15/updateQuestionAnswer.json";
        String securityQuestion = "Что измерят тахометр";
        String securityAnswer = "Скорость";
        Response response = customerService_2_0.checkUpdateQuestionAnswer(securityQuestion, securityAnswer, customerId, refreshToken);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Изменение контрольного вопроса/ответа, используя некорректный метод запроса")
    @Description("Данный тест-кейс проверяет возможность изменения контрольного вопроса/ответа используя некорректный метод запроса (POST, GET, PUT, DELETE вместо PATCH / OPTIONS)")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2084")
    @ParameterizedTest(name = "Http метод: {0}")
    @CsvSource({
            "POST",
            "GET",
            "PUT",
            "DELETE"
    })
    public void checkUpdateQuestionAnswerInvalidHttpMethod(String invalidHttpMethod) {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");

        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String securityQuestion = "Что измерят тахометр";
        String securityAnswer = "Скорость";
        Response response = customerService_2_0.updateQuestionAnswerInvalidHttpMethod(securityQuestion, securityAnswer, invalidHttpMethod, refreshToken);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Изменение контрольного вопроса/ответа, используя невалидный (не существующий) URL")
    @Description("Данный тест-кейс проверяет возможность изменения контрольного вопроса/ответа используя невалидный (не существующий) URL")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2086")
    @Test()

    public void checkUpdateQuestionAnswerInvalidUrl() {
        String customerId = CustomerService_2_0_DataBaseRequest.getAllCustomerId().get(0);
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        String securityQuestion = "Что измерят тахометр";
        String securityAnswer = "Скорость";
        Response response = customerService_2_0.checkUpdateQuestionAnswerInvalidUrl(securityQuestion, securityAnswer, customerId);
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Изменение контрольного вопроса/ответа (переданы пустые/невалидные данные)")
    @Description("Данный тест-кейс проверяет возможность изменения контрольного вопроса/ответа при передаче в теле запроса пустых или невалидных данных.")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2087")
    @ParameterizedTest(name = "Вопрос: {1}")
    @CsvSource({
            "'', ''",
            "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа," +
                    ", аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа"
    })
    public void checkUpdateQuestionAnswerInvalidInvalidData(String securityQuestion, String securityAnswer) {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String refreshToken = getToken.jsonPath().get("refreshToken");
        String customerId = CustomerService_2_0_DataBaseRequest.getAllCustomerId().get(0);
        String jsonSchemaPath = "schemas/customerService_2_0/customerService_2_0_BadRequest400.json";
        Response response = customerService_2_0.checkUpdateQuestionAnswer(securityQuestion, securityAnswer, customerId, refreshToken);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
