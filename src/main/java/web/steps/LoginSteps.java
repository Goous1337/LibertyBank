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
    public LoginSteps enterPhone(String phoneNumber) {
        loginPage.enterPhone(phoneNumber);
        return this;
    }

    @Step("Нажать на поле логин")
    public void clickInputPhone() {
        loginPage.clickInputPhone();
    }

    @Step("Ввести валидный пароль")
    public LoginSteps enterPassword(String password) {
        loginPage.enterPassword(password);
        return this;
    }

    @Step("Нажать на поле пароль")
    public LoginSteps clickInputPassword() {
        loginPage.clickInputPassword();
        return this;
    }

    @Step("Выйти из поля номер телефона")
    public LoginSteps outFormPhone() {
        loginPage.outFormPhone();
        return this;
    }

    @Step("Выйти из поля пароль")
    public LoginSteps outFormPassword() {
        loginPage.outFormPassword();
        return this;
    }

    @Step("Нажать кнопку 'Вперед' и перейти на страницу main")
    public LoginSteps tapSubmitButtonToMain() {
        loginPage.submitToMainPage();
        return this;
    }

    @Step("Кликнуть по кнопке 'Вперед'")
    public LoginSteps clickSubmitButton() {
        loginPage.clickSubmitButton();
        return this;
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
    public LoginSteps assertAmountSymbolsPhoneInput(int amountSymbols) {
        softAssertions.assertThat(loginPage.getTextFromPhoneInput().length())
                .as("Проверка кол-ва символов поля \"Номер телефона\"")
                .isEqualTo(amountSymbols);
        return this;
    }

    @Step("Проверка кол-ва символов поля \"Пароль\"")
    public LoginSteps assertAmountSymbolsPasswordInput(int amountSymbols) {
        softAssertions.assertThat(loginPage.getTextFromPasswordInput().length())
                .as("Проверка кол-ва символов поля \"Пароль\"")
                .isEqualTo(amountSymbols);
        return this;
    }

    @Step("Проверка цвета поля \"Номер телефона\"")
    public LoginSteps assertColorPhoneInput(String colorPhoneInput) {
        softAssertions.assertThat(loginPage.getPhoneInputBorderColor())
                .as("Проверка цвета поля \"Номер телефона\"")
                .isEqualTo(colorPhoneInput);
        return this;
    }

    @Step("Проверка цвета поля \"Пароль\"")
    public LoginSteps assertColorPasswordInput(String colorPasswordInput) {
        softAssertions.assertThat(loginPage.getPasswordInputBorderColor())
                .as("Проверка цвета поля \"Пароль\"")
                .isEqualTo(colorPasswordInput);
        return this;
    }

    @Step("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
    public LoginSteps assertColorPlaceholderPhoneInput(String colorPlaceholderPhone) {
        softAssertions.assertThat(loginPage.getColorPlaceholderPhone())
                .as("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
                .isEqualTo(colorPlaceholderPhone);
        return this;
    }

    @Step("Проверка цвета плэйсхолдера поля \"Пароль\"")
    public LoginSteps assertColorPlaceholderPasswordInput(String colorPlaceholderPassword) {
        softAssertions.assertThat(loginPage.getColorPlaceholderPassword())
                .as("Проверка цвета плэйсхолдера поля \"Номер телефона\"")
                .isEqualTo(colorPlaceholderPassword);
        return this;
    }

    @Step("Проверка текста сообщения об ошибке поля \"Номер телфона\" при недостаточном кол-ве символов")
    public LoginSteps assertErrorPhoneHint(String textErrorPhone) {
        softAssertions.assertThat(loginPage.checkErrorPhoneHint())
                .as("Проверка сообщения об ошибке поля \"Номер телефона\" при недостаточном кол-ве символов")
                .isEqualTo(textErrorPhone);
        return this;
    }

    @Step("Проверка текста сообщения об ошибке поля \"Пароль\" при недостаточном кол-ве символов")
    public LoginSteps assertErrorPasswordHint(String textErrorPassword) {
        softAssertions.assertThat(loginPage.checkErrorPasswordHint())
                .as("Проверка сообщения об ошибке поля \"Пароль\" при недостаточном кол-ве символов")
                .isEqualTo(textErrorPassword);
        return this;
    }

    @Step
    public LoginSteps assertErrorPasswordHintIsDisplayed(boolean isVisible) {
        softAssertions.assertThat(loginPage.isErrorPasswordHintDisplayed())
                .as("Проверка наличия подсказки под полем \"Пароль\"")
                .isEqualTo(isVisible);
        return this;
    }

    @Step
    public LoginSteps assertErrorPhoneHintIsDisplayed(boolean isVisible) {
        softAssertions.assertThat(loginPage.isErrorPhoneHintDisplayed())
                .as("Проверка наличия подсказки под полем \"Номер телефона\"")
                .isEqualTo(isVisible);
        return this;
    }

    public void assertAllChecks() {
        softAssertions.assertAll();
    }
}
