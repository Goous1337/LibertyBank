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

import static constant.CustomerServiceConstants.REGISTERED_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EP_3_SavingVerificationCodeTest extends BaseTest {

    @Test
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-243")
    @Description("[US EP-3] Успешное сохранение кода верификации")
    public void verificationSuccessfulSavingVerificationCode() {
        Response response = verificationService.verificationService(REGISTERED_PHONE_NUMBER);
        String verificationCodeFromDB = "";
        // здесь будет метод, который отправляет запрос к БД и получает верификационный код
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(verificationCodeFromDB,
                        response.body().jsonPath().get("verificationCode"),
                        "Верификационный код не соответствует ожидаемому")
        );
    }

    @Test
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-245")
    @Description("[US EP-3] Повторный запрос кода, если время блокировки не истекло")
    public void requestCodeLockTimeNotExpired() {
        assertEquals(HttpStatus.SC_OK, verificationService.verificationService(REGISTERED_PHONE_NUMBER).statusCode(),
                "Код ответа не соответствует ожидаемому");
        assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, verificationService.verificationService(REGISTERED_PHONE_NUMBER).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"7999123", "599932555145620", "799912fdf3456", "kdkdk"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-244")
    @Description("[US EP-3] Неуспешное сохранение кода в БД")
    public void unsuccessfulSavingCodeDatabase(String invalidPhoneNumber) {
        Response response = verificationService.verificationService(invalidPhoneNumber);
        String verificationCodeFromDB = "";
        // здесь будет метод, который отправляет запрос к БД и получает верификационный код
        assertAll(
                () -> assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("", verificationCodeFromDB,
                        "Верификационный код не соответствует ожидаемому")
        );
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"799912345678", "599932145620", "7999123456", "5999321456"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-253")
    @Description("[US EP-3] Отправка номера невалидной длины")
    public void sendingNumberInvalidLength(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                verificationService.verificationService(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @CsvSource({
            "POST, 77777777777",
            "PUT, 77777777777",
            "GET, 77777777777",
            "DELETE, 77777777777"
    })
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-312")
    @Description("[US EP-3] Проверка кода верификации, если отправить невалидный метод")
    public void checkingVerificationCodeSendInvalidMethod(String invalidHttpMethod, String validPhoneNumber) {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                verificationService.checkVerificationCodeInvalidHttpMethod(invalidHttpMethod, validPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"0000000000", "111111111111", "+1234567890", "-1234567890", " ()12345678901"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-314")
    @Description("[US EP-3] Проверка отправки невалидного номера телефона")
    public void checkingWhetherInvalidPhoneNumberWasSent(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                verificationService.verificationService(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"7(999)1234567", "7 999 123 45 67", "7999-123-45-67", "+79991234567"}
    )
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-256")
    @Description("[US EP-3] Отправка телефона в форматированном виде")
    public void sendingPhoneNumberFormattedForm(String invalidPhoneNumber) {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                verificationService.verificationService(invalidPhoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");

    }

    @Test
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-246")
    @Description("[US EP-3] Отправка номера телефона, где пустое тело запроса")
    public void sendingPhoneNumberRequestBodyEmpty() {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                verificationService.checkVerificationCodeWithoutParam().statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

}