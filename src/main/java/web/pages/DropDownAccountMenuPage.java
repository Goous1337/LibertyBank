package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class DropDownAccountMenuPage extends BasePage {
    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement exitFromAccountBtn;
    @FindBy(xpath = "//a[@href='/customer/security']")
    private WebElement securityBtn;
    @FindBy(xpath = "//a[contains(@href,'notification')]")
    private WebElement notificationBtn;
    @FindBy(xpath = "//a[contains(@href,'personal-date')]")
    private WebElement personalDateBtn;
    @FindBy(xpath = "//a[contains(@href,'legal-information')]")
    private WebElement legalInfoBtn;
    @FindBy(xpath = "//a[contains(@href,'service-packages')]")
    private WebElement servicePackagesBtn;
    @FindBy(xpath = "//a[contains(text(),'Услуги')]")
    private WebElement serviceBtn;
    @FindBy(xpath = "//a[contains(@href,'contact-with-the-bank')]")
    private WebElement contactBankBtn;
    @FindBy(xpath = "//p[contains(@class,'text_regular_xv9cv_24 _text_s')]")
    private WebElement userName;
    @FindBy(xpath = "//div[contains(@class,'user__general')]")
    private WebElement userPanel;

    public void exitFromUserAccount() {
        Waiters.waitElement(exitFromAccountBtn).click();

    }

    public void clickSecurityBtn() {
        Waiters.waitElement(securityBtn).click();
    }

    public boolean isUserPanelDisplayed() {
        return userPanel.isDisplayed();
    }

    public void checkVisibilityOfUserPanelButtons() {
        int wait = 5;
        Waiters.waitElementWithOwnTime(contactBankBtn, wait);
        Waiters.waitElementWithOwnTime(securityBtn, wait);
        Waiters.waitElementWithOwnTime(servicePackagesBtn, wait);
        Waiters.waitElementWithOwnTime(legalInfoBtn, wait);
        Waiters.waitElementWithOwnTime(personalDateBtn, wait);
        Waiters.waitElementWithOwnTime(notificationBtn, wait);
        Waiters.waitElementWithOwnTime(securityBtn, wait);
        Waiters.waitElementWithOwnTime(exitFromAccountBtn, wait);
        Waiters.waitElementWithOwnTime(userName, wait);
    }
}
