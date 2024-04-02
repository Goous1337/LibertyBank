package web.steps;

import io.qameta.allure.Step;
import web.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.INVALID_COLOR;
import static web.constans.AccountServiceConstants.INVALID_TEXT_IN_ELEMENT;

public class LoginSteps {

    protected LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    @Step("Ввести валидный номер телефона")
    public void enterPhone(String phoneNumber) {
        loginPage.enterPhone(phoneNumber);
    }

    @Step("Ввести валидный пароль")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
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
}
