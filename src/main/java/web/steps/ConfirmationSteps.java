package web.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.helpers.TestListener;
import web.pages.ConfirmationPage;

import static web.constans.AccountServiceConstants.NOT_DISPLAYED_MESSAGE;

public class ConfirmationSteps {

    protected ConfirmationPage confirmationPage;

    public ConfirmationSteps() {
        confirmationPage = new ConfirmationPage();
    }

    @Step("Отображается диалоговое окно для подтверждения")
    public void assertSetMainAccountDialogBoxIsDisplayed() {
        Assertions.assertTrue(confirmationPage.isSetMainAccountDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается диалоговое окно об успешности")
    public void assertSuccessDialogBoxIsDisplayed() {
        Assertions.assertTrue(confirmationPage.isSuccessSetMainAccountDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается диалоговое окно об успешности переименования")
    public void assertSuccessRenameAccountNameDialogBoxIsDisplayed() {
        Assertions.assertTrue(confirmationPage.isSuccessRenameAccountNameDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        TestListener.takeScreenshot();
    }

    @Step("Вернуться к счету")
    public void returnToAccount() {
        confirmationPage.clickReturnToAccountButton();
    }

    @Step("Отображается диалоговое окно для подтверждения блокирования")
    public void assertBlockAccountDialogBoxIsDisplayed() {
        Assertions.assertTrue(confirmationPage.isBlockAccountDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        TestListener.takeScreenshot();
    }

    @Step("Подтвердить")
    public void accept() {
        confirmationPage.clickAcceptButton();
    }

    @Step("Отменить")
    public void deny() {
        confirmationPage.clickDenyButton();
    }

    @Step("Отображается сообщение об подтверждении закрытия счета")
    public void assertCloseVerificationMessageIsDisplayed() {
        Assertions.assertTrue(confirmationPage.isCloseVerificationMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение о подтверждении закрытия счета"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается сообщение об успешном закрытии счета")
    public void assertCloseSuccessfullyMessageDisplayed() {
        Assertions.assertTrue(confirmationPage.isCloseSuccessfullyMessageDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Сообщение об успешном закрытии счета"));
        TestListener.takeScreenshot();
    }

    @Step("Нажать кнопку Вернуться к счетам")
    public void clickNavigateToAccountsPageButton() {
        confirmationPage.clickNavigateToAccountsPageButton();
    }

    @Step("Отображается диалоговое окно с подтверждением блокировки карты")
    public void assertBlockCardDialogBoxDisplayed() {
        Assertions.assertTrue(confirmationPage.isBlockCardDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается диалоговое окно с подтверждением закрытия карты")
    public void assertCloseCardDialogBoxDisplayed() {
        Assertions.assertTrue(confirmationPage.isCloseCardDialogBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Диалоговое окно"));
        TestListener.takeScreenshot();
    }

    @Step("Отображается форма ввода кода подтверждения из смс")
    public void assertEnterCodeFromSmsBoxDisplayed() {
        Assertions.assertTrue(confirmationPage.isEnterCodeFromSmsBoxDisplayed(), String.format(NOT_DISPLAYED_MESSAGE, "Форма ввода кода из смс"));
    }

    @Step("Ввести код подтверждения из смс")
    public void setConfirmationCode(String value) {
        confirmationPage.setValueInConfirmationCodeField(value);
    }

    @Step("Подтвердить закрытие")
    public void confirm() {
        confirmationPage.clickConfirmButton();
    }
}
