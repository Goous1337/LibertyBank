package api.userAccountService;

import api.BaseTest;
import dataBase.requests.UserAccountServiceDataBaseRequests;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.CustomerServiceConstants.REGISTERED_PHONE_NUMBER;


import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.URL_USER_ACCOUNT_SERVICE;

public class UAS_5_SettingNewPassword extends BaseTest {

    {
        RestAssured.baseURI = URL_USER_ACCOUNT_SERVICE;
    }

    @DisplayName("Установление нового пароля")
    @Description("Проверка успешного установления нового пароля пользователю")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1535")
    @Test

    public void successfulInstallationsNewPassword() {
        String mobileNumber = "79137193837";
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(mobileNumber);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(mobileNumber);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(mobileNumber, verificationCode);
        String hashOldPassword = UserAccountServiceDataBaseRequests.getHashPasswordInUserProfileTable(idCustomer);
        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.changePasswordForUserUpdatedDatabase(getTokenResponse.jsonPath().get("sessionToken"), newPassword);

        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotEquals(newPassword, hashOldPassword,
                        "Новый пароль отличается от старого"));
    }

    @DisplayName("Валидация метода запроса установления пароля")
    @Description("Тест направлен на проверку возможности отправить запроса методом отличным от PATCH.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1536")
    @ParameterizedTest(name = "invalidHttpMethod: {0}")
    @CsvSource({
            "POST",
            "PUT",
            "GET",
            "DELETE"
    })

    public void unsuccessfulInstallationsNewPasswordInvalidMethod(String invalidHttpMethod) {
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);

        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.checkPasswordChangesInvalidMethod(invalidHttpMethod,
                getTokenResponse.jsonPath().get("sessionToken"), newPassword);

        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @DisplayName("Обязательность ключей в теле запроса при установлении нового пароля")
    @Description("Тест направлен на проверку обязательности ключей в теле запроса без sessionToken с валидным newPassword.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1537")
    @Test

    public void unsuccessfulInstallationsNewPasswordWithoutToken() {
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.changePasswordForUserUpdatedDatabaseWithoutToken(newPassword);

        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @DisplayName("Обязательность ключей в теле запроса при установлении нового пароля")
    @Description("Тест направлен на проверку обязательности ключей в теле запроса c sessionToken без newPassword.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1537")
    @Test

    public void unsuccessfulInstallationsNewPasswordWithoutNewPassword() {
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.changePasswordForUserUpdatedDatabaseWithoutNewPassword(getTokenResponse.jsonPath().get("sessionToken"));

        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @DisplayName("Обязательность ключей в теле запроса при установлении нового пароля")
    @Description("Тест направлен на проверку обязательности ключей в теле запроса c пустыми значениями.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1537")
    @ParameterizedTest(name = "sessionToke: {0}, password: {1}")
    @CsvSource({
            ","
    })

    public void unsuccessfulInstallationsNewPasswordWithoutBodyIsNull(String sessionToke, String password) {
        Response response = userAccountService.changePasswordForUserUpdatedDatabase(sessionToke, password);
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @DisplayName("Обязательность ключей в теле запроса при установлении нового пароля")
    @Description("Тест направлен на проверку обязательности ключей в теле запроса без тела.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1537")
    @Test

    public void unsuccessfulInstallationsNewPasswordWithoutBody() {

        Response response = userAccountService.changePasswordForUserUpdatedDatabaseWithoutBody();
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB-1565")
    @DisplayName("Добавление нового пароля, если пароль у пользователя уже есть")
    @Description("Тест проверяет возможность добавить пароль, если у пользователя пароль уже существует и записан в БД")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1538")
    @Test

    public void unsuccessfulInstallationsNewPassword() {
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        userAccountService.changePasswordForUserUpdatedDatabase(getTokenResponse.jsonPath().get("sessionToken"), newPassword);
        Response response = userAccountService.changePasswordForUserUpdatedDatabase(getTokenResponse.jsonPath().get("sessionToken"), newPassword);

        assertAll(
                () -> assertEquals(SC_CONFLICT, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @Disabled("BUG https://jira.astondevs.ru/browse/LIB-1574")
    @DisplayName("Валидация URL при установлении нового пароля")
    @Description("Тест проверяет обработку запроса с невалидным URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1540")
    @Test

    public void unsuccessfulInstallationsNewPasswordInvalidURL() {
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        String hashOldPassword = UserAccountServiceDataBaseRequests.getHashPasswordInUserProfileTable(idCustomer);
        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.changePasswordForUserUpdatedDatabaseInvalidURL(getTokenResponse.jsonPath().get("sessionToken"), newPassword);

        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotEquals(newPassword, hashOldPassword,
                        "Новый пароль отличается от старого"));
    }

    @DisplayName("Валидация токена при установлении нового пароля")
    @Description("Тест проверяет валидацию sessionToken c одним изменненым символом.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1541")
    @Test

    public void checksSessionTokenValidationChange() {
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.changePasswordForUserUpdatedDatabase(getTokenResponse.jsonPath().get("sessionToken") + "2", newPassword);

        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @Disabled("BUG https://jira.astondevs.ru/browse/LIB-1573")
    @DisplayName("Валидация токена при установлении нового пароля")
    @Description("Тест проверяет валидацию sessionToken c пустым значениес.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1541")
    @Test

    public void checksSessionTokenValidationWithEmptyValue() {
        String sessionToken = " ";
        String newPassword = "NTk5NDQ3MWFiYjAxMTEyYWZjYzE4MTU5ZjZjYzc0YjRmNTExYjk5ODA2ZGE1OWIzY2FmNWE5YzE3M2NhY2ZjNQ==";
        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        Response response = userAccountService.changePasswordForUserUpdatedDatabase(sessionToken, newPassword);

        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB-1572")
    @DisplayName("Установление нового пароля")
    @Description("Проверка успешного установления нового пароля пользователю")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1535")
    @ParameterizedTest(name = "password: {0}")
    @ValueSource(
            strings = {"148435e56#%$@r8",
                    "b37cd97c75f08ee94949452504499724da61ab82153dae71872e58d2a6f0391c",
                    "MTQ4NDM1ZTU2IyUkQH",
                    "0a84c01be6e419470998ac5053c4bb573bb97ade7116681161d13aeae2975150"}
    )

    public void checksPasswordValidation(String newPassword) {

        userAccountService.checkVerificationCodeSuccessfulSaved(REGISTERED_PHONE_NUMBER);
        String idCustomer = UserAccountServiceDataBaseRequests.getIdCustomerByPhoneNumber(REGISTERED_PHONE_NUMBER);
        String verificationCode = UserAccountServiceDataBaseRequests.getVerificationCodeByIdCustomer(idCustomer);
        Response getTokenResponse = userAccountService.verificationMobilePhoneVerificationCode(REGISTERED_PHONE_NUMBER, verificationCode);
        UserAccountServiceDataBaseRequests.updatePasswordInUserProfileTableIsNull(idCustomer);
        Response response = userAccountService.changePasswordForUserUpdatedDatabase(getTokenResponse.jsonPath().get("sessionToken"), newPassword);

        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.getBody(), "Сообщение об ошибке отсутсвует")
        );
    }
}