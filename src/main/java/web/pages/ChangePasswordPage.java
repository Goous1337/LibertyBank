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
    @FindBy(xpath = "//input[@name='oldPassword']")
    private WebElement oldPasswordInput;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement newPasswordInput;
    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordInput;
    @FindBy(xpath = "//p[contains(text(), 'Успех')]")
    private WebElement successResult;

    public ChangePasswordPage setKeysToOldPasswordInput(String oldPassword) {
        oldPasswordInput.sendKeys(oldPassword);
        return this;
    }

    public ChangePasswordPage setKeysToNewPasswordInput(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        return this;
    }

    public ChangePasswordPage setKeysToConfirmPasswordInput(String confirmPassword) {
        confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }

    public void clickSubmitPasswordChangeBtn() {
        submitPasswordChangeBtn.click();
    }

    public boolean isSuccessResult() {
        return waitElement(successResult).isDisplayed();
    }

    public void clickCancelPasswordChangeBtn() {
        waitElement(cancelPasswordChangeBtn).click();
    }

    public boolean isChangePasswordPresent() {
        return changePasswordPage.isDisplayed();
    }
}
