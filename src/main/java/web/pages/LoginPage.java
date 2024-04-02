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

    @FindBy(xpath = "//input[@name = 'password']/..")
    private WebElement borderForInput;

    @FindBy(xpath = "//p[contains(text(), 'Неверный пароль или номер телефона')]")
    private WebElement errorHint;

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

    public boolean verifyButtonColors(String bgColor, String textColor) {
        waitElementWithColor(submitButton, bgColor);
        System.out.println(submitButton.getCssValue("background-color"));
        System.out.println(submitButton.getCssValue("color"));
        return submitButton.getCssValue("background-color").
                equals(bgColor) && submitButton.getCssValue("color").equals(textColor);
    }

    public boolean verifyInputColors(String color) {
        System.out.println(borderForInput.getCssValue("border-color"));
        return borderForInput.getCssValue("border-color").
                equals(color);
    }

    public boolean checkErrorHint(String text) {
        return Objects.equals(errorHint.getText(), text);
    }
}
