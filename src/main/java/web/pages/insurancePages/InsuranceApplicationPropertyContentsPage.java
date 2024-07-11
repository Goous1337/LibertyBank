package web.pages.insurancePages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.constans.insurance.InsuranceEnum.ContentsApplicationField;
import web.drivers.DriverManager;
import web.pages.BasePage;

import java.util.List;

import static web.helpers.Waiters.waitElement;

public class InsuranceApplicationPropertyContentsPage extends BasePage {
    @FindBy(xpath = "//*[contains(text(),'Далее')]")
    private WebElement nextButton;
    @FindBy(xpath = "//*[contains(text(),'Оформить заявку')]")
    private WebElement confirmButton;
    @FindBy(xpath = "//*[@id='RUB']")
    private WebElement radioCurrencyRub;
    @FindBy(xpath = "//*[@id='USD']")
    private WebElement radioCurrencyUsd;
    @FindBy(xpath = "//*[@id='EUR']")
    private WebElement radioCurrencyEur;
    @FindBy(xpath = "//*[@class = '_icon_1a54x_1']")
    private WebElement durationInputBoarder;
    @FindBy(xpath = "//*[@class='_selectBlock_1a54x_5']//li[1]")
    private WebElement durationFirstVariant;
    @FindBy(xpath = "//input[@id='duration']")
    private WebElement durationInput;
    @FindBy(xpath = "//*[@data-testid ='icon-calendar']")
    private WebElement startingDateCalendarIcon;
    @FindBy(xpath = "//h3[contains(@class, ' _containerTitle_1pww5_25')]")
    private WebElement pageTitle;
    private List<WebElement> cityNames;
    @FindBy(xpath = "//*[contains(@name, 'city')]/../../following::ul")
    private WebElement cityNameList;
    @FindBy(xpath = "//*[contains(@name, 'things.0.name')]/../..")
    private WebElement thingNameBorder;
    @FindBy(xpath = "//*[contains(@name, 'things.0.name')]")
    private WebElement thingNameInput;
    @FindBy(xpath = "//*[contains(@name, 'things.0.cost')]/../..")
    private WebElement thingCostBorder;
    @FindBy(xpath = "//*[contains(@name, 'things.0.cost')]")
    private WebElement thingCostInput;
    @FindBy(xpath = "//*[@class='_selectBlock_1a54x_5']//*[@data-testid = 'icon-arrow-down-grey']")
    private WebElement thingTypeIcon;
    private List<WebElement> thingTypeOptions;
    @FindBy(xpath = "//*[@class='_selectBlock_1a54x_5']//*[@data-testid = 'icon-arrow-down-grey'][1]")
    private WebElement constructionTypeBorder;
    private List<WebElement> constructionTypes;
    @FindBy(xpath = "//*[contains(@name, 'city')]/../following::*")
    private WebElement cityBorder;
    @FindBy(xpath = "//*[contains(@name, 'city')]")
    private WebElement cityInput;
    @FindBy(xpath = "//*[contains(@name, 'street')]/../..")
    private WebElement streetBorder;
    @FindBy(xpath = "//*[contains(@name, 'street')]")
    private WebElement streetInput;
    @FindBy(xpath = "//*[contains(@name, 'building')]/../..")
    private WebElement houseBorder;
    @FindBy(xpath = "//*[contains(@name, 'building')]")
    private WebElement houseInput;
    @FindBy(xpath = "//*[contains(@name, 'apartment')]/../..")
    private WebElement apartmentBorder;
    @FindBy(xpath = "//*[contains(@name, 'apartment')]")
    private WebElement apartmentInput;
    @FindBy(xpath = "//*[contains(@name, 'floor')]/../..")
    private WebElement floorBorder;
    @FindBy(xpath = "//*[contains(@name, 'floor')]")
    private WebElement floorInput;
    @FindBy(xpath = "//*[contains(@name, 'entrance')]/../..")
    private WebElement entranceBorder;
    @FindBy(xpath = "//*[contains(@name, 'entrance')]")
    private WebElement entranceInput;
    @FindBy(xpath = "//*[@id='Да']")
    private WebElement isSecuritySystemYes;
    @FindBy(xpath = "//*[id='Нет']")
    private WebElement isSecuritySystemNo;
    @FindBy(xpath = "//button[contains(@class,'_back-btn_c17ux_167')]")
    private WebElement backButton;
    @FindBy(xpath = "//*[@class='react-calendar _content__total_calendar_1r0ah_1 _calendar_1r0ah_121']")
    private WebElement calendarForm;
    @FindBy(xpath = "//*[@class='_fail-buttons_1gvr6_26']")
    private WebElement failImage;
    @FindBy(xpath = "//img[@src = '/assets/send-application-success-a7c06c8a.png']")
    private WebElement successImage;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__next-button']")
    private WebElement nextMonth;
    @FindBy(xpath = "//*[contains(@class,'react-calendar__tile--now')]/following-sibling::button[1]")
    private WebElement tomorrowButton;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__label']")
    private WebElement navigationLabel;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__prev2-button']")
    private WebElement previousYear;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__next2-button']")
    private WebElement nextYear;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__prev-button']")
    private WebElement previousMonth;
    private List<WebElement> dayButton;

