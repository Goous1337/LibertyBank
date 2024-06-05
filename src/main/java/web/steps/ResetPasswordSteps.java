package web.steps;

import io.qameta.allure.Step;
import web.pages.ResetPasswordPage;

public class ResetPasswordSteps {
    protected ResetPasswordPage resetPasswordPage;

    public ResetPasswordSteps() {
        resetPasswordPage = new ResetPasswordPage();
    }

    public ResetPasswordSteps clickForgotPassword() {
        resetPasswordPage.clickForgotPassword();
        return this;
    }

    @Step("Ввести валидный номер телефона")
    public ResetPasswordSteps enterPhone(String phoneNumber) {
        resetPasswordPage.enterPhone(phoneNumber);
        return this;
    }

    @Step("Ввод и Подверждение валидного пароля")
    public ResetPasswordSteps enterNewPassword(String password) {
        resetPasswordPage.enterNewPassword(password);
        return this;
    }

    @Step("Ввести код из СМС")
    public ResetPasswordSteps enterVerificationCode(String verificationCode) {
        resetPasswordPage.enterVerificationCode(verificationCode);
        return this;
    }

    @Step("Кликнуть по кнопке 'Вперед'")
    public void clickSubmitButton() {
        resetPasswordPage.clickSubmitButton();
    }
}

