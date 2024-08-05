package web.pages.insurancePages.dms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

public class DmsTypesPage extends BasePage {
    @FindBy(xpath = "//button[@data-testid = 'back_button']")
    private WebElement backButton;
    @FindBy(xpath = "//*[contains(text(),'Standart')]")
    private WebElement widgetDMSStandardText;
    @FindBy(xpath = "//*[contains(text(),'Standart')]/following-sibling::button")
    private WebElement widgetDMSStandardButton;
    @FindBy(xpath = "//*[contains(text(),'Standart+')]")
    private WebElement widgetDMSStandardPlusText;
    @FindBy(xpath = "//*[contains(text(),'Standart+')]/following-sibling::button")
    private WebElement widgetDMSStandardPlusButton;
    @FindBy(xpath = "//*[contains(text(),'Premium')]")
    private WebElement widgetDMSPremiumText;
    @FindBy(xpath = "//*[contains(text(),'Premium')]/following-sibling::button")
    private WebElement widgetDMSPremiumButton;
    @FindBy(xpath = "//*[contains(text(),'VIP')]")
    private WebElement widgetDMSVIPText;
    @FindBy(xpath = "//*[contains(text(),'VIP')]/following-sibling::button")
    private WebElement widgetDMSVIPButton;

    public void pressBackButton() {
        waitElement(backButton).click();
    }

    public String getWidgetDmsStandartText() {
        return getTextElement(waitElement(widgetDMSStandardText));
    }

    public void pressWidgetDmsStandartButton() {
        waitElement(widgetDMSStandardButton).click();
    }

    public String getWidgetDmsStandartPlusText() {
        return getTextElement(waitElement(widgetDMSStandardPlusText));
    }

    public void pressWidgetDmsStandartPlusButton() {
        waitElement(widgetDMSStandardPlusButton).click();
    }

    public String getWidgetDmsPremiumText() {
        return getTextElement(waitElement(widgetDMSPremiumText));
    }

    public void pressWidgetDmsPremiumButton() {
        waitElement(widgetDMSPremiumButton).click();
    }

    public String getWidgetDmsVipText() {
        return getTextElement(waitElement(widgetDMSVIPText));
    }

    public void pressWidgetDmsVipButton() {
        waitElement(widgetDMSVIPButton).click();
    }
}
