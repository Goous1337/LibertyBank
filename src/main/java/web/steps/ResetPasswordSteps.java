package web.steps;

import io.qameta.allure.Step;
import web.pages.ResetPasswordPage;

public class ResetPasswordSteps {
    protected ResetPasswordPage resetPasswordPage;

    public ResetPasswordSteps() {
        resetPasswordPage = new ResetPasswordPage();
    }

    public void clickForgotPassword() {
        resetPasswordPage.clickForgotPassword();
    }

    @Step("Ввести валидный номер телефона")
    public void enterPhone(String phoneNumber) {
        resetPasswordPage.enterPhone(phoneNumber);
    }

    @Step("Ввщд и Подверждение валидного пароля")
    public void enterNewPassword(String password) {
        resetPasswordPage.enterNewPassword(password);
    }

    @Step("Ввести код из СМС")
    public void enterVerificationCode(String verificationCode) {
        resetPasswordPage.enterVerificationCode(verificationCode);
    }

    @Step("Кликнуть по кнопке 'Вперед'")
    public void clickSubmitButton() {
        resetPasswordPage.clickSubmitButton();
    }

}

