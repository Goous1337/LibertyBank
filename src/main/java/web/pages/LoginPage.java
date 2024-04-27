package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static web.helpers.Waiters.*;

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

    public boolean isValidInput(String color) {
        System.out.println(borderForPasswordInput.getCssValue("border-color"));
        return borderForPasswordInput.getCssValue("border-color").
                equals(color);
    }

    public boolean checkErrorHint(String text) {
        return Objects.equals(errorHint.getText(), text);
    }
}
