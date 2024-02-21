package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class HomePage extends BasePage {
    @FindBy(xpath = "//button[contains(@class,'_avatar')]")
    private WebElement userMenuBtn;
    @FindBy(xpath = "//h1[contains(text(),'Войдите в Liberty Bank')]")
    private WebElement unauthorizedHomePage;

    public void clickUserMenu() {
        Waiters.waitElement(userMenuBtn).click();
    }

    public boolean isUnauthorizedHomeDisplayed() {
        return unauthorizedHomePage.isDisplayed();
    }

}
