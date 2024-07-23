package web.pages.insurancePages.accident;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

public class AccidentInsuranceTypesPage extends BasePage {
    @FindBy(xpath = "//button[@data-testid = 'back_button']")
    private WebElement backButton;
    @FindBy(xpath = "//*[@src = '/assets/road-cone-5b8290fd.png']/../../descendant::p")
    private WebElement widgetAccidentText;
    @FindBy(xpath = "//*[@src = '/assets/road-cone-5b8290fd.png']/../../descendant::button")
    private WebElement widgetAccidentButton;
}
