package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class HomePage extends BasePage {
    @FindBy(xpath = "//button[contains(@class,'_avatar')]")
    private WebElement userMenuBtn;
    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement exitFromAccountBtn;
    @FindBy(xpath = "//div[contains(@class,'user__general')]")
    private WebElement userPanel;
    @FindBy(xpath = "//h1[contains(text(),'Войдите в Liberty Bank')]")
    private WebElement unauthorizedHomePage;

    public void clickUserMenu() {
        Waiters.waitElement(userMenuBtn).click();
    }

    public void exitFromUserAccount() {
        Waiters.waitElement(exitFromAccountBtn).click();
    }

    public boolean isUserPanelDisplayed() {
        return userPanel.isDisplayed();
    }

    public boolean isUnauthorizedHomeDisplayed() {
        return unauthorizedHomePage.isDisplayed();
    }
}
