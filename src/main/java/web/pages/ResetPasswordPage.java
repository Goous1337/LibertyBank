package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class ResetPasswordPage {
    @FindBy(xpath = "//input[@name = 'phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//h1[text()='Введите код из смс']")
    private WebElement forgotHeading;

    @FindBy(xpath = "//input[@data-index='0']")
    private WebElement firstNumbSMSCodeInput;

    @FindBy(xpath = "//input[@data-index='1']")
    private WebElement secondNumbSMSCodeInput;

    @FindBy(xpath = "//input[@data-index='2']")
    private WebElement thirdNumbSMSCodeInput;

    @FindBy(xpath = "//input[@data-index='3']")
    private WebElement fourthNumbSMSCodeInput;

    @FindBy(xpath = "//input[@data-index='4']")
    private WebElement fifthNumbSMSCodeInput;

    @FindBy(xpath = "//input[@data-index='5']")
    private WebElement sixthNumbSMSCodeInput;

    public void enterPhone(String phoneNumber) {
        waitElement(phoneInput);
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickSubmitButton() {
        waitElement(submitButton).click();
    }

    public boolean isSecurityBarDisplayed() {
        return forgotHeading.isDisplayed();
    }

}
