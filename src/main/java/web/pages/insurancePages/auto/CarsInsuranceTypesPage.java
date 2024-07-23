package web.pages.insurancePages.auto;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

public class CarsInsuranceTypesPage extends BasePage {
    @FindBy(xpath = "//button[@data-testid = 'back_button']")
    private WebElement backButton;
    @FindBy(xpath = "//*[contains(text(),'ОСАГО')]")
    private WebElement widgetOsagoText;
    @FindBy(xpath = "//*[contains(text(),'ОСАГО')]/following-sibling::button")
    private WebElement widgetOsagoButton;
    @FindBy(xpath = "//*[contains(text(),'КАСКО')]")
    private WebElement widgetKaskoText;
    @FindBy(xpath = "//*[contains(text(),'КАСКО')]/following-sibling::button")
    private WebElement widgetKaskoButton;
}
