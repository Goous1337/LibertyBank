package web.pages.insurancePages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

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
    private WebElement carouselNextButton;
    @FindBy(xpath = "//button[contains(@class,'prevButton')]")
    private WebElement carouselPrevButton;
    @FindBy(xpath = "//img[@src = '/assets/mercedes-benz-3629f470.png']/../../descendant::p")
    private WebElement kaskoContainerText;
    @FindBy(xpath = "//img[@src = '/assets/mercedes-benz-3629f470.png']/../../descendant::button")
    private WebElement kaskoContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/road-cone-5b8290fd.png']/../../descendant::p")
    private WebElement accidentContainerText;
    @FindBy(xpath = "//img[@src ='/assets/road-cone-5b8290fd.png']/../../descendant::button")
    private WebElement accidentContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/condo-29ee2909.png']/../../descendant::p")
    private WebElement apartmentContainerText;
    @FindBy(xpath = "//img[@src ='/assets/condo-29ee2909.png']/../../descendant::button")
    private WebElement apartmentContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/heart-9a2306d8.png']/../../descendant::p")
    private WebElement standardDMSContainerText;
    @FindBy(xpath = "//img[@src ='/assets/heart-9a2306d8.png']/../../descendant::button")
    private WebElement standardDMSContainerButton;
    @FindBy(xpath = "//img[@src ='/assets/earth-4e3824bd.png']/../../descendant::p")
    private WebElement travelContainerText;
    @FindBy(xpath = "//img[@src ='/assets/earth-4e3824bd.png']/../../descendant::button")
    private WebElement travelContainerButton;

    public boolean dmsIconPresent() {
        return waitElement(dmsIcon).isDisplayed();
    }

    public boolean carIconPresent() {
        return waitElement(carIcon).isDisplayed();
    }

    public boolean travelIconPresent() {
        return waitElement(travelIcon).isDisplayed();
    }

    public boolean accidentIconPresent() {
        return waitElement(accidentIcon).isDisplayed();
    }

    public boolean propertyIconPresent() {
        return waitElement(propertyIcon).isDisplayed();
    }

    public String getDmsCategoryText() {
        return getTextElement(waitElement(dmsText));
    }

    public String getCarCategoryText() {
        return getTextElement(waitElement(carText));
    }

    public String getAccidentCategoryText() {
        return getTextElement(waitElement(accidentText));
    }

    public String getTravelCategoryText() {
        return getTextElement(waitElement(travelText));
    }

    public String getPropertyCategoryText() {
        return getTextElement(waitElement(propertyText));
    }

    public void selectDmsCategory() {
        waitElement(dmsIcon).click();
    }

    public void selectCarsCategory() {
        waitElement(carIcon).click();
    }

    public void selectTravelCategory() {
        waitElement(travelIcon).click();
    }

    public void selectAccidentCategory() {
        waitElement(accidentIcon).click();
    }

    public void selectPropertyCategory() {
        waitElement(propertyIcon).click();
    }

    public boolean carouselNextButtonPresent() {
        return waitElement(carouselNextButton).isDisplayed();
    }

    public boolean carouselPrevButtonPresent() {
        return waitElement(carouselPrevButton).isDisplayed();
    }

    public void pressCarouselNextButton() {
        waitElement(carouselNextButton).click();
    }

    public void pressCarouselPrevButton() {
        waitElement(carouselPrevButton);
    }

    public String getKaskoContainerText() {
        return getTextElement(waitElement(kaskoContainerText));
    }

    public String getDmsContainerText() {
        return getTextElement(waitElement(standardDMSContainerText));
    }

    public String getTravelContainerText() {
        return getTextElement(waitElement(travelContainerText));
    }

    public String getApartmentContainerText() {
        return getTextElement(waitElement(apartmentContainerText));
    }

    public String getAccidentContainerText() {
        return getTextElement(waitElement(accidentContainerText));
    }

    public void pressKaskoContainerButton() {
        waitElement(kaskoContainerButton).click();
    }

    public void pressDmsContainerButton() {
        waitElement(standardDMSContainerButton).click();
    }

    public void pressTravelContainerButton() {
        waitElement(travelContainerButton).click();
    }

    public void pressApartmentContainerButton() {
        waitElement(apartmentContainerButton).click();
    }

    public void pressAccidentContainerButton() {
        waitElement(accidentContainerButton).click();
    }

}
