package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void enterPhone(String phoneNumber) {
        waitElement(phoneInput);
        phoneInput.sendKeys(phoneNumber);
    }

    public void enterPassword(String password) {
        waitElement(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void submit() {
        waitElement(submitButton);
        submitButton.click();
        waitElement(mainView);
    }

    public boolean isActiveSubmitButton(String color) {
        waitElementWithColor(submitButton, color);
        return submitButton.isEnabled() && submitButton.getCssValue("background-color").
                equals(color) && submitButton.getCssValue("color").equals("rgba(245, 245, 245, 1)");
    }

    public boolean isSuccessInput(String color) {
        return borderForInput.getCssValue("border-color").
                equals(color);
    }
}
