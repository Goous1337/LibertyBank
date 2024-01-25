package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Вы действительно хотите сделать счет основным?')]")
    private WebElement setMainAccountDialogBox;

    @FindBy(xpath = "//*[contains(text(), 'Ваш счет успешно стал основным')]")
    private WebElement successSetMainAccountDialogBox;

    @FindBy(xpath = "//button[contains(text(), 'Вернуться к счету')]")
    private WebElement returnToAccountButton;

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

    public void clickReturnToAccountButton() {
        returnToAccountButton.click();
    }

    public void clickAcceptButton() {
        acceptButton.click();
    }

    public void clickDenyButton() {
        denyButton.click();
    }
}
