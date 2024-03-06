package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateEmailPage extends BasePage {
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    public UpdateEmailPage sendKeysToEmailInput(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public void clickToSubmitBtn() {
        submitBtn.click();
    }
}
