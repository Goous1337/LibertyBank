package api.clientService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_1_CheckRegistrationByPhoneTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "79031553942, e670aafa-adfd-49e6-bdbf-b65d9e0c7d53",
            "79958984928, 033140e9-ea0a-40c3-a738-060283531147",
            "79849475391, e455e1f7-1f9c-4c99-99f1-ccab5556f4b1",
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
            strings = {"79974699104", "79060996597", "79948964168"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-320")
    @Description("Проверка регистрации если пользователь уже зарегистрирован в СДБО")
    public void checkRegistrationByPhoneAlreadyRegisteredUser(String invalidPhoneNumber) {
        Response response = registrationService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_CONFLICT,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Пользователь уже зарегистрирован в СДБО, и повторно зарегистрироваться нельзя",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviders.ClientServiceDataProviders#provideNonExistentClientPhoneNumbers")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-289")
    @Description("Проверка регистрации по номеру телефона, " +
            "когда пользователь не зарегистрирован в приложении и не является клиентом банка")
    public void checkRegistrationByPhoneNotAClient(String invalidPhoneNumber) {
        Response response = registrationService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProviders.ClientServiceDataProviders#generateRandomInvalidPhoneNumbers")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-275")
    @Description("Проверка работы валидации номера телефона")
    public void checkRegistrationByPhoneInvalidPhoneNumber(String invalidPhoneNumber) {
        Response response = registrationService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"79137193837", "79978020792"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-260")
    @Description("Проверка попытки регистрации заблокированного пользователя")
    public void checkRegistrationByPhoneBlockedUser(String invalidPhoneNumber) {
        Response response = registrationService.checkRegistrationByPhone(invalidPhoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_FORBIDDEN,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Пользователь заблокирован",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

    @Test
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-291")
    @Description("Проверка регистрации по номеру телефона," +
            "когда номер телефона пользователя не передан в качестве параметра запроса")
    public void checkRegistrationByPhoneWithoutParam() {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                registrationService.checkRegistrationByPhoneWithoutParam().statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "registratio, 79031553942",
            "register, 79958984928",
            "reg, 79849475391"
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
            "POST, 79031553942",
            "PUT, 79958984928",
            "PATCH, 79849475391",
            "DELETE, 79031553942"
    })
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-293")
    @Description("Проверка регистрации пользователя по номеру телефона используя невалидный метод запроса")
    public void checkRegistrationByPhoneInvalidMethod(String invalidHttpMethod, String validPhoneNumber) {
        Response response = registrationService.checkRegistrationByPhoneInvalidHttpMethod(invalidHttpMethod, validPhoneNumber);
        assertAll(
                () -> assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Метод не разрешен. Сервер знает о запрашиваемом методе, но он был деактивирован и не может быть использован.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }

}
