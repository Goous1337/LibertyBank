package api.clientService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_1_CheckRegistrationByPhoneTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "77777777777, clientId1",
            "77777777777, clientId2",
            "77777777777, clientId3"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-288")
    @Description("Проверка регистрации по номеру телефона, когда пользователь является клиентом банка, " +
            "но не зарегистрирован в приложении")
    public void checkRegistrationByPhoneNonRegisteredClient(String validPhoneNumber, String clientId) {
        Response response = registrationService.checkRegistrationByPhone(validPhoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(validPhoneNumber,
                        response.body().jsonPath().get("mobilePhone"),
                        "Номер телефона в ответе не соответствует ожидаемому"),
                () -> assertEquals(clientId,
                        response.body().jsonPath().get("id"),
                        "ID клиента в ответе не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"77777777777", "77777777777", "77777777777"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-320")
    @Description("Проверка регистрации если пользователь уже зарегистрирован в СДБО")
    public void checkRegistrationByPhoneAlreadyRegisteredUser(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_CONFLICT,
                registrationService.checkRegistrationByPhone(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"77777777777", "77777777777", "77777777777"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-289")
    @Description("Проверка регистрации по номеру телефона, " +
            "когда пользователь не зарегистрирован в приложении и не является клиентом банка")
    public void checkRegistrationByPhoneNotAClient(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_BAD_REQUEST,
                registrationService.checkRegistrationByPhone(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"7a99988877,", "7777777777", "777777777777", ""}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-275")
    @Description("Проверка работы валидации номера телефона")
    public void checkRegistrationByPhoneInvalidPhoneNumber(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_BAD_REQUEST,
                registrationService.checkRegistrationByPhone(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"77777777777", "77777777777", "77777777777"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-260")
    @Description("Проверка попытки регистрации заблокированного пользователя")
    public void checkRegistrationByPhoneBlockedUser(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_FORBIDDEN,
                registrationService.checkRegistrationByPhone(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @Test
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-291")
    @Description("Проверка регистрации по номеру телефона," +
            "когда номер телефона пользователя не передан в качестве параметра запроса")
    public void checkRegistrationByPhoneWithoutParam() {
        assertEquals(HttpStatus.SC_BAD_REQUEST,
                registrationService.checkRegistrationByPhoneWithoutParam().statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "invalidURL1, 77777777777",
            "invalidURL2, 77777777777",
            "invalidURL3, 77777777777"
    })
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-295")
    @Description("Проверка регистрации пользователя по номеру телефона используя невалидный URL запроса")
    public void checkRegistrationByPhoneInvalidURL(String invalidURL, String validPhoneNumber) {
        assertEquals(HttpStatus.SC_NOT_FOUND,
                registrationService.checkRegistrationByPhoneInvalidURL(invalidURL, validPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "POST, 77777777777",
            "PUT, 77777777777",
            "PATCH, 77777777777",
            "DELETE, 77777777777"
    })
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-293")
    @Description("Проверка регистрации пользователя по номеру телефона используя невалидный метод запроса")
    public void checkRegistrationByPhoneInvalidMethod(String invalidHttpMethod, String validPhoneNumber) {
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                registrationService.checkRegistrationByPhoneInvalidHttpMethod(invalidHttpMethod, validPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

}
