package web.pages.insurancePages.dms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

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

}
