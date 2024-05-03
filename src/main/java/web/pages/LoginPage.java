package web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static web.helpers.Waiters.waitElement;
import static web.helpers.Waiters.waitElementWithColor;

public class LoginPage extends BasePage {

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

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

    @FindBy(xpath = "//p[contains(text(), 'Номер телефона должен содержать 11 цифр')]")
    private WebElement errorPhoneHint;

    @FindBy(xpath = "//p[contains(text(), 'Пароль должен содержать от 6 до 20 символов')]")
    private WebElement errorPasswordHint;

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

    public String checkErrorPhoneHint() {
        return errorPhoneHint.getText();
    }

    public String checkErrorPasswordHint() {
        return errorPasswordHint.getText();
    }

    public String checkErrorPhoneAndPasswordHint() {
        return errorHint.getText();
    }

    public String getTextFromPhoneInput() {
        return phoneInput.getAttribute("value");
    }

    public String getTextFromPasswordInput() {
        return passwordInput.getAttribute("value");
    }
}
