package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class HomePage extends BasePage {
    @FindBy(xpath = "//button[contains(@class,'_avatar')]")
    private WebElement userMenuBtn;
    @FindBy(xpath = "//h1[contains(text(),'Войдите в Liberty Bank')]")
    private WebElement unauthorizedHomePage;

    public void clickUserMenu() {
        waitElement(userMenuBtn).click();
    }

    public boolean isUnauthorizedHomeDisplayed() {
        return unauthorizedHomePage.isDisplayed();
    }

}
