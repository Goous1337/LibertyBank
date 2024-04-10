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
import web.pages.LoginPage;

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

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Номер телефона\" по количеству символов с недостаточным количеством символов")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @Test
    public void checkValidationTenSymbolsPhoneInput() {
        loginSteps.enterPhone("7912212122");
        loginSteps.clickSubmitButton();
        loginSteps.assertAmountSymbolsPhoneInput(16);
        loginSteps.assertColorPhoneInput("rgb(245, 60, 20)");
        loginSteps.assertErrorPhoneInput("Номер телефона должен содержать 11 цифр");
        loginSteps.assertAllChecks();
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
    public void checkValidationPhoneInput(String phoneNumber, int amountSymbols, String colorPhoneInput,
                                          String colorPlaceholderPhone, String textErrorPhone) throws InterruptedException {

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

    static Stream<Object[]> provideTestDataForPassword() {
        return Stream.of(
                new Object[]{"пустой"},
                new Object[]{"5 символов"},
                new Object[]{"21 символ"},
                new Object[]{"пробел в начале"},
                new Object[]{"пробел в конце"},
                new Object[]{"пробел внутри"},
                new Object[]{"цифры, спецсимволы, только заглавные буквы"},
                new Object[]{"цифры, спецсимволы, только строчные буквы"},
                new Object[]{"спецсимволы, строчные и заглавные буквы"},
                new Object[]{"цифры, строчные и заглавные буквы"},
                new Object[]{"кирилица"},
                new Object[]{"только цифры 000000"}
        );
    }

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Пароль\"")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @MethodSource("provideTestDataForPassword")
    public void checkValidationPasswordInput(
            String password, int amountSymbols, String inputPasswordColor, String textErrorPassword) {
        loginSteps.enterPhone(password);
        if (!password.isEmpty()) {
            loginSteps.clickSubmitButton();
        }
        loginSteps.assertAmountSymbolsPhoneInput(amountSymbols);
        loginSteps.assertColorPhoneInput(inputPasswordColor);
        if (!textErrorPassword.isEmpty()) {
            loginSteps.assertErrorPhoneInput(textErrorPassword);
        }
        loginSteps.assertAllChecks();
    }
}
