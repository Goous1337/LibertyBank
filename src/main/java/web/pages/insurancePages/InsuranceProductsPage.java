package web.pages.insurancePages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

public class InsuranceProductsPage extends BasePage {
    @FindBy(xpath = "//*[@data-testid = 'icon-med']")
    private WebElement dmsIcon;
    @FindBy(xpath = "//*[@data-testid = 'icon-med']/following-sibling::p")
    private WebElement dmsText;
    @FindBy(xpath = "//*[@data-testid = 'icon-car']")
    private WebElement carIcon;
    @FindBy(xpath = "//*[@data-testid = 'icon-car']/following-sibling::p")
    private WebElement carText;
    @FindBy(xpath = "//*[@data-testid = 'icon-travel']")
    private WebElement travelIcon;
    @FindBy(xpath = "//*[@data-testid = 'icon-travel']/following-sibling::p")
    private WebElement travelText;
    @FindBy(xpath = "//*[@data-testid = 'icon-sad']")
    private WebElement accidentIcon;
    @FindBy(xpath = "//*[@data-testid = 'icon-sad']/following-sibling::p")
    private WebElement accidentText;
    @FindBy(xpath = "//*[@data-testid = 'icon-home-circle']")
    private WebElement propertyIcon;
    @FindBy(xpath = "//*[@data-testid = 'icon-home-circle']/following-sibling::p")
    private WebElement propertyText;
    @FindBy(xpath = "//button[contains(@class,'nextButton')]")
    private WebElement caruselNextButton;
    @FindBy(xpath = "//button[contains(@class,'prevButton')]")
    private WebElement caruselPrevButton;
    @FindBy(xpath = "//img[@src = '/assets/mercedes-benz-3629f470.png']/../../descendant::p")
    private WebElement osagoContainerText;
    @FindBy(xpath = "//img[@src = '/assets/mercedes-benz-3629f470.png']/../../descendant::button")
    private WebElement osagoContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/road-cone-5b8290fd.png']/../../descendant::p")
    private WebElement accidentContainerText;
    @FindBy(xpath = "//img[@src ='/assets/road-cone-5b8290fd.png']/../../descendant::button")
    private WebElement accidentContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/condo-29ee2909.png']/../../descendant::p")
    private WebElement apartmentContainerText;
    @FindBy(xpath = "//img[@src ='/assets/condo-29ee2909.png']/../../descendant::button")
    private WebElement apartmentContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/heart-9a2306d8.png']/../../descendant::p")
    private WebElement standartDMSContainerText;
    @FindBy(xpath = "//img[@src ='/assets/heart-9a2306d8.png']/../../descendant::button")
    private WebElement standartDMSContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/earth-4e3824bd.png']/../../descendant::p")
    private WebElement travelContainerText;
    @FindBy(xpath = "//img[@src ='/assets/earth-4e3824bd.png']/../../descendant::button")
    private WebElement travelContainerButton;

}