    public boolean successImageIsPresent() {
        return waitElement(successImage).isDisplayed();
    }

    public boolean failImageIsPresent() {
        return waitElement(failImage).isDisplayed();
    }

    public boolean confirmButtonIsEnabled() {
        return waitElement(confirmButton).isEnabled();
    }

    public void pressConfirmButton() {
        waitElement(confirmButton).click();
    }

    public boolean nextButtonIsEnabled() {
        return waitElement(nextButton).isEnabled();
    }

    public void pressNextButton() {
        waitElement(nextButton).click();
    }

    public void clickRadioRub() {
        waitElement(radioCurrencyRub).click();
    }

    public boolean isSelectedRub() {
        return waitElement(radioCurrencyRub).isSelected();
    }

    public void clickRadioEur() {
        waitElement(radioCurrencyEur).click();
    }

    public boolean isSelectedEur() {
        return waitElement(radioCurrencyEur).isSelected();
    }

    public void clickRadioUsd() {
        waitElement(radioCurrencyUsd).click();
    }

    public boolean isSelectedUsd() {
        return waitElement(radioCurrencyUsd).isSelected();
    }

    public void selectStartingTomorrow() {
        waitElement(startingDateCalendarIcon).click();
        waitElement(calendarForm);
        try {
            waitElement(tomorrowButton).click();
        } catch (NoSuchElementException e) {
            waitElement(nextMonth).click();
            dayButton = DriverManager.getDriver().findElements(By
                    .xpath("//*[@class='react-calendar__month-view__days']/button"));
            dayButton.get(0).click();
        }

    }

    public String getTitleText() {
        return pageTitle.getText();
    }

    public void selectThingType(Integer type) {
        waitElement(thingTypeIcon).click();
        thingTypeOptions = DriverManager.getDriver().findElements(By
                .xpath("//*[@class='_selectBlock_1a54x_5']//li"));
        thingTypeOptions.get(type).click();
    }

    public void selectConstructionType(Integer type) {
        waitElement(constructionTypeBorder).click();
        constructionTypes = DriverManager.getDriver().findElements(By
                .xpath("//*[@class = '_selectBlock_1a54x_5']/ul/li"));
        constructionTypes.get(type).click();
    }

    public void inputCity(Integer city) {
        waitElement(cityBorder).click();
        waitElement(cityNameList).isDisplayed();
        cityNames = DriverManager.getDriver().findElements(By
                .xpath("//*[contains(@name, 'city')]/../../following::ul/li"));
        cityNames.get(city).click();
    }

    public void inputField(ContentsApplicationField field, String data) {
        switch (field) {
            case INSURANCE_DURATION:
                waitElement(durationInputBoarder).click();
                waitElement(durationInput).sendKeys(data);
                waitElement(durationFirstVariant).click();
                break;
            case THING_NAME:
                waitElement(thingNameBorder).click();
                waitElement(thingNameInput).sendKeys(data);
                break;
            case THING_COST:
                waitElement(thingCostBorder).click();
                waitElement(thingCostInput).sendKeys(data);
                break;
            case STREET:
                waitElement(streetBorder).click();
                waitElement(streetInput).sendKeys(data);
                break;
            case BUILDING:
                waitElement(houseBorder).click();
                waitElement(houseInput).sendKeys(data);
                break;
            case APARTMENT:
                waitElement(apartmentBorder).click();
                waitElement(apartmentInput).sendKeys(data);
                break;
            case FLOOR:
                waitElement(floorBorder).click();
                waitElement(floorInput).sendKeys(data);
                break;
            case ENTRANCE:
                waitElement(entranceBorder).click();
                waitElement(entranceInput).sendKeys(data);
                break;
        }
    }

    public void pressBackButton() {
        waitElement(backButton).click();
    }
}
