package web.pages.insurancePages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.enums.InsuranceEnum.ApartmentApplicationField;
import web.helpers.CalendarElement;
import web.pages.BasePage;

import java.util.List;

import static web.helpers.Waiters.waitElement;

public class InsuranceApplicationApartmentPage extends BasePage {
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
    @FindBy(xpath = "//*[@name = 'duration']/../..")
    private WebElement durationInputBoarder;
    @FindBy(xpath = "//input[@name='duration']")
    private WebElement durationInput;
    @FindBy(xpath = "//*[@name ='startDate']/../following-sibling::*")
    private WebElement startingDateCalendarIcon;
    @FindBy(xpath = "//*[@name='partsPayment']/following::label")
    private WebElement togglePaymentParts;
    @FindBy(xpath = "//h3[contains(@class, ' _containerTitle_1pww5_25')]")
    private WebElement pageTitle;
    @FindBy(xpath = "//*[@name ='partsPayment']/following-sibling::label")
    private WebElement partsPaymentSwitch;
    @FindBy(xpath = "//*[contains(@name, 'lastName')]/../..")
    private WebElement lastNameBorder;
    @FindBy(xpath = "//*[contains(@name, 'lastName')]")
    private WebElement lastNameInput;
    @FindBy(xpath = "//*[contains(@name, 'firstName')]/../..")
    private WebElement firstNameBorder;
    @FindBy(xpath = "//*[contains(@name, 'firstName')]")
    private WebElement firstNameInput;
    @FindBy(xpath = "//*[contains(@name, 'patronymic')]/../..")
    private WebElement patronymicBorder;
    @FindBy(xpath = "//*[contains(@name, 'patronymic')]")
    private WebElement patronymicInput;
    @FindBy(xpath = "//*[@class='_selectBlock_1a54x_5']//*[@data-testid = 'icon-arrow-down-grey']")
    private WebElement documentTypeBorder;
    private List<WebElement> documentTypes;
    @FindBy(xpath = "//*[contains(@name, 'documentType')]/following::ul")
    private WebElement documentTypeList;
    @FindBy(xpath = "//*[contains(@name, 'passportNumber')]/../..")
    private WebElement passportBorder;
    @FindBy(xpath = "//*[contains(@name, 'passportNumber')]")
    private WebElement passportInput;
    @FindBy(xpath = "//*[contains(@name, 'refugeeCertificateNumber')]/../..")
    private WebElement refugeBorder;
    @FindBy(xpath = "//*[contains(@name, 'refugeeCertificateNumber')]")
    private WebElement refugeInput;
    @FindBy(xpath = "//*[contains(@name, 'residentCardNumber')]/../..")
    private WebElement residenceBorder;
    @FindBy(xpath = "//*[contains(@name, 'residentCardNumber')]")
    private WebElement residenceInput;
    @FindBy(xpath = "//label[contains(text(),'Дата выдачи')]/../following-sibling::*")
    private WebElement documentGotCalendarIcon;
    @FindBy(xpath = "//*[@name='dateOfBirth']/../following-sibling::*")
    private WebElement dateOfBirthIcon;
    @FindBy(xpath = "//*[contains(@name, 'issuedBy')]/../..")
    private WebElement issuedByBorder;
    @FindBy(xpath = "//*[contains(@name, 'issuedBy')]")
    private WebElement issuedByInput;
    @FindBy(xpath = "//*[contains(@name, 'phoneNumber')]/../..")
    private WebElement phoneNumberBorder;
    @FindBy(xpath = "//*[contains(@name, 'phoneNumber')]")
    private WebElement phoneNumberInput;
    @FindBy(xpath = "//*[contains(@name, 'email')]/../..")
    private WebElement emailBorder;
    @FindBy(xpath = "//*[contains(@name, 'email')]")
    private WebElement emailInput;
    @FindBy(xpath = "//*[contains(@name, 'region')]/../..")
    private WebElement stateBorder;
    @FindBy(xpath = "//*[contains(@name, 'city')]/../..")
    private WebElement cityBorder;
    @FindBy(xpath = "//*[contains(@name, 'street')]/../..")
    private WebElement streetBorder;
    @FindBy(xpath = "//*[contains(@name, 'house')]/../..")
    private WebElement buildingBorder;
    @FindBy(xpath = "//*[contains(@name, 'house')]")
    private WebElement buildingInput;
    @FindBy(xpath = "//*[contains(@name, 'entrance')]/../..")
    private WebElement entranceBorder;
    @FindBy(xpath = "//*[contains(@name, 'entrance')]")
    private WebElement entranceInput;
    @FindBy(xpath = "//*[contains(@name, 'apartament')]/../..")
    private WebElement apartmentBorder;
    @FindBy(xpath = "//*[contains(@name, 'apartament')]")
    private WebElement apartmentInput;
    @FindBy(xpath = "//*[contains(@name, 'yearOfConstruction')]/../..")
    private WebElement yearOfConstructionBorder;
    @FindBy(xpath = "//*[contains(@name, 'yearOfConstruction')]")
    private WebElement yearOfConstructionInput;
    @FindBy(xpath = "//*[contains(@name, 'buildingArea')]/../..")
    private WebElement buildingAreaBorder;
    @FindBy(xpath = "//*[contains(@name, 'buildingArea')]")
    private WebElement buildingAreaInput;
    @FindBy(xpath = "//*[contains(@name, 'actualCost')]/../..")
    private WebElement actualCostBorder;
    @FindBy(xpath = "//*[contains(@name, 'actualCost')]")
    private WebElement actualCostInput;
    @FindBy(xpath = "//*[contains(@name, 'insuranceAmount')]/../..")
    private WebElement insuranceAmountBorder;
    @FindBy(xpath = "//*[contains(@name, 'insuranceAmount')]")
    private WebElement insuranceAmountInput;
    @FindBy(xpath = "//*[@name = 'regionOfBuilding']/../..")
    private WebElement regionOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'regionOfBuilding']")
    private WebElement regionOfBuildingInput;
    @FindBy(xpath = "//*[@name = 'cityOfBuilding']/../..")
    private WebElement cityOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'cityOfBuilding']")
    private WebElement cityOfBuildingInput;
    @FindBy(xpath = "//*[@name = 'streetOfBuilding']/../..")
    private WebElement streetOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'streetOfBuilding']")
    private WebElement streetOfBuildingInput;
    @FindBy(xpath = "//*[@name = 'houseOfBuilding']/../..")
    private WebElement houseOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'houseOfBuilding']")
    private WebElement houseOfBuildingInput;
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

    public void pressPayPartsToggle() {
        togglePaymentParts.click();
    }

    public void selectDocumentType(Integer type) {
        waitElement(documentTypeBorder).click();
        waitElement(documentTypeList).isDisplayed();
        documentTypes = DriverManager.getDriver().findElements(By
                .xpath("//*[contains(@name, 'documentType')]/following::ul/li"));
        documentTypes.get(type).click();
    }

    public void inputDocumentGotDate(Integer year, Integer month, Integer day) {
        waitElement(documentGotCalendarIcon).click();
        waitElement(calendarForm);
        CalendarElement calendarElement = new CalendarElement();
        calendarElement.switchMonth(navigationLabel, nextMonth, previousMonth, month);
        calendarElement.switchYear(navigationLabel, nextYear, previousYear, year);
        dayButton = DriverManager.getDriver().findElements(By
                .xpath("//*[@class='react-calendar__month-view__days']/button"));
        dayButton.get(day).click();
    }

    public void inputDateOfBirth(Integer year, Integer month, Integer day) {
        waitElement(dateOfBirthIcon).click();
        waitElement(calendarForm);
        CalendarElement calendarElement = new CalendarElement();
        calendarElement.switchMonth(navigationLabel, nextMonth, previousMonth, month);
        calendarElement.switchYear(navigationLabel, nextYear, previousYear, year);
        dayButton = DriverManager.getDriver().findElements(By
                .xpath("//*[@class='react-calendar__month-view__days']/button"));
        dayButton.get(day).click();
    }

    public void scrollDown() {
        scrollToElement(nextButton);
    }

    public void pressBackButton() {
        waitElement(backButton).click();
    }

    public void inputField(ApartmentApplicationField field, String data) {
        switch (field) {
            case INSURANCE_DURATION:
                inputIntoBasicField(durationInputBoarder, durationInput, data);
                break;
            case LAST_NAME:
                inputIntoBasicField(lastNameBorder, lastNameInput, data);
                break;
            case FIRST_NAME:
                inputIntoBasicField(firstNameBorder, firstNameInput, data);
                break;
            case PATRONYMIC:
                inputIntoBasicField(patronymicBorder, patronymicInput, data);
                break;
            case PASSPORT_NUMBER:
                inputIntoBasicField(passportBorder, passportInput, data);
                break;
            case RESIDENCE_NUMBER:
                inputIntoBasicField(residenceBorder, residenceInput, data);
                break;
            case REFUGE_NUMBER:
                inputIntoBasicField(refugeBorder, refugeInput, data);
                break;
            case ISSUED_BY:
                inputIntoBasicField(issuedByBorder, issuedByInput, data);
                break;
            case PHONE:
                inputIntoBasicField(phoneNumberBorder, phoneNumberInput, data);
                break;
            case EMAIL:
                inputIntoBasicField(emailBorder, emailInput, data);
                break;
            case REGION:
                inputIntoSuggestionField(stateBorder, data);
                break;
            case CITY:
                inputIntoSuggestionField(cityBorder, data);
                break;
            case STREET:
                inputIntoSuggestionField(streetBorder, data);
                break;
            case BUILDING:
                inputIntoBasicField(buildingBorder, buildingInput, data);
                break;
            case APARTMENT:
                inputIntoBasicField(apartmentBorder, apartmentInput, data);
                break;
            case ENTRANCE:
                inputIntoBasicField(entranceBorder, entranceInput, data);
                break;
            case CONSTRUCTION_YEAR:
                inputIntoBasicField(yearOfConstructionBorder, yearOfConstructionInput, data);
                break;
            case ACTUAL_COST:
                inputIntoBasicField(actualCostBorder, actualCostInput, data);
                break;
            case INSURANCE_AMOUNT:
                inputIntoBasicField(insuranceAmountBorder, insuranceAmountInput, data);
                break;
            case BUILDING_SPACE:
                inputIntoBasicField(buildingAreaBorder, buildingAreaInput, data);
                break;
            case BUILDING_REGION:
                inputIntoBasicField(regionOfBuildingBorder, regionOfBuildingInput, data);
                break;
            case BUILDING_CITY:
                inputIntoBasicField(cityOfBuildingBorder, cityOfBuildingInput, data);
                break;
            case BUILDING_STREET:
                inputIntoBasicField(streetOfBuildingBorder, streetOfBuildingInput, data);
                break;
            case BUILDING_HOUSE:
                inputIntoBasicField(houseOfBuildingBorder, houseOfBuildingInput, data);
                break;
        }
    }
}
