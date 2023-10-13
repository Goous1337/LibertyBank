package api.userAccountService;

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
import service.UserAccountService;

import static constant.CustomerServiceConstants.NOT_REGISTERED_PHONE_NUMBER;
import static constant.CustomerServiceConstants.REGISTERED_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_4_UserVerificationByPhoneNumber extends BaseTest {

    UserAccountService verificationService = new UserAccountService();

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-279")
    @Description("Верификация пользователя с валидными данными")
    public void userVerificationWithValidData() {
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        Response response = verificationService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-292")
    @Description("Верификация пользователя, когда параметр 'verificationCode' не заполнен")
    public void userVerificationWithoutVerificationCode() {
        Response response = verificationService.verificationMobilePhone(REGISTERED_PHONE_NUMBER);
        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-294")
    @Description("Верификация пользователя, когда параметр 'verificationCode' не соответствует сгенерированному коду")
    public void userVerificationWithInvalidVerificationCode() {
        assertEquals(HttpStatus.SC_BAD_REQUEST, verificationService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER,
                        "111111").statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-296")
    @Description("Верификация пользователя, когда параметр 'mobilePhone' не заполнен")
    public void userVerificationWithoutMobilePhone() {
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        assertEquals(HttpStatus.SC_BAD_REQUEST, verificationService.verificationVerificationCode(verificationCode).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "NOT_REGISTERED_PHONE_NUMBER, '123456'",
            "NOT_REGISTERED_PHONE_NUMBER, '234567'",
            "NOT_REGISTERED_PHONE_NUMBER, '345678'",
            "NOT_REGISTERED_PHONE_NUMBER, '123456'",
            "NOT_REGISTERED_PHONE_NUMBER, '234567'",
            "NOT_REGISTERED_PHONE_NUMBER, '345678'"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-297")
    @Description("Проверка блокировки пользователя при введении не валидных параметров более 5 раз")
    public void userVerificationWithInvalidData(String invalidMobilePhone, String invalidVerificationCode) {
        Response response = verificationService.verificationInvalidMobilePhoneAndVerificationCode(invalidMobilePhone, invalidVerificationCode);
        assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"NOT_REGISTERED_PHONE_NUMBER",
                    "799988877771",
                    "7999h8888д1",
                    "7999%111111",
                    "7999888777",
                    " 7999888777",
                    "7999888777 "
            }
    )
    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-304,https://jira.astondevs.ru/browse/LIB-310")
    @Description("Верификация пользователя, когда в параметре 'mobilePhone' указано не валидное значение")
    public void userVerificationWithInvalidMobilePhone(String mobilePhone) {
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        Response response = verificationService.verificationMobilePhoneVerificationCode(mobilePhone, verificationCode);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-305")
    @Description("Верификация пользователя, когда метод не POST")
    public void userVerificationWithInvalidMethod() {
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        Response response = verificationService.verificationWithInvalidMethod(REGISTERED_PHONE_NUMBER, verificationCode);
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "NOT_REGISTERED_PHONE_NUMBER, '123456'",
            "NOT_REGISTERED_PHONE_NUMBER, '234567'",
            "NOT_REGISTERED_PHONE_NUMBER, '345678'",
            "NOT_REGISTERED_PHONE_NUMBER, '123456'",
            "NOT_REGISTERED_PHONE_NUMBER, '234567'",
            "NOT_REGISTERED_PHONE_NUMBER, '345678'"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-301")
    @Description("Проверка верификации пользователя при введении валидного кода, после блокировки")
    public void userVerificationWithValidDataAfterBlocking(String invalidMobilePhone, String invalidVerificationCode) {
        Response response = verificationService.verificationInvalidMobilePhoneAndVerificationCode(invalidMobilePhone, invalidVerificationCode);
        assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        Response response1 = verificationService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, response1.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-302")
    @Description("Проверка верификации пользователя при введении валидного кода, после 1 попытки ввода невалидного кода")
    public void userVerification() {
        Response response = verificationService.verificationInvalidMobilePhoneAndVerificationCode(NOT_REGISTERED_PHONE_NUMBER,
                "111111");
        assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
        String verificationCode = verificationService.getVerificationCode(REGISTERED_PHONE_NUMBER);
        Response response1 = verificationService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        assertEquals(HttpStatus.SC_OK, response1.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"11111",
                    "$$$111",
                    "111ANы",
                    " 11111",
                    "11111 ",
                    "1111111"
            }
    )
    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-303")
    @Description("Верификация пользователя с невалидным 'verificationCode'")
    public void userVerificationWithInvalidVerificationCode(String verificationCode) {
        Response response = verificationService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "79998887777, '111111'",
            "'79998887777', 111111"
    })
    @Tags({@Tag("smoke"), @Tag("API")})
    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-307,https://jira.astondevs.ru/browse/LIB-308")
    @Description("Верификация пользователя, когда в параметре 'mobilePhone' и 'verificationCode' указан тип данных 'number'")
    public void userVerificationWithInvalidType(String invalidMobilePhone, String invalidVerificationCode) {
        Response response = verificationService.verificationInvalidMobilePhoneAndVerificationCode(invalidMobilePhone, invalidVerificationCode);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }
}