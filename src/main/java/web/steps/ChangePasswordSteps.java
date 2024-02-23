package web.steps;

import io.qameta.allure.Step;
import web.pages.ChangePasswordPage;

public class ChangePasswordSteps {
    protected ChangePasswordPage changePasswordPage;

    public ChangePasswordSteps() {
        changePasswordPage = new ChangePasswordPage();
    }

    @Step("Кликнуть на кнопку 'Отмена'.")
    public void clickChangePasswordBtn() {
        changePasswordPage.clickCancelPasswordChangeBtn();
    }

    @Step("Отображается надпись 'Изменить пароль'")
    public boolean isChangePasswordPresent() {
        return changePasswordPage.isChangePasswordPresent();
    }
}
