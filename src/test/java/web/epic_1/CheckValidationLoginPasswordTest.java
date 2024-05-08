package web.epic_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import web.BaseTest;

import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;

@Epic("Epic -1 Регистрация/Авторизация/Безопасность")
@DisplayName("US-1.2 Авторизация")
public class CheckValidationLoginPasswordTest extends BaseTest {
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
    @Description("Проверить валидацию поля \"Номер телефона\"")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("dataProviders.gui.AuthorizationDataProviders#provideTestDataForPhoneNumber")
    public void checkValidationPhoneInput(
            String phoneNumber, int amountSymbols, String colorPhoneInput, String colorPlaceholderPhone,
            String textErrorMessage, boolean isVisible)
            throws InterruptedException {

        loginSteps.enterPhone(phoneNumber)
                .outFormPhone();
        Thread.sleep(1000);
        loginSteps.assertAmountSymbolsPhoneInput(amountSymbols)
                .assertColorPhoneInput(colorPhoneInput)
                .assertColorPlaceholderPhoneInput(colorPlaceholderPhone);
        if (!textErrorMessage.isEmpty()) {
            loginSteps.assertErrorPhoneHint(textErrorMessage);
        } else {
            loginSteps.assertErrorPhoneHintIsDisplayed(isVisible);
        }
        loginSteps.assertAllChecks();
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Пароль\" при недостаточном кол-ве символов, шаги 7-9")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("dataProviders.gui.AuthorizationDataProviders#provideTestDataForPasswordAmountSymbols")
    public void checkValidationPasswordInputAmountSymbols(
            String password, int amountSymbols, String inputPasswordColor, String colorPlaceholderPassword,
            String textErrorMessage, boolean isVisible) throws InterruptedException {

        loginSteps.enterPassword(password).outFormPassword();
        Thread.sleep(1000);
        loginSteps.assertAmountSymbolsPasswordInput(amountSymbols)
                .assertColorPasswordInput(inputPasswordColor)
                .assertColorPlaceholderPasswordInput(colorPlaceholderPassword);
        if (!textErrorMessage.isEmpty()) {
            loginSteps.assertErrorPasswordHint(textErrorMessage);
        } else {
            loginSteps.assertErrorPasswordHintIsDisplayed(isVisible);
        }
        loginSteps.assertAllChecks();
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Пароль\" при невалидных значениях, шаги 10-18")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("dataProviders.gui.AuthorizationDataProviders#provideTestDataForPasswordInvalidSymbols")
    public void checkValidationPasswordInputInvalidSymbols(
            String password, int amountSymbols, String inputPasswordColor, String colorPlaceholderPassword,
            String textErrorMessage) throws InterruptedException {

        loginSteps.enterPassword(password)
                .outFormPassword();
        Thread.sleep(1000);
        loginSteps.assertAmountSymbolsPasswordInput(amountSymbols)
                .assertColorPasswordInput(inputPasswordColor)
                .assertColorPlaceholderPasswordInput(colorPlaceholderPassword);
        if (!textErrorMessage.isEmpty()) {
            loginSteps.assertErrorPasswordHint(textErrorMessage);
        }
        loginSteps.assertAllChecks();
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
}