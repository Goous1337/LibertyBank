package web.pages.insurancePages.travel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

public class TravelInsuranceTypesPage extends BasePage {
    @FindBy(xpath = "//button[@data-testid = 'back_button']")
    private WebElement backButton;
    @FindBy(xpath = "//*[@src = '/assets/earth-4e3824bd.png']/../../descendant::p")
    private WebElement widgetTravelText;
    @FindBy(xpath = "//*[@src = '/assets/earth-4e3824bd.png']/../../descendant::button")
    private WebElement widgetTravelButton;

    public void pressBackButton() {
        waitElement(backButton).click();
    }

    public String getWidgetTravelText() {
        return getTextElement(waitElement(widgetTravelText));
    }

    public void pressWidgetTravelButton() {
        waitElement(widgetTravelButton).click();
    }
}
