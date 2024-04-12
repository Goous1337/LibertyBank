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

    @DisplayName("Проверка валидации полей формы авторизации при использовании невалидных данных")
    @Description("Проверить валидацию поля \"Номер телефона\" по количеству символов с недостаточным количеством символов")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Negative")})
    @TmsLink("LIB-2429")
    @ParameterizedTest
    @CsvSource({"7912123456, 16, rgb(245, 60, 20), Номер телефона должен содержать 11 цифр"})
    public void checkValidationPhoneInput(String phoneNumber, int amountSymbols, String inputPhoneColor, String textErrorPhone) {
        loginSteps.enterPhone(phoneNumber);
        loginSteps.clickSubmitButton();
        loginSteps.assertAmountSymbolsPhoneInput(amountSymbols);
        loginSteps.assertColorPhoneInput(inputPhoneColor);
        loginSteps.assertErrorPhoneInput(textErrorPhone);
        loginSteps.assertAllChecks();
    }
}
