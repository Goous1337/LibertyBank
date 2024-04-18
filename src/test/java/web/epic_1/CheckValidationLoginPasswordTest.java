package web.epic_1;

import java.util.stream.Stream;

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
        loginSteps.enterPhone(USER_PHONE);
        loginSteps.enterPassword(password);
        loginSteps.assertSubmitButtonAndInputSuccessful(
                "rgba(0, 90, 254, 1)",
                "rgba(245, 245, 245, 1)",
                "rgb(117, 127, 138)");
    }

    @DisplayName("Проверка авторизации незарегистрированного пользователя по номеру телефона")
    @Description("Проверка соответствия введенного номера телефона и пароля при прохождении авторизации")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2435")
    @ParameterizedTest
    @CsvSource({"71111111111, Login-1", "79228134511, Login-107543"})
    public void checkAuthUnregisteredUserTest() {
        loginSteps.enterPhone("71111111111");
        loginSteps.enterPassword(USER_PASSWORD);
        loginSteps.clickSubmitButton();
        loginSteps.assertSubmitButtonAndInputInvalid(
                "rgba(216, 223, 234, 1)",
                "rgba(77, 95, 113, 1)",
                "rgb(245, 60, 20)");
    }

    static Stream<Object[]> provideTestDataForPhoneNumber() {
        return Stream.of(
                new Object[]{"", 0, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)", ""},
                new Object[]{"7912123452", 16, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Номер телефона должен содержать 11 цифр"},
                new Object[]{"791212345333", 17, "rgb(243, 244, 248)", "rgba(77, 95, 113, 1)", ""},
                new Object[]{"aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ", 4,
                        "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)", "Номер телефона должен содержать 11 цифр"},
                new Object[]{"!'(%)*$+,#-./:\";<=>?@[]^_`{|}~[]", 4, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Номер телефона должен содержать 11 цифр"},
                new Object[]{" 7 9 1 2 1 2 3 4 5 6 7 ", 17, "rgb(243, 244, 248)", "rgba(77, 95, 113, 1)", ""}
        );
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Номер телефона\"")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("provideTestDataForPhoneNumber")
    public void checkValidationPhoneInput(
            String phoneNumber, int amountSymbols, String colorPhoneInput, String colorPlaceholderPhone,
            String textErrorPhone)
            throws InterruptedException {

        loginSteps.enterPhone(phoneNumber);
        loginSteps.clickSubmitButton();
        Thread.sleep(1000);
        loginSteps.assertAmountSymbolsPhoneInput(amountSymbols);
        loginSteps.assertColorPhoneInput(colorPhoneInput);
        loginSteps.assertColorPlaceholderPhoneInput(colorPlaceholderPhone);
        if (!textErrorPhone.isEmpty()) {
            loginSteps.assertErrorPhoneInput(textErrorPhone);
        }
        loginSteps.assertAllChecks();
    }

    static Stream<Object[]> provideTestDataForPasswordAmountSymbols() {
        return Stream.of(
                new Object[]{"", 0, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Пароль должен содержать от 6 до 20 символов"},
                new Object[]{"1Aa2@", 5, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Пароль должен содержать от 6 до 20 символов"},
                new Object[]{"1234567890AaAaAaAa@#", 20, "rgb(243, 244, 248)",
                        "rgba(77, 95, 113, 1)", ""}
        );
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Пароль\" при недостаточном кол-ве символов, шаги 7-9")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("provideTestDataForPasswordAmountSymbols")
    public void checkValidationPasswordInputAmountSymbols(
            String password, int amountSymbols, String inputPasswordColor, String colorPlaceholderPassword,
            String textErrorPassword) throws InterruptedException {

        loginSteps.enterPassword(password);
        loginSteps.clickSubmitButton();
        Thread.sleep(1000);
        loginSteps.assertAmountSymbolsPasswordInput(amountSymbols);
        loginSteps.assertColorPasswordInput(inputPasswordColor);
        loginSteps.assertColorPlaceholderPasswordInput(colorPlaceholderPassword);
        if (!textErrorPassword.isEmpty()) {
            loginSteps.assertErrorPasswordInput(textErrorPassword);
        }
        loginSteps.assertAllChecks();
    }

    static Stream<Object[]> provideTestDataForPasswordInvalidSymbols() {
        return Stream.of(
                new Object[]{" 789DFs%", 8, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"4321HgT&* ", 10, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"14 75E ea!$", 11, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"1928AZ!@", 8, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"1987pea&", 8, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"!@#$%AZds&*(", 12, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"2024QWEasd123", 13, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"Привет2020№;%*", 14, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"},
                new Object[]{"0192837", 7, "rgb(245, 60, 20)", "rgba(245, 60, 20, 1)",
                        "Неверный пароль или номер телефона"}
        );
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Пароль\" при невалидных значениях, шаги 10-18")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("provideTestDataForPasswordInvalidSymbols")
    public void checkValidationPasswordInputInvalidSymbols(
            String password, int amountSymbols, String inputPasswordColor, String colorPlaceholderPassword,
            String textErrorPassword) throws InterruptedException {

        loginSteps.enterPassword(password);
        loginSteps.clickSubmitButton();
        Thread.sleep(1000);
        loginSteps.assertAmountSymbolsPasswordInput(amountSymbols);
        loginSteps.assertColorPasswordInput(inputPasswordColor);
        loginSteps.assertColorPlaceholderPasswordInput(colorPlaceholderPassword);
        if (!textErrorPassword.isEmpty()) {
            loginSteps.assertErrorPasswordInputInvalidSymbols(textErrorPassword);
        }
        loginSteps.assertAllChecks();
    }

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
                "rgb(117, 127, 138)");
        loginSteps.clickSubmitButton();
        homeSteps.clickUserMenu();
        homeSteps.assertIsUserPanelDisplayed();
    }
}