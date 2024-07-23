package web.pages.insurancePages.property;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

public class PropertyInsuranceTypesPage extends BasePage {
    @FindBy(xpath = "//button[@data-testid = 'back_button']")
    private WebElement backButton;
    @FindBy(xpath = "//*[@src = '/assets/house-0b418cc2.png']/../../descendant::p")
    private WebElement widgetApartmentText;
    @FindBy(xpath = "//*[@src = '/assets/house-0b418cc2.png']/../../descendant::button")
    private WebElement widgetApartmentButton;
    @FindBy(xpath = "//*[@src = '/assets/washer-bddf171f.png']/../../descendant::p")
    private WebElement widgetContentsText;
    @FindBy(xpath = "//*[@src = '/assets/washer-bddf171f.png']/../../descendant::p")
    private WebElement widgetContentsButton;
    @FindBy(xpath = "//*[@src = '/assets/condo-29ee2909.png']/../../descendant::p")
    private WebElement widgetHouseText;
    @FindBy(xpath = "//*[@src = '/assets/condo-29ee2909.png']/../../descendant::p")
    private WebElement widgetHouseButton;

    public void pressBackButton() {
        waitElement(backButton).click();
    }

    public String getWidgetAppartmentText() {
        return getTextElement(waitElement(widgetApartmentText));
    }

    public void pressWidgetAccidentButton() {
        waitElement(widgetApartmentButton).click();
    }

    public String getWidgetHouseText() {
        return getTextElement(waitElement(widgetHouseText));
    }

    public void pressWidgetHouseButton() {
        waitElement(widgetHouseButton).click();
    }

    public String getWidgetContentsText() {
        return getTextElement(waitElement(widgetContentsText));
    }

    public void pressWidgetContentsButton() {
        waitElement(widgetContentsButton).click();
    }
}
