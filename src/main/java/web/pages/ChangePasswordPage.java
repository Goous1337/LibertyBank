package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class ChangePasswordPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'Отмена')]")
    private WebElement cancelPasswordChangeBtn;
    @FindBy(xpath = "//button[contains(text(), 'Отправить')]")
    private WebElement submitPasswordChangeBtn;
    @FindBy(xpath = "//h3[contains(text(),'Изменить пароль')]")
    private WebElement changePasswordPage;
    @FindBy (xpath = "//input[@name='oldPassword']/..")
    private WebElement oldPasswordInputClick;
    @FindBy(xpath = "//input[@name='oldPassword']")
    private WebElement oldPasswordInput;
    @FindBy(xpath = "//input[@name='password']/..")
    private WebElement newPasswordInputClick;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement newPasswordInput;
    @FindBy(xpath = "//input[@name='confirmPassword']/..")
    private WebElement confirmPasswordInputClick;
    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordInput;

    public void setKeysToOldPasswordInput(String oldPassword) {
        oldPasswordInputClick.click();
        oldPasswordInput.sendKeys(oldPassword);
    }

    public void setKeysToNewPasswordInput(String newPassword) {
        newPasswordInputClick.click();
        newPasswordInput.sendKeys(newPassword);
    }

    public void setKeysToConfirmPasswordInput(String confirmPassword) {
        confirmPasswordInputClick.click();
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void clickSubmitPasswordChangeBtn() {
        submitPasswordChangeBtn.click();
    }

    public void clickCancelPasswordChangeBtn() {
        waitElement(cancelPasswordChangeBtn).click();
    }

    public boolean isChangePasswordPresent() {
        return changePasswordPage.isDisplayed();
    }
}
