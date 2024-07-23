package web.pages.insurancePages.dms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

public class DmsTypesPage extends BasePage {
    @FindBy(xpath = "//button[@data-testid = 'back_button']")
    private WebElement backButton;
    @FindBy(xpath = "//*[contains(text(),'Standart')]")
    private WebElement widgetDMSStandartText;
    @FindBy(xpath = "//*[contains(text(),'Standart')]/following-sibling::button")
    private WebElement widgetDMSStandartButton;
    @FindBy(xpath = "//*[contains(text(),'Standart+')]")
    private WebElement widgetDMSStandartPlusText;
    @FindBy(xpath = "//*[contains(text(),'Standart+')]/following-sibling::button")
    private WebElement widgetDMSStandartPlusButton;
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
        return getTextElement(waitElement(widgetDMSStandartText));
    }

    public void pressWidgetDmsStandartButton() {
        waitElement(widgetDMSStandartButton).click();
    }

    public String getWidgetDmsStandartPlusText() {
        return getTextElement(waitElement(widgetDMSStandartPlusText));
    }

    public void pressWidgetDmsStandartPlusButton() {
        waitElement(widgetDMSStandartPlusButton).click();
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
