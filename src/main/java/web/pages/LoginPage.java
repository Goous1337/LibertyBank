package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static web.helpers.Waiters.*;

public class LoginPage extends BasePage {

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(), 'Главная')]")
    private WebElement mainView;

    @FindBy(xpath = "//input[@name = 'phone']/..")
    private WebElement borderForPhoneInput;

    @FindBy(xpath = "//input[@name = 'password']/..")
    private WebElement borderForPasswordInput;

    @FindBy(xpath = "//p[contains(text(), 'Неверный пароль или номер телефона')]")
    private WebElement errorHint;

    @FindBy(xpath = "//p[contains(text(), 'Номер телефона должен содержать 11 цифр')]")
    private WebElement errorPhoneHint;

    @FindBy(xpath = "//label[text()='Номер телефона']")
    private WebElement placeholderPhone;

    public void enterPhone(String phoneNumber) {
        waitElement(phoneInput);
        phoneInput.sendKeys(phoneNumber);
    }

    public void enterPassword(String password) {
        waitElement(passwordInput);
        passwordInput.sendKeys(password);
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

    public String getColorPlaceholderPhone() {
        return placeholderPhone.getCssValue("color");
    }

    public boolean isValidInput(String color) {
        return borderForPasswordInput.getCssValue("border-color").
                equals(color);
    }

    public boolean checkErrorPhoneHint(String text) {
        return Objects.equals(errorPhoneHint.getText(), text);
    }

    public boolean checkErrorHint(String text) {
        return Objects.equals(errorHint.getText(), text);
    }

    public String getTextFromPhoneInput() {
        return phoneInput.getAttribute("value");
    }
}
