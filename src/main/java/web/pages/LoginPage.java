package web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

import java.util.Objects;

import static web.helpers.Waiters.waitElement;
import static web.helpers.Waiters.waitElementWithColor;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@name='phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//*[@name='phone']/../..")
    private WebElement phoneInputClick;

    @FindBy(xpath = "//*[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@name='password']/../..")
    private WebElement passwordInputClick;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(), 'Главная')]")
    private WebElement mainView;

    @FindBy(xpath = "//input[@name = 'password']/../..")
    private WebElement borderForPasswordInput;

    @FindBy(xpath = "//input[@name = 'phone']/../..")
    private WebElement borderForPhoneInput;

    @FindBy(xpath = "//p[contains(text(), 'Неверный пароль или номер телефона')]")
    private WebElement errorHint;

    @FindBy(xpath = "//div[contains(@class, 'password-container')]/descendant::p")
    private WebElement errorPasswordHint;

    @FindBy(xpath = "//div[contains(@class, 'phone-container')]/descendant::p")
    private WebElement errorPhoneHint;

    @FindBy(xpath = "//label[text()='Номер телефона']")
    private WebElement placeholderPhone;

    @FindBy(xpath = "//label[text()='Пароль']")
    private WebElement placeholderPassword;

    public void enterPhone(String phoneNumber) {
        waitElement(borderForPhoneInput);
        borderForPhoneInput.click();
        phoneInput.sendKeys(phoneNumber);
    }

    public void enterPassword(String password) {
        waitElement(borderForPasswordInput);
        borderForPasswordInput.click();
        passwordInput.sendKeys(password);
    }

    public void clickInputPhone() {
        waitElement(phoneInputClick);
        phoneInputClick.click();
    }

    public void clickInputPassword() {
        waitElement(passwordInputClick);
        passwordInputClick.click();
    }

    public void outFormPhone() {
        phoneInput.sendKeys(Keys.TAB);
    }

    public void outFormPassword() {
        passwordInput.sendKeys(Keys.TAB);
    }

    public void submitToMainPage() {
        clickSubmitButton();
        waitElement(mainView);
    }

    public void clickSubmitButton() {
        waitElement(submitButton).click();
    }

    public boolean checkButtonCondition(String bgColor, String textColor, boolean isEnabled) {
        waitElementWithColor(submitButton, bgColor);
        return submitButton.isEnabled() == isEnabled
                && submitButton.getCssValue("background-color").equals(bgColor)
                && submitButton.getCssValue("color").equals(textColor);
    }

    public String getPhoneInputBorderColor() {
        return borderForPhoneInput.getCssValue("border-color");
    }

    public String getPasswordInputBorderColor() {

        return borderForPasswordInput.getCssValue("border-color");
    }

    public String getColorPlaceholderPhone() {
        return placeholderPhone.getCssValue("color");
    }

    public String getColorPlaceholderPassword() {
        return placeholderPassword.getCssValue("color");
    }

    public boolean isValidInput(String color) {
        System.out.println(borderForPasswordInput.getCssValue("border-color"));
        return borderForPasswordInput.getCssValue("border-color").
                equals(color);
    }

    public boolean checkErrorHint(String text) {
        return Objects.equals(errorHint.getText(), text);
    }

    public String getErrorPasswordHintText() {
        return errorPasswordHint.getText();
    }

    public String getErrorPhoneHintText() {
        return errorPhoneHint.getText();
    }

    public boolean isErrorPasswordHintNotDisplayed() {
        return Waiters.isElementNotDisplayed(errorPasswordHint);
    }

    public boolean isErrorPhoneHintNotDisplayed() {
        return Waiters.isElementNotDisplayed(errorPhoneHint);
    }

    public String getTextFromPhoneInput() {
        return phoneInput.getAttribute("value");
    }

    public String getTextFromPasswordInput() {
        return passwordInput.getAttribute("value");
    }
}
