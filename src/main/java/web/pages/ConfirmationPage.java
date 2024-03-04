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

    @FindBy(xpath = "//p[contains(text(), 'Вы действительно хотите заблокировать карту?')]")
    private WebElement blockCardDialogBox;

    @FindBy(xpath = "//p[contains(text(), 'Вы действительно хотите закрыть карту?')]")
    private WebElement closeCardDialogBox;

    @FindBy(xpath = "//p[contains(text(), 'Введите код из смс')]")
    private WebElement enterCodeFromSmsBox;

    @FindBy(xpath = "//div[contains(@class, '_enterPhone')]")
    private WebElement confirmationCodeField;

    @FindBy(xpath = "//button[contains(text(), 'Подтвердить')]")
    private WebElement confirmButton;

    @FindBy(xpath = "//button[contains(text(), 'Да')]")
    private WebElement acceptButton;

    @FindBy(xpath = "//button[contains(text(), 'Нет')]")
    private WebElement denyButton;

    @FindBy(xpath = "//p[contains(text(), 'На данный момент у Вас отсутствуют карточные продукты')]")
    private WebElement missingCardProductsMessage;

    @FindBy(xpath = "//button[contains(text(), 'Открыть карточный продукт')]")
    private WebElement openCardProductButton;

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

    public boolean isBlockCardDialogBoxDisplayed() {
        return blockCardDialogBox.isDisplayed();
    }

    public boolean isCloseCardDialogBoxDisplayed() {
        return closeCardDialogBox.isDisplayed();
    }

    public boolean isEnterCodeFromSmsBoxDisplayed() {
        return enterCodeFromSmsBox.isDisplayed();
    }

    public void setValueInConfirmationCodeField(String value) {
        confirmationCodeField.sendKeys(value);
    }

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public boolean isMissingCardProductsMessageDisplayed() {
        return missingCardProductsMessage.isDisplayed();
    }

    public boolean isOpenCardProductButtonEnabled() {
        return openCardProductButton.isEnabled();
    }
}
