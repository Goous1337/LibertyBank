package web.steps;

import io.qameta.allure.Step;
import web.pages.ChangePasswordPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static property.UserPropertiesReader.USER_PASSWORD;
import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;

public class ChangePasswordSteps {
    protected ChangePasswordPage changePasswordPage;

    public ChangePasswordSteps() {
        changePasswordPage = new ChangePasswordPage();
    }

    @Step("Ввести старый пароль")
    public ChangePasswordSteps setKeysToOldPasswordInput(String oldPassword) {
        changePasswordPage.setKeysToOldPasswordInput(oldPassword);
        return this;
    }

    @Step("Ввести новый пароль")
    public ChangePasswordSteps setKeysToNewPasswordInput(String newPassword) {
        changePasswordPage.setKeysToNewPasswordInput(newPassword);
        return this;
    }

    @Step("Повторно ввести новыый пароль")
    public ChangePasswordSteps setKeysToConfirmPasswordInput(String confirmPassword) {
        changePasswordPage.setKeysToConfirmPasswordInput(confirmPassword);
        return this;
    }

    @Step("Нажать на кнопку изменения пароля")
    public void clickSubmitPasswordChangeBtn() {
        changePasswordPage.clickSubmitPasswordChangeBtn();
    }

    @Step("Кликнуть на кнопку 'Отмена'.")
    public void clickCancelPasswordChangeBtn() {
        changePasswordPage.clickCancelPasswordChangeBtn();
    }

    @Step("Отображается надпись 'Изменить пароль'")
    public boolean isChangePasswordPresent() {
        return changePasswordPage.isChangePasswordPresent();
    }

    @Step("Проверка отображения кнопки 'Изменить пароль'")
    public void assertChangePasswordBtnIsPresent() {
        assertTrue(isChangePasswordPresent(), String.format(NOT_DISPLAYED_MESSAGE,
                "кнопка 'Изменить пароль'"));
    }

    @Step("Сброс нового пароля")
    public void resetPasswordForUser(String oldPassword) {
        setKeysToOldPasswordInput(oldPassword)
                .setKeysToNewPasswordInput(USER_PASSWORD)
                .setKeysToConfirmPasswordInput(USER_PASSWORD)
                .clickSubmitPasswordChangeBtn();
    }
}
