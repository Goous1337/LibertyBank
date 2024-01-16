package api.customerService;

import api.BaseTest;
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
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("CRS-1 Проверка регистрации по номеру телефона")
public class CRS_1_CheckRegistrationByPhoneTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Проверка попытки регистрации заблокированного пользователя")
    @Description("Проверка попытки регистрации заблокированного пользователя")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-260")
    @ParameterizedTest(name = "invalidPhoneNumber: {0}")
    @ValueSource(
            strings = {"79137193837", "79978020792"}
    )

    public void checkRegistrationByPhoneBlockedUser(String invalidPhoneNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(SC_FORBIDDEN,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка работы валидации номера телефона")
    @Description("Проверка работы валидации номера телефона")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-275")
    @ParameterizedTest
    @MethodSource("dataProviders.ClientServiceDataProviders#generateRandomInvalidPhoneNumbers")

    public void checkRegistrationByPhoneInvalidPhoneNumber(String invalidPhoneNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации по номеру телефона, когда пользователь является клиентом банка, но не зарегистрирован в приложении")
    @Description("Данный тест кейс проверяет, что при отправке номера телефона пользователя, который есть в базе клиентов банка, " +
            "получим ответ от сервера 200 OK")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-288")
    @ParameterizedTest(name = "validPhoneNumber: {0}, clientId: {1}")
    @CsvSource({
            "79031553942, e670aafa-adfd-49e6-bdbf-b65d9e0c7d53",
            "79958984928, 033140e9-ea0a-40c3-a738-060283531147",
            "79849475391, e455e1f7-1f9c-4c99-99f1-ccab5556f4b1",
    })

    public void checkRegistrationByPhoneNonRegisteredClient(String validPhoneNumber, String clientId) {
        String jsonSchemaPath = "schemas/customerService/CRS_1/checkRegistrationByPhoneBlockedUser.json";
        Response response = customerService.checkRegistrationByPhone(validPhoneNumber);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации по номеру телефона, когда пользователь не зарегистрирован в приложении и не является клиентом банка")
    @Description("Данный тест кейс проверяет, что при отправке номера телефона пользователя, которого нет в базе клиентов банка, " +
            "получим ответ от сервера 400 Bad Request")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-289")
    @ParameterizedTest
    @MethodSource("dataProviders.ClientServiceDataProviders#provideNonExistentClientPhoneNumbers")

    public void checkRegistrationByPhoneNotAClient(String invalidPhoneNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @Test
    @DisplayName("Проверка регистрации по номеру телефона, когда номер телефона пользователя не передан в качестве параметра запроса")
    @Description("Тест кейс проверяет результат отправки данного GET запроса без обязательного параметра 'mobilePhone'")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-291")

    public void checkRegistrationByPhoneWithoutParam() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhoneWithoutParam();
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR,
                       response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка попытки регистрации заблокированного пользователя")
    @Description("Данный тест кейс проверяет обработку невалидного http метода при отправке запроса для проверки " +
            "регистрации пользователя по номеру телефона.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-293")
    @ParameterizedTest(name = "invalidHttpMethod: {0}, validPhoneNumber: {1}")
    @CsvSource({
            "POST, 79031553942",
            "PUT, 79958984928",
            "PATCH, 79849475391",
            "DELETE, 79031553942"
    })

    public void checkRegistrationByPhoneInvalidMethod(String invalidHttpMethod, String validPhoneNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhoneInvalidHttpMethod(invalidHttpMethod, validPhoneNumber);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка регистрации пользователя по номеру телефона используя невалидный URL запроса")
    @Description("Данный тест кейс проверяет обработку невалидного URL запроса для проверки регистрации пользователя по номеру телефона.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-295")
    @ParameterizedTest(name = "invalidURL: {0}, validPhoneNumber: {1}")
    @CsvSource({
            "registratio, 79031553942",
            "register, 79958984928",
            "reg, 79849475391"
    })

    public void checkRegistrationByPhoneInvalidURL(String invalidURL, String validPhoneNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhoneInvalidURL(invalidURL, validPhoneNumber);
        assertAll(
                ()->assertEquals(SC_NOT_FOUND,
                response.statusCode(),
                "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
                );
    }

    @DisplayName("Проверка регистрации если пользователь уже зарегистрирован в СДБО")
    @Description("Проверка регистрации уже зарегистрированного пользователя")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-320")
    @ParameterizedTest(name = "invalidPhoneNumber: {0}")
    @ValueSource(
            strings = {"79974699104", "79727824061", "79948964168"}
    )

    public void checkRegistrationByPhoneAlreadyRegisteredUser(String invalidPhoneNumber) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(SC_CONFLICT,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

}