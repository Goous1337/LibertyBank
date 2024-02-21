package web.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.helpers.Waiters;

import java.time.Duration;

import static web.drivers.DriverManager.getDriver;

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

    public static WebElement waitElementWithOwnTime(WebElement element, int time) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(time))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void checkVisibilityOfUserPanelButtons(int time) {
        waitElementWithOwnTime(contactBankBtn, time);
        waitElementWithOwnTime(securityBtn, time);
        waitElementWithOwnTime(servicePackagesBtn, time);
        waitElementWithOwnTime(legalInfoBtn, time);
        waitElementWithOwnTime(personalDateBtn, time);
        waitElementWithOwnTime(notificationBtn, time);
        waitElementWithOwnTime(securityBtn, time);
        waitElementWithOwnTime(exitFromAccountBtn, time);
        waitElementWithOwnTime(userName, time);
    }
}
