package web.steps;

import io.qameta.allure.Step;
import web.pages.LoginPage;

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
}
