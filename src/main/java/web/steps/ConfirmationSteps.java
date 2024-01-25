package web.steps;

import io.qameta.allure.Step;
import web.pages.ConfirmationPage;

public class ConfirmationSteps {

    protected ConfirmationPage confirmationPage;

    public ConfirmationSteps() {
        confirmationPage = new ConfirmationPage();
    }

    @Step("Отображается диалоговое окно для подтверждения")
    public boolean isSetMainAccountDialogBoxDisplayed() {
        return confirmationPage.isSetMainAccountDialogBoxDisplayed();
    }

    @Step("Отображается диалоговое окно об успешности")
    public boolean isSuccessDialogBoxDisplayed() {
        return confirmationPage.isSuccessSetMainAccountDialogBoxDisplayed();
    }

    @Step("Вернуться к счету")
    public void returnToAccount() {
        confirmationPage.clickReturnToAccountButton();
    }

    @Step("Отображается диалоговое окно для подтверждения блокирования")
    public boolean isBlockAccountDialogBoxDisplayed() {
        return confirmationPage.isBlockAccountDialogBoxDisplayed();
    }

    @Step("Подтвердить")
    public void accept() {
        confirmationPage.clickAcceptButton();
    }

    @Step("Отменить")
    public void deny() {
        confirmationPage.clickDenyButton();
    }
}
