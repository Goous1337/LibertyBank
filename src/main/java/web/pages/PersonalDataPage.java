package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class PersonalDataPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'Изменить')]")
    private WebElement changeEmailBtn;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    public void clickChangeEmailBtn() {
        waitElement(changeEmailBtn).click();
    }

    public String getEmailFromInput() {
        return waitElement(emailInput).getAttribute("value");
    }
}
