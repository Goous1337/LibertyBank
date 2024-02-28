package web.steps;

import io.qameta.allure.Step;
import web.pages.ChangePasswordPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;

public class ChangePasswordSteps {
    protected ChangePasswordPage changePasswordPage;

    public ChangePasswordSteps() {
        changePasswordPage = new ChangePasswordPage();
    }

    @Step("Ввести старый пароль")
    public ChangePasswordPage setKeysToOldPasswordInput(String oldPassword) {
        return changePasswordPage.setKeysToOldPasswordInput(oldPassword);
    }

    @Step("Ввести новый пароль")
    public ChangePasswordPage setKeysToNewPasswordInput(String newPassword) {
        return changePasswordPage.setKeysToNewPasswordInput(newPassword);
    }

    @Step("Повторно ввести новыый пароль")
    public ChangePasswordPage setKeysToConfirmPasswordInput(String confirmPassword) {
        return changePasswordPage.setKeysToConfirmPasswordInput(confirmPassword);
    }

    @Step("Нажать на кнопку изменения пароля")
    public void clickSubmitPasswordChangeBtn() {
        changePasswordPage.clickSubmitPasswordChangeBtn();
    }

    @Step("Проверить результат изменения пароля")
    public boolean isSuccessResult() {
        return changePasswordPage.isSuccessResult();
    }

    @Step("Кликнуть на кнопку 'Отмена'.")
    public void clickCancelPasswordChangeBtn() {
        changePasswordPage.clickCancelPasswordChangeBtn();
    }

    @Step("Отображается надпись 'Изменить пароль'")
    public boolean isChangePasswordPresent() {
        return changePasswordPage.isChangePasswordPresent();
    }

    @Step("Статус изменения пароля соотвествует статусу 'success'")
    public void assertChangePasswordStatusIsSuccess() {
        assertTrue(isSuccessResult(), String.format(DISPLAYED_MESSAGE, "Положительныый результат"));
    }

    @Step("Проверка отображения кнопки 'Изменить пароль'")
    public void assertChangePasswordBtnIsPresent() {
        assertTrue(isChangePasswordPresent(), String.format(DISPLAYED_MESSAGE,
                "Отображается кнопка 'Изменить пароль'"));
    }
}
