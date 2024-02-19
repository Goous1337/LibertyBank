package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class SecurityPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'Изменить')]")
    private WebElement changePasswordBtn;
    @FindBy(xpath = "//p[contains(text(),'Безопасность')]")
    private WebElement securityBar;

    public void clickChangePasswordBtn() {
        Waiters.waitElement(changePasswordBtn).click();
    }

    public boolean isSecurityBarDisplayed() {
        return securityBar.isDisplayed();
    }
}
