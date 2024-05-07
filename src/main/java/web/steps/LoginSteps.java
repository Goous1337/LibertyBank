package web.steps;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import web.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.INVALID_COLOR;
import static web.constans.AccountServiceConstants.INVALID_TEXT_IN_ELEMENT;

public class LoginSteps {

    protected LoginPage loginPage;

    SoftAssertions softAssertions = new SoftAssertions();

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    public void clearAssertions() {
        softAssertions = new SoftAssertions();
    }

    @Step("Ввести валидный номер телефона")
    public void enterPhone(String phoneNumber) {
        loginPage.enterPhone(phoneNumber);
    }

    @Step("Нажать на поле логин")
    public void clickInputPhone() {
        loginPage.clickInputPhone();
    }

    @Step("Ввести валидный пароль")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("Нажать на поле пароль")
    public void clickInputPassword() {
        loginPage.clickInputPassword();
    }

    @Step("Выйти из поля номер телефона")
    public void outFormPhone() {
        loginPage.outFormPhone();
    }

    @Step("Выйти из поля пароль")
    public void outFormPassword() {
        loginPage.outFormPassword();
    }

    @Step("Нажать кнопку 'Вперед' и перейти на страницу main")
    public void tapSubmitButtonToMain() {
        loginPage.submitToMainPage();
    }

    @Step("Кликнуть по кнопке 'Вперед'")
    public void clickSubmitButton() {
        loginPage.clickSubmitButton();
    }

    @Step("Проверка кнопки и поля ввода на правильных значениях")
    public void assertSubmitButtonAndInputSuccessful(
            String bgButtonColor, String textButtonColor, String inputColor) {
        assertAll(
                () -> assertTrue(loginPage.checkButtonCondition(bgButtonColor, textButtonColor, true),
                        INVALID_COLOR),
                () -> assertTrue(loginPage.isValidInput(inputColor), INVALID_COLOR)
        );
    }

    @Step("Проверка кнопки и поля ввода при неправильных значениях")
    public void assertSubmitButtonAndInputInvalid(
            String bgButtonColor, String textButtonColor, String inputColor) {
        assertAll(
                () -> assertTrue(loginPage.checkButtonCondition(bgButtonColor, textButtonColor, false),
                        INVALID_COLOR),
                () -> assertTrue(loginPage.isValidInput(inputColor), INVALID_COLOR),
                () -> assertTrue(loginPage.checkErrorHint("Неверный пароль или номер телефона"),
                        INVALID_TEXT_IN_ELEMENT)
        );
    }

    @Step("Проверка кол-ва символов поля \"Номер телефона\"")
    public void assertAmountSymbolsPhoneInput(int amountSymbols) {
        softAssertions.assertThat(loginPage.getTextFromPhoneInput().length())
                .as("Проверка кол-ва символов поля \"Номер телефона\"")
                .isEqualTo(amountSymbols);
    }

    @Step("Проверка кол-ва символов поля \"Пароль\"")
    public void assertAmountSymbolsPasswordInput(int amountSymbols) {
        softAssertions.assertThat(loginPage.getTextFromPasswordInput().length())
                .as("Проверка кол-ва символов поля \"Пароль\"")
                .isEqualTo(amountSymbols);
    }

    @Step("Проверка цвета поля \"Номер телефона\"")
    public void assertColorPhoneInput(String colorPhoneInput) {
        softAssertions.assertThat(loginPage.getPhoneInputBorderColor())
                .as("Проверка цвета поля \"Номер телефона\"")
                .isEqualTo(colorPhoneInput);
    }

    @Step("Проверка цвета поля \"Пароль\"")
    public void assertColorPasswordInput(String colorPasswordInput) {
        softAssertions.assertThat(loginPage.getPasswordInputBorderColor())
                .as("Проверка цвета поля \"Пароль\"")
                .isEqualTo(colorPasswordInput);
    }

    @Step("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
    public void assertColorPlaceholderPhoneInput(String colorPlaceholderPhone) {
        softAssertions.assertThat(loginPage.getColorPlaceholderPhone())
                .as("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
                .isEqualTo(colorPlaceholderPhone);
    }

    @Step("Проверка цвета плэйсхолдера поля \"Пароль\"")
    public void assertColorPlaceholderPasswordInput(String colorPlaceholderPassword) {
        softAssertions.assertThat(loginPage.getColorPlaceholderPassword())
                .as("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
                .isEqualTo(colorPlaceholderPassword);
    }

    @Step("Проверка текста сообщения об ошибке поля \"Пароль\" при недостаточном кол-ве символов")
    public void assertErrorMessage(String textErrorPassword) {
        softAssertions.assertThat(loginPage.checkErrorMessage())
                .as("Проверка сообщения об ошибке поля \"Пароль\" при недостаточном кол-ве символов")
                .isEqualTo(textErrorPassword);
    }

    public void assertAllChecks() {
        softAssertions.assertAll();
    }
}
