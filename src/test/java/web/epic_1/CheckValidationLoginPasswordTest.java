package web.epic_1;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import web.BaseTest;

import static property.UserPropertiesReader.USER_PASSWORD;
import static property.UserPropertiesReader.USER_PHONE;

@Epic("Epic -1 Регистрация/Авторизация/Безопасность")
@DisplayName("US-1.2 Авторизация")
public class CheckValidationLoginPasswordTest extends BaseTest {
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