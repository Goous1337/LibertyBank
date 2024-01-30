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

    @Step("Отображается диалоговое окно об успешности переименования")
    public boolean isSuccessRenameAccountNameDialogBoxDisplayed() {
        return confirmationPage.isSuccessRenameAccountNameDialogBoxDisplayed();
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

    public boolean isCloseVerificationMessageDisplayed() {
        return confirmationPage.isCloseVerificationMessageDisplayed();
    }

    public boolean isCloseSuccessfullyMessageDisplayed() {
        return confirmationPage.isCloseSuccessfullyMessageDisplayed();
    }

    @Step("Нажать кнопку Вернуться к счетам")
    public void clickNavigateToAccountsPageButton() {
        confirmationPage.clickNavigateToAccountsPageButton();
    }

}
