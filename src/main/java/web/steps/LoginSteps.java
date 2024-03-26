package web.steps;

import io.qameta.allure.Step;
import web.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.INVALID_COLOR;

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

    @Step("Нажать кнопку 'Вперед'")
    public void tapSubmitButton() {
        loginPage.submit();
    }

    @Step("Проверка изменения цвета поля ввода и кнопки")
    public void assertSubmitButtonEnabled(String bgColor, String inputColor) {
        assertAll(
                () -> assertTrue(loginPage.isActiveSubmitButton(bgColor), INVALID_COLOR),
                () -> assertTrue(loginPage.isSuccessInput(inputColor), INVALID_COLOR)
        );
    }
}
