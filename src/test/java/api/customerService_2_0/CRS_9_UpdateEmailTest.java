package api.customerService_2_0;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.customerService_2_0.UpdatedEmail;
import pojo.customerService_2_0.UserAuthorizationByPhone;

import static constant.CustomerService_2_0_Constants.*;
import static constant.Message.ERROR_MESSAGE_NOT_EXPECTED;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

@DisplayName("CRS-9 Обновление email Клиента")
public class CRS_9_UpdateEmailTest extends BaseTest {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    @DisplayName("Основной сценарий. Обновление email Клиента")
    @Description("Данный тест-кейс проверяет возможность обновления email пользователя в БД")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2121")
    @Test
    public void successfulUpdateEmail() {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");

        Response response = customerService_2_0.updateEmailForClient(token, new UpdatedEmail("vasyapupkin@gmail.com"));
        assertEquals(SC_OK, response.statusCode(), RESPONSE_CODE_NOT_EXPECTED);
    }

    @DisplayName("Успешное обновление email пользователя проверка валидации полей")
    @Description("Данный тест-кейс проверяет возможность обновления email пользователя в БД")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("positive"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2164")
    @ParameterizedTest()
    @ValueSource(strings = {"va@ya.ru", "vasiavasiavasiavasiavasiavasi@yahooyahooyahoo.com", "vasiaPUPKIN@yahoo.com",
            "159@yahoo.com", "vasya.pupkin_v-a@gmail.com"})
    public void successfulUpdateEmailCheckValidation(String email) {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");

        Response response = customerService_2_0.updateEmailForClient(token, new UpdatedEmail(email));
        assertEquals(SC_OK, response.statusCode(), RESPONSE_CODE_NOT_EXPECTED);
    }

    @DisplayName("Проверка обязательности параметров при обновлении email пользователя в БД")
    @Description("Данный тест-кейс проверяет обязательность параметров при обновлении email пользователя")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2135")
    @Test
    public void unsuccessfulUpdateEmailWithoutBody() {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");

        Response response = customerService_2_0.updateEmailForClientWithoutEmail(token);
        assertAll(
                () -> assertEquals(SC_UNSUPPORTED_MEDIA_TYPE, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(
                        "Формат запрашиваемых данных не поддерживается сервером, поэтому запрос отклонён.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Обновление email пользователя в БД при вводе не валидных данных")
    @Description("Данный тест-кейс проверяет возможность обновления email пользователя в БД")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2124")
    @ParameterizedTest()
    @ValueSource(strings = {"vasiapupkinvasiapupkinvasiapupkinvasiapupki@mail.ru", "vasiapupkinvasiapupkinvasiapupk@mail.ru",
            "vasiapupkin@mailmailmailmail.ru", "vasiapupkin@mail.ruru", "v@mail.ru", "vasiapupkin@m.ru",
            "vasiapupkin@mail.r", "васяпупкин@mail.ru", ".vasiapupkin@mail.ru", "vasiapupkin@mail.ru.",
            "vasiapupkin.@mail.ru", " vasiapupkin.@mail.ru", "-vasiapupkin.@mail.ru", ""})
    public void unsuccessfulUpdateEmailWithInvalidParams(String email) {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");

        Response response = customerService_2_0.updateEmailForClient(token, new UpdatedEmail(email));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(
                        "Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }

    @DisplayName("Обновление email клиента в БД при указании не валидного URL")
    @Description("Данный тест-кейс проверяет возможность обновления email пользователя в БД при использовании " +
            "не валидного URL")
    @Tags({@Tag("smoke"), @Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2123")
    @Test
    public void unsuccessfulUpdateEmailWithInvalidURL() {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");

        Response response = customerService_2_0.updateEmailForClientWithInvalidURL(token,
                new UpdatedEmail("vasyapupkin@gmail.com"),
                "customer/api/v1/auth/user/set/emailll");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Страница не найдена", response.body().jsonPath().get("message"), ERROR_MESSAGE_NOT_EXPECTED)
        );
        assertEquals(SC_NOT_FOUND, response.statusCode(), RESPONSE_CODE_NOT_EXPECTED);
    }

    @DisplayName("Обновление email пользователя в БД когда метод не PATCH.")
    @Description("В данном  тест-кейсе проверяем возвращение ответа от сервера Status code 405 на запрос любым методом" +
            " вместо PATCH")
    @Tags({@Tag("API"), @Tag("negative"), @Tag("CRS")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-2122")
    @ParameterizedTest(name = "method: {0}")
    @ValueSource(strings = {"POST", "PUT", "DELETE", "GET"})
    public void unsuccessfulUpdateEmailWithInvalidHttpMethod(String method) {
        UserAuthorizationByPhone userAuthorizationByPhone = new UserAuthorizationByPhone
                (CUSTOMER_USER_PHONE, CUSTOMER_USER_PASSWORD, CUSTOMER_MOBILE_PHONE_TYPE);
        Response getToken = customerService_2_0.userAuthorizationByMobilePhone(userAuthorizationByPhone);
        String token = getToken.body().jsonPath().get("accessToken");

        Response response = customerService_2_0.updateEmailForClientWithHttpMethod(token, method,
                new UpdatedEmail("vasyapupkin@gmail.com"));
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, но он был " +
                                "деактивирован и не может быть использован.", response.body().jsonPath().get("message"),
                        ERROR_MESSAGE_NOT_EXPECTED)
        );
    }
}
