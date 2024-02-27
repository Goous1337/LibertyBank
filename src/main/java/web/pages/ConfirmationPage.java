package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class ConfirmationPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Вы действительно хотите сделать счет основным?')]")
    private WebElement setMainAccountDialogBox;

    @FindBy(xpath = "//p[contains(text(), 'Вы действительно хотите закрыть счет?')]")
    private WebElement closeVerificationMessage;

    @FindBy(xpath = "//*[contains(text(), 'Ваш счет успешно стал основным')]")
    private WebElement successSetMainAccountDialogBox;

    @FindBy(xpath = "//*[contains(text(), 'Название счета успешно изменено')]")
    private WebElement successRenameAccountNameDialogBox;

    @FindBy(xpath = "//button[contains(text(), 'Вернуться к счету')]")
    private WebElement returnToAccountButton;

    @FindBy(xpath = "//*[contains(text(), 'Ваш счет успешно закрыт')]")
    private WebElement closeSuccessfullyMessage;

    @FindBy(xpath = "//button[contains(text(), 'Вернуться к счетам')]")
    private WebElement navigateToAccountsPageButton;

    @FindBy(xpath = "//*[contains(text(), 'Вы действительно хотите заблокировать счет?')]")
    private WebElement blockAccountDialogBox;

    @FindBy(xpath = "//button[contains(text(), 'Да')]")
    private WebElement acceptButton;

    @FindBy(xpath = "//button[contains(text(), 'Нет')]")
    private WebElement denyButton;

    public boolean isSetMainAccountDialogBoxDisplayed() {
        return setMainAccountDialogBox.isDisplayed();
    }

    public boolean isBlockAccountDialogBoxDisplayed() {
        return blockAccountDialogBox.isDisplayed();
    }

    public boolean isSuccessSetMainAccountDialogBoxDisplayed() {
        return successSetMainAccountDialogBox.isDisplayed();
    }

    public boolean isSuccessRenameAccountNameDialogBoxDisplayed() {
        return successRenameAccountNameDialogBox.isDisplayed();
    }

    public void clickReturnToAccountButton() {
        returnToAccountButton.click();
    }

    public void clickAcceptButton() {
        acceptButton.click();
    }

    public void clickDenyButton() {
        denyButton.click();
    }

    public boolean isCloseVerificationMessageDisplayed() {
        Waiters.waitElement(closeVerificationMessage);
        return closeVerificationMessage.isDisplayed();
    }

    public boolean isCloseSuccessfullyMessageDisplayed() {
        Waiters.waitElement(closeSuccessfullyMessage);
        return closeVerificationMessage.isDisplayed();
    }

    public void clickNavigateToAccountsPageButton() {
        navigateToAccountsPageButton.click();
    }
}
