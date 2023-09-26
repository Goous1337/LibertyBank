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
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_2_CheckRegistrationByPassportTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "123456, clientId1",
            "1234567890, clientId2",
            "12345678901234567890, clientId3",
            "AZ-012, clientId4"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-247")
    @Description("Проверка регистрации по номеру паспорта, когда пользователь является клиентом банка, " +
            "но не зарегистрирован в приложении")
    public void checkRegistrationByPassportRegisteredClient(String validPassport, String clientId) {
        Response response = registrationService.checkRegistrationByPassport(validPassport);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(validPassport,
                        response.body().jsonPath().get("mobilePhone"),
                        "Номер паспорта в ответе не соответствует ожидаемому"),
                () -> assertEquals(clientId,
                        response.body().jsonPath().get("id"),
                        "ID клиента в ответе не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"AZ-012", "123456", "1234567890", "12345678901234567890"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-280")
    @Description("Проверка регистрации по паспорту если пользователь уже зарегистрирован в СДБО")
    public void checkRegistrationByPassportAlreadyRegisteredUser(String invalidPassport) {
        assertEquals(HttpStatus.SC_CONFLICT,
                registrationService.checkRegistrationByPassport(invalidPassport).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"AZ-012", "123456", "1234567890", "12345678901234567890"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-274")
    @Description("Проверка регистрации по номеру пасспорта, " +
            "когда пользователь не зарегистрирован в приложении и не является клиентом банка")
    public void checkRegistrationByPassportNotAClient(String invalidPassport) {
        assertEquals(HttpStatus.SC_BAD_REQUEST,
                registrationService.checkRegistrationByPassport(invalidPassport).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"12345,", "123456789012345678901", "az-0123", ""}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-261")
    @Description("Проверка работы валидации паспорта")
    public void checkRegistrationByPassportInvalidPassport(String invalidPassport) {
        assertEquals(HttpStatus.SC_BAD_REQUEST,
                registrationService.checkRegistrationByPassport(invalidPassport).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"AZ-012", "123456", "1234567890", "12345678901234567890"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-285")
    @Description("Проверка попытки регистрации заблокированного пользователя")
    public void checkRegistrationByPhoneBlockedUser(String invalidPassport) {
        assertEquals(HttpStatus.SC_FORBIDDEN,
                registrationService.checkRegistrationByPassport(invalidPassport).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "POST, 1234567890",
            "PUT, 1234567890",
            "PATCH, 1234567890",
            "DELETE, 1234567890"
    })
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-306")
    @Description("Проверка регистрации пользователя по номеру телефона используя невалидный метод запроса")
    public void checkRegistrationByPhoneInvalidMethod(String invalidHttpMethod, String validPassport) {
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED,
                registrationService.checkRegistrationByPassportInvalidHttpMethod(invalidHttpMethod, validPassport).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

}
