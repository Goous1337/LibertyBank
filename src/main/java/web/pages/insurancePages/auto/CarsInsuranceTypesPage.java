package web.pages.insurancePages.auto;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

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

    public void pressBackButton() {
        waitElement(backButton).click();
    }

    public String getWidgetOsagoText() {
        return getTextElement(waitElement(widgetOsagoText));
    }

    public void pressWidgetOsagoButton() {
        waitElement(widgetOsagoButton).click();
    }

    public String getWidgetKaskoText() {
        return getTextElement(waitElement(widgetKaskoText));
    }

    public void pressWidgetKaskoButton() {
        waitElement(widgetKaskoButton).click();
    }
}
