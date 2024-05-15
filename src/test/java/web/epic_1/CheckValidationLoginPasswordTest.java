package web.epic_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import dataBase.requests.CustomerService_2_0_DataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
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
        loginSteps.clearAssertions();
    }

    @DisplayName("Основной сценарий: проверка валидации полей формы авторизации")
    @Description("Данный тест-кейс проверяет валидацию полей \"Номер телефона\", \"Пароль\" при вводе валидных данных")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2554")
    @ParameterizedTest
    @ValueSource(strings = {"A!123a", "!\"#$%&'()*+,-./:;Zz0", "<=>?@[]^_`{|}~8qA"})
    public void checkValidationLoginPasswordTest(String password) {
        loginSteps.clickInputPhone();
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.clickInputPassword();
        loginSteps.enterPassword(password);
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
        loginSteps.clickInputPhone();
        loginSteps.enterPhone(phone);
        loginSteps.clickInputPassword();
        loginSteps.enterPassword(password);
        loginSteps.clickSubmitButton();
        loginSteps.assertSubmitButtonAndInputInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)",
                "rgb(245, 60, 20)");
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию полей \"Номер телефона\" и \"Пароль\"")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("dataProviders.gui.AuthorizationDataProviders#provideTestDataForPhoneNumberAndPassword")
    public void checkValidationPhoneInput(
            String phoneNumber, String password,
            int amountSymbolsPhone, int amountSymbolsPassword,
            String colorPhoneInput, String colorPasswordInput,
            String colorPlaceholderPhone, String colorPlaceholderPassword,
            String textErrorMessagePhone, String textErrorMessagePassword,
            boolean isErrorPhoneHintNotVisible, boolean isErrorPasswordHintNotVisible)
            throws InterruptedException {

        loginSteps.enterPhone(phoneNumber)
                .outFormPhone()
                .assertPhoneInput(amountSymbolsPhone, colorPhoneInput, colorPlaceholderPhone,
                                    textErrorMessagePhone, isErrorPhoneHintNotVisible)
                .enterPassword(password)
                .outFormPassword()
                .assertPasswordInput(amountSymbolsPassword, colorPasswordInput, colorPlaceholderPassword,
                        textErrorMessagePassword, isErrorPasswordHintNotVisible)
                .assertAllChecks();
    }

    @DisplayName("US-1.2.1 Авторизация по номеру телефона (первичный вход)")
    @Description("Авторизоваться в личном кабинете с валидными значениями телефона и пароля")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @TmsLink("LIB-2432")
    @Test
    public void checkValidationAuthTest() {
        loginSteps.clickInputPhone();
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.clickInputPassword();
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
        resetPasswordSteps.clickForgotPassword()
                .enterPhone(USER_PHONE)
                .clickSubmitButton();
        String verificationCodeRequest = CustomerService_2_0_DataBaseRequest
                                        .getLastVerificationCodeByMobilePhone(USER_PHONE);
        resetPasswordSteps.enterVerificationCode(verificationCodeRequest)
                .clickSubmitButton();
        resetPasswordSteps.enterNewPassword(newPassword)
                .clickSubmitButton();
        loginSteps.enterPhone(USER_PHONE)
                .enterPassword(newPassword)
                .assertSubmitButtonAndInputSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)",
                "rgb(0, 26, 52)")
                .clickSubmitButton();
        homeSteps.clickUserMenu()
                .assertIsUserPanelDisplayed();
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