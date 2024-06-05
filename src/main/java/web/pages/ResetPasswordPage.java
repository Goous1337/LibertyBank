package web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class ResetPasswordPage extends BasePage {
    @FindBy(xpath = "//input[@name = 'phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name = 'confirmPassword']")
    private WebElement passwordConfirmInput;

    @FindBy(xpath = "//input[@data-testid = 'inputBase']")
    private WebElement verificationCodeInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name = 'phone']/../..")
    private WebElement borderForPhoneInput;

    @FindBy(xpath = "//input[@name = 'password']/../..")
    private WebElement borderForPasswordInput;

    @FindBy(xpath = "//a[text() = 'Забыли пароль?']")
    private WebElement forgotPassword;

    @FindBy(xpath = "//*[contains(text(), 'Главная')]")
    private WebElement mainView;

    @FindBy(xpath = "//h1[text()='Введите код из смс']")
    private WebElement forgotHeading;

    @FindBy(xpath = "//input[@data-index='0']")
    private WebElement firstNumbSMSCodeInput;

    public void enterPhone(String phoneNumber) {
        waitElement(borderForPhoneInput).click();
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickSubmitButton() {
        waitElement(submitButton).click();
    }

    public boolean isSecurityBarDisplayed() {
        return forgotHeading.isDisplayed();
    }

    public void clickForgotPassword() {
        waitElement(forgotPassword).click();
    }

    public void enterVerificationCode(String verificationCode) {
        waitElement(verificationCodeInput).click();
        verificationCodeInput.sendKeys(verificationCode);
    }

    public void enterNewPassword(String password) {
        waitElement(borderForPasswordInput).click();
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.TAB);
        passwordConfirmInput.sendKeys(password);

    }
}
