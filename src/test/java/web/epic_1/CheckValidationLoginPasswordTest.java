package web.epic_1;

import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.customerService_2_0.ChangeUserAccountPasswordByPhone;
import preconditions.UserAuthorization;
import service.CustomerService_2_0;
import web.BaseTest;

import java.security.NoSuchAlgorithmException;

import static api.utils.PasswordEncoder.encryptToSHA256ToBase64;
import static constant.CustomerService_2_0_Constants.CUSTOMER_MOBILE_PHONE_TYPE;
import static property.BaseProperties.CUSTOMER_SERVICE_2_0;
import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;

@Epic("Epic -1 Регистрация/Авторизация/Безопасность")
@DisplayName("US-1.2 Авторизация")
public class CheckValidationLoginPasswordTest extends BaseTest {
    private String newPassword = USER_PASSWORD;

    @BeforeEach
    public void setUpTest() {
        open("");
    }



    @DisplayName("Основной сценарий: проверка валидации полей формы авторизации")
    @Description("Данный тест-кейс проверяет валидацию полей \"Номер телефона\", \"Пароль\" при вводе валидных данных")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2554")
    @ParameterizedTest
    @ValueSource(strings = {"A!123a", "!\"#$%&'()*+,-./:;Zz0", "<=>?@[]^_`{|}~8qA"})
    public void checkValidationLoginPasswordTest(String password) {
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(password);
        loginSteps.outFormPassword();
        loginSteps.assertSubmitButtonAndInputSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)",
                "rgb(77, 95, 113)");
    }

    @DisplayName("Проверка авторизации незарегистрированного пользователя по номеру телефона")
    @Description("Проверка соответствия введенного номера телефона и пароля при прохождении авторизации")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2435")
    @ParameterizedTest
    @CsvSource({"71111111111, Login-1", "79228134511, Login-107543"})
    public void checkAuthUnregisteredUserTest(String phone, String password) {
        loginSteps.enterPhone(phone);
        loginSteps.enterPassword(password);
        loginSteps.clickSubmitButton();
        loginSteps.assertSubmitButtonAndInputInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)",
                "rgb(245, 60, 20)");
    }

    @Disabled
    @DisplayName("US-1.2.1 Авторизация по номеру телефона (первичный вход)")
    @Description("Авторизоваться в личном кабинете с валидными значениями телефона и пароля")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2432")
    @Test
    public void checkValidationAuthTest() {
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(USER_PASSWORD);
        loginSteps.assertSubmitButtonAndInputSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)",
                "rgb(0, 26, 52)");
        loginSteps.clickSubmitButton();
        homeSteps.clickUserMenu();
        homeSteps.assertIsUserPanelDisplayed();
    }

    @DisplayName("US-1.2.5 Восстановление пароля")
    @Description("Проверка восстановления пароля по номеру телефона через веб-сайт")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2494")
    @Test
        public void forgotPassword() {
        newPassword = USER_PASSWORD + '1';
        resetPasswordSteps.clickForgotPassword();
        resetPasswordSteps.enterPhone(USER_PHONE);
        resetPasswordSteps.clickSubmitButton();
        String verificationCodeRequest = CustomerService_2_0_DataBaseRequest
                                        .getLastVerificationCodeByMobilePhone(USER_PHONE);
        resetPasswordSteps.enterVerificationCode(verificationCodeRequest);
        resetPasswordSteps.clickSubmitButton();
        resetPasswordSteps.enterNewPassword(newPassword);
        resetPasswordSteps.clickSubmitButton();
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(newPassword);
        loginSteps.assertSubmitButtonAndInputSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)",
                "rgb(0, 26, 52)");
        loginSteps.clickSubmitButton();
        homeSteps.clickUserMenu();
        homeSteps.assertIsUserPanelDisplayed();
    }

    @AfterEach
    public void tearDownTest() throws NoSuchAlgorithmException {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
        if (!newPassword.equals(USER_PASSWORD)) {
            String token = new UserAuthorization()
                    .getRefreshToken(USER_PHONE, encryptToSHA256ToBase64(newPassword), CUSTOMER_MOBILE_PHONE_TYPE);

            new CustomerService_2_0().checkListAbilityChangePasswordInPersonalAccount
                    (new ChangeUserAccountPasswordByPhone(encryptToSHA256ToBase64(newPassword),
                            encryptToSHA256ToBase64(USER_PASSWORD)), token);
            newPassword = USER_PASSWORD;
        }
    }
}