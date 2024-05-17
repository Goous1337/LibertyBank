package api.userAccountService;

import api.BaseTest;
import dataBase.requests.UserAccountServiceDataBaseRequests;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.URL_USER_ACCOUNT_SERVICE;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("UAS-3 Сохранение кода верификации")
public class UAS_3_SavingVerificationCodeTest extends BaseTest {

    {
        RestAssured.baseURI = URL_USER_ACCOUNT_SERVICE;
    }

    @DisplayName("Успешное сохранение кода верификации")
    @Description("Проверка успешного сохранения кода верификации в БД")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-243")
    @Test

    public void successfulSavingVerificationCode() {
        String phoneNumber = "79958984928";
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);

        Response response = userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(
                        response.body().jsonPath().get("blockSeconds")),
                () -> assertNotNull(UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer),
                        "Верификационный код необнаружен"));
    }

    @DisplayName("Неуспешное сохранение кода в БД")
    @Description("Проверка возникновения ошибки при сохранении кода верификации в БД")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-244")
    @ParameterizedTest(name = "phoneNumber: {0}")
    @ValueSource(
            strings = {"7999123", "599932555145620", "799912fdf3456", "kdkdk"}
    )

    public void unsuccessfulSavingVerificationCode(String phoneNumber) {
        Response response = userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNull(idCustomer, "Создана запись id несуществующего пользователя")
        );
    }

    @DisplayName("Повторный запрос кода, если время блокировки не истекло")
    @Description("Пользователь делает повторный запрос SMS-кода верификации, когда время блокировки еще не истекло " +
            "(не позднее 30 секунд)")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-245")
    @Test

    public void unsuccessfulReRequestVerificationCodeTimeNotExpired() {
        String phoneNumber = "79808901750";

        assertEquals(SC_OK, userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
        assertEquals(SC_NOT_ACCEPTABLE, userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Отправка номера телефона, где пустое тело запроса")
    @Description("Отправка невалидного значения мобильного телефона, где вместо значения передаётся пустое тело")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-246")
    @ParameterizedTest
    @NullAndEmptySource

    public void unsuccessfulSavingVerificationCodePhoneNumberIsEmpty(String phoneNumber) {
        assertEquals(SC_INTERNAL_SERVER_ERROR,
                userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Отправка номера невалидной длины")
    @Description("Тест на определение валидации телефонного номера по его длине")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-253")
    @ParameterizedTest(name = "phoneNumber: {0}")
    @ValueSource(
            strings = {"7999123456", "799912345678"}
    )

    public void unsuccessfulSavingVerificationCodePhoneNumberInvalidLength(String phoneNumber) {
        assertEquals(SC_INTERNAL_SERVER_ERROR,
                userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Отправка телефона в форматированном виде")
    @Description("Проверка ввода номера телефона в форматированном виде")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-256")
    @ParameterizedTest(name = "phoneNumber: {0}")
    @ValueSource(
            strings = {"7(999)1234567", "7 999 123 45 67", "7999-123-45-67", "+79991234567"}
    )

    public void unsuccessfulSavingVerificationCodePhoneNumberFormattedForm(String phoneNumber) {
        assertEquals(SC_INTERNAL_SERVER_ERROR,
                userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Отправка телефона, который существует в БД")
    @Description("Здесь мы проверяем корректность обработки UPDATE запроса в БД")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-290")
    @Test

    public void successfulUpdatingVerificationCode() {
        String phoneNumber = "79490515678";
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);
        String oldVerificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);

        Response response = userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        String newVerificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(
                        response.body().jsonPath().get("blockSeconds")),
                () -> assertNotEquals(oldVerificationCode, newVerificationCode,
                        "Код верификации не был обновлен!"));
    }

    @DisplayName("Проверка кода верификации, если отправить невалидный метод")
    @Description("Проверка поведения программы, если отправить невалидный метод (отличающийся от PATСН)")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-312")
    @Issue("https://jira.astondevs.ru/browse/LIB-1195")
    @ParameterizedTest(name = "httpMethod: {0}, phoneNumber: {1}")
    @CsvSource({
            "POST, 77777777777",
            "PUT, 77777777777",
            "GET, 77777777777",
            "DELETE, 77777777777"
    })

    public void unsuccessfulSavingVerificationCodeInvalidMethod(String httpMethod, String phoneNumber) {
        assertEquals(SC_METHOD_NOT_ALLOWED,
                userAccountService.checkVerificationCodeInvalidHttpMethod(httpMethod, phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Отправка номера телефона, которого нет в БД")
    @Description("Проверка корректности обработки INSERT-запроса в БД. Зарегистрированный пользователь еще ни разу " +
            "не отправлял запрос на получение кода верификации.")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-313")
    @Test

    public void successfulFirstSavingVerificationCode() {
        String phoneNumber = "79974699104";
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(phoneNumber);

        UserAccountServiceDataBaseRequests.deleteVerificationCodeByIdCustomer(idCustomer);
        Response response = userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(
                        response.body().jsonPath().get("blockSeconds")),
                () -> assertNotNull(UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer),
                        "Верификационный код необнаружен"));
    }

    @DisplayName("Проверка отправки невалидного номера телефона")
    @Description("Пользователь пробует отправить невалидный номер телефона (валидный номер телефона, 11 цифр без «+»)")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-314")
    @ParameterizedTest(name = "phoneNumber: {0}")
    @ValueSource(
            strings = {"0000000000", "111111111111", "+1234567890", "-1234567890", " ()12345678901"}
    )

    public void unsuccessfulSavingVerificationCodeInvalidPhoneNumber(String phoneNumber) {
        assertEquals(SC_INTERNAL_SERVER_ERROR,
                userAccountService.checkVerificationCodeSuccessfulSaved(phoneNumber).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

}